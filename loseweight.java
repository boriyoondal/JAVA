import java.util.Scanner;

public class 몸무게감량 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("현재몸무게 : ");
		int nowkg = sc.nextInt();
		System.out.print("목표몸무게 : ");
		int wantkg= sc.nextInt();
		
		int looskg=0;
		int week =1;
		do {
			System.out.print(week+"주차 감량 몸무게 : ");
			looskg = sc.nextInt();
			nowkg = nowkg-looskg;
			week++;
			
		}
		while(nowkg>wantkg);
			System.out.println(nowkg+"kg 감량달성!!추카합니다.");
		
			
		
	
	
	
	
	
	
	
	
	
	
	
	
	}
}
