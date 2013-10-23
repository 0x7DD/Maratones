import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;


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
	
	
	static class Node implements Comparable<Node>{
		int idx;
		int c;
		public Node(int i, int co){
			idx = i;
			c = co;
		}
		@Override
		public int compareTo(Node o) {
			if (c == o.c)
				return idx - o.idx;
			return c - o.c;
		}
		public String toString(){
			return String.format("(%d, %d)", idx, c);
		}
	}
	
	static int N,F;
	static long A, B, C;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = sc.nextInt();
		F = sc.nextInt();
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		int[] colores = new int[N];
		TreeSet<Node> set = new TreeSet<Node>();
		long ant = A;
		Node tmp = new Node(0, (int)ant);
		set.add(tmp);
		colores[0] = (int)ant;
		for(int i = 1; i < N; i++){
			long ncolor = B * ant + C;
			ncolor = ncolor % F;
			tmp = new Node(i, (int) ncolor);
			set.add(tmp);
			colores[i] = (int)ncolor;
			ant = ncolor;
		}
		int q = sc.nextInt();
		for(int i = 0; i < q; i++){
			int I = sc.nextInt();
			int E = sc.nextInt();
			I--;
			Node delete = new Node(I, colores[I]);
			set.remove(delete);
			Node add = new Node(I, E);
			set.add(add);
			Node left = set.lower(add);
			Node right = set.higher(add);
			int ansl = -1;
			int ansr = -1;
			if (left != null && left.c == E)
				ansl = left.idx;
			if (right != null && right.c == E)
				ansr = right.idx;
			int primero = 0, segundo = 0;
			if (ansl == -1){
				Node izq = set.lower(new Node(150000, E));
				primero = N - izq.idx + I;
			}
			else
				primero = I - ansl;
			if (ansr == -1){
				Node der = set.higher(new Node(-1, E));
				segundo = N - I + der.idx;
			}
			else
				segundo = ansr - I;
			bw.write(primero+" "+segundo+"\n");
			colores[I] = E;
		}
		bw.flush();
	}

}
