package com.survey.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.dao.Report;
import com.survey.util.ZipUtil;

public final class ReportingApp {
	static ReportingApp app = new ReportingApp();
	//static SimpleDateFormat source = new SimpleDateFormat("ddMMyyyy");
	static SimpleDateFormat source = new SimpleDateFormat("MMddyyyy");
	static SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd");
	
	Report report = null;

	private ReportingApp() {
		report = new Report();
	}

	public static synchronized ReportingApp getInstance() {
		return app;
	}

	public Object getReport(Map req) {
		Object response = null;

		try {
			String reportDate = null;
			String reportDates[] = (String[]) req.get("date");
			reportDate = reportDates[0];
			try {
				System.out.println("input date : " + reportDate);
				Date date = source.parse(reportDate);
				System.out.println("parsed source date : " + date);

				reportDate = target.format(date);
				System.out.println("parsed target date : " + reportDate);

			} catch (Exception e) {
				reportDate = null;
				response = "Error while parsing date, " + e.getMessage();
			}
			if (reportDate != null) {
				Object surveyResult  = report.getSurveyResult(reportDate);
				Object timeTaken = report.getTimeTaken(reportDate);
				Object feedback = report.getFeedback(reportDate);
				Object metadata = report.getMetaData(reportDate);
				Object alldata = report.getAllData();
				
				ObjectMapper om = new ObjectMapper();

				surveyResult = om.writeValueAsString(surveyResult).getBytes();
				timeTaken = om.writeValueAsString(timeTaken).getBytes();
				feedback = om.writeValueAsString(feedback).getBytes();
				metadata = om.writeValueAsString(metadata).getBytes();
				alldata = om.writeValueAsString(alldata).getBytes();

				Map data = new HashMap();
				//data.put("SurveyResult.json", surveyResult);
				//data.put("TimeTaken.json", timeTaken);
				//data.put("Feedback.json", feedback);
				//data.put("metadata.json", metadata);
				data.put("alldata.json", alldata);
				
				Object tmpresponse[] = new Object[2];
				tmpresponse[0] = "SurveyReport_"+reportDates[0]+".zip";
				tmpresponse[1] = ZipUtil.prepareZipFile(data);
				response = tmpresponse;
				
			}
			
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response = "Unable to fetch report, " + e.getMessage();
		}
		return response;
	}

}
