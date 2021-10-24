import java.util.Scanner;

public class 입력인덱스별출력 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[] array = new int[num];
		
		for(int i=0; i<array.length; i++) {
			System.out.print(i+"번째 별의수 : ");
			 array[i] = sc.nextInt(); 
		}
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i]+" : ");
			for(int j=0; j<array[i]; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	
	
	
	}
}
