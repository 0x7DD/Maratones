import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		long nextLong() throws NumberFormatException, IOException{
			return Long.valueOf(next());
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		Scanner in  =  new Scanner();
		int t = in.nextInt();
		while(t-- > 0){
			String [] line = in.rd.readLine().split(" "); 
			boolean[] dicho = new boolean[line.length];
			Arrays.fill(dicho, false);
			while(true){
				String cad = in.rd.readLine();
				if (cad.equals("what does the fox say?")){
					boolean primero = true;
					for(int i = 0; i < dicho.length; i++){
						if (dicho[i]) continue;
						if (!primero)
							System.out.print(' ');
						System.out.print(line[i]);
						primero = false;
					}
					System.out.println();
					break;
				}
				String[] tmp = cad.split(" ");
				for(int i = 0; i < line.length; i++)
					if (line[i].equals(tmp[2]))
						dicho[i] = true;
			}
		}
		
		
	}
}
