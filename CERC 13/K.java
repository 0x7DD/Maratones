import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class K {
	
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
	
	static boolean cycle(int node, LinkedList<Integer> l){
		if (color[node] == 1){
			l.add(node);
			while(true){
				int t = l.pollFirst();
				if (t == node)
					break;
			}
			return true;
		}
		if (color[node] == 2)
			return false;
		color[node] = 1;
		l.add(node);
		for(int i = 0; i < 26; i++)
			if (matrix[node][i])
				if (cycle(i, l))
					return true;
		l.pollLast();
		color[node] = 2;
		return false;
	}
	
	
	
	
	static int depth(int node){
		if (depth[node] != -1)
			return depth[node];
		depth[node] = 0;
		for(int i = 0; i < 26; i++)
			if (matrix[node][i])
				depth[node] = Math.max(depth[node], 1 + depth(i));
		return depth[node];
	}
	
	static void recover(int node, LinkedList<Integer> l){
		l.add(node);
		int max = -1;
		int cual = 0;
		for(int i = 0; i < 26; i++){
			if (matrix[node][i]){
				if (depth(i) > max){
					max = depth(i);
					cual = i;
				}
			}
		}
		if (max != -1)
			recover(cual, l);
	}
	
	
	static boolean[][] matrix;
	static int[] color;
	static int[] depth;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		int T = sc.nextInt();
		for(int c = 0; c < T; c++){
			int N = sc.nextInt();
			matrix = new boolean[26][26];
			for(int i = 0; i < 26; i++)
				Arrays.fill(matrix[i], true);
			for(int i = 0; i < N; i++){
				char[] t = sc.next().toCharArray();
				matrix[t[0] - 'a'][t[1] - 'a'] = false;
			}
			color = new int[26];
			Arrays.fill(color, 0);
			LinkedList<Integer> l = new LinkedList<Integer>();
			boolean cycle_found = false;
			for(int i = 0; i < 26 && !cycle_found; i++){
				l.clear();
				if (cycle(i,l)){
					int[] array = new int[l.size()];
					int index =0 ;
					for(int e: l)
						array[index++] = e;
					for(int j = 0; j < 20; j++){
						for(int k = 0; k < 20; k++){
							char ch = (char) (array[(j + k) % array.length] + 'a');
							System.out.print(ch+"");
						}
						System.out.println();
					}
					cycle_found = true;
					break;
				}
			}
			if (cycle_found)
				continue;
			depth = new int[26];
			Arrays.fill(depth, -1);
			int best = 0;
			int cual = 0;
			for(int i = 0; i < 26; i++){
				if (depth(i) > best){
					best = depth(i);
					cual = i;
				}
			}
			l.clear();
			recover(cual, l);
			int[] array = new int[l.size()];
			int index =0 ;
			for(int e: l)
				array[index++] = e;
			int limit = (array.length + 1)/ 2;
			for(int j = 0; j < limit; j++){
				for(int k = 0; k < limit; k++){
					char ch = (char) (array[(j + k) % array.length] + 'a');
					System.out.print(ch+"");
				}
				System.out.println();
			}
		}
	}
	
	

}
