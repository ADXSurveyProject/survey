package com.survey.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DecisionFactor extends BaseDao {
	static Map df = null;
	DecisionFactor() {
		df = new HashMap();
		List al = getAll();
		
		Iterator iter = al.iterator();
		while (iter.hasNext()) {
			Map each = (Map) iter.next();
			int df_id = (Integer) each.get("df_id");
			df.put(df_id, each);
		}
	}

	public List getAll() {
		String sql = "select * from decision_factor";
		List res = executeFetchSql(sql);
		return res;
	}

	public Map get(int df_id) {
		return (Map) df.get(df_id);		
	}
}
