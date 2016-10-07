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

public class Question extends BaseDao {
	
	static Map questions = null;
	static Map di = null;
	static Map df = null;

	static DecisionFactor decision_factor = new DecisionFactor();
	static DecisionIssue decision_issue = new DecisionIssue();
	
	public Question() {
		if (questions == null) {
			questions = new HashMap();
			di = new HashMap();
			df = new HashMap();
			
			List res = getAll();
			Iterator iter = res.iterator();
			while (iter.hasNext()) {
				Map each = (Map) iter.next();
				String id = each.get("q_id").toString();
				questions.put(id, each);
				
				Integer di_id = (Integer) each.get("di_id");
				Integer df_id = (Integer) each.get("df_id");
				
				int num_of_yes = Integer.parseInt( "" + (Integer) each.get("num_of_yes_to_decide"));
				
				di.put(di_id, new Integer[]{num_of_yes, df_id});
				
				int num_of_di_to_decide = Integer.parseInt("" + (Integer) each.get("num_of_di_to_decide"));
				df.put(df_id, num_of_di_to_decide);
				
				
			}
		}
	}
	public int getNumOfYesByDiId(int di_id) {
		Integer[] d = (Integer[]) di.get(di_id);
		return d[0];
	}
	public int getNumOfDiByDfId(int df_id) {
		return (Integer) df.get(df_id);
	}
	public Map getDecisionFactorById(int df_id) {
		return (Map) decision_factor.get(df_id);
	}
	public Map getDecisionIssueById(int di_id) {
		return (Map) decision_issue.get(di_id);
	}
	public List getAll() {
		String sql = "select q.q_id, q.question, q.q_num, df.df_id, df.df_factor, df.num_of_di_to_decide, df.summary_pretext, df.summary_posttext, di.di_id, di.di_issue, di.num_of_yes_to_decide from question q, decision_factor df, decision_issue di where q.di_id = di.di_id and df.df_id = di.df_id";
		List res = executeFetchSql(sql);
		return res;
	}
	public Map getQuestions() {
		return questions;
	}

	public List getById(String i) {
		Map each = (Map) questions.get(i);
		List res = new ArrayList();
		res.add(each);
		return res;
	}


	public static void main(String args[]) throws Exception {
		Question q = new Question();
		System.out.println(q.getAll());
	}

	public List getCurrentQuestionByPid(String pid) {
		String sql = "select max(q.q_id)+1 as q_id from question q, participant p, response r where q.di_id=r.di_id and q.q_num = r.q_num and p.p_id = r.p_id and p.p_id=?";
		List res = executeFetchSql(sql, new String[]{pid});
		return res;
		
	}

	public List getTotalQuestions() {
		String sql = "select max(q_id) as total from question";
		List res = executeFetchSql(sql);
		return res;
	}

}
