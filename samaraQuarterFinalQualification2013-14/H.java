import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class H {
	
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
		char[] a = sc.next().toCharArray();
		char[] b = sc.next().toCharArray();
		int count = 0;
		for(int i = 0; i < N - 1; i++){
			if ((a[i] == b[i]) && (a[i + 1] != b[i + 1]))
				count++;
		}
		if (a[0] != b[0])
			count++;
		System.out.println(count);
	}

}
