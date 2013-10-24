import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Dartboard {
	
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
	
	static double sigma;
	
	static double Computedprobability(double r1, double r2){
		double u1 = (-1 * r1 * r1) / (2 * sigma * sigma);
		double u2 = (-1 * r2 * r2) / (2 * sigma * sigma);
		return -1 * (Math.exp(u2) - Math.exp(u1));
	}
	
	static double[] r;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		r = new double[6];
		for(int i = 0; i < 6; i++)
			r[i] = sc.nextDouble();
		sigma = sc.nextDouble();
		double promedio = 10.5;
		double expected = 0.0;
		double c = Computedprobability(0.0, r[0]);
		expected = 50.0 * c;
		c = Computedprobability(r[0], r[1]);
		expected += 25.0 * c;
		c = Computedprobability(r[1], r[2]);
		expected += promedio * c;
		c = Computedprobability(r[2], r[3]);
		expected += 3 * promedio * c;
		c = Computedprobability(r[3], r[4]);
		expected += promedio * c;
		c = Computedprobability(r[4], r[5]);
		expected += 2 * promedio * c;
		System.out.println(expected);
	}

}
