import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class LogoTurtle {
	
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
	
	
	static Integer[][][][] dp;
	
	static int dp(int pos, int quedan, int dir, int idx){
		int npos = pos + 100;
		if (dp[npos][quedan][dir][idx] != null)
			return dp[npos][quedan][dir][idx];
		if (idx == commands.length){
			dp[npos][quedan][dir][idx] = (quedan % 2 == 0)? Math.abs(pos) : 0;
			return dp[npos][quedan][dir][idx];
		}
		int ret = 0;
		// no change
		if (commands[idx] == 'T')
			ret = dp(pos, quedan, (dir + 1) % 2, idx + 1);
		else{
			int np = ((dir == 0)? pos + 1: pos - 1);
			ret = dp(np, quedan, dir, idx + 1);
		}
		if (quedan == 0){
			dp[npos][quedan][dir][idx] = ret;
			return ret;
		}
		// change
		if (commands[idx] == 'F')
			ret = Math.max(ret, dp(pos, quedan - 1, (dir + 1) % 2, idx + 1));
		else{
			int np = ((dir == 0)? pos + 1: pos - 1);
			ret = Math.max(ret, dp(np, quedan - 1, dir, idx + 1));
		}
		dp[npos][quedan][dir][idx] = ret;
		return ret;
	}
	
	static char[] commands;
	static int N;
	
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner();
		commands = sc.next().toCharArray();
		N = sc.nextInt();
		dp = new Integer[205][N + 1][2][commands.length + 1];
		System.out.println(dp(0, N, 0, 0));
	}

}
