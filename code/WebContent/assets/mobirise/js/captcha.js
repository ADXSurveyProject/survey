$(document).ready(function(){
   $("#startsurvey").hide();
   
   $(".g-recaptcha").click(function () {
       if ($("#recaptcha-anchor-label").val() == "I'm not a robot")
    {
       $("#startsurvey").show();
    }
   });
    
});

function recaptchaCallback() {
    $('#startsurvey').show();
};