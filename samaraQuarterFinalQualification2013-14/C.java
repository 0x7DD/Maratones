	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.HashMap;
	import java.util.StringTokenizer;
	
	
	public class C {
		
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
		
		static int N;
		static long S;
		
		public static void main(String args[]) throws NumberFormatException, IOException{
			Scanner sc = new Scanner();
			N = sc.nextInt();
			S = sc.nextLong();
			HashMap<Long, Integer> map = new HashMap<Long, Integer>();
			map.put(0L, 1);
			long ans = 0;
			long sum = 0;
			for(int i = 0; i < N; i++){
				int num = sc.nextInt();
				sum += num;
				long complemento = sum - S;
				if (map.containsKey(complemento))
					ans += map.get(complemento);
				if (!map.containsKey(sum))
					map.put(sum, 1);
				else
					map.put(sum, map.get(sum) + 1);
			}
			System.out.println(ans);
		}
	
	}
