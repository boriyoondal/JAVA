package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ex02Delete")
public class ex02Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");

		String id = request.getParameter("id");

		try {		
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";
			
			Connection conn = DriverManager.getConnection(url, dbid, dbpw);
		
			
			String sql = "DELETE FROM JDBC_member where id is null";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setNString(1, id);
			
			int cnt = psmt.executeUpdate();
			
			if(cnt>0) {
				response.sendRedirect("Main.html");
			}else {
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
