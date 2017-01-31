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
import com.survey.dao.Question;
import com.survey.service.App;

@WebServlet("/rs/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map reqmap = req.getParameterMap();
		
		String action = req.getParameter("action");
				
		if (action != null) {
			Object response="success";
			try {
				System.out.println("req : " + action +"," + req);
				App app = App.getInstance();
				
				Method method = App.class.getMethod(action, new Class[]{Map.class});
				response = method.invoke(app, new Object[]{reqmap});
				if (response != null && (response.toString().endsWith(".html") || response.toString().endsWith(".jsp"))) {
					RequestDispatcher rd = req.getRequestDispatcher(response.toString());
					rd.forward(req, resp);
				} else {
					writeResponse(response, resp);
				}
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				pw.close();
				sw.close();
				
				response = "Error : " + sw.toString();
				writeResponse(response, resp);
			}
		}

	}

}
