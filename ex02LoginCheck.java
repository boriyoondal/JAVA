package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex02LoginCheck")
public class ex02LoginCheck extends HttpServlet {


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. ����ڰ� �Է��� ID,PW�� �������ÿ�
		//2. ID�� 'smart'�̰�, PW��'123'�� �� ������ ex02LoginS.jsp�� �̵��ϰ�
		//���н� ex02LoginF.jsp�� �̵�
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.equals("smart") && pw.equals("123")){
			response.sendRedirect("ex02LoginS.jsp");
		}else{
			response.sendRedirect("ex02LoginF.jsp");
		}
		
	}

}
