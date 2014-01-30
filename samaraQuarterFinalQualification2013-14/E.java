import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;


public class E {
	
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
	
	static class Node{
		int type;
		int id;
		LinkedList<Integer> hijos;
		int papa;
		int incoming_edges;
		public Node(int i, int t){
			id = i;
			type = t;
			hijos = new LinkedList<Integer>();
			papa = -1;
			incoming_edges = 0;
		}
	}
	
	static int aux(char c){
		if (c == '0')
			return 0;
		if (c == '+')
			return 1;
		return 2;
	}
	
	static void determine_rights(){
		if (array[0].type == 0)
			array[0].type = 2;
		LinkedList<Integer> cola = new LinkedList<Integer>();
		cola.add(0);
		while(!cola.isEmpty()){
			int c = cola.poll();
			if (array[c].type == 0)
				array[c].type = array[array[c].papa].type;
			for(int e: array[c].hijos)
				cola.add(e);
		}
		return;
	}
	
	
	static Node[] array;
	static int N;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		N = sc.nextInt();
		char[] cad = sc.next().toCharArray();
		array = new Node[N];
		for(int i = 0; i < N; i++)
			array[i] = new Node(i, aux(cad[i]));
		for(int i = 0; i < N - 1; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			a--;
			b--;
			array[a].hijos.add(b);
			array[b].papa = a;
			array[a].incoming_edges++;
		}
		determine_rights();
//		for(int i = 0; i < N; i++)
//			System.out.println(i+" "+array[i].type);
		LinkedList<Integer> roots = new LinkedList<Integer>();
		for(int i = 0; i < N; i++)
			if (array[i].incoming_edges == 0)
				roots.add(i);
		LinkedList<Integer> topo = new LinkedList<Integer>();
		while(!roots.isEmpty()){
			int c = roots.poll();
			topo.add(c);
			int e = array[c].papa;
			if (e == -1)
				continue;
			array[e].incoming_edges--;
			if (array[e].incoming_edges == 0)
				roots.add(e);
		}
		
		
//		for(int c: topo)
//			System.out.print(c+" ");
//		System.out.println();
		
		for(int c: topo){
			if (array[c].type == 2)
				array[c].type = 0;
			if (array[c].hijos.size() == 0)
				continue;
			boolean todos = true;
			for(int e: array[c].hijos){
				if (array[e].type == 0)
					todos = false;
			}
			if (todos)
				array[c].type = 1;
			else
				array[c].type = 0;
		}
		
		Stack<Integer> st = new Stack<Integer>();
		st.add(0);
		LinkedList<Integer> ans = new LinkedList<Integer>();
		while(!st.isEmpty()){
			int c = st.pop();
			if (array[c].type == 1){
				ans.add(array[c].id + 1);
				continue;
			}
			for(int e: array[c].hijos)
				st.push(e);
		}
		Collections.sort(ans);
		System.out.println(ans.size());
		for(int e: ans)
			System.out.print(e+" ");
		System.out.println();
	}

}
