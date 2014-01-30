import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class A {
	
	
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
	}
	
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		int N = sc.nextInt();
		int pequeno = 0;
		int mediano = 0;
		int[] g = new int[N];
		int[] m = new int[N];
		for(int i = 0; i < N; i++){
			int[] array = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
			Arrays.sort(array);
			pequeno = Math.max(pequeno, array[0]);
			mediano = Math.max(mediano, array[1]);
			m[i] = array[1];
			g[i] = array[2];
		}
		LinkedList<Integer> ans = new LinkedList<Integer>();
		for(int i = 0; i < N; i++)
			if (m[i] >= pequeno && g[i] >= mediano)
				ans.add(i);
		System.out.println(ans.size());
		for(int e: ans)
			System.out.print((e+1)+" ");
		System.out.println();
	}

}
