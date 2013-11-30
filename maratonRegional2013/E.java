import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class E {
	
	static class Scanner{
		BufferedReader br=null;
		StringTokenizer tk=null;
		public Scanner(){
			br=new BufferedReader(new InputStreamReader(System.in));
		}
		public String next() throws IOException{
			while(tk==null || !tk.hasMoreTokens()){
				String cad = br.readLine();
				if (cad == null)
					return null;
				tk=new StringTokenizer(cad);
			}
			return tk.nextToken();
		}
		public int nextInt() throws NumberFormatException, IOException{
			return Integer.valueOf(next());
		}
		public double nextDouble() throws NumberFormatException, IOException{
			return Double.valueOf(next());
		}
	}
	
	static long[][] C;
	static int mod = 1000000000 + 7;
	static Long[][][][] dp;
	static int[] amount;
	
	static void init(){
		C = new long[60][60];
		for(int i = 0; i  < 60; i++)
			C[i][0] = 1;
		for(int i = 1; i < 60; i++)
			for(int k = 1; k <= i; k++)
				C[i][k] = (C[i - 1][k] + C[i - 1][k - 1]) % mod;
	}
	
	static long dp(int digito, int par, int impar, int suma){
		if (digito == 10)
			return (suma == 0)?1:0;
		if (dp[digito][par][impar][suma] != null)
			return dp[digito][par][impar][suma];
		long ret = 0;
		for(int i = 0; i <= Math.min(amount[digito], par); i++){
			int c = amount[digito] - i;
			if (c > impar)
				continue;
			long tmp = (C[par][i] * C[impar][c]) % mod;
			int nsuma = digito*(c - i);
			while(nsuma < 0)
				nsuma += 11;
			nsuma = (suma + nsuma) % 11;
			tmp = (tmp * dp(digito + 1, par - i, impar - c, nsuma)) % mod;
			ret = (ret + tmp) % mod;
		}
		dp[digito][par][impar][suma] = ret;
		return ret;
	}
	
	public static void main(String args[]) throws IOException{
		init();
		Scanner sc = new Scanner();
		while(true){
			String cad = sc.next();
			if (cad == null)
				break;
			char[] number = cad.toCharArray();
			amount = new int[10];
			Arrays.fill(amount, 0);
			for(int i = 0; i < number.length; i++)
				amount[number[i] - '0']++;
			long ret = 0;
			int total = number.length - 1;
			int pares = total / 2;
			int impares = total - (total / 2);
			for(int i = 1; i < 10; i++){
				if (amount[i] == 0)
					continue;
				int initsum = i;
				if (number.length % 2 == 0)
					initsum = 11 - i;
				amount[i]--;
				dp = new Long[10][55][55][11];
				ret = (ret + dp(0, pares, impares, initsum)) % mod;
				amount[i]++;
			}
			System.out.println(ret);
		}
	}

}
