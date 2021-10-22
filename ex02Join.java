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


@WebServlet("/ex02Join")
public class ex02Join extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		//���ڵ�
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nick = request.getParameter("nick");
		
		//JDBC�ڵ��Է�
		//1. ojdbc6.jar ��������(WEB-INF->lib)	
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {		
			//2. OracleDriver.class �����ε��ϱ�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//3. Oracle�� ���� DBID, DBPW�� ����
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";
			
			 conn = DriverManager.getConnection(url, dbid, dbpw);
			
			if(conn!=null) {
				System.out.println("���Ἲ��");
			}else {
				System.out.println("�������");
			}
			
			//4.sql�� �غ�ܰ�
			String sql = "insert into JDBC_member values(?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setNString(1, id);
			psmt.setNString(2, pw);
			psmt.setNString(3, nick);
			
			//5. SQL�� ��� �� ó��
			int cnt = psmt.executeUpdate();
			
			if(cnt>0) {
				response.sendRedirect("ex02Login.html");
			}else {
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//1. ��������
			//2. ����ó��
		
			try {
				if(psmt!=null) {
				psmt.close();
				}
				if(conn!=null) {
				conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

}
