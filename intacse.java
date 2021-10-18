import java.util.Scanner;

public class 정수오름차순정렬 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] array = new int[5];
		int temp = 0;
		for(int i=0; i<array.length; i++) {
			System.out.print((i+1)+"번째 수 입력 : ");
			array[i] = sc.nextInt();
		}
		
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<i; j++) {
				if(array[i]>array[j]) {
					 temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		System.out.println("정렬 후  ");	
		for(int i =0; i<array.length; i++) {
			System.out.print(array[i]+" ");
		}
	
	
	
	}

}
