package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.MemberDAO;
import Model.ModelVO;

public class MemberView {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("== ȸ������ ���α׷� ==");
		MemberDAO dao = new MemberDAO();
		
		while(true) {
			System.out.println("[1]ȸ������ [2]ȸ���������� [3]ȸ������ [4]��üȸ������ [5]Ư��ȸ������ [6]����");
			System.out.print("�޴� ���� >> ");
			int menu = sc.nextInt();
			if(menu==1) {
				System.out.println("��ȸ�����ԡ�");
				System.out.print("ID : ");
				String id = sc.next();
				System.out.print("PW : ");
				String pw = sc.next();
				System.out.print("Nick : ");
				String nick = sc.next();
							
				ModelVO vo =new ModelVO(id,pw,nick);
				//ȸ������ ��ü ��������
				int cnt = dao.insert(vo);
				
				//6. ��� �� ó��
				if(cnt>0) {
					System.out.println("�Է¼���");
				}else {
					System.out.println("�Է½���");
				}
				
			}else if(menu==2) {
				String pw = "";
				String nick = "";
				System.out.println("ȸ�� ���� ���� ����");
				System.out.print("ID : ");
				String id = sc.next();
				System.out.println("[1]PW [2]NICK");
				int choice = sc.nextInt();
				System.out.println("���� �� ���� ");
				if (choice == 1) {
					System.out.print("���� �� PW : ");
					pw = sc.next();
				} else if (choice == 2) {
					System.out.print("���� �� NickName : ");
					nick = sc.next();
				}
				

				int cnt = dao.update(id,choice,nick);

			}else if(menu==3) {
				System.out.println("ȸ������ ����");
				System.out.print("ID : ");
				String id = sc.next();

				dao.delete(id);
				
			}else if(menu==4) {

				ArrayList<ModelVO> al = dao.selectAll();
				
				//for~each
				//�迭, ArrayList�ȿ� �ִ� ���� �ٷ� �� ȿ�������� �����.
				//for(�迭,ArrayList�� ����ִ� ��Ҹ� �ӽ������� ����� �̸� : 
				//�迭, ArrayList�� �̸�(���۷���������))
				for(ModelVO vo : al) {
					System.out.println("ID : "+vo.getId());
					System.out.println("PW : "+vo.getPw());
					System.out.println("NICK : "+vo.getNick());
					System.out.println("====================");
				}
			}else if(menu==5) {
				System.out.println("== Ư�� ȸ������ ã�� ==");
				System.out.print("ã�� ���̵� :");
				String id = sc.next();
				
				ModelVO vo = dao.selectOne(id);
				System.out.println("ID : " + vo.getId());
				System.out.println("PW : "+ vo.getPw());
				System.out.println("NICK : "+ vo.getNick());
			}else {
				System.out.println("���α׷� ����!");
				break;
			}
		}
	}
}
