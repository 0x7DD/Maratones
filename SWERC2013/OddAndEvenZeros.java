import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.StringTokenizer;


public class OddAndEvenZeros {
	
	static class Scanner{
		BufferedReader rd;
		StringTokenizer tk;
		public Scanner(){
			rd = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() throws IOException{
			while(tk == null || !tk.hasMoreTokens()){
				String cad = rd.readLine();
				if (cad == null)
					return null;
				tk = new StringTokenizer(cad);
			}
			return tk.nextToken();
		}
		int nextInt() throws NumberFormatException, IOException{
			return Integer.valueOf(next());
		}
		long nextLong() throws NumberFormatException, IOException{
			return Long.valueOf(next());
		}
	}
	
	
	static Long[][] dp;
	
	static long dp(int pow, int begins) {
		if (dp[pow][begins] != null)
			return dp[pow][begins];
		if (0 == pow)
			return dp[pow][begins] = (begins ^ 1L);
		
		if (0 == (pow % 2))
			return dp[pow][begins] = 3L * dp(pow - 1, begins) + 2L * dp(pow -1, begins ^ 1);
		
		return dp[pow][begins] = 5 * dp(pow -1, begins);
	}
	
//	public static void main(String args[]){
//		int[] pows = {5,25,125,625,625*5, 625*25};
//		HashSet<Integer> p = new HashSet<Integer>();
//		for(int i = 0; i < pows.length; i++) p.add(pows[i]);
//		int N = 101;
//		int paridad = 0;
//		int ans = 0;
//		int ant = -1;
//		dp = new Long[10][2];
//		for(int i = 0; i < N; i++){
//			int times = 0;
//			int actual = i;
//			while(actual > 0 && actual % 5 == 0){
//				times++;
//				actual /= 5;
//			}
//			paridad = (paridad + times) % 2;
//			if (paridad  == 0)
//				ans++;
//			if (p.contains(i))
//				System.out.println(i+" "+ant+" "+dp(times, 0));
//			ant = ans;
//		}
//	}
	
	
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner();
		while(true){
			long nn = sc.nextLong();
			if (nn < 0)
				return;
			nn++;
			BigInteger N = new BigInteger(nn + "");
			char[] b = N.toString(5).toCharArray();

			long ans = 0;
			dp = new Long[b.length + 5][2];
			
//			for(char c: b)
//				System.out.print(c);
//			System.out.println();
			
			int begins  = 0;
			int power = b.length - 1;
			for(int i = 0; i < b.length ; i++){
				int dig = (b[i] - '0');
				if (0 == dig) {
					power--;
					continue;
				}
				
				if (power % 2 == 1) {
					int op1 = (dig + 1)>>1;
					int op2 = dig - op1; 
					ans += op1 * dp(power, begins) + op2 * dp(power, begins ^ 1); 
				} else {
					ans += dig * dp(power, begins);
				}
				
				if ((power % 2 != 0) && (dig % 2 != 0))
					begins = begins ^ 1;
				power--;
			}
			System.out.println(ans);
		}
	}

}
