import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ex04SelectAll {

	public static void main(String[] args) {
		
		// ���� ���� �Ǿ� �ִ� ��� ȸ�� ���� ���(�ܼ�)
		// JDBC_member ���̺� ��� ���� ��� -> ���� x
		
		
//		-- DML - INSERT,DELETE,UPDATE -> COMMIT �����ݿ�
//		-- DCL - CREATE,DROP -> �����ϸ� �ڵ����� COMMIT
//		-- ECLIPSE -> AUTO COMMIT  >> COMMIT ���ص���
		
		
		//��������ȭ
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = null; // java.sql �ҷ�����
		
		// try~catch : ���� ó�� (��Ÿ�ӿ���)
		try {
			// ������ �ڵ�
			// 1. ����̹� �ε� -> ClassNotFoundException
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";
			
			// 2. ������ ���̽� ���� (Connection)��ü ����
			conn = DriverManager.getConnection(url,dbid,dbpw); // sqlexception -> connx
			
			String sql = "select * from JDBC_member";
			
			// 3. sql ����/�غ�(PreparedStatement) ��ü ����
			psmt = conn.prepareStatement(sql);
			
			// executeUpdate() : insert, delete, update -> ���̺� ��ȭ O
			// -> ��ȯ�� (INT) -> ��� �࿡�� ��ȭ�� �Ͼ����
			// executeQuery() : select -> ���̺� ��ȭ x 
			// -> ��ȯ�� (ResultSet(Object)) -> select�� ����� ����� + Ŀ��
			// next() : Ŀ���� ��ĭ ������(���� ���� ����Ŵ)
			// -> Ŀ���� ����Ű�� �࿡ ���� �ִ��� ������ �Ǵ� -> ������ true��ȯ ������ false��ȯ
			
			//4. sql �� ����
			rs = psmt.executeQuery(); 
			
			// 5. ���� ���
		
			while(rs.next()) { 
				// ������ �÷��� �о����
				String id = rs.getString(1); // �÷��� ����(1���� ����) ����
				String pw = rs.getString("PW"); // �÷� �̸� (String) ����  / 2���ص� ����
				String nick = rs.getString(3);
				
				//int a = rs.getInt(1) -> �������� ������ �ö�
				
				System.out.println("id :" + id);
				System.out.println("pw : " + pw);
				System.out.println("nick : " + nick);
				System.out.println("====================");
			
			}
			
		} catch(Exception e) {
			// try �� �ȿ��� �߻��� ���� ��Ȳ�� ���� ó��
			e.printStackTrace();
		} finally {
			// try �ȿ��� ���ܻ�Ȳ�� �߻��ϴ��� �߻����� �ʴ��� ������ �����ϴ� ����
			// 6. ����� ��ü �ݱ� (�ڿ���ȯ)
			try {
			rs.close();  // ��ü ������ ���� ���� ��� -> ���ʿ��� ���ܻ�Ȳ �߻� -> close() ȣ��x -> ��Ÿ�� ����
			psmt.close();
			conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
