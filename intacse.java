import java.util.Scanner;

public class ���������������� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] array = new int[5];
		int temp = 0;
		for(int i=0; i<array.length; i++) {
			System.out.print((i+1)+"��° �� �Է� : ");
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
		System.out.println("���� ��  ");	
		for(int i =0; i<array.length; i++) {
			System.out.print(array[i]+" ");
		}
	
	
	
	}

}
