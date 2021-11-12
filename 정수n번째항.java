import java.util.Scanner;

public class 정수n번째항 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("n 입력: ");
		int intg = sc.nextInt();
		int sum=1;
		
		for(int i=0; i<intg; i++) {
			sum= sum+i;
			System.out.print(sum+" ");
		}
		
	
	}
}
