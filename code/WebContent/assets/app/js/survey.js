

var YES=1;
var NO=0;
var SUCCESS=1;
var ERROR=-1;
var LOCAL_STORE=false;
var CURRENT_QUESTION=1;
var TOTAL_QUESTIONS=116;
var pid=null;
var latlon=null;
//local store keys
var QUESTION="QUESTION";
var VISITED="VISITED";
var LATLON="LATLON";
var PID="PID";
var FEEDBACK_COMPLETED="FEEDBACK_COMPLETED";
function doAction(action) {
	console.log('performing action : ' + action);
	if (pid == null) {
		getParticipantId();
		if (pid != null) {
			var url = 'rs?action=getParticipantId&pid='+pid;
			fetch(url, setParticipantId);
		}
	}
	if (latlon == null) {
		getLocation();
	}
	action();
}
function saveLocation(key, val) {
	var url='rs?action=saveLocation&pid='+pid+'&latlon='+latlon;
	fetch(url, null);
}
function nextFromHome() {
	var url="survey.html";
	fetch(url, showSurvey);
}
function showSurvey(resp) {
	document.getElementById("bodycontent").innerHTML = resp;
}

function doProgress() {
	var cnt = CURRENT_QUESTION;
	var ele = document.getElementById("progdiv");
	
	var perc = (cnt*100)/TOTAL_QUESTIONS;
	ele.setAttribute("aria-valuenow",perc);
	ele.setAttribute("aria-valuemax",TOTAL_QUESTIONS);
	
	ele.setAttribute("style","width:" + perc + "%");
	console.log('Progress, perc : ' + perc + ', tot : ' + TOTAL_QUESTIONS);
}
function initFeedback() {
	LOCAL_STORE = checkIfLocalStoreAvailable();
	var fbcompleted = read(FEEDBACK_COMPLETED);
	if (fbcompleted == YES) {
		location.href='thankyou.html';
	}

	
	var pid = read(PID);
	if (pid == null) {
		location.href='/';
	} else {
		document.frmFeedback.pid.value=pid;
		console.log("Setting pid to feedback form : " + pid);
	}
}
function init() {

	LOCAL_STORE = checkIfLocalStoreAvailable();
	var visited = checkIfTheParticipantIsRevisiting();
	getParticipantId();
	getTotalQuestions();
	latlon = read(LATLON);	
	if (visited == YES) {
		CURRENT_QUESTION = read(QUESTION);
	} else {	
		save(QUESTION, CURRENT_QUESTION, null);	
		save(VISITED,YES, null);
	}
		
}
function setParticipantId(resptext) {
	var resp = JSON.parse(resptext);
	pid = parseInt(resp);
	console.log('setParticipantId : ' + pid);
	if (isNaN(pid)) {
		pid = 1;
	}
	save(PID, pid, null);
	CURRENT_QUESTION = read(QUESTION);
}
function saveProgress(key, val) {
	saveOnServer('saveProgress', key, val);
}
function startTheSurvey() {
	var ele = document.getElementById("startsurvey");
	ele.setAttribute("id","content");
	showNextQuestion();
}
function showNextQuestion() {
	var url="rs?action=getQuestion&questionId="+CURRENT_QUESTION+'&pid='+pid;
	fetch(url, showQuestion);	
}
function resetSurvey() {
	localStorage.clear();
	location.href='/';
	
}
function showQuestion(resptext) {
	var resp = JSON.parse(resptext);
	log(resp);
	if (resp != 'Survey completed') {
    	var options = "YESNO";
    	CURRENT_QUESTION = parseInt(resp[0]);
    	printWithOpts(resp[1], options);
	} else {
		location.href='presummary.html';
	}
}
function showSummary() {
	location.href='summary.html';
}
function doOnFeedback() {
	location.href='feedback.jsp';
}
function onYesClick() {
	printWithOpts('Loading the next question, please wait..', null);
	save(CURRENT_QUESTION, YES, onSaveResponse);	
	CURRENT_QUESTION++;
	save(QUESTION, CURRENT_QUESTION);
	showNextQuestion();
	doProgress();
}
function onNoClick() {
	printWithOpts('Loading the next question, please wait..', null);
	save(CURRENT_QUESTION, NO, onSaveResponse);
	CURRENT_QUESTION++;
	save(QUESTION, CURRENT_QUESTION);
	showNextQuestion();
	doProgress();
}
function onSaveResponse(key, val) {
	saveOnServer('saveResponse', key, val);
}
function doOnSurveyComplete() {
	var url = 'rs?action=onSurveyComplete&pid='+pid;
	fetch(url, onSurveyComplete);
}
function onSurveyComplete(resptext) {
	var resp = JSON.parse(resptext);
	
	if (resp['pie'].length > 0) {
		drawChart(resp['pie']);
	}
	
	var kdi = document.getElementById('KeyDecisionIssues');
	kdi.innerHTML=resp['KeyDecisionIssues'];
	
	var sdf = document.getElementById('SpecialDecisionFactors');		
	sdf.innerHTML=resp['SpecialDecisionFactors'];
	
	var decisions = document.getElementById('decisions');
	var decision_factors = resp['decision_factors'];
	for (var eachdf  in decision_factors) {
		var mapDecisionFactor = decision_factors[eachdf];
		var dfname = mapDecisionFactor['each_df'];
		var dec_pre = mapDecisionFactor[dfname+'_pre'];
		var dec_issues = mapDecisionFactor[dfname];
		var dec_post = mapDecisionFactor[dfname+'_post'];
		var decision_div = displayEachDecisionFactor(dfname, dec_pre, dec_issues, dec_post);		
		decisions.appendChild(decision_div);		
	}
	
	
	var feedbackBtn = document.createElement('div');
	feedbackBtn.setAttribute('class','mbr-buttons mbr-buttons--center');

    var a = document.createElement('a');
    a.setAttribute('class','mbr-buttons__btn btn btn-lg btn-default');
    a.setAttribute("onclick",'doAction(doOnFeedback)');
    a.innerHTML='Feedback please..';
    
    feedbackBtn.appendChild(a);
    document.body.appendChild(document.createElement('br'));
    document.body.appendChild(document.createElement('br'));
    
	document.body.appendChild(feedbackBtn);
	
	var r = document.createElement('a');
	r.setAttribute('class','mbr-buttons__btn btn btn-lg btn-default');
    r.setAttribute("onclick",'doAction(resetSurvey)');
    r.innerHTML='Reset cache (temporary button)..';
    
    var resetBtn = document.createElement('div');
    resetBtn.setAttribute('class','mbr-buttons mbr-buttons--center');
    resetBtn.appendChild(r);
    document.body.appendChild(resetBtn);

	
}
function displayEachDecisionFactor(title, pretext, issues, posttext) {
	var div = document.createElement('div');
	div.setAttribute('class','mbr-reviews__text');
	//add title
	var strong = document.createElement('strong');
	var p1 = document.createElement('p');
	p1.setAttribute('class','mbr-reviews__p');
	p1.setAttribute('style','font-size:25px;text-align: left');
	p1.innerHTML='Decision Factor: ' + title;
	strong.appendChild(p1);
	div.appendChild(strong);
	
	//add pre text	
	var p = getSummaryDecisionParaTemplate();
	p.innerHTML=pretext;
	div.appendChild(p);
	
	//add decision issues
	for(var each in issues) {
		p = getSummaryDecisionParaTemplate();
		var i = document.createElement('i');
		i.innerHTML=issues[each];
		p.appendChild(i);
		div.appendChild(p);
	}
	
	//post text
	p = getSummaryDecisionParaTemplate();
	p.innerHTML=posttext;
	div.appendChild(p);
	
	return div;
}
function getSummaryDecisionParaTemplate() {
	var p = document.createElement('p');
	p.setAttribute('style', 'text-align: left');
	p.setAttribute('class', 'mbr-reviews__p');
	return p;
}
var callbacks = [];
function fetch(url, callback) {
	var xmlhttp = new XMLHttpRequest();
	console.log('fetching ' + url);
	callbacks[url] = callback;
	xmlhttp.onreadystatechange = function() {
		
	    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    	var tmpcallback = callbacks[url];
	    	console.log('onFetch : '+url+': ' + xmlhttp.responseText+', callback:' + tmpcallback);
	    	
	    	if (tmpcallback != null) {
	    		tmpcallback(xmlhttp.responseText);		    		
	    	}
	    }
	};
	xmlhttp.open("POST", url, true);	
	xmlhttp.send();	
	
}
 
