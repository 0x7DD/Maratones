import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	static class Node{
		int id;
		LinkedList<Integer> v;
		long bestsum;
		long alternatives;
		public Node(int idd){
			id = idd;
			v = new LinkedList<Integer>();
			bestsum = 0;
			alternatives = 0;
		}
	}
	
	static int N,E;
	static Node[] array;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		N = sc.nextInt();
		E = sc.nextInt();
		array = new Node[N];
		for(int i = 0; i < N; i++)
			array[i] = new Node(i);
		for(int i = 0; i < E; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			a--;b--;
			array[a].v.add(b);
		}
		// calculating clearings alternatives
		array[N - 1].alternatives = 1L;
		for(int i = N - 2; i >= 0; i--){
			long t = 0;
			for(int e: array[i].v)
				t += array[e].alternatives;
			array[i].alternatives = t;
		}
		/*
		for(int i = 0; i < N; i++)
			System.out.print(array[i].alternatives+" ");*/
		array[N - 1].bestsum = 1L;
		for(int i = N - 2; i >= 0; i--){
			long t = 0;
			for(int e: array[i].v)
				t = Math.max(t, array[e].bestsum);
			if (t > 0)
				array[i].bestsum = t + array[i].alternatives;
			else
				array[i].bestsum = 0;
		}
		System.out.println(array[0].bestsum);
	}

}
