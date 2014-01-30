import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class VivoParceAC {
	
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
	
	
	static class Node {
		int value;
		HashSet<Integer> v;
		public Node(){
			value = -1;
			v  = new HashSet<Integer>();
		}
	}
	
	static boolean safe(int node, int color) {
		for(int ch : ar[node].v ) 
			if (ar[ch].value == color) return false;
		return true;
	}
	

	static boolean solve(int node) {
		if (node == N) return true;
		
		for (int i = 0; i < 4; ++i) {
			if (safe(node, i)) {
				ar[node].value = i;		
				if(true == solve(node + 1))
					return true;	
				ar[node].value = -1;
			}
		}
		return false;
	}
	
	static int N;
	static Node[] ar;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		String cad = sc.next();
		while(true){
			N = Integer.valueOf(cad);
			ar = new Node[N];
			for(int i = 0; i < N; i++)
				ar[i] = new Node();
			cad = sc.next();
			while(cad != null){
				String[] array = cad.split("-");
				if (array.length < 2)
					break;
				int a = Integer.valueOf(array[0]) - 1;
				int b = Integer.valueOf(array[1]) - 1;
				ar[a].v.add(b);
				ar[b].v.add(a);
				cad = sc.next();
				if (cad == null)
					break;
			}
		
			solve(0);
			for (int i = 0; i < N; i++)
				System.out.printf("%d %d\n", i + 1, ar[i].value + 1);
			if (cad == null) 
				break;
		}
	}

}
