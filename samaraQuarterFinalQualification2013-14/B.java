import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class B {
	
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
	
	static String compute(String cad){
		int[] mapping = new int[30];
		Arrays.fill(mapping, -1);
		StringBuilder sb = new StringBuilder();
		int current = 0;
		for(int i = 0; i < cad.length(); i++){
			if (mapping[cad.charAt(i)  - 'a'] == -1){
				mapping[cad.charAt(i)  - 'a'] = current;
				current++;
			}
			sb.append(mapping[cad.charAt(i)  - 'a']);
			
		}
		return sb.toString();
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		int N = sc.nextInt();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int  i = 0; i < N; i++){
			String key = compute(sc.next());
			if (!map.containsKey(key))
				map.put(key, 1);
			else
				map.put(key, map.get(key) + 1);
		}
		long ans = 0;
		for(String s: map.keySet()){
			long n = map.get(s);
			ans += (n*(n - 1)) / 2;
		}
		System.out.println(ans);
	}

}
