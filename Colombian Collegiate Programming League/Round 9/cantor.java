import java.util.Scanner;


public class cantor {
	
	
	
	static String[] array = new String[13];

	
	static void gen(){
		array[0] = "-";
		for(int i = 1; i < 13; i++){
			StringBuilder sb = new StringBuilder();
			sb.append(array[i - 1]);
			for(int j = 0; j < array[i - 1].length(); j++)
				sb.append(" ");
			sb.append(array[i - 1]);
			array[i] = sb.toString();
		}
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		gen();
		while(sc.hasNext())
			System.out.println(array[sc.nextInt()]);
	}

}
