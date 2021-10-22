import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ex04SelectAll {

	public static void main(String[] args) {
		
		// 현재 가입 되어 있는 모든 회원 정보 출력(콘솔)
		// JDBC_member 테이블에 모든 정보 출력 -> 조건 x
		
		
//		-- DML - INSERT,DELETE,UPDATE -> COMMIT 실제반영
//		-- DCL - CREATE,DROP -> 실행하면 자동으로 COMMIT
//		-- ECLIPSE -> AUTO COMMIT  >> COMMIT 안해도됨
		
		
		//전역변수화
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = null; // java.sql 불러오기
		
		// try~catch : 예외 처리 (런타임오류)
		try {
			// 실행할 코드
			// 1. 드라이버 로드 -> ClassNotFoundException
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";
			
			// 2. 데이터 베이스 연결 (Connection)객체 생성
			conn = DriverManager.getConnection(url,dbid,dbpw); // sqlexception -> connx
			
			String sql = "select * from JDBC_member";
			
			// 3. sql 실행/준비(PreparedStatement) 객체 생성
			psmt = conn.prepareStatement(sql);
			
			// executeUpdate() : insert, delete, update -> 테이블에 변화 O
			// -> 반환값 (INT) -> 몇개의 행에서 변화가 일어났는지
			// executeQuery() : select -> 테이블에 변화 x 
			// -> 반환값 (ResultSet(Object)) -> select문 실행시 결과값 + 커서
			// next() : 커서를 한칸 움직임(다음 행을 가리킴)
			// -> 커서가 가리키는 행에 값이 있는지 없는지 판단 -> 있으면 true반환 없으면 false반환
			
			//4. sql 문 실행
			rs = psmt.executeQuery(); 
			
			// 5. 값을 출력
		
			while(rs.next()) { 
				// 각각의 컬럼값 읽어오기
				String id = rs.getString(1); // 컬럼의 순서(1부터 시작) 지정
				String pw = rs.getString("PW"); // 컬럼 이름 (String) 지정  / 2로해도 가능
				String nick = rs.getString(3);
				
				//int a = rs.getInt(1) -> 정수형값 가지고 올때
				
				System.out.println("id :" + id);
				System.out.println("pw : " + pw);
				System.out.println("nick : " + nick);
				System.out.println("====================");
			
			}
			
		} catch(Exception e) {
			// try 문 안에서 발생한 예외 상황에 대한 처리
			e.printStackTrace();
		} finally {
			// try 안에서 예외상황이 발생하던지 발생하지 않던지 무조건 실행하는 구문
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
