import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class F {
	
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
	
	static char[][] m;
	static boolean[][] vivos;
	
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,1,-1};
	
	
	static int solve(int x, int y){
		int ret = 0;
		for(int i = 0; i < 4; i++){
			int mx = x + dx[i];
			int my = y + dy[i];
			if (mx < 0 || mx >= 10 || my < 0 || my >= 10 || m[mx][my] != 'B' || !vivos[mx][my])
				continue;
			mx = x + 2 * dx[i];
			my = y + 2 * dy[i];
			if (mx < 0 || mx >= 10 || my < 0 || my >= 10)
				continue;
			if (m[mx][my] == 'W')
				continue;
			if (m[mx][my] == 'B' && vivos[mx][my])
				continue;
			vivos[x + dx[i]][y + dy[i]] = false;
			ret = Math.max(ret, 1 + solve(mx,my));
			vivos[x + dx[i]][y + dy[i]] = true;
		}
		return ret;
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		int T = sc.nextInt();
		for(int c = 0; c < T; c++){
			sc.rd.readLine();
			m = new char[10][];
			for(int i = 0; i < 10; i++)
				m[i] = sc.rd.readLine().trim().toCharArray();
			vivos = new boolean[10][10];
			for(int i = 0; i < 10; i++)
				Arrays.fill(vivos[i], true);
			int ans = 0;
			for(int i = 0; i < 10; i++)
				for(int j = 0; j < 10; j++)
					if (m[i][j] == 'W'){
						m[i][j] = '.';
						ans = Math.max(ans, solve(i,j));
						m[i][j] = 'W';
					}
			System.out.println(ans);
		}
	}
	
	

}
