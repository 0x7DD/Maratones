import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Rings {
	
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
	
	static class Rune{
		int[] array;
		int positive;
		int negative;
		public Rune(int[] a){
			array = a;
			positive = 0;
			negative = 0;
			for(int i = 0; i < 3; i++){
				if (array[i] > 0)
					positive = positive | (1<<array[i]);
				else
					negative = negative | (1<<(-array[i]));
			}
			positive >>= 1;
			negative >>= 1;
		}
		boolean eval(int mask){
			return ((positive & mask) != 0) || ((negative & (~mask)) != 0);
			/*
			mask = mask << 1;
			for(int i = 0; i < 3; i++){
				int number = array[i];
				if (number > 0){
					int bit = (mask >> number) & 1;
					if (bit == 1)
						return true;
				}
				else{
					int bit = (mask >> (-number)) & 1;
					if (bit == 0)
						return true;
				}
			}
			return false;*/
		}
		boolean NullRing(){
			for(int i = 0; i < 3; i++)
				if (array[i] == 0)
					return true;
			return false;
		}
		boolean RingMissing(){
			for(int i = 0; i < 3; i++)
				if (array[i] < -V || array[i] > V)
					return true;
			return false;
		}
		boolean RepeatedRing(){
			TreeSet<Integer> set = new TreeSet<Integer>();
			for(int i = 0; i < 3; i++)
				set.add(Math.abs(array[i]));
			if (set.size() < 3)
				return true;
			return false;
		}
	}
	
	static boolean issat(Rune[] a){
		for(int m = 0; m < (1<<V); m++){
			boolean flag = true;
			for(int i = 0; i < a.length && flag; i++)
				flag = a[i].eval(m);
			if (flag)
				return true;
				
		}
		return false;
	}
	
	static int V,S;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		int T = sc.nextInt();
		for(int c = 0; c < T; c++){
			V = sc.nextInt();
			S = sc.nextInt();
			Rune[] array = new Rune[S];
			for(int i = 0; i < S; i++){
				int[] t = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
				sc.nextInt();
				array[i] = new Rune(t);
			}
			boolean error = false;
			for(int i = 0; i < S && !error; i++)
				error = array[i].NullRing();
			if (error){
				System.out.println("INVALID: NULL RING");
				continue;
			}
			for(int i = 0; i < S && !error; i++)
				error = array[i].RingMissing();
			if (error){
				System.out.println("INVALID: RING MISSING");
				continue;
			}
			for(int i = 0; i < S && !error; i++)
				error = array[i].RepeatedRing();
			if (error){
				System.out.println("INVALID: RUNE CONTAINS A REPEATED RING");
				continue;
			}
			if (issat(array))
				System.out.println("RUNES SATISFIED!");
			else
				System.out.println("RUNES UNSATISFIABLE! TRY ANOTHER GATE!");
		}
	}

}
