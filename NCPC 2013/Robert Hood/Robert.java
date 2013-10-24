import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Robert {
	
	static class point{
		double x,y;
		public point(double xx,double yy){
			x=xx;
			y=yy;
		}
		point sub(point a){
			return new point(x - a.x,y - a.y);
		}
		point add(point a){
			return new point(x + a.x,y + a.y);
		}
		double cross(point a){
			return x*a.y - a.x*y;
		}
		double norm(){
			return Math.sqrt(x*x + y*y);
		}
		public point rotate(double angle){
			//counterclockwise
			double xx=this.x*Math.cos(angle) - this.y*Math.sin(angle);
			double yy=this.x*Math.sin(angle) + this.y*Math.cos(angle);
			return new point(xx,yy);
		}
		public double dot(point o){
			return this.x*o.x + this.y*o.y;
		}
		public double angle(point o){
			return Math.acos(this.dot(o)/ (this.norm()*o.norm()) );
		}
		public point multbyscalar(double u) {
			return new point(u*x,u*y);
		}
	}
	
	static double eps=1e-7;
	static point first_point;
	
	static class cmp1 implements Comparator<point>{
		@Override
		public int compare(point f, point s) {
			if (Math.abs(f.x - s.x)<eps)
				return Double.compare(f.y,s.y);
			return Double.compare(f.x, s.x);
		}
	}
	
	static class cmp2 implements Comparator<point>{
		@Override
		public int compare(point f, point s) {
			double tmp=ccw(first_point,f,s);
			if (Math.abs(tmp)<eps){
				if (f.sub(first_point).norm() < s.sub(first_point).norm())
					return -1;
				else
					return 1;
			}
			return (tmp>eps)?1:-1;
		}
	}
	
	static ArrayList<point> convexhull(point[] in,int n){
		ArrayList<point> hull=new ArrayList<point>(n);
		Arrays.sort(in,0,n,new cmp1());
		first_point=in[0];
		Arrays.sort(in,1,n,new cmp2());
		if (n<=3){
			for(int i=0;i<n;i++)
				hull.add(in[i]);
			return hull;
		}
		hull.add(first_point);
		hull.add(in[1]);
		int top=1;
		int i=2;
		while(i<n){
			if (top>0 && ccw(hull.get(top-1),hull.get(top),in[i])>-1*eps){
				hull.remove(top);
				top=top-1;
			}
			else{
				top=top+1;
				hull.add(in[i]);
				i++;
			}
		}
		return hull;
	}
	
	static double ccw(point a,point b,point c){
		return a.sub(b).cross(c.sub(b));
	}
	
	static point[] array=new point[100010];
	
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
		public double nextDouble() throws NumberFormatException, IOException {
			return Double.valueOf(next());
		}
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc=new Scanner();
		int N=sc.nextInt();
		for(int i=0;i<N;i++){
			double a=sc.nextDouble();
			double b=sc.nextDouble();
			array[i]=new point(a,b);
		}
		ArrayList<point> ch=convexhull(array,N);
		
		double D = 0.0;
		for(int i = 0; i < ch.size(); i++)
			for(int j = i + 1; j < ch.size(); j++)
				D = Math.max(D, ch.get(i).sub(ch.get(j)).norm() );
		/*
		ArrayList<point> ch=convexhull(array,N);
		double d = Antipodal_Pairs(ch);*/
		String ans = String.format("%.8f", D);
		ans = ans.replace(",", ".");
		System.out.println(ans);
	}
}

