import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class ShoppingMalls {
	
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
	
	static class Edge{
		int e;
		double w;
		public Edge(int ee, double ww){
			e = ee;
			w = ww;
		}
	}
	
	static class Node implements Comparable<Node>{
		int x,y,z;
		LinkedList<Edge> v;
		double dist;
		boolean visited;
		int parent;
		int id;
		public Node(int xx, int yy, int zz, int code){
			x = xx;
			y = yy;
			z = zz;
			v = new LinkedList<Edge>();
			dist = 0;
			visited = false;
			parent = -1;
			id = code;
		}
		double euclidean_distance(Node o){
			double a = 5*(x - o.x);
			double b = (y - o.y);
			double c = (z - o.z);
			return Math.sqrt(a * a + b * b + c * c);
		}
		@Override
		public int compareTo(Node o) {
			if (Math.abs(dist - o.dist) < eps)
				return id - o.id;
			return Double.compare(dist, o.dist);
		}
	}
	
	
	
	static void Dijkstra(int source, int target){
		for(int i = 0; i < N; i++){
			array[i].dist = Double.MAX_VALUE;
			array[i].visited = false;
			array[i].parent = -1;
		}
		array[source].dist = 0;
		TreeSet<Node> pq = new TreeSet<Node>();
		pq.add(array[source]);
		while(!pq.isEmpty()){
			Node c = pq.pollFirst();
			c.visited = true;
			if (c.id == target){
				Stack<Integer> st = new Stack<Integer>();
				int current = target;
				while(current != -1){
					st.push(current);
					current = array[current].parent;
				}
				System.out.print(st.pop());
				while(!st.isEmpty())
					System.out.print(" " + st.pop());
				System.out.println();
				return;
			}
			for(Edge e: c.v){
				Node hijo = array[e.e];
				if (hijo.visited)
					continue;
				if (hijo.dist > c.dist + e.w){
					pq.remove(hijo);
					hijo.parent = c.id;
					hijo.dist = c.dist + e.w;
					pq.add(hijo);
				}
			}
		}
	}
	
	static double eps = 1e-9;
	static int N, M;
	static Node[] array;
	
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner();
		boolean primero = true;
		while(true){
			String cad = sc.next();
			if (cad == null)
				break;
			N = Integer.valueOf(cad);
			M = sc.nextInt();
			array = new Node[N];
			if (!primero)
				System.out.println();
			primero = false;
			for(int i = 0; i < N; i++)
				array[i] = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt(), i);
			for(int i = 0; i < M; i++){
				int a = sc.nextInt();
				int b = sc.nextInt();
				String t = sc.next();
				double dist = array[a].euclidean_distance(array[b]);
				if (t.equals("walking") || t.equals("stairs")){
					array[a].v.add(new Edge(b, dist));
					array[b].v.add(new Edge(a, dist));
				}
				else if (t.equals("lift")){
					array[a].v.add(new Edge(b, 1.0));
					array[b].v.add(new Edge(a, 1.0));
				}
				else{
					array[a].v.add(new Edge(b, 1.0));
					array[b].v.add(new Edge(a, 3 * dist));
				}
			}
			int Q = sc.nextInt();
			for(int q = 0; q < Q; q++){
				int a = sc.nextInt();
				int b = sc.nextInt();
				Dijkstra(a, b);
			}
		}
	}

}
