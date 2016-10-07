package com.survey.dao;

import java.util.List;
import java.util.Map;

public class Participant extends BaseDao {

	public List findParticipantByLatLon(String lat, String lon) {
		//String sql = "select * from participant where latlon=ST_GeomFromText('POINT("+lat+" "+lon+")')";		
		String sql = "select * from participant where latlon_str=?";
		List res = executeFetchSql(sql, new String[]{lat+","+lon});
		return res;
	}

	public String createNewParticipant() {
		String sql = "select max(p_id)+1 as newid from participant";
		List res = executeFetchSql(sql);
		String newid = null;
		try {
			newid = ((Map)res.get(0)).get("newid").toString();
		} catch (NullPointerException e) {
			newid = "1";
		}
		String insertsql=null;
			//insertsql = "INSERT INTO participant(uid,latlon_str) VALUES (?,ST_GeomFromText('POINT("+lat+" "+lon+")'))";
		insertsql = "INSERT INTO participant(p_id) VALUES (?)";
		executeUpdate(insertsql, new String[]{newid});
		return newid;
	}

	public List findParticipantById(String pid) {
		String sql = "select * from participant where p_id=?";
		List res = executeFetchSql(sql, new String[]{pid});		
		return res;
	}

	public void saveLocation(String pid, String latlon) {
		String updatesql = "update participant set latlon_str=? where p_id = ?";
		executeUpdate(updatesql, new String[]{latlon, pid});		
		
	}

	
}
