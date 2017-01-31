<!DOCTYPE html>
<html>
<head>

  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">

   <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="assets/mobirise/css/style.css">
  <link rel="stylesheet" href="assets/mobirise/css/mbr-additional.css" type="text/css">
  <link rel="stylesheet" href="assets/mobirise/css/feedback.css">

  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
  <script src="assets/web/assets/jquery/jquery.min.js"></script>
  <script src="assets/bootstrap/js/bootstrap.min.js"></script>
  <script src="assets/mobirise/js/feedback.js"></script>
  <script src="assets/app/js/survey.js"></script>
    
</head>

<body onload="initFeedback()">
    <center>
    <section class="mbr-section mbr-section--relative mbr-section--fixed-size" id="msg-box3-2" style="background-color: rgb(255, 255, 255);">

     <div class="mbr-section__container container mbr-section__container--first" style="padding-top: 62px;">
        <div class="mbr-header mbr-header--wysiwyg row">
            <div class="col-sm-8 col-sm-offset-2">
                <h3 class="mbr-header__text">Feedback </h3>
                
            </div>
            
        </div>
    </div>

    <form name="frmFeedback" id="frmFeedback" action="<%=request.getContextPath()%>/rs" method="post">
		<input type="hidden" name="action" value="onSubmitFeedback"/>
		<input type="hidden" name="pid" value=""/>
		
		<table cellspacing="0" cellpadding="0" class="Feedbackmain" border="0" style="text-size:16px">

            <tr>
            	<td height="50" class="QuestionBackground">
            	</td>
            	<td colspan="6" valign="middle" class="QuestionBackground" width="100%" style ="text-align:justify">
            		<div class="QuestionTitle">
            			<span>Please take a moment to complete this short Feedback to tell us about your experience. Your feedback will be used to improve our service. Your responses will be kept confidential.</span>
        			</div>
            	</td>
            	<td class="QuestionBackground">
                </td>
            </tr>
            
            <tr>
            	<td height="10" colspan="8" valign="top">
            	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="QuestionBackground">
            			<tr>
            				<td width="100%" height="10"></td>
            			</tr>
            		</table>
            	</td>
            </tr>
                
    		<tr>
    			<td colspan="8" valign="top" class="PageBackground Feedbackquestionseparator">
    			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="PageBackground">
    					<tr>
    						<td class="Feedbackquestionseparator">&nbsp;</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	
		
            <tr>
            	<td class="QuestionBackground">
            		&nbsp;</td>
            	<td colspan="6" class="QuestionBackground">
            		<div>
            		    <span id="ImpoQuestion1" style="color:red">**</span>
            		    <span id="FeedbackPage_ctl00_QuestionNumber" class="QuestionTitle">1. </span>
            			<span id="FeedbackPage_ctl00_QuestionText" class="QuestionTitle">Did the summary analysis provide you with new insight into your personal decision</span>
            			<span id="FeedbackPage_ctl00_QuestionSuffix" class="QuestionTitle">?</span>
            		</div>
            		<span class="QuestionSubTitle">
            			
            		<table class="QuestionText" border="0">
                    	<tr>
                    		<td>
                    		    <input id="FeedbackPage_ctl00_Options_0" name="fbq1" type="radio" value="1" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>Yes</label>
                    		</td>
                    	</tr>
                    	<tr>
            	        	<td>
            		            <input id="FeedbackPage_ctl00_Options_1" name="fbq1" type="radio" value="0" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>No</label>
            		        </td>
            	        </tr>
                    </table>
            		</span>
            		
            	</td>
            	<td class="QuestionBackground">
                </td>
            </tr>
            
            <tr>
            	<td height="10" colspan="8" valign="top">
            	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="QuestionBackground">
            			<tr>
            				<td width="100%" height="10"></td>
            			</tr>
            		</table>
            	</td>
            </tr>
                
    		<tr>
    			<td colspan="8" valign="top" class="PageBackground Feedbackquestionseparator">
    			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="PageBackground">
    					<tr>
    						<td class="Feedbackquestionseparator">&nbsp;</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	
		
            <tr>
            	<td class="QuestionBackground">
                </td>
            	<td colspan="6" class="QuestionBackground">
            		<div>
            		     <span id="ImpoQuestion2" style="color:red">**</span>
            		    <span id="FeedbackPage_ct200_QuestionNumber" class="QuestionTitle">2. </span>
            			<span id="FeedbackPage_ct200_QuestionText" class="QuestionTitle">How would you rate the insight, on a scale of 1-5 star where 1 is 'I learned nothing', and '5' is 'I learned a lot about myself.'</span>
            			<span id="FeedbackPage_ct200_QuestionSuffix" class="QuestionTitle">?</span>
            		</div>
            		<span class="QuestionSubTitle">
            			
            		<table id="FeedbackPage_ct200_Options" class="QuestionText" border="0">
                    	<tr>
                    		<td>
                    		    <span class="starRating">
                                    <input id="q1rating5" type="radio" name="fbq2" value="5">
                                    <label for="q1rating5" >5</label>
                                    <input id="q1rating4" type="radio" name="fbq2" value="4">
                                    <label for="q1rating4">4</label>
                                    <input id="q1rating3" type="radio" name="fbq2" value="3">
                                    <label for="q1rating3">3</label>
                                    <input id="q1rating2" type="radio" name="fbq2" value="2">
                                    <label for="q1rating2">2</label>
                                    <input id="q1rating1" type="radio" name="fbq2" value="1">
                                    <label for="q1rating1">1</label>
                                </span>
                        	</td>
                    	</tr>
                    </table>
            		</span>
            		
            	</td>
            	<td class="QuestionBackground">
            	</td>
            </tr>


            <tr>
            	<td height="10" colspan="8" valign="top">
            	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="QuestionBackground">
            			<tr>
            				<td width="100%" height="10"></td>
            			</tr>
            		</table>
            	</td>
            </tr>
                
    		<tr>
    			<td colspan="8" valign="top" class="PageBackground Feedbackquestionseparator">
    			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="PageBackground">
    					<tr>
    						<td class="Feedbackquestionseparator">&nbsp;</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	
		
            <tr>
            	<td class="QuestionBackground">
                </td>
            	<td colspan="6" class="QuestionBackground">
            		<div>
            		    <span id="ImpoQuestion3"  style="color:red">**</span>
            		    <span id="FeedbackPage_ct300_QuestionNumber" class="QuestionTitle">3. </span>
            			<span id="FeedbackPage_ct300_QuestionText" class="QuestionTitle">Was the process clear to you</span>
            			<span id="FeedbackPage_ct300_QuestionSuffix" class="QuestionTitle">?</span>
            		</div>
            		<span class="QuestionSubTitle">
            			
            		<table id="FeedbackPage_ct300_Options" class="QuestionText" border="0">
                    	<tr>
                    		<td>
                    		    <input id="FeedbackPage_ct300_Options_0" name="fbq3" type="radio" value="1" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>Yes</label>
                    		</td>
                    	</tr>
                    	<tr>
            	        	<td>
            		            <input id="FeedbackPage_ct300_Options_1" name="fbq3" type="radio" value="0" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>No</label>
            		        </td>
            	        </tr>
                    </table>
            		</span>
            		
            	</td>
            	<td class="QuestionBackground">
            	</td>
            </tr>

            <tr>
            	<td height="10" colspan="8" valign="top">
            	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="QuestionBackground">
            			<tr>
            				<td width="100%" height="10"></td>
            			</tr>
            		</table>
            	</td>
            </tr>
                
    		<tr>
    			<td colspan="8" valign="top" class="PageBackground Feedbackquestionseparator">
    			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="PageBackground">
    					<tr>
    						<td class="Feedbackquestionseparator">&nbsp;</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	
		
            <tr>
            	<td class="QuestionBackground">
                </td>
            	<td colspan="6" class="QuestionBackground">
            		<div>
            		     <span id="ImpoQuestion4"  style="color:red">**</span>
            		    <span id="FeedbackPage_ct400_QuestionNumber" class="QuestionTitle">4. </span>
            			<span id="FeedbackPage_ct400_QuestionText" class="QuestionTitle">Did you understand the results</span>
            			<span id="FeedbackPage_ct400_QuestionSuffix" class="QuestionTitle">?</span>
            		</div>
            		<span class="QuestionSubTitle">
            			
            		<table id="FeedbackPage_ct400_Options" class="QuestionText" border="0">
                    	<tr>
                    		<td>
                    		    <input id="FeedbackPage_ct400_Options_0" name="fbq4" type="radio" value="1" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>Yes</label>
                    		</td>
                    	</tr>
                    	<tr>
            	        	<td>
            		            <input id="FeedbackPage_ct400_Options_1" name="fbq4" type="radio" value="0" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>No</label>
            		        </td>
            	        </tr>
                    </table>
            		</span>
            		
            	</td>
            	<td class="QuestionBackground">
            	</td>
            </tr>


            <tr>
            	<td height="10" colspan="8" valign="top">
            	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="QuestionBackground">
            			<tr>
            				<td width="100%" height="10"></td>
            			</tr>
            		</table>
            	</td>
            </tr>
                
    		<tr>
    			<td colspan="8" valign="top" class="PageBackground Feedbackquestionseparator">
    			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="PageBackground">
    					<tr>
    						<td class="Feedbackquestionseparator">&nbsp;</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	
		
            <tr>
            	<td class="QuestionBackground">
                </td>
            	<td colspan="6" class="QuestionBackground">
            		<div>
            		     <span id="ImpoQuestion5"  style="color:red">**</span>
            		    <span id="FeedbackPage_ct500_QuestionNumber" class="QuestionTitle">5. </span>
            			<span id="FeedbackPage_ct500_QuestionText" class="QuestionTitle">Did you feel that all you were asked questions about all relevant factors for making a decision</span>
            			<span id="FeedbackPage_ct500_QuestionSuffix" class="QuestionTitle">?</span>
            		</div>
            		<span class="QuestionSubTitle">
            			
            		<table id="FeedbackPage_ct500_Options" class="QuestionText" border="0">
                    	<tr>
                    		<td>
                    		    <input id="FeedbackPage_ct500_Options_0" name="fbq5" type="radio" value="1" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>Yes</label>
                    		</td>
                    	</tr>
                    	<tr>
            	        	<td>
            		            <input id="FeedbackPage_ct500_Options_1" name="fbq5" type="radio" value="0" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>No</label>
            		        </td>
            	        </tr>
                    </table>
            		</span>
            		
            	</td>
            	<td class="QuestionBackground">
            	</td>
            </tr>


            <tr>
            	<td height="10" colspan="8" valign="top">
            	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="QuestionBackground">
            			<tr>
            				<td width="100%" height="10"></td>
            			</tr>
            		</table>
            	</td>
            </tr>
                
    		<tr>
    			<td colspan="8" valign="top" class="PageBackground Feedbackquestionseparator">
    			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="PageBackground">
    					<tr>
    						<td class="Feedbackquestionseparator">&nbsp;</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	
		
            <tr>
            	<td class="QuestionBackground">
                </td>
            	<td colspan="6" class="QuestionBackground">
            		<div>
            		     <span id="ImpoQuestion6"  style="color:red">**</span>
            		    <span id="FeedbackPage_ct600_QuestionNumber" class="QuestionTitle">6. </span>
            			<span id="FeedbackPage_ct600_QuestionText" class="QuestionTitle">Did you feel like something was missed? Do you know of something that would influence your medical decisions that was not identified</span>
            			<span id="FeedbackPage_ct600_QuestionSuffix" class="QuestionTitle">?</span>
            		</div>
            		<span class="QuestionSubTitle">
            			
            		<table id="FeedbackPage_ct600_Options" class="QuestionText" border="0">
                    	<tr>
                    		<td>
                    		    <input id="FeedbackPage_ct600_Options_0" class="FeedbackPage_ct600_Options_0" name="fbq6" type="radio" value="1" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>Yes</label>
                    		</td>
                    	</tr>
                    	<tr>
            	        	<td>
            		            <input id="FeedbackPage_ct600_Options_1" class="FeedbackPage_ct600_Options_1" name="fbq6" type="radio" value="0" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>No</label>
            		        </td>
            	        </tr>
            	        <tr>
            	        	<td>     
                                 <textarea class="textareainput" name="fbq6_text" placeholder="If yes, what was missed?" ></textarea>
            		        </td>
            	        </tr>
                    </table>
            		</span>
            		
            	</td>
            	<td class="QuestionBackground">
            	</td>
            </tr>

             <tr>
            	<td height="10" colspan="8" valign="top">
            	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="QuestionBackground">
            			<tr>
            				<td width="100%" height="10"></td>
            			</tr>
            		</table>
            	</td>
            </tr>
                
    		<tr>
    			<td colspan="8" valign="top" class="PageBackground Feedbackquestionseparator">
    			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="PageBackground">
    					<tr>
    						<td class="Feedbackquestionseparator">&nbsp;</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	
		
            <tr>
            	<td class="QuestionBackground">
                </td>
            	<td colspan="6" class="QuestionBackground">
            		<div>
            		     <span id="ImpoQuestion7"  style="color:red">**</span>
            		    <span id="FeedbackPage_ct700_QuestionNumber" class="QuestionTitle">7. </span>
            			<span id="FeedbackPage_ct700_QuestionText" class="QuestionTitle">Was the language of the Feedback understandable? How would you rate its clarity, where '1' is 'I didn't understand many words in several questions' and '5' is 'I understood all of the words in all of the questions.'</span>
            			<span id="FeedbackPage_ct700_QuestionSuffix" class="QuestionTitle">?</span>
            		</div>
            		<span class="QuestionSubTitle">
            			
            		<table id="FeedbackPage_ct700_Options" class="QuestionText" border="0">
                    	<tr>
                    		<td>
                    		    <span class="starRating">
                                    <input id="q7rating5" type="radio" name="fbq7" value="5">
                                    <label for="q7rating5">5</label>
                                    <input id="q7rating4" type="radio" name="fbq7" value="4">
                                    <label for="q7rating4">4</label>
                                    <input id="q7rating3" type="radio" name="fbq7" value="3">
                                    <label for="q7rating3">3</label>
                                    <input id="q7rating2" type="radio" name="fbq7" value="2">
                                    <label for="q7rating2">2</label>
                                    <input id="q7rating1" type="radio" name="fbq7" value="1">
                                    <label for="q7rating1">1</label>
                                </span>
                        	</td>
                    	</tr>
                    </table>
            		</span>
            		
            	</td>
            	<td class="QuestionBackground">
            	</td>
            </tr>

    
            <tr>
            	<td height="10" colspan="8" valign="top">
            	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="QuestionBackground">
            			<tr>
            				<td width="100%" height="10"></td>
            			</tr>
            		</table>
            	</td>
            </tr>
                
    		<tr>
    			<td colspan="8" valign="top" class="PageBackground Feedbackquestionseparator">
    			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="PageBackground">
    					<tr>
    						<td class="Feedbackquestionseparator">&nbsp;</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	
		
            <tr>
            	<td class="QuestionBackground">
                </td>
            	<td colspan="6" class="QuestionBackground">
            		<div>
            		     <span id="ImpoQuestion8" style="color:red">**</span>
            		    <span id="FeedbackPage_ct800_QuestionNumber" class="QuestionTitle">8. </span>
            			<span id="FeedbackPage_ct800_QuestionText" class="QuestionTitle">How was the length of the Feedback? Did you start to rush your answers because you became bored</span>
            			<span id="FeedbackPage_ct800_QuestionSuffix" class="QuestionTitle">?</span>
            		</div>
            		<span class="QuestionSubTitle">
            			
            		<table id="FeedbackPage_ct800_Options" class="QuestionText" border="0">
                    	<tr>
                    		<td>
                    		    <input id="FeedbackPage_ct800_Options_0" name="fbq8" type="radio" value="1" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>Yes</label>
                    		</td>
                    	</tr>
                    	<tr>
            	        	<td>
            		            <input id="FeedbackPage_ct800_Options_1" name="fbq8" type="radio" value="0" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>No</label>
            		        </td>
            	        </tr>
                    </table>
            		</span>
            		
            	</td>
            	<td class="QuestionBackground">
            	</td>
            </tr>


            <tr>
            	<td height="10" colspan="8" valign="top">
            	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="QuestionBackground">
            			<tr>
            				<td width="100%" height="10"></td>
            			</tr>
            		</table>
            	</td>
            </tr>
                
    		<tr>
    			<td colspan="8" valign="top" class="PageBackground Feedbackquestionseparator">
    			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="PageBackground">
    					<tr>
    						<td class="Feedbackquestionseparator">&nbsp;</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	
		
            <tr>
            	<td class="QuestionBackground">
                </td>
            	<td colspan="6" class="QuestionBackground">
            		<div>
            		     <span id="ImpoQuestion9"  style="color:red">**</span>
            		    <span id="FeedbackPage_ct900_QuestionNumber" class="QuestionTitle">9. </span>
            			<span id="FeedbackPage_ct900_QuestionText" class="QuestionTitle">Do you think that the summary report you received would help you making a difficult decision</span>
            			<span id="FeedbackPage_ct900_QuestionSuffix" class="QuestionTitle">?</span>
            		</div>
            		<span class="QuestionSubTitle">
            			
            		<table id="FeedbackPage_ct900_Options" class="QuestionText" border="0">
                    	<tr>
                    		<td>
                    		    <input id="FeedbackPage_ct900_Options_0" class="FeedbackPage_ct900_Options_0" name="fbq9" type="radio" value="1" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>Yes</label>
                    		</td>
                    	</tr>
                    	<tr>
            	        	<td>
            		            <input id="FeedbackPage_ct900_Options_1" class="FeedbackPage_ct900_Options_1" name="fbq9" type="radio" value="0" /><label style="font-weight: normal;"><span class='CheckboxSeparator'>&nbsp;</span>No</label>
            		        </td>
            	        </tr>
            	        <tr>
            	        	<td>
                                 <textarea class="textareainputq9" name="fbq9_text" placeholder="If not, why not?" ></textarea>
            		        </td>
            	        </tr>
                    </table>
            		</span>
            		
            	</td>
            	<td class="QuestionBackground">
            	</td>
            </tr>
           
		</table>
   
	</form>
     </br>
            <div class="col-sm-8 col-sm-offset-2">
                <div class="mbr-buttons mbr-buttons--center"><a class="mbr-buttons__btn btn btn-lg btn-default" id="submitbutton" onclick="save(FEEDBACK_COMPLETED,YES,null);document.frmFeedback.submit();">SUBMIT</a>
                </div>
            </div>
     </br>
     </section>
    </center>
    </br>
 
  
  
</body>
</html>