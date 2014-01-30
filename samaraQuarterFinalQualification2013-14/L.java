import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class L {
	
	static class Scanner{
		BufferedReader rd;
		StringTokenizer tk;
		public Scanner(){
			rd = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() throws IOException{
			while(tk == null || !tk.hasMoreTokens()){
				tk = new StringTokenizer(rd.readLine());
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
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		int N = sc.nextInt();
		ArrayList<Integer> divs = new ArrayList<Integer>();
		for(int i = 2; ((i + 0L)*i) <= N; i++){
			if (N % i == 0){
				divs.add(i);
				int otro = N / i;
				if (otro != i)
					divs.add(otro);
			}
		}
		Collections.sort(divs);
		int[] memo = new int[divs.size()];
		int ans = N/2 + 1;
		for(int i = 0; i < divs.size(); i++){
			int actual = divs.get(i);
			memo[i] = (actual / 2 + 1);
			for(int j = 0; j < i; j++){
				int t = divs.get(j);
				if (actual % t != 0)
					continue;
				int aux = actual / t;
				memo[i] = Math.min(memo[i], memo[j] * (aux/2 + 1));
			}
			int tmp = N / actual;
			ans = Math.min(ans, memo[i] * (tmp/2 + 1));
		}
		System.out.println(ans);
	}

}
