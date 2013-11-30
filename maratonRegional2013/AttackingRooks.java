import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class AttackingRooks {
	
	static class Edge {
	    int from, to, index;
	    long cap, flow;

	    Edge(int fromi, int toi, long capi, long flowi, int indexi) {
	        from = fromi; to = toi; cap = capi; flow = flowi; 
	        index = indexi;
	    }
	}

	static class PushRelabel {
	    int N; int ans; boolean[] active;
	    ArrayList <Edge> [] G; long[] excess; int[] dist, count;
	    ArrayDeque <Integer> Q = new ArrayDeque <Integer> ();

	    PushRelabel(int N1) {
	        N = N1; G = new ArrayList[N]; active = new boolean[N];
	        for(int i = 0; i < N; i++)
	            G[i] = new ArrayList <Edge> ();
	        excess = new long[N]; dist = new int[N]; 
	        count = new int[2 * N];
	    }
	    
	    void AddEdge(int from, int to, int cap) {
	        int cambio = from == to ? 1 : 0;
	        G[from].add(new Edge(from, to, cap, 0, G[to].size() + cambio));
	        G[to].add(new Edge(to, from, 0, 0, G[from].size() - 1));
	    }

	    void Enqueue(int v) { 
	        if (!active[v] && excess[v] > 0){active[v] = true; Q.add(v);} 
	    }

	    void Push(Edge e) {
	        long amt = Math.min(excess[e.from], e.cap - e.flow);
	        if(dist[e.from] <= dist[e.to] || amt == 0) return;
	        e.flow += amt; G[e.to].get(e.index).flow -= amt;
	        excess[e.to] += amt;  excess[e.from] -= amt; Enqueue(e.to);
	    }

	    void Gap(int k) {
	        for(int v = 0; v < N; v++)  {
	            if(dist[v] < k)  continue;
	            count[dist[v]]--; dist[v] = Math.max(dist[v], N + 1);
	            count[dist[v]]++; Enqueue(v);
	        }
	    }

	    void Relabel(int v) {
	        count[dist[v]]--; dist[v] = 2 * N;
	        for (Edge e : G[v]) 
	            if (e.cap - e.flow > 0) 
	                dist[v] = Math.min(dist[v], dist[e.to] + 1);
	        count[dist[v]]++; Enqueue(v);
	    }

	    void Discharge(int v) {
	        for(Edge e : G[v]) { if(excess[v] <= 0) break; Push(e);}
	        if(excess[v] > 0) 
	            {if(count[dist[v]] == 1) Gap(dist[v]); else Relabel(v);}
	    }

	    long GetMaxFlow(int s, int t) {
	        count[0] = N - 1; count[N] = 1; dist[s] = N; 
	        active[s] = active[t] = true;
	        for (Edge e : G[s]) { excess[s] += e.cap; Push(e); }
	        while (!Q.isEmpty()) { int v = Q.poll(); active[v] = false; 
	                               Discharge(v);}
	        long totflow = 0;
	        for (Edge e : G[s]) totflow += e.flow; return totflow;
	    }
	}
	
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
	
	static int N;
	static char[][] m;
	static int[][] rows, cols;
	
	
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner();
		while(true){
			String cad = sc.next();
			if (cad == null)
				return;
			N = Integer.valueOf(cad);
			m = new char[N][];
			for(int  i = 0; i < N; i++)
				m[i] = sc.next().toCharArray();
			rows = new int[N][N];
			cols = new int[N][N];
			for(int i = 0; i < N; i++){
				Arrays.fill(rows[i], 0);
				Arrays.fill(cols[i], 0);
			}
			int number = 0;
			boolean aumentar = true;
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					if (m[i][j] == 'X')
						aumentar = true;
					else{
						if (aumentar)
							number++;
						rows[i][j] = number;
						aumentar = false;
					}
				}
				aumentar = true;
			}
			int nodes_rows = number;
			aumentar = true;
			for(int j = 0; j < N; j++){
				for(int i = 0; i < N; i++){
					if (m[i][j] == 'X')
						aumentar = true;
					else{
						if (aumentar)
							number++;
						cols[i][j] = number;
						aumentar = false;
					}
				}
				aumentar = true;
			}
			int nodes_cols = number;
//			debug(rows);
//			debug(cols);
			int source = 0;
			int sink = number + 1;
			PushRelabel flow = new PushRelabel(sink + 1);
			for(int i = 0; i < N; i++)
				for(int j = 0; j< N; j++){
					if (m[i][j] == 'X')
						continue;
					flow.AddEdge(rows[i][j], cols[i][j], 1);
				}
			for(int i = 1; i <= nodes_rows; i++)
				flow.AddEdge(0, i, 1);
			for(int i = nodes_rows + 1; i < sink; i++)
				flow.AddEdge(i, sink, 1);
			System.out.println(flow.GetMaxFlow(source, sink));
		}
	}
	
	static void debug(int[][] m){
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if (m[i][j] == 0)
					System.out.print('#'+" ");
				else
					System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
	}

}
