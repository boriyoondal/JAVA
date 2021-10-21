package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
	// DAO : Data Access Object
	// CRUD : insert,update,delete,select 작업 수행
	// database와 연결
	// 기능정의(기능은 view에서 호출해서 사용)
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// 데이터베이스 연결 메서드
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Class파일 확인");

			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";
			conn = DriverManager.getConnection(url, dbid, dbpw);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 객체 닫는 메소드(사용한 자원 반환)
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			psmt.close();
			conn.close();
			// 사용한 객체 닫아주고 자원을 반환해준다.
		} catch (Exception e2) {
			e2.getStackTrace();
		}
	}


	// 1.회원가입
	public int insert(ModelVO vo) {
		// 변수명은 vo가 아니여도 ok
		int cnt = 0;

		try {
			connect();

			if (conn != null) {
				System.out.println("연결성공");
			} else {
				System.out.println("연결실패");
			}

			String sql = "insert into JDBC_member values(?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			psmt.setString(3, vo.getNick());

			cnt = psmt.executeUpdate();

		} catch (Exception e) {

			System.out.println("Class파일 확인 실패");
		} finally {
			close();

			return cnt;
		}

	}

	// 2.회원정보수정
	public int update(String id, int choice, String nick) {

		int cnt = 0;
		try {
			connect();
			if (choice == 1) {
				String sql = "update JDBC_member set pw = ?  where id = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(2, id);

			} else if (choice == 2) {
				String sql = "update JDBC_member set nick = ?  where id = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nick);
				psmt.setString(2, id);
			}

			cnt = psmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;

	}

	// 3.회원삭제
	public void delete(String id) {

		try {
			connect();

			String sql = "delete from JDBC_member where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			int cnt = psmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("삭제성공");
			} else {
				System.out.println("삭제실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public ArrayList<ModelVO> selectAll() {
		ArrayList<ModelVO> al = new ArrayList<ModelVO>();

		try {
			connect();
			String sql = "select * from jdbc_member";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			// 5. 값을 출력
			while (rs.next()) {
				String id = rs.getString(1); // 컬럼의 순서(1부터 시작) -> (resultset 상에 순서) 지정
				String pw = rs.getString("pw"); // 컬럼 이름(String) 지정
				String nick = rs.getString(3);
			
				ModelVO vo = new ModelVO(id, pw, nick);
				al.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return al;
	}

	// 5.특정회원정보
	public ModelVO selectOne(String id) {
		ModelVO vo = null;

		try {
			connect();

			String sql = "select * from jdbc_member where id = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next()) {

				String getid = rs.getNString(1);
				String pw = rs.getString("pw");
				String nick = rs.getString(3);

				vo = new ModelVO(getid, pw, nick);
			} else {
				System.out.println("찾는 아이디가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}
}
