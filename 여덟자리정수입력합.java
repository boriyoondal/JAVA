import java.util.Scanner;

public class �����ڸ������Է��� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("���� �Է� : ");
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
