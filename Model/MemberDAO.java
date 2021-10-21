package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
	// DAO : Data Access Object
	// CRUD : insert,update,delete,select �۾� ����
	// database�� ����
	// �������(����� view���� ȣ���ؼ� ���)
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// �����ͺ��̽� ���� �޼���
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Class���� Ȯ��");

			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";
			conn = DriverManager.getConnection(url, dbid, dbpw);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ��ü �ݴ� �޼ҵ�(����� �ڿ� ��ȯ)
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			psmt.close();
			conn.close();
			// ����� ��ü �ݾ��ְ� �ڿ��� ��ȯ���ش�.
		} catch (Exception e2) {
			e2.getStackTrace();
		}
	}


	// 1.ȸ������
	public int insert(ModelVO vo) {
		// �������� vo�� �ƴϿ��� ok
		int cnt = 0;

		try {
			connect();

			if (conn != null) {
				System.out.println("���Ἲ��");
			} else {
				System.out.println("�������");
			}

			String sql = "insert into JDBC_member values(?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			psmt.setString(3, vo.getNick());

			cnt = psmt.executeUpdate();

		} catch (Exception e) {

			System.out.println("Class���� Ȯ�� ����");
		} finally {
			close();

			return cnt;
		}

	}

	// 2.ȸ����������
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

	// 3.ȸ������
	public void delete(String id) {

		try {
			connect();

			String sql = "delete from JDBC_member where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			int cnt = psmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("��������");
			} else {
				System.out.println("��������");
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

			// 5. ���� ���
			while (rs.next()) {
				String id = rs.getString(1); // �÷��� ����(1���� ����) -> (resultset �� ����) ����
				String pw = rs.getString("pw"); // �÷� �̸�(String) ����
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

	// 5.Ư��ȸ������
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
				System.out.println("ã�� ���̵� �����ϴ�.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}
}
