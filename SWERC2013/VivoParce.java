import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class VivoParce {
	
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
	
	
	static class Node implements Comparable<Node>{
		int id;
		int value;
		TreeSet<Integer> v;
		public Node(int i){
			id = i;
			value = -1;
			v = new TreeSet<Integer>();
		}
		@Override
		public int compareTo(Node arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
//	
//	static boolean dfs(int idx){
//		Node actual = ar[idx];
//		if (actual.opts.size() <= 0)
//			return false;
//		boolean paila = false;
//		for(int op: actual.opts){
//			if (paila) continue;
//			actual.value = op;
//			int indice = 0;
//			Node[] mishijos = new Node[actual.v.size()];
//			for(int h: actual.v){
//				ar[h].opts.remove(op);
//				mishijos[indice++] = ar[h];
//			}
//			Arrays.sort(mishijos);
//			for(Node hijo: mishijos){
//				if (ar[hijo.id].value > 0){
//					if (ar[hijo.id].value == op)
//						paila = true;
//					else
//						continue;
//				}
//				if (paila) continue;
//				boolean ret = dfs(hijo.id);
//				if (!ret)
//					paila = true;
//			}
//			for(int h: actual.v)
//				ar[h].opts.add(op);
//			actual.value = -1;
//		}
//		return !paila;
//	}
	
	
	
	static int correct(){
		for(int i = 0; i < N; i++){
			int color_actual = ar[i].value;
			for(int h: ar[i].v)
				if (ar[h].value == color_actual)
					return i;
		}
		return -1;
	}
	
	static void corregir(int idx, Random r){
		int[] mapping = new int[4];
		Arrays.fill(mapping, 0);
		HashSet<Integer> set = new HashSet<Integer>();
		for(int h : ar[idx].v){
			mapping[ar[h].value]++;
			set.add(ar[h].value);
		}
		if (set.size() < 4){
			for(int c = 0; c <  4; c++)
				if (!set.contains(c)){
					ar[idx].value = c;
					return;
				}
		}
		int viejo = r.nextInt(4);
		int nuevo = r.nextInt(4);
		while(viejo == nuevo)
			nuevo = r.nextInt(4);
		set.remove(viejo);
		for(int h: ar[idx].v)
			if (ar[h].value == viejo)
				ar[h].value = nuevo;
		ar[idx].value = viejo;
		return;
	}
	
	static void solve(){
		Random r = new Random();
		for(int i = 0; i < N; i++)
			ar[i].value = r.nextInt(4);
		while(true){
			int h = correct();
			System.out.println(h);
			if (h < 0)
				break;
			corregir(h, r);
		}
			
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
				ar[i] = new Node(i);
			cad = sc.next();
			while(cad != null){
				String[] array = cad.split("-");
				if (array.length < 2)
					break;
				cad = sc.next();
				if (cad == null)
					break;
				int a = Integer.valueOf(array[0]) - 1;
				int b = Integer.valueOf(array[1]) - 1;
				ar[a].v.add(b);
				ar[b].v.add(a);
			}
			solve();
			for(int i = 0; i < N; i++)
				System.out.printf("%d %d\n", i + 1, ar[i].value + 1);
			if (cad == null) 
				break;
		}
	}

}
