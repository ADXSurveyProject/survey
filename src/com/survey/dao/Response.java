package com.survey.dao;

import java.util.List;

public class Response extends BaseDao {
	
	public void saveResponse(String uid, String din, String qn, String response) {
			
		String insertsql = "INSERT INTO response (p_id, di_id,q_num,response) VALUES (?,?,?,?)";
		executeUpdate(insertsql, new String[]{uid, din, qn, response});
		
	}

	public List getResponsesForParticipant(String pid) {
		String sql = "select di.df_id, r.di_id, count(*) numofyes from response r , decision_issue di where r.p_id=? and r.response=1 and r.di_id = di.di_id group by r.di_id ";
		return executeFetchSql(sql, new String[]{pid});
	}

	

}
