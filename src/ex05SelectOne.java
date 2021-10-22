import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ex05SelectOne {

	public static void main(String[] args) {
		// Ư�� ȸ���� id�Է� -> �ش� id �� ���� ȸ���� ������ ���
		// id�� ��ġ�� �ʴ´�
		Scanner sc = new Scanner(System.in);
		System.out.println("==Ư�� ȸ�� ���� ���==");
		System.out.println("���̵��Է� : ");
		String id = sc.next();
		
		
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		// try~catch : ���� ó�� (��Ÿ�ӿ���)
		try {
			// ������ �ڵ�
			// 1. ����̹� �ε� -> ClassNotFoundException
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";
			
			// 2. ������ ���̽� ���� (Connection)��ü ����
			conn = DriverManager.getConnection(url,dbid,dbpw); // sqlexception -> conn x
			
			String sql = "select * from JDBC_member where id =?";
			
			// 3. sql ����/�غ�(PreparedStatement) ��ü ����
			psmt = conn.prepareStatement(sql);
			
			// 4. ? �ڸ� ä���
			psmt.setString(1, id); // index ��  1���� ����
			
			// executeUpdate() : insert, delete, update -> ���̺� ��ȭ O
			// -> ��ȯ�� (INT) -> ��� �࿡�� ��ȭ�� �Ͼ����
			// executeQuery() : select -> ���̺� ��ȭ x 
			// -> ��ȯ�� (ResultSet(Object)) -> select�� ����� ����� + Ŀ��
			// next() : Ŀ���� ��ĭ ������(���� ���� ����Ŵ)
			// -> Ŀ���� ����Ű�� �࿡ ���� �ִ��� ������ �Ǵ� -> ������ true��ȯ ������ false��ȯ
			
			//5. sql �� ����
			rs = psmt.executeQuery();
			
			// 6. ���� ���
			if(rs.next()) {
				// ������ �÷��� �о����
				String getid = rs.getString(1); // �÷��� ����(1���� ����) ����
				String pw = rs.getString("PW"); // �÷� �̸� (String) ����
				String nick = rs.getString(3);
				
				//int a = rs.getInt(1) -> �������� ������ �ö�
				
				System.out.println("id :" + getid);
				System.out.println("pw : " + pw);
				System.out.println("nick : " + nick);
				System.out.println("====================");
			} else {
				System.out.println("�ش� ȸ���� �������� �ʽ��ϴ�!");
			}
			
		} catch(Exception e) {
			// try �� �ȿ��� �߻��� ���� ��Ȳ�� ���� ó��
			e.printStackTrace();
		} finally {
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
