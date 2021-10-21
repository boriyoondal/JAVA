package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.MemberDAO;
import Model.ModelVO;

public class MemberView {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("== 회원관리 프로그램 ==");
		MemberDAO dao = new MemberDAO();
		
		while(true) {
			System.out.println("[1]회원가입 [2]회원정보수정 [3]회원삭제 [4]전체회원정보 [5]특정회원정보 [6]종료");
			System.out.print("메뉴 선택 >> ");
			int menu = sc.nextInt();
			if(menu==1) {
				System.out.println("◎회원가입◎");
				System.out.print("ID : ");
				String id = sc.next();
				System.out.print("PW : ");
				String pw = sc.next();
				System.out.print("Nick : ");
				String nick = sc.next();
							
				ModelVO vo =new ModelVO(id,pw,nick);
				//회원가입 객체 가져오기
				int cnt = dao.insert(vo);
				
				//6. 명령 후 처리
				if(cnt>0) {
					System.out.println("입력성공");
				}else {
					System.out.println("입력실패");
				}
				
			}else if(menu==2) {
				String pw = "";
				String nick = "";
				System.out.println("회원 정보 수정 서비스");
				System.out.print("ID : ");
				String id = sc.next();
				System.out.println("[1]PW [2]NICK");
				int choice = sc.nextInt();
				System.out.println("수정 할 정보 ");
				if (choice == 1) {
					System.out.print("수정 할 PW : ");
					pw = sc.next();
				} else if (choice == 2) {
					System.out.print("수정 할 NickName : ");
					nick = sc.next();
				}
				

				int cnt = dao.update(id,choice,nick);

			}else if(menu==3) {
				System.out.println("회원삭제 서비스");
				System.out.print("ID : ");
				String id = sc.next();

				dao.delete(id);
				
			}else if(menu==4) {

				ArrayList<ModelVO> al = dao.selectAll();
				
				//for~each
				//배열, ArrayList안에 있는 값을 다룰 때 효율적으로 사용함.
				//for(배열,ArrayList에 들어있는 요소를 임시적으로 사용할 이름 : 
				//배열, ArrayList의 이름(레퍼런스변수명))
				for(ModelVO vo : al) {
					System.out.println("ID : "+vo.getId());
					System.out.println("PW : "+vo.getPw());
					System.out.println("NICK : "+vo.getNick());
					System.out.println("====================");
				}
			}else if(menu==5) {
				System.out.println("== 특정 회원정보 찾기 ==");
				System.out.print("찾을 아이디 :");
				String id = sc.next();
				
				ModelVO vo = dao.selectOne(id);
				System.out.println("ID : " + vo.getId());
				System.out.println("PW : "+ vo.getPw());
				System.out.println("NICK : "+ vo.getNick());
			}else {
				System.out.println("프로그램 종료!");
				break;
			}
		}
	}
}
