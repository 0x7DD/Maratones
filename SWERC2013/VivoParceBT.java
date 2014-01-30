import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class VivoParceBT {
	
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
		HashSet<Integer> pos;
		int [] par;
		public Node(){
			value = -1;
			v   = new HashSet<Integer>();
			pos = new  HashSet<Integer>();
			par = new int [4];
			for (int i = 0; i < 4; ++i) {
				pos.add(i);
				par[i] = 0;
			}
		}
	}
	

	static boolean solve(int node) {
		if (ar[node].pos.size() == 0) return false;
		
		for (int col : ar[node].pos) {
			
			ar[node].value = col;
			for (int ch : ar[node].v) {
				ar[ch].pos.remove(col);
				ar[ch].par[col]++;
			}
			boolean ok = true;
			for (int ch : ar[node].v) {
				if (-1 != ar[ch].value) continue;
				if (!solve(ch)) ok = false;
			}
			if (ok) return true;
			
			ar[node].value = -1;
			for (int ch : ar[node].v) {
				ar[ch].par[col]--;
				if (0 == ar[ch].par[col])
					ar[ch].pos.add(col);
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
