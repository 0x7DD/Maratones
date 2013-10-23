import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
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
	static int[] array = new int[1000001];
	static boolean[] barray = new boolean[1000001];
	static int[] ks = new int[1000001];
	static int[][] matrix = new int[12][1000000 + 1];
	
	static void init(){
		Arrays.fill(array, 0);
		Arrays.fill(barray, true);
		barray[0] = barray[1] = false;
		for(int i = 2; i <= 1000000; i++)
			if (barray[i]){
				array[i] = i;
				for(int j = i + i; j <= 1000000; j+=i){
					array[j] += i;
					barray[j] = false;
				}
			}
		int[] acum = new int[12];
		Arrays.fill(acum, 0);
		for(int i = 1; i <= 1000000; i++){
			if (barray[i])
				ks[i] = 1;
			else
				ks[i] = 1 + ks[array[i]];
			acum[ks[i] - 1]++;
			for(int j = 0; j < 12; j++)
				matrix[j][i] = acum[j];
		}
	}
	
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		int P = sc.nextInt();
		for(int i = 0; i < P; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			int k = sc.nextInt();
			if (k > 12)
				bw.write("0\n");
			else{
				int ans = matrix[k - 1][b] - matrix[k - 1][a - 1];
				bw.write(ans + "\n");
			}
		}
		bw.flush();
	}

}
