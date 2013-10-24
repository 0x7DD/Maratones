import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Timebomb {
	
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
	
	static String[] patrones = {  " ***   * *** *** * * *** *** *** *** ***",
								  " * *   *   *   * * * *   *     * * * * *",
								  " * *   * *** *** *** *** ***   * *** ***",
								  " * *   * *     *   *   * * *   * * *   *",
								  " ***   * *** ***   * *** ***   * *** ***" };
	
	
	static char[][] call(){
		char[][] ret;
		ret = new char[5][];
		for(int i = 0; i < 5; i++)
			ret[i] = patrones[i].toCharArray();
		return ret;
	}
	
	static int get_number(char[][] input, int index, char[][] pat){
		for(int n = 0; n < 10; n++){
			boolean bien = true;
			for(int i = 0; i < 5; i++)
				for(int j = 0; j < 4; j++){
					int a = i;int b = j + index;
					int aa = i;int bb = j + 4*n;
					char t1 = input[i][j + index];
					char t2 = pat[i][j + 4*n];
					if (t1 != t2)
						bien = false;
				}
			if (bien)
				return n;
		}
		return -1;
	}
	
	
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner();
		char[][] m = new char[5][];
		char[][] patrones = call();
		for(int i = 0; i < 5; i++){
			String cad = " " + sc.br.readLine();
			m[i] = cad.toCharArray();
		}
		if (m[0].length % 4 != 0){
			System.out.println("BOOM!!");
			return ;
		}
		int acum = 0;
		for(int j = 0; j <  m[0].length; j+=4){
			int read = get_number(m, j, patrones);
			if (read < 0){
				System.out.println("BOOM!!");
				return ;
			}
			acum *= 10;
			acum += read;
		}
		if (acum % 6 == 0)
			System.out.println("BEER!!");
		else
			System.out.println("BOOM!!");
		//System.out.println(acum);
	}

}
