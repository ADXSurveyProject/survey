$(document).ready(function(){
    $(".textareainput").hide(); 
    $(".textareainputq9").hide();
    $("#ImpoQuestion1").hide();
    $("#ImpoQuestion2").hide();
    $("#ImpoQuestion3").hide();
    $("#ImpoQuestion4").hide();
    $("#ImpoQuestion5").hide();
    $("#ImpoQuestion6").hide();
    $("#ImpoQuestion7").hide();
    $("#ImpoQuestion8").hide();
    $("#ImpoQuestion9").hide();
    
    $(".FeedbackPage_ct600_Options_0").click(function(){
        $(".textareainput").show();
    });
     $(".FeedbackPage_ct600_Options_1").click(function(){
        $(".textareainput").hide();
    });
    
       
    $(".FeedbackPage_ct900_Options_1").click(function(){
        $(".textareainputq9").show();
    });
     $(".FeedbackPage_ct900_Options_0").click(function(){
        $(".textareainputq9").hide();
    });

    $("#submitbutton").click(function() {
        // 1st Question
        if ((document.getElementById("FeedbackPage_ctl00_Options_0").checked) || (document.getElementById("FeedbackPage_ctl00_Options_1").checked)){
            $("#ImpoQuestion1").hide();
        }
        else {
            $("#ImpoQuestion1").show();
        }
        
        // 2nd Question
        if ((document.getElementById("q1rating5").checked) || (document.getElementById("q1rating4").checked) || (document.getElementById("q1rating3").checked) || (document.getElementById("q1rating2").checked) || (document.getElementById("q1rating1").checked)){
            $("#ImpoQuestion2").hide();
        }
        else
        {
            $("#ImpoQuestion2").show();
        }
        
        // 3rd Question
        if ((document.getElementById("FeedbackPage_ct300_Options_0").checked) || (document.getElementById("FeedbackPage_ct300_Options_1").checked)){
            $("#ImpoQuestion3").hide();
        }
        else {
            $("#ImpoQuestion3").show();
        }
        
        // 4th Question
        if ((document.getElementById("FeedbackPage_ct400_Options_0").checked) || (document.getElementById("FeedbackPage_ct400_Options_1").checked)){
            $("#ImpoQuestion4").hide();
        }
        else {
            $("#ImpoQuestion4").show();
        }
        
        // 5th Question
        if ((document.getElementById("FeedbackPage_ct500_Options_0").checked) || (document.getElementById("FeedbackPage_ct500_Options_1").checked)){
            $("#ImpoQuestion5").hide();
        }
        else {
            $("#ImpoQuestion5").show();
        }
        
        // 6th Question
        if ((document.getElementById("FeedbackPage_ct600_Options_0").checked) || (document.getElementById("FeedbackPage_ct600_Options_1").checked)){
            $("#ImpoQuestion6").hide();
        }
        else {
            $("#ImpoQuestion6").show();
        }
        
         // 7th Question
        if ((document.getElementById("q7rating5").checked) || (document.getElementById("q7rating4").checked) || (document.getElementById("q7rating3").checked) || (document.getElementById("q7rating2").checked) || (document.getElementById("q7rating1").checked)){
            $("#ImpoQuestion7").hide();
        }
        else if ((document.getElementById("q7rating5").checked == false) || (document.getElementById("q7rating4").checked ==false) || (document.getElementById("q7rating3").checked == false) || (document.getElementById("q7rating2").checked == false) || (document.getElementById("q7rating1").checked == false)){
            $("#ImpoQuestion7").show();
        }
        else {
             $("#ImpoQuestion7").hide();
        }
        
          // 8th Question
        if ((document.getElementById("FeedbackPage_ct800_Options_0").checked) || (document.getElementById("FeedbackPage_ct800_Options_1").checked)){
            $("#ImpoQuestion8").hide();
        }
        else {
            $("#ImpoQuestion8").show();
        }
        
          // 9th Question
        if ((document.getElementById("FeedbackPage_ct900_Options_0").checked) || (document.getElementById("FeedbackPage_ct900_Options_1").checked)){
            $("#ImpoQuestion9").hide();
        }
        else {
            $("#ImpoQuestion9").show();
        }
        
        //Question 6 Textarea
        var impo6t = $(".textareainput").is(':visible');
        var impo6tval = $(".textareainput").val();
        if (impo6t == true)
        {
            if(impo6tval == "")
            {
               $("#ImpoQuestion6").show();
            }
            else
            {
                $("#ImpoQuestion6").hide();
            }
        }
        
        //Question 9 Textarea
        var impo9t = $(".textareainputq9").is(':visible');
        var impo9tval = $(".textareainputq9").val();
        if (impo9t == true)
        {
            if(impo9tval == "")
            {
               $("#ImpoQuestion9").show();
            }
            else
            {
                $("#ImpoQuestion9").hide();
            }
        }
    });
    
    //1st Question
    $("#FeedbackPage_ctl00_Options_0").click(function() {
        $("#ImpoQuestion1").hide();
    });
      
     $("#FeedbackPage_ctl00_Options_1").click(function() {
        $("#ImpoQuestion1").hide();
    });
    
     //2nd Question
    $("#q1rating5").click(function() {
        $("#ImpoQuestion2").hide();
    });
      
    $("#q1rating4").click(function() {
        $("#ImpoQuestion2").hide();
    });
    $("#q1rating3").click(function() {
        $("#ImpoQuestion2").hide();
    });
    $("#q1rating2").click(function() {
        $("#ImpoQuestion2").hide();
    });
    $("#q1rating1").click(function() {
        $("#ImpoQuestion2").hide();
    });
   
    
    //3rd Question
    $("#FeedbackPage_ct300_Options_0").click(function() {
        $("#ImpoQuestion3").hide();
    });
      
     $("#FeedbackPage_ct300_Options_1").click(function() {
        $("#ImpoQuestion3").hide();
    });
    
    //4th Question
    $("#FeedbackPage_ct400_Options_0").click(function() {
        $("#ImpoQuestion4").hide();
    });
      
     $("#FeedbackPage_ct400_Options_1").click(function() {
        $("#ImpoQuestion4").hide();
    });
      
    //5th Question
    $("#FeedbackPage_ct500_Options_0").click(function() {
        $("#ImpoQuestion5").hide();
    });
      
     $("#FeedbackPage_ct500_Options_1").click(function() {
        $("#ImpoQuestion5").hide();
    });
    
    //6th Question
    $("#FeedbackPage_ct600_Options_0").click(function() {
        $("#ImpoQuestion6").hide();
    });
      
     $("#FeedbackPage_ct600_Options_1").click(function() {
        $("#ImpoQuestion6").hide();
    });
    
    //7th Question
        //2nd Question
    $("#q7rating5").click(function() {
        $("#ImpoQuestion7").hide();
    });
      
    $("#q7rating4").click(function() {
        $("#ImpoQuestion7").hide();
    });
    $("#q7rating3").click(function() {
        $("#ImpoQuestion7").hide();
    });
    $("#q7rating2").click(function() {
        $("#ImpoQuestion7").hide();
    });
    $("#q7rating1").click(function() {
        $("#ImpoQuestion7").hide();
    });
    
    //8th Question
    $("#FeedbackPage_ct800_Options_0").click(function() {
        $("#ImpoQuestion8").hide();
    });
      
     $("#FeedbackPage_ct800_Options_1").click(function() {
        $("#ImpoQuestion8").hide();
    });
    
    //9th Question
    $("#FeedbackPage_ct900_Options_0").click(function() {
        $("#ImpoQuestion9").hide();
    });
      
     $("#FeedbackPage_ct900_Options_1").click(function() {
        $("#ImpoQuestion9").hide();
    });
});

