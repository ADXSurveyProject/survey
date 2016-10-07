var xmlhttp = new XMLHttpRequest();

var tablestyle = "table table-striped table-bordered";
var yesno;
function printWithOpts(data, opt) {	
	yesno = opt;
	print(data);
}
function print(data) {	
	// data=data._source;
	var ele = document.getElementById("content");

	ele.innerHTML = "";
	var table = document.createElement("table");
	table.className = tablestyle;
	table = toTableNew(data, null, null);
	ele.appendChild(table);
}
function toTableNew(data, parent, selfkey) {
	if (typeof data === 'object') { // Array or Map
		if (Object.prototype.toString.call(data) === '[object Array]') { // Array

			var table = createTable();

			if (data != null && data.length > 0) {

				if (Object.prototype.toString.call(data[0]) === '[object Array]') { // Array
					handleArrayObjects(table, data, parent, selfkey);
				} else if (typeof data[0] === 'object') { // map

					var cr = createHeaderRow(table);
					var cd1 = createHeaderCell(cr);

					var a = document.createElement("span");
					if (selfkey == null) {
						a.innerHTML = '(+) ' + ' ';	
					} else {
						a.innerHTML = '(+) ' + selfkey + ' ';
					}
					cd1.appendChild(a);
					var span = document.createElement("span");
					span.className = "badge";
					span.innerHTML = '(' + data.length + ')';
					cd1.appendChild(span);

					cd1.setAttribute("style", "vertical-align: top;");

					var cd2 = createDataCell(cr);
					var div = document.createElement("div");

					var ctable2 = createTable();
					var tblid = tableid;
					ctable2.className = ctable2.className + " collapse";
					ctable2.setAttribute("data-target", "#" + tableid);

					a.setAttribute('onclick', "toggle(this," + "'" + tableid
							+ "','" + data.length + "')");

					div.appendChild(ctable2);
					div.setAttribute("id", "div_" + tblid);
					cd2.appendChild(div);

					cd2.setAttribute("id", "ctd_" + tblid);
					ctdMap["div_" + tblid] = div;

					handleArrayMaps(ctable2, data, parent, selfkey);

				} else { // data
					handleArrayData(table, data, parent, selfkey);
				}

			}

			return table;
		} else { // Map

			var table = createTable();

			handleMapObject(table, data, parent, selfkey);

			return table;
		}
	} else { // data
		console.log("data");
		var table = createTable();
		if (yesno == "YESNO") {
			
			
			var tr = createDataRow(table);
			var td = createDataCell(tr);
			
			var p = document.createElement("p");
			p.className="mbr-questiontext__p";
			p.innerHTML=data;
			td.appendChild(p);
			
			tr = createDataRow(table);
			td = createDataCell(tr);
			var yes = document.createElement('button');
			yes.innerHTML='Yes';
			yes.className="btn btn-success";
			yes.setAttribute("onclick","onYesClick()");
			td.className="yesno-btn-align";
			td.appendChild(yes);
			
			var span = document.createElement('span');
			span.innerHTML='     ';
			td.appendChild(span);
			
			var no = document.createElement('button');
			no.innerHTML='No';
			no.className="btn btn-info";
			no.setAttribute("onclick","onNoClick()");
			td.appendChild(no);
			
		} else {
			
			var tr = createDataRow(table);
			var td = createDataCell(tr);
			
			var span = document.createElement("p");
			span.className="mbr-questiontext__p";
			span.innerHTML=data;
			td.appendChild(span);	
			
		}
		return table;

	}
}
function handleArrayData(table, data, parent, selfkey) {
	for ( var row in data) {
		var tr = createDataRow(table);
		var td = createDataCell(tr);
		var value = toTableNew(data[row], data, row);
		if (typeof value == 'object') {
			td.appendChild(value);
		} else {
		 
			td.innerHTML = value;
			
		}		
	}
}
var orderedHeadersMap = new Object();
function handleMapObject(table, data, parent, selfkey) {

	var tr = null;
	for ( var key in data) {
		if (key.endsWith("_Ordered")) {
			orderedHeadersMap[key] = data[key];
		}
	}

	for ( var key in data) {
		if (key.endsWith("_Ordered")) {
			continue;
		}
		tr = createDataRow(table);
		var td = createHeaderCell(tr);
		td.innerHTML = key;

		var td = createDataCell(tr);
		var value = toTableNew(data[key], data, key);
		if (typeof value == 'object') {
			td.appendChild(value);
		} else {
			td.innerHTML = value;
		}

	}

}
function handleArrayMaps(table, data, parent, selfkey) {
	var orderedHeader = null;

	if (orderedHeadersMap[selfkey + "_Ordered"] != null) {
		orderedHeader = orderedHeadersMap[selfkey + "_Ordered"];
	}

	if (orderedHeader != null) {
		var tr = createHeaderRow(table);
		for ( var key in orderedHeader) {			
			var td = createHeaderCell(tr);
			td.innerHTML = orderedHeader[key];
		}

		for ( var row in data) {
			var tr = createDataRow(table);
			for ( var key in orderedHeader) {				
				var td = createDataCell(tr);
				var value = toTableNew(data[row][orderedHeader[key]],
						data[row], orderedHeader[key]);
				if (typeof value == 'object') {
					td.appendChild(value);
				} else {
					td.innerHTML = value;
				}
			}
		}
	} else {
		var tr = createHeaderRow(table);
		for ( var key in data[0]) {
			var td = createHeaderCell(tr);
			td.innerHTML = key;
		}

		for ( var row in data) {
			var tr = createDataRow(table);

			for ( var key in data[row]) {
				var td = createDataCell(tr);
				var value = toTableNew(data[row][key], data[row], key);
				if (typeof value == 'object') {
					td.appendChild(value);
				} else {
					td.innerHTML = value;
				}
			}
		}
	}
}
function handleArrayObjects(table, data, parent, selfkey) {
	for ( var row in data) {
		var tr = createDataRow(table);
		for ( var key in data[row]) {
			var td = createDataCell(tr);
			var value = toTableNew(data[row][key], data[row], key);
			if (typeof value == 'object') {
				td.appendChild(value);
			} else {
				td.innerHTML = value;				
			}
		}
	}
}
function createTable() {

	tableid++;

	var table = document.createElement("table");
	table.className = tablestyle;
	table.setAttribute("id", tableid);

	var thead = document.createElement("thead");
	table.appendChild(thead);

	var tbody = document.createElement("tbody");
	table.appendChild(tbody);

	return table;
}
function createDataRow(table) {
	var tr = document.createElement("tr");

	var tbody = table.getElementsByTagName("tbody")[0];
	tbody.appendChild(tr);

	return tr;
}
function createHeaderRow(table) {
	var tr = document.createElement("tr");

	var thead = table.getElementsByTagName("thead")[0];
	thead.appendChild(tr);

	return tr;
}
function createHeaderCell(tr) {
	var td = document.createElement("td");
	tr.appendChild(td);
	return td;
}
function createDataCell(tr) {
	var td = document.createElement("td");
	tr.appendChild(td);
	return td;
}

var tableid = 0;
var ctdMap = new Object();
function toggle(span, eleName, datalen) {
	var ele = document.getElementById(eleName);
	var ctd = document.getElementById("ctd_" + eleName);
	var div = document.getElementById("div_" + eleName);

	if (span.innerHTML.indexOf('+') != -1) {
		span.innerHTML = span.innerHTML.replace('+', '-');

		var divCache = ctdMap["div_" + eleName];

		ctd.appendChild(divCache);

		ele = document.getElementById(eleName);
		$(ele.getAttribute('data-target')).collapse('toggle');

		if (datalen > 2) {
			$('#' + eleName).DataTable();
		}

	} else {
		span.innerHTML = span.innerHTML.replace('-', '+');
		$(ele.getAttribute('data-target')).collapse('toggle');

		ctd.removeChild(div);
	}
}
