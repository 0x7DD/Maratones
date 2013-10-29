import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Matrioshka {
	
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
	
	static boolean[][] IsOK;
	static int N;
	static int[] array;
	static int[] dp;
	static Integer[][] C;
	
	static void init(){
		boolean[] apariciones = new boolean[500];
		for(int i = 0; i < N; i++){
			Arrays.fill(apariciones, false);
			int idx = 0;
			for(int j = i ; j < N; j++){
				apariciones[array[j]] = true;
				while(idx < apariciones.length && apariciones[idx])
					idx++;
				if (idx == (j - i + 1))
					IsOK[i][j] = true;
			}
		}
		
//		for(int i = 0; i < N; i++){
//			for(int j = 0; j < N; j++)
//				System.out.print(IsOK[i][j]+" ");
//			System.out.println();
//		}
		
	}
	
	static int C(int a, int b){
		if (C[a][b] != null)
			return C[a][b];
		if (a == b - 1){
			C[a][b] = 0;
			return 0;
		}
		if (a == b - 2){
			C[a][b] = 1;
			return 1;
		}
		int[] tmp = new int[b - a];
		for(int i = a; i < b; i++)
			tmp[i - a] = array[i];
		Arrays.sort(tmp);
		int[] map = new int[500];
		for(int i = 0; i < tmp.length; i++)
			map[tmp[i]] = i;
		boolean[] ds = new boolean[b - a];
		Arrays.fill(ds, false);
		int Before = ds.length;
		int ret = Integer.MAX_VALUE;
		for(int i = a + 1; i < b; i++){
			if (ds[0] == false)
				Before = Math.min(Before, map[array[i - 1]]);
			ds[map[array[i - 1]]] = true;
			if(ds[0])
				while(ds[Before])Before++;
			int add = ds.length - Before;
			ret = Math.min(ret, C(a, i) + C(i, b) + add);
		}
		C[a][b] = ret;
		return ret;
	}
	
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner();
		while(true){
			String cad = sc.next();
			if (cad == null)
				break;
			N = Integer.valueOf(cad);
			array = new int[N];
			IsOK = new boolean[N][N];
			for(int i = 0; i < N; i++)
				Arrays.fill(IsOK[i], false);
			for(int i = 0; i < N; i++)
				array[i] = sc.nextInt() - 1;
			init();
			dp = new int[N + 1];
			dp[N] = 0;
			C = new Integer[N + 1][N + 1];
			for(int i = N - 1; i >= 0; i--){
				dp[i] = Integer.MAX_VALUE;
				for(int j = i + 1; j <= N; j++){
					if (!IsOK[i][j - 1])
						continue;
					if (dp[j] != Integer.MAX_VALUE)
						dp[i] = Math.min(dp[i], dp[j] + C(i,j));
				}
			}
			if (dp[0] == Integer.MAX_VALUE)
				System.out.println("impossible");
			else
				System.out.println(dp[0]);
		}
	}

}
