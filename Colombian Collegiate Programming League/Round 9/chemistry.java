import java.util.Scanner;


public class chemistry {
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		boolean primero = true;
		double anterior= -1;
		while(true){
			String cad = sc.next();
			if (cad.equals("999"))
				break;
			if (primero){
				anterior = Double.valueOf(cad);
			}
			else{
				double actual = Double.valueOf(cad);
				String ans = String.format("%.2f", actual - anterior);
				ans = ans.replace(",", ".");
				System.out.println(ans);
				anterior = actual;
			}
			primero = false;
		}
		System.out.println("End of Output");
	}

}
