import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ex01Insert {

	public static void main(String[] args) {
		
		System.out.println("회원가입서비스");
		Scanner sc = new Scanner(System.in);
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("PW : ");
		String pw = sc.next();
		System.out.print("NICK : ");
		String nick = sc.next();
		
		//JDBC(Java DataBase Connectivity)
		//Java와 DataBase를 연결해주는 API(Interface)
		
		//연결순서
		//1. oracle에서 제공하는 Java와 연결할 수 있는 외부 .jar가져와야함
		
		//2. ojdbc6.jar에서 연결하는 기능을 담당하는 드라이버.class파일 동적로딩
		
		Connection conn = null;
		PreparedStatement psmt = null;
		//Java에서 발생하는 오류 2가지
		//1. 컴파일오류 : 문법적인오류
		//2. 런타임오류 : 실행해야지만 알 수 있는 오류(DB, 네트워크)
		//예외처리문 try~catch문
		try {
			//정상실행
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Class파일확인");
			
			//3.oracle에 가서 DB아이디와 PW를 인증(oracle주소, id, pw)
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // 앞 ip = 집(컴)주소 : 뒤 port(방번호) > 문자로 바꾸면 도메인
			String dbid = "hr";
			String dbpw = "hr";
			//Connection객체 리턴 : Java에서 oracle을 핸들링 할 수 있는 객체 
			conn = DriverManager.getConnection(url, dbid, dbpw);
			
			if(conn!=null) {
				System.out.println("연결성공");
			}else {
				System.out.println("연결실패");
			}
			
			//4. oracle에 명령할 SQL문 준비 
			String sql = "insert into JDBC_member values(?, ?, ?)"; // 오라클디벨로퍼에서 가져옴
			psmt = conn.prepareStatement(sql); // 입력한 sql문에 오타가있는지 확인
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, nick);
			
			//5 SQL명령 실행
			int cnt = psmt.executeUpdate();
			
			//6. 명령 후 처리
			if(cnt>0) {
				System.out.println("입력성공");
			}else {
				System.out.println("입력실패");
			}
			
			
			
		} catch (Exception e) {
			      //오류발생//
			e.printStackTrace();//어떤 오류가 발생했는지 출력 *중요*
			System.out.println("Class파일확인실패");
		} finally {
			//정상적으로 실행되던 오류가 났던 마지막으로 실행되는 부분
			//7. 연결종료
			try {
				psmt.close();
				conn.close();				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		
		
		

	}

}





