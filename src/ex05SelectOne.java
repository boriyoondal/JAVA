import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ex05SelectOne {

	public static void main(String[] args) {
		// 특정 회원의 id입력 -> 해당 id 를 가진 회원의 정보만 출력
		// id는 겹치지 않는다
		Scanner sc = new Scanner(System.in);
		System.out.println("==특정 회원 정보 출력==");
		System.out.println("아이디입력 : ");
		String id = sc.next();
		
		
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		// try~catch : 예외 처리 (런타임오류)
		try {
			// 실행할 코드
			// 1. 드라이버 로드 -> ClassNotFoundException
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";
			
			// 2. 데이터 베이스 연결 (Connection)객체 생성
			conn = DriverManager.getConnection(url,dbid,dbpw); // sqlexception -> conn x
			
			String sql = "select * from JDBC_member where id =?";
			
			// 3. sql 실행/준비(PreparedStatement) 객체 생성
			psmt = conn.prepareStatement(sql);
			
			// 4. ? 자리 채우기
			psmt.setString(1, id); // index 는  1부터 시작
			
			// executeUpdate() : insert, delete, update -> 테이블에 변화 O
			// -> 반환값 (INT) -> 몇개의 행에서 변화가 일어났는지
			// executeQuery() : select -> 테이블에 변화 x 
			// -> 반환값 (ResultSet(Object)) -> select문 실행시 결과값 + 커서
			// next() : 커서를 한칸 움직임(다음 행을 가리킴)
			// -> 커서가 가리키는 행에 값이 있는지 없는지 판단 -> 있으면 true반환 없으면 false반환
			
			//5. sql 문 실행
			rs = psmt.executeQuery();
			
			// 6. 값을 출력
			if(rs.next()) {
				// 각각의 컬럼값 읽어오기
				String getid = rs.getString(1); // 컬럼의 순서(1부터 시작) 지정
				String pw = rs.getString("PW"); // 컬럼 이름 (String) 지정
				String nick = rs.getString(3);
				
				//int a = rs.getInt(1) -> 정수형값 가지고 올때
				
				System.out.println("id :" + getid);
				System.out.println("pw : " + pw);
				System.out.println("nick : " + nick);
				System.out.println("====================");
			} else {
				System.out.println("해당 회원이 존재하지 않습니다!");
			}
			
		} catch(Exception e) {
			// try 문 안에서 발생한 예외 상황에 대한 처리
			e.printStackTrace();
		} finally {
			// 6. 사용한 객체 닫기 (자원반환)
			try {
			rs.close();  // 객체 생성이 되지 않은 경우 -> 위쪽에서 예외상황 발생 -> close() 호출x -> 런타임 오류
			psmt.close();
			conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		
		

	}

}
