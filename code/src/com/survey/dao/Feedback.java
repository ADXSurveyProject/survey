package com.survey.dao;

import java.util.List;
import java.util.Map;

public class Feedback extends BaseDao{
	public String saveFeedback(String pid, String[] fbq, String fbq6_text, String fbq9_text) {
		

		String insertsql = "INSERT INTO feedback_response (p_id,fb_id,fb_response,fb_response_text) VALUES (?,?,?,?)";
		for (int i=1;i<=9;i++) {
			String fbtext = null;
			if (i == 6) {
				fbtext = fbq6_text;
			} else  if (i == 9) {
				fbtext = fbq9_text;
			}
			if (fbq[i-1] != null) {
				executeUpdate(insertsql, new String[]{pid,""+i,fbq[i-1],fbtext});
			}
		}
		return "Saved successfully";
	}

}
