
public class getMiddle {

	public static void main(String[] args) {
		
		String word ="test";
		String result = getMiddle(word);
		System.out.println(result);

	}

	private static String getMiddle(String word) {
		String answer = "";
		int spell = word.length()/2;
		
		if(word.length()/2==0) {
			
			answer = word.charAt(spell-1)+word.charAt(spell)+"";
		}else {
			answer = word.charAt(spell)+"";
			
		}
		return answer;
	}
	
}