function saveOnServer(action, key, val) {
	var url = 'rs?action='+action+'&pid='+pid+'&key='+key+'&val='+val;
	xmlhttp.onreadystatechange = function() {
	    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    	console.log('saveOnServer success : ' + action + ',' + xmlhttp.responseText);	    	
	    }
	};
	xmlhttp.open("POST", url, true);	
	xmlhttp.send();	
}
function checkIfTheParticipantIsRevisiting() {
	return read(VISITED);	
}

function checkIfLocalStoreAvailable() {
	if (typeof(Storage) !== "undefined") {	    
	    return SUCCESS;
	} else {
		log("storage not found");
	    return ERROR;
	}
}

function save(key, val, onSave) {
	if (LOCAL_STORE == SUCCESS) {
		log('saving ' + key +',' + val);
		localStorage.setItem(key, val);
		
		if (key == LATLON) {
			doAction(saveLocation);
		}
	}
	if (onSave != null) {
		onSave(key, val);
	}
}
function read(key) {
	if (LOCAL_STORE == SUCCESS) {
		
		var ret = localStorage.getItem(key);
		log('reading ' + key+', ' + ret);
		if (key == QUESTION) {
			var url = 'rs?action=getCurrentQuestion&pid='+pid;
			fetch(url, setCurrentQuestion);
		}

		if (ret == null) {
			if (key == LATLON) {
				getLocation();
			} 
		}
		return ret;
	}
}
function setCurrentQuestion(resptext) {
	
	CURRENT_QUESTION = parseInt(resptext);
	
	save(QUESTION, CURRENT_QUESTION);
}
function getLocation() {
	if (pid != null) {
	    if (navigator.geolocation) {
	        navigator.geolocation.getCurrentPosition(showPosition, showError);
	    } else {
	        log("Geolocation is not supported by this browser.");
	    }	
	}
}
function getParticipantId() {
	pid = read(PID);
	if (pid == 'NaN') {
		pid = null;
	}
	if (pid == null ) {
		var url = 'rs?action=getParticipantId';
		fetch(url, setParticipantId);
	} else {
		var url = 'rs?action=getParticipantId&pid='+pid;
		fetch(url, setParticipantId);
	}
}
function getTotalQuestions() {
	if (pid == null ) {
		var url = 'rs?action=getTotalQuestions';
		fetch(url, setTotalQuestions);
	}
}
function setTotalQuestions(resptext) {

	var resp = JSON.parse(resptext);
	
	TOTAL_QUESTIONS = parseInt(resp);
	console.log('Total questions : ' + TOTAL_QUESTIONS);
}
function showPosition(position) {
    latlon = position.coords.latitude + "," + position.coords.longitude;
    log(latlon);
    save(LATLON, latlon, null);	
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
        	log("User denied the request for Geolocation.");
            break;
        case error.POSITION_UNAVAILABLE:
        	log("Location information is unavailable.");
            break;
        case error.TIMEOUT:
        	log("The request to get user location timed out.");
            break;
        case error.UNKNOWN_ERROR:
        	log("An unknown error occurred.");
            break;
    }
}
var logdiv = document.getElementById('logs');
function log(msg) {
	console.log(msg);
	//logdiv = document.getElementById('logs');
	//logdiv.innerHTML+=msg;
	//logdiv.innerHTML+='<br>';
}

function drawChart(piedata) {
	console.log(piedata);
	//var div = document.createElement('div');
	//div.setAttribute('id','chartContainer');
	//div.setAttribute('style','height: 80px; width: 300px;');
	//document.getElementById('content').appendChild(div);
	var chart = new CanvasJS.Chart("chartContainer",
	{
		
		data: [
		{
			type: "pie",
			dataPoints: piedata
		}
		]
	});
	chart.render();
}
