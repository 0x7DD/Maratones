import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class F {
	
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
	
	static int N, P, Q;
	static int[] array;
	
	
	static boolean test(int moves){
		int available = moves;
		int diff = P - Q;
		for(int i = 0; i < N; i++){
			long t = array[i] - ((moves + 0L)* Q);
			if (t > 0){
				if (diff == 0)
					return false;
				available -= (t + diff - 1) / diff;
				if (available < 0)
					return false;
			}
				
		}
		return true;
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		N  =sc.nextInt();
		P = sc.nextInt();
		Q = sc.nextInt();
		array = new int[N];
		for(int i = 0; i < N; i++)
			array[i] = sc.nextInt();
		int low = 0;
		int high = 1000000000;
		while(low < high){
			int mid = (low + high)/2;
			if (test(mid))
				high = mid;
			else
				low = mid + 1;
		}
		System.out.println(low);
	}

}
