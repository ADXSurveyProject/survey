package com.survey.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.service.ReportingApp;

@WebServlet("/reports/*")
public class ReportingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void writeResponse(String filename, byte mdata[], HttpServletResponse response) {
		try {
			response.setHeader("Content-Disposition", "attachement ; filename= \""+filename+"\"");
			response.setContentLength(mdata.length);
			response.setContentType("application/zip");
			response.getOutputStream().write(mdata);
			response.getOutputStream().close();
			
		} catch (Exception e) {
			// ignore
			e.printStackTrace();
		}

	}
	private void writeResponse(Object mdata, HttpServletResponse response) {
		try {
			ObjectMapper om = new ObjectMapper();

			byte buf[] = om.writeValueAsString(mdata).getBytes();
			System.out.println("Response json : " + new String(buf));
			response.setContentLength(buf.length);
			response.setContentType("application/json");
			response.getOutputStream().write(buf);
			response.getOutputStream().close();
			
		} catch (Exception e) {
			// ignore
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map reqmap = req.getParameterMap();
		
		String action = req.getParameter("action");
				
		if (action != null) {
			Object response="success";
			try {
				System.out.println("req : " + action +"," + req);
				ReportingApp app = ReportingApp.getInstance();
				
				Method method = ReportingApp.class.getMethod(action, new Class[]{Map.class});
				response = method.invoke(app, new Object[]{reqmap});
				
				writeResponse((String) ((Object[])response)[0], (byte[])((Object[])response)[1], resp);
				
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				pw.close();
				sw.close();
				
				response = ("Error : " + sw.toString()).getBytes();
				writeResponse((byte[])response, resp);
			}
		}

	}

}
