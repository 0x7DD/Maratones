import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Number {
	
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
	
	static long gcd(long a, long b){
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
	
	static long[] pows = new long[9];
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		pows[0] = 1;
		for(int i = 1; i < 9; i++)
			pows[i] = 10 * pows[i - 1];
		double n = sc.nextDouble();
		long entero = (long)Math.round(n * 10000);
		boolean found = false;
		for(int nn = 1; nn <= 8; nn++)
			for(int e = 1; e <= 9; e++){
				long numerador = (e * pows[nn] - e) * pows[4];
				long denominador = pows[5] - entero;
				if (denominador == 0 || numerador % denominador != 0)
					continue;
				long x = numerador / denominador;
				long k = (entero * x) / pows[4];
				if ((x+"").length() != nn || (k+"").length() != nn)
					continue;
				found = true;
				System.out.println(x);
			}
		if (!found)
			System.out.println("No solution");
	}

}
