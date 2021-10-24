import java.util.Scanner;

public class 여덟자리정수입력합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("정수 입력 : ");
		String num = sc.next();
		int[] array = new int[num.length()];
		
		for(int i=0; i<num.length(); i++) {
			array[i] = num.charAt(i)-'0';
			
		}
		int sum = 0;
		for(int i=0; i<num.length(); i++) {
			sum+=array[i];
		}
		System.out.println(sum);
		
		
		

	
	
	}
}
