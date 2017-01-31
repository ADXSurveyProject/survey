package com.survey.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.survey.dao.Feedback;
import com.survey.dao.Participant;
import com.survey.dao.Question;
import com.survey.dao.Response;

public final class App {
	static App app = new App();
	Question q = null;
	private App() {
		q = new Question();
	}

	public static synchronized App getInstance() {
		return app;
	}

	public Object getQuestion(Map req) {
		try {
			String pid[] = (String[]) req.get("pid");

			String questionId = "1";
			questionId = ((String[]) req.get("questionId"))[0];

			

			Object response = null;
			try {
				Integer.parseInt(questionId);
			} catch (Exception e) {
				questionId = "1";
			}
			int totQuestions = q.getQuestions().size();
			//totQuestions = 40; //TODO temporary, remove this line
			if (Integer.parseInt(questionId) <= totQuestions) {
				List questions = q.getById(questionId);

				if (questions.size() > 0) {

					Map question = (Map) questions.get(0);

					List survey = new ArrayList();
					survey.add(question.get("q_id"));
					survey.add(question.get("question"));

					response = survey;
				}
			} else {
				response = "Survey completed";
				//16-01-2017 changes - comment out the following 3 lines
				//if (pid != null) {
					//updateSurveyCompletionCode(pid[0]);
				//}
			}
			return response;

		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}

	private void updateSurveyCompletionCode(String pid) {
		Participant p = new Participant();
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(0, 6).toUpperCase();
		p.updateSurveyCompletionCode(uuid,pid);
	}

	public Object getSurveyCompletionCode(Map req) {
		String pid[] = (String[]) req.get("pid");
		if (pid != null) {
			Participant p = new Participant();
			List data = p.getSurveyCompletionCode(pid[0]);
			if (data != null && data.size() > 0) {
				Map row = (Map) data.get(0);
				String survey_comp_code = (String) row.get("survey_comp_code");
				return survey_comp_code;
			} else {
				return "Participant not found";
			}
			
		} else {
			return "Participant ID not found";
		}
	
	}
	public Object saveResponse(Map req) {
		String pid[] = (String[]) req.get("pid");
		String key[] = (String[]) req.get("key");
		String val[] = (String[]) req.get("val");
		System.out.println("saveResponse : pid : " + pid[0] + ", key : " + key[0] + ", val : " + val[0]);
		List lquestion = q.getById(key[0]);
		Map mquestion = (Map) lquestion.get(0);
		
		String uid = pid[0];
		String din = (String) mquestion.get("di_id").toString();
		String qn = (String) mquestion.get("q_num").toString();
		String response = val[0];

		Response r = new Response();
		r.saveResponse(uid, din, qn, response);
		
		return "Response saved";
	}
	public Object getCurrentQuestion(Map req) {
		String pid[] = (String[]) req.get("pid");
		if (pid != null) {
			
			Question q = new Question();
			List currentQ = q.getCurrentQuestionByPid(pid[0]);
			if (currentQ.size() > 0) {
				try {
					return Integer.parseInt(((Map)currentQ.get(0)).get("q_id").toString());
				} catch (Exception e) {
					return 1;
				}
			}
		}
		return 1;
	}
	public Object saveLocation(Map req) {
		String latlon[] = (String[]) req.get("latlon");
		String pid[] = (String[]) req.get("pid");
		
		if (pid != null && latlon != null) {
			Participant p = new Participant();
			p.saveLocation(pid[0], latlon[0]);
		}
		
		
		return "Location saved successfully";
	}
	public Object getParticipantId(Map req) {
		String pid[] = (String[]) req.get("pid");
		Participant p = new Participant();
		if (pid != null) {
			try {
				Integer.parseInt(pid[0]);
				List pidFromDb = p.findParticipantById(pid[0]);
				if (pidFromDb.size() > 0) {
					Map each = (Map) pidFromDb.get(0);
					if (pid[0].equals(""+each.get("p_id"))) {
						return pid;
					}
				}	
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		String uid= p.createNewParticipant();
		System.out.println("returning pid : " + uid);
		return uid;
		
	}
	public Object getTotalQuestions(Map req) {
		Question q = new Question();
		List res = q.getTotalQuestions();
		if (res.size() > 0) {
			return ((Map)res.get(0)).get("total").toString();
		}
		return -1;
	}
	public Object saveProgress(Map req) {
		System.out.println("Save progress");
		return "success";
	}
	
	public Object onSurveyComplete(Map req) {
		String pid[] = (String[]) req.get("pid");
		Response r = new Response();
		
		List responses = r.getResponsesForParticipant(pid[0]);
		Question q = new Question();
		
		Iterator iter = responses.iterator();
		
		Map shortListedDfDIIds = new HashMap();
		
		while (iter.hasNext()) {
			Map each = (Map) iter.next();
			int df_id = (Integer) each.get("df_id");
			int di_id = (Integer) each.get("di_id");
			int numofyes = Integer.parseInt("" + (Long) each.get("numofyes"));
			int requiredNumberOfYes = q.getNumOfYesByDiId(di_id);
			
			if (numofyes >= requiredNumberOfYes) {
				List df_dis = (List) shortListedDfDIIds.get(df_id);
				if (df_dis == null) {
					df_dis = new ArrayList();
					shortListedDfDIIds.put(df_id, df_dis);	
				}
				df_dis.add(di_id);				
			}
		}
		
		List decision_factors = new ArrayList();
		
		iter = shortListedDfDIIds.keySet().iterator();
		int kdi = 0;
		int sdf = 0;
		List pie = new ArrayList();
		while (iter.hasNext()) {
			int df_id = (Integer) iter.next();
			List dis = (List) shortListedDfDIIds.get(df_id);
			int requiredNumOfDi = q.getNumOfDiByDfId(df_id);
			Map df_factor = q.getDecisionFactorById(df_id);
			
			if (dis.size() >= requiredNumOfDi) {
				sdf++;
				//df shorlisted				
				Map eachdf = new HashMap();
				
				List diIssues = new ArrayList();
				Iterator disIter = dis.iterator();
				kdi += dis.size();

				Map y = new HashMap();
				y.put("y", dis.size());
				pie.add(y);

				while (disIter.hasNext()) {
					int di_id = (Integer) disIter.next();
					Map di_issue = q.getDecisionIssueById(di_id);					
					diIssues.add( di_issue.get("di_issue") );
				}
			
				eachdf.put("each_df", df_factor.get("df_factor"));
				eachdf.put(df_factor.get("df_factor"), diIssues);
				eachdf.put(df_factor.get("df_factor").toString() + "_pre", df_factor.get("summary_pretext"));
				eachdf.put(df_factor.get("df_factor").toString() + "_post", df_factor.get("summary_posttext"));
				decision_factors.add(eachdf);	
			}
		
				
		}
		Map decisions = new HashMap();
		
		decisions.put("pie", pie);
		decisions.put("KeyDecisionIssues", kdi);
		decisions.put("SpecialDecisionFactors", sdf);					
		decisions.put("decision_factors", decision_factors);
		
		return decisions;
	}
	
	public String onSubmitFeedback(Map req) {
		String pid;
		String fb[]=new String[9];
		String fb6_text = null;
		String fb9_text = null;
		pid = ((String[]) req.get("pid"))[0]; 
		for (int i=1;i<=9;i++) {	
			String tmp[] = (String[]) req.get("fbq"+i);
			if (tmp != null) {
				fb[i-1] = tmp[0]; 
			} else {
				fb[i-1] = null;
			}
		}
		String tmp[] = (String[]) req.get("fbq6_text");
		if (tmp != null) {
			fb6_text = tmp[0];
		}
		tmp = (String[]) req.get("fbq9_text");
		if (tmp != null) {
			fb9_text = tmp[0];
		}

		Feedback f = new Feedback();
		f.saveFeedback(pid, fb, fb6_text, fb9_text);
		//16-01-2017 changes - add the following 3 lines
		if (pid != null) {
			updateSurveyCompletionCode(pid);
		}
		return "thankyou.html";
	}
}

