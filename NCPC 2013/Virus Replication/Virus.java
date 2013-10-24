import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Virus {
	
	static class Scanner{
		BufferedReader br=null;
		StringTokenizer tk=null;
		public Scanner(){
			br=new BufferedReader(new InputStreamReader(System.in));
		}
		public String next() throws IOException{
			while(tk==null || !tk.hasMoreTokens())
				tk=new StringTokenizer(br.readLine());
			return tk.nextToken();
		}
		public int nextInt() throws NumberFormatException, IOException{
			return Integer.valueOf(next());
		}
		public double nextDouble() throws NumberFormatException, IOException{
			return Double.valueOf(next());
		}
	}
	
	public static void  main(String args[]) throws IOException{
		Scanner sc = new Scanner();
		char[] a = sc.next().toCharArray();
		char[] b = sc.next().toCharArray();
		int limitizq = 0;
		while(limitizq < Math.min(a.length, b.length) && a[limitizq] == b[limitizq])
			limitizq++;
		int limitder = 0;
		while(limitder < Math.min(a.length, b.length) && a[a.length - 1 - limitder] == b[b.length - 1 - limitder])
			limitder++;
		limitizq--;limitder--;
		int left = limitizq;
		int right = b.length - 1 - limitder;
		int amount = 0;
		if (right > left)
			amount = right - left - 1;
		if (amount == 0){
			if (b.length > a.length)
				System.out.println(b.length - a.length);
			else
				System.out.println(0);
		}
		else
			System.out.println(amount);
	}

}
