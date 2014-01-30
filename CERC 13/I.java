import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class I {
	
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
	
	
	static void swap(int a, int b, int[] array){
		int tam = b - a + 1;
		int half = tam / 2;
		for(int i = a; i < a + half; i++){
			int tmp = array[i];
			array[i] = array[i + half];
			array[i + half] = tmp;
		}
		return;
	}
	
	static int find(int from, int num, int[] array){
		for(int i = from; i < array.length; i++)
			if (array[i] == num)
				return i;
		return 0/0;
	}
	
	static void print(int[] array){
		StringBuilder sb = new StringBuilder();
		sb.append("( ");
		for(int i = 0 ; i < array.length; i++)
			sb.append(" "+array[i]+" ");
		sb.append(")");
		System.out.println(sb.toString());
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = sc.nextInt();
		int[] a = new int[1000000];
		int[] b = new int[1000000];
		for(int c = 0; c < T; c++){
			int N = sc.nextInt();
			int[] array = new int[N];
			for(int i = 0; i < N; i++)
				array[i] = sc.nextInt();
			int indice = 0;
			for(int i = 0; i < N - 1; i++){
				int idx = find(i, i + 1, array);
				while(idx > i){
					int tam = idx - i + 1;
					if (tam % 2 == 0){
						swap(i, idx, array);
						a[indice] = i;
						b[indice] = idx;
					}
					else{
						swap(idx - 1, idx, array);
						a[indice] = idx - 1;
						b[indice] = idx;
					}
					//print(array);
					indice++;
					idx = find(i, i + 1, array);
				}
			}
			bw.write(indice + "\n");
			for(int i = 0; i < indice; i++)
				bw.write((a[i]+ 1)+ " " +(b[i]+1)+ "\n");
		}
		bw.flush();
	}
	
	

}
