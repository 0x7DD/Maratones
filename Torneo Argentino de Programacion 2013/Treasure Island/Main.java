import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
	
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
	
	
	
	static int R,C;
	static int[][] matrix;
	static boolean[][] visited;
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	
	static boolean test(int time){
		for(int i = 0; i < R; i++)
			Arrays.fill(visited[i], false);
		LinkedList<int[]> q = new LinkedList<int[]>();
		if (matrix[0][0] <= time)
			return false;
		visited[0][0] = true;
		int[] state = {0,0,time};
		q.add(state);
		while(!q.isEmpty()){
			int[] c = q.poll();
			if (c[0] == R - 1 && c[1] == C - 1)
				return true;
			for(int i = 0; i < 4; i++){
				int nx = c[0] + dx[i];
				int ny = c[1] + dy[i];
				if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny] || matrix[nx][ny] <= c[2] + 1)
					continue;
				visited[nx][ny] = true;
				int[] ns = {nx, ny, c[2] + 1};
				q.add(ns);
			}
		}
		return false;
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		R = sc.nextInt();
		C = sc.nextInt();
		matrix = new int[R][C];
		visited = new boolean[R][C];
		for(int i = 0; i < R; i++)
			for(int j = 0; j < C; j++)
				matrix[i][j] = sc.nextInt();
		int low = 0;
		int high = 1000010;
		if (!test(0)){
			System.out.println("-1");
			return;
		}
		int ans = -1;
		while(true){
			int mid = (low + high)/2;
			boolean tmp = test(mid);
			if (tmp && !test(mid + 1)){
				ans = mid;
				break;
			}
			if (tmp)
				low = mid + 1;
			else
				high = mid - 1;
		}
		System.out.println(ans);
	}

}
