import java.util.Scanner;

public class �����԰��� {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("��������� : ");
		int nowkg = sc.nextInt();
		System.out.print("��ǥ������ : ");
		int wantkg= sc.nextInt();
		
		int looskg=0;
		int week =1;
		do {
			System.out.print(week+"���� ���� ������ : ");
			looskg = sc.nextInt();
			nowkg = nowkg-looskg;
			week++;
			
		}
		while(nowkg>wantkg);
			System.out.println(nowkg+"kg �����޼�!!��ī�մϴ�.");
		
			
		
	
	
	
	
	
	
	
	
	
	
	
	
	}
}
