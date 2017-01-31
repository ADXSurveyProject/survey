package com.survey.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Report extends BaseDao {
	
	
	public Report() {
		
	}
	
	public List getSurveyResult(String reportDate) {
		System.out.println("getSurveyResult : " + reportDate );
		String sql = "select p.p_id as participant_id,q.q_id as question_id,q.question,r.response from participant p,response r,question q where date(r.created)=? and p.p_id = r.p_id and r.q_num = q.q_num and r.di_id = q.di_id order by p.p_id,r.di_id,r.q_num";
		List res = executeFetchSql(sql, new String[]{reportDate});
		System.out.println("res : " + res );
		return res;
	}
	
	public static void main(String args[]) throws Exception {
		Report q = new Report();
		System.out.println(q.getSurveyResult("2016-12-10"));
	}

	public Object getTimeTaken(String reportDate) {
		String sql = "select r1.p_id as participant_id,q.q_id as question_id,q.question,timediff(r2.created,r1.created) as TimeTaken from response r1 left join response r2 on r2.r_id = (r1.r_id+1), question q, participant p where date(r1.created)=? and r1.q_num=q.q_num and r1.di_id=q.di_id and p.p_id=r1.p_id order by q.q_id";
		List res = executeFetchSql(sql, new String[]{reportDate});
		return res;	
	}

	public Object getFeedback(String reportDate) {
		String sql = "select r.p_id participant_id,q.fb_id question_uid,q.fb_question,r.fb_response response from feedback_response r,feedback_question q  where  date(r.created)=? and r.fb_id = q.fb_id order by r.p_id,r.fb_id";
		List res = executeFetchSql(sql, new String[]{reportDate});
		return res;	
	}

	public Object getMetaData(String reportDate) {
		String sql = "select p.p_id as participant_id, p.latlon_str as LatLon,created as DateCreated from participant p where date(p.created)=? order by p.p_id";
		List res = executeFetchSql(sql, new String[]{reportDate});
		return res;
	}
	public Object getAllData() {
		
		List retVal = new ArrayList();
		
		List<Map> identityInfo = getIdentityInfo();
		Map surveys = getAllSurveys();
		Map feedbacks = getAllFeedbacks();
		
		for (Map each : identityInfo) {
			Object pid = each.get("p_id");
			List surveyInput = (List) surveys.get(pid);
			List feedbackInput = (List) feedbacks.get(pid);
			each.put("SurveyInput", surveyInput);
			each.put("FeedbackInput", feedbackInput);
			segregateDemographics(each);
			retVal.add(each);
		}
		
		return retVal;
	}
	
	private void segregateDemographics(Map each) {
		String[] demographicsData = new String[]{ "latlon_str",
		        "browser",
		        "os",
		        "gender",
		        "yob",
		        "income",
		        "education",
		        "marital_status",
		        "surrogacy",
		        "sibling",
		        "housing"};
		Map mapDemographicsData = new HashMap();
		for (String colname : demographicsData) {
			Object value = each.remove(colname);
			mapDemographicsData.put(colname, value);
		}
		each.put("Demographics", mapDemographicsData);
	}

	private List<Map> getIdentityInfo() {
		String sql = "select p.p_id,concat(              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@p_id)*4294967296))*36+1, 1),              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1),              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1),              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1),              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1),              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1),              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1),              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1),              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1),              substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed)*36+1, 1)             ) as UserID,		date(created) as created_date,        (          (            select max(r.created) from response r            where r.p_id=p.p_id          )          -          (            select min(r.created) from response r            where r.p_id=p.p_id          )		) as elaspsed_time,        ((select count(*) from response r where r.p_id=p.p_id) /         (select count(*) from question)) as questions_completed,        1.00 as survey_version, concat(p.survey_comp_code,'') as survey_code,        p.latlon_str,        'Not Captured' as browser,        'Not Captured' as os,        'Not Captured' as gender,        'Not Captured' as yob,        'Not Captured' as income,        'Not Captured' as education,        'Not Captured' as marital_status,        'Not Captured' as surrogacy,        'Not Captured' as sibling, 'Not Captured' as housing from participant p order by p.p_id";
		List res = executeFetchSql(sql);
		return res;
	}
	
	
	private Map getAllSurveys() {
		String sql = "select r.p_id,q.q_id as question_number,q.question as question_text,r.response,'Not Captured' as presented, concat('',r.created) as responded from response r, question q where r.q_num=q.q_num and r.di_id=q.di_id order by r.p_id,q.q_id";
		List <Map> res = executeFetchSql(sql);
		
		Map retVal = makeMapByColumn(res, "p_id");
		
		return retVal;
	}
	private Map makeMapByColumn(List<Map> res, String colname) {
		Map retVal = new HashMap();
		for (Map each : res) {
			Object pid = each.get(colname);
			List pid_data = (List) retVal.get(pid);
			if (pid_data == null) {
				pid_data = new ArrayList();
				retVal.put(pid, pid_data);
			}
			pid_data.add(each);
		}
		return retVal;
	}

	private Map getAllFeedbacks() {
		String sql = "select fr.p_id,fq.fb_id as question_number,fq.fb_question as question_text,fr.fb_response,'Not Captured' as presented, concat('',fr.created) as responded from feedback_response fr, feedback_question fq where fr.fb_id=fq.fb_id order by fr.p_id,fq.fb_id";
		List res = executeFetchSql(sql);
		
		Map retVal = makeMapByColumn(res, "p_id");
		
		return retVal;
	}
	

}
