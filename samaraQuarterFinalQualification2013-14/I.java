import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class I {
	
	
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
	
	
	static int N;
	static int[] t, d;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		N = sc.nextInt();
		t = new int[N];
		d = new int[N];
		for(int i = 0; i < N; i++){
			t[i] = sc.nextInt();
			d[i] = sc.nextInt();
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int life = 0;
		int shots = 0;
		int ant = 0;
		for(int i = 0; i < N; i++){
			life += t[i] - ant;
			ant = t[i];
			pq.add(-d[i]);
			life -= d[i];
			while (life < 0){
				life += pq.poll() * -1;
				shots++;
			}
		}
		System.out.println(shots);
	}

}
