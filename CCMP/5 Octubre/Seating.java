import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Seating {
	
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
	
	static class FT{
		int[] ft;
		int tam;
		public FT(int t){
			tam = t;
			ft = new int[tam];
		}
		int query(int a, int b){
			if (a == 0){
				int sum = 0 ;
				for(; b >= 0; b = (b & (b + 1)) - 1)
					sum += ft[b];
				return sum;
			}
			else
				return (query(0, b) - query(0, a - 1));
		}
		void increase(int k, int inc){
			for(; k < tam; k |= k + 1)
				ft[k] += inc;
		}
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		while(true){
			int N = sc.nextInt();
			if (N == 0)
				return;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(int i = 0; i < N; i++){
				String cad = sc.next();
				map.put(cad, i);
			}
			FT tree = new FT(100010);
			long ans = 0;
			for(int i = 0;  i < N; i++){
				String cad = sc.next();
				int number = map.get(cad);
				ans += (tree.query(number + 1, 100005) + 0L);
				tree.increase(number, 1);
			}
			System.out.println(ans);
		}
	}

}
