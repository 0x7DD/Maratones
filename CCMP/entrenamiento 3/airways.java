import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class airways {
	
	static class Scanner{
		StringTokenizer tk;
		BufferedReader rd;
		public Scanner(){
			rd = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() throws IOException{
			while(tk == null || !tk.hasMoreTokens())
				tk = new StringTokenizer(rd.readLine());
			return tk.nextToken();
		}
		int nextInt() throws NumberFormatException, IOException{
			return Integer.valueOf(next());
		}
	}
	
	
	
	static class Node{
		int id;
		int incoming;
		LinkedList<Edge> v = new LinkedList<Edge>();
		public Node(int idd){
			id = idd;
			incoming  = 0;
		}
	}
	
	
	static class Edge implements Comparable<Edge>{
		int a,b;
		int number;
		public Edge(int aa, int bb, int n){
			a = aa;
			b = bb;
			number = n;
		}
		@Override
		public int compareTo(Edge o) {
			return number - o.number;
		}
	}
	
	static TreeMap<String, Integer> map = new TreeMap<String, Integer>();
	static int idx = 0;
	static Node[] array = new Node[30000];
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		int T = sc.nextInt();
		for(int c = 0; c < T; c++){
			idx = 0;
			map.clear();
			int F = sc.nextInt();
			for(int i = 0; i < F; i++){
				String a = sc.next();
				String b = sc.next();
				int n = sc.nextInt(); 
				if (!map.containsKey(a)){
					array[idx] = new Node(idx);
					map.put(a, idx);
					idx++;
				}
				if (!map.containsKey(b)){
					array[idx] = new Node(idx);
					map.put(b, idx);
					idx++;
				}
				int ida = map.get(a);
				int idb = map.get(b);
				array[ida].v.add(new Edge(ida,idb, n));
				array[idb].incoming++;
			}
			TreeSet<Edge> set = new TreeSet<Edge>();
			for(int i = 0; i < idx; i++)
				if (array[i].incoming == 0)
					for(Edge e: array[i].v)
						set.add(e);
			LinkedList<Integer> ans = new LinkedList<Integer>();
			while(!set.isEmpty()){
				Edge curr = set.pollFirst();
				ans.add(curr.number);
				array[curr.b].incoming--;
				if (array[curr.b].incoming == 0){
					for(Edge e: array[curr.b].v)
						set.add(e);
				}
			}
			boolean primero = true;
			StringBuilder sb = new StringBuilder();
			for(int a: ans){
				if (!primero)
					sb.append(" ");
				sb.append(a);
				primero = false;
			}
			System.out.println(sb.toString());
		}
	}

}
