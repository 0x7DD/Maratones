import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;


public class Main {
	
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
	
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner();
		char[] array = sc.next().toCharArray();
		HashSet<Integer> set = new HashSet<Integer>();
		int out = 1;
		while(true){
			set.clear();
			for(int i = 0; (i + out - 1) < array.length; i++){
				int acum = 0;
				for(int j = 0; j < out; j++){
					acum =  acum << 1;
					acum += (array[i + j] == '1')? 1: 0;
				}
				set.add(acum);
			}
			if (set.size() != (1<<out))
				break;
			//
			out++;
		}
		System.out.println(out);
	}

}
