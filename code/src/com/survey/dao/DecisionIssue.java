package com.survey.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DecisionIssue extends BaseDao {

	static Map di = null;
	DecisionIssue() {
		di = new HashMap();
		List al = getAll();
		
		Iterator iter = al.iterator();
		while (iter.hasNext()) {
			Map each = (Map) iter.next();
			int di_id = (Integer) each.get("di_id");
			di.put(di_id, each);
		}
	}
	private List getAll() {
		String sql = "select * from decision_issue";
		List res = executeFetchSql(sql);
		return res;
	}

	public Map get(int di_id) {
		return (Map) di.get(di_id);
	}
}
