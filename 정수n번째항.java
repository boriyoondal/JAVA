import java.util.Scanner;

public class ����n��°�� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("n �Է�: ");
		int intg = sc.nextInt();
		int sum=1;
		
		for(int i=0; i<intg; i++) {
			sum= sum+i;
			System.out.print(sum+" ");
		}
		
	
	}
}
