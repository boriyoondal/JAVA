import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ex01Insert {

	public static void main(String[] args) {
		
		System.out.println("ȸ�����Լ���");
		Scanner sc = new Scanner(System.in);
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("PW : ");
		String pw = sc.next();
		System.out.print("NICK : ");
		String nick = sc.next();
		
		//JDBC(Java DataBase Connectivity)
		//Java�� DataBase�� �������ִ� API(Interface)
		
		//�������
		//1. oracle���� �����ϴ� Java�� ������ �� �ִ� �ܺ� .jar�����;���
		
		//2. ojdbc6.jar���� �����ϴ� ����� ����ϴ� ����̹�.class���� �����ε�
		
		Connection conn = null;
		PreparedStatement psmt = null;
		//Java���� �߻��ϴ� ���� 2����
		//1. �����Ͽ��� : �������ο���
		//2. ��Ÿ�ӿ��� : �����ؾ����� �� �� �ִ� ����(DB, ��Ʈ��ũ)
		//����ó���� try~catch��
		try {
			//�������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Class����Ȯ��");
			
			//3.oracle�� ���� DB���̵�� PW�� ����(oracle�ּ�, id, pw)
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // �� ip = ��(��)�ּ� : �� port(���ȣ) > ���ڷ� �ٲٸ� ������
			String dbid = "hr";
			String dbpw = "hr";
			//Connection��ü ���� : Java���� oracle�� �ڵ鸵 �� �� �ִ� ��ü 
			conn = DriverManager.getConnection(url, dbid, dbpw);
			
			if(conn!=null) {
				System.out.println("���Ἲ��");
			}else {
				System.out.println("�������");
			}
			
			//4. oracle�� ����� SQL�� �غ� 
			String sql = "insert into JDBC_member values(?, ?, ?)"; // ����Ŭ�𺧷��ۿ��� ������
			psmt = conn.prepareStatement(sql); // �Է��� sql���� ��Ÿ���ִ��� Ȯ��
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, nick);
			
			//5 SQL��� ����
			int cnt = psmt.executeUpdate();
			
			//6. ��� �� ó��
			if(cnt>0) {
				System.out.println("�Է¼���");
			}else {
				System.out.println("�Է½���");
			}
			
			
			
		} catch (Exception e) {
			      //�����߻�//
			e.printStackTrace();//� ������ �߻��ߴ��� ��� *�߿�*
			System.out.println("Class����Ȯ�ν���");
		} finally {
			//���������� ����Ǵ� ������ ���� ���������� ����Ǵ� �κ�
			//7. ��������
			try {
				psmt.close();
				conn.close();				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		
		
		

	}

}





