import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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
	
	
	static long gcd(long a, long b){
		long tmp = 0;
		while(b != 0){
			tmp = a;
			a = b;
			b = tmp % b;
		}
		return a;
	}
	
	static class Frac{
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (d ^ (d >>> 32));
			result = prime * result + (int) (n ^ (n >>> 32));
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Frac other = (Frac) obj;
			if (d != other.d)
				return false;
			if (n != other.n)
				return false;
			return true;
		}
		long n,d;
		public Frac(long nn, long dd){
			n = nn;
			d = dd;
			simplify();
		}
		void simplify(){
			long g = gcd(n, d);
			n /= g;
			d /= g;
		}
		Frac multiply(Frac o){
			Frac ret = new Frac(n * o.n, d * o.d);
			return ret;
		}
		Frac division(Frac o){
			Frac ret = new Frac(n * o.d, d * o.n);
			return ret;
		}
		Frac add(Frac o){
			Frac ret = new Frac(n * o.d + o.n * d, d * o.d);
			return ret;
		}
		Frac sub(Frac o){
			Frac ret = new Frac(n * o.d - o.n * d, d * o.d);
			return ret;
		}
		public String toString(){
			return String.format("%d/%d", n, d);
		}
	}
	
	static HashMap<Integer, Integer> mapping = new HashMap<Integer, Integer>();
	
	static void init(){
		for(int i = 1; i <= 1001; i++)
			mapping.put((i*(i - 1))/2, i);
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		init();
		int P = sc.nextInt();
		int[] xx = new int[P];
		int[] yy = new int[P];
		for(int i = 0; i < P; i++){
			int x = sc.nextInt();
			int y = sc.nextInt();
			xx[i] = x;
			yy[i] = y;
		}
		HashMap<Frac, HashMap<Frac, Integer>> MainH = new HashMap<Frac, HashMap<Frac, Integer>>();
		for(int i = 0; i < P; i++){
			for(int j = i + 1; j < P; j++){
				int elevacion = yy[j] - yy[i];
				int avance = xx[j] - xx[i];
				if (elevacion == 0)
					continue;
				Frac m;
				if (avance == 0)
					m = new Frac(0, 1);
				else
					m = new Frac(elevacion, avance);
				Frac c;
				if (avance == 0)
					c = new Frac(xx[j], 1);
				else{
					Frac b = new Frac(yy[j], 1);
					b = b.sub(m.multiply(new Frac(xx[j], 1)));
					c = b.division(m);
					c = c.multiply(new Frac(-1,1));
				}
				if (!MainH.containsKey(c)){
					HashMap<Frac, Integer> hmap = new HashMap<Frac, Integer>();
					MainH.put(c, hmap);
				}
				HashMap<Frac, Integer> actual = MainH.get(c);
				if (!actual.containsKey(m))
					actual.put(m, 0);
				actual.put(m, actual.get(m) + 1);
			}
		}
		//System.out.println(mapping);
		HashSet<Integer> ans = new HashSet<Integer>();
		for(Frac k: MainH.keySet()){
			HashMap<Frac, Integer> actual = MainH.get(k);
			int rayas = actual.size();
			int covered = 0;
			for(Frac mp: actual.keySet())
				covered += mapping.get(actual.get(mp));
			ans.add(rayas + P - covered);
		}
		System.out.println(ans.size() + 1);
	}
}

