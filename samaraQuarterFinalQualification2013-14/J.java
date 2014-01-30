		import java.io.BufferedReader;
		import java.io.IOException;
		import java.io.InputStreamReader;
		import java.util.StringTokenizer;
		
		
		public class J {
			
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
			
			public static void main(String args[]) throws NumberFormatException, IOException{
				Scanner sc = new Scanner();
				int N = sc.nextInt();
				int[] a = new int[N];
				int[] b = new int[N];
				for(int i = 0; i < N; i++){
					a[i] = sc.nextInt();
					b[i] = sc.nextInt();
				}
				int max = 0;
				int ans = 0;
				for(int i = 0; i < N; i++){
					if (a[i] > max)
						ans = Math.max(ans, b[i]);
					max = Math.max(a[i], max);
				}
				System.out.println(ans);
			}
		
		}
