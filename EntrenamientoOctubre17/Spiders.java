import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Spiders {
	
	static class Scanner{
		BufferedReader br=null;
		StringTokenizer tk=null;
		public Scanner() throws FileNotFoundException{
			br=new BufferedReader(new FileReader("input.txt"));
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
	
	static class Node{
		int id;
		boolean visited;
		LinkedList<Integer> v;
		public Node(int idd){
			id = idd;
			v = new LinkedList<Integer>();
			visited = false;
		}
	}
	
	static int S;
	
	
	static class Pair{
		int u,v;
		public Pair(int uu, int vv){
			u = uu;
			v = vv;
		}
	}
	
	static Pair dfs(int inicio,Node[] array){
		if (array[inicio].visited)
			return null;
		array[inicio].visited = true;
		Pair ret = new Pair(0, inicio);
		for(int v: array[inicio].v){
			Pair sig = dfs(v, array);
			if (sig == null)
				continue;
			sig.u++;
			if (ret.u < sig.u)
				ret = sig;
		}
		return ret;
	}
	
	static int solve(int start,Node[] array){
		Pair p = dfs(start, array);
		for(int i = 0; i < array.length; i++)
			array[i].visited = false;
		p = dfs(p.v, array);
		return p.u;
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		S = sc.nextInt();
		int sum = 0;
		for(int i = 0; i < S; i++){
			int Nodos = sc.nextInt();
			Node[] array = new Node[Nodos];
			for(int j = 0; j < Nodos; j++)
				array[j] = new Node(j);
			for(int j = 0; j < Nodos - 1; j++){
				int a = sc.nextInt();
				int b = sc.nextInt();
				a--;b--;
				array[a].v.add(b);
				array[b].v.add(a);
			}
			int tam = solve(0, array);
			sum += tam;
		}
		bw.write(sum+"");
		bw.flush();
	}

}
