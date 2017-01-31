$(document).ready(function(){
   $('#startnext').hide();
   
   $(".g-recaptcha").click(function () {
       if ($("#recaptcha-anchor-label").val() == "I'm not a robot")
    {
       $("#startnext").show();
       $(".g-recaptcha").hide();
    }
   });
    
});

function recaptchaCallback() {
    $('#startnext').show();
    $(".g-recaptcha").hide();
};