import java.io.BufferedReader;


public class Peg {
	
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
	
	static int[] dx = {-1, 0 , 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class State{
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(array);
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
			State other = (State) obj;
			if (!Arrays.equals(array, other.array))
				return false;
			return true;
		}

		int[] array;
		int moves;
		
		public State(int[] a){
			Arrays.sort(a);
			array = a;
			moves = 0;
		}
		
		char[][] unpack(){
			char[][] ret = new char[5][9];
			for(int i = 0; i < 5; i++)
				Arrays.fill(ret[i], '.');
			Arrays.fill(ret[0], '#');
			Arrays.fill(ret[4], '#');
			for(int i = 3; i < 6; i++){
				ret[0][i] = '.';
				ret[4][i] = '.';
			}
			for(int i = 0; i < array.length; i++){
				int pos = array[i];
				ret[pos / 9][pos % 9] = 'o';
			}
			return ret;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			char[][] c = unpack();
			for(int i = 0; i < c.length; i++){
				for(int j = 0; j < c[0].length; j++)
					sb.append(c[i][j]+"");
				sb.append("\n");
			}
			return sb.toString();
		}
	}
	
	static State pack(char[][] m){
		LinkedList<Integer> ret = new LinkedList<Integer>();
		for(int i = 0; i < m.length; i++)
			for(int j = 0; j < m[0].length; j++)
				if (m[i][j] == 'o')
					ret.push(i * 9 + j);
		int[] array = new int[ret.size()];
		int index = 0;
		for(int e: ret)
			array[index++] = e;
		State nuevo = new State(array);
		return nuevo;
	}
	
	static LinkedList<State> hijos(State current){
		char[][] c = current.unpack();
		LinkedList<State> ret = new LinkedList<State>();
		for(int i = 0; i < current.array.length; i++){
			int x = current.array[i] / 9;
			int y = current.array[i] % 9;
			for(int d = 0; d < 4; d++){
				int dxx = 2 * dx[d];
				int dyy = 2 * dy[d];
				int nx = x + dxx;
				int ny = y + dyy;
				boolean cond = (nx < 0 || ny < 0 || nx >= 5 || ny >= 9);
				if (cond)
					continue;
				if (c[nx][ny] != '.')
					continue;
				int deltax = dx[d];
				int deltay = dy[d];
				int middlex = x + deltax;
				int middley = y + deltay;
				if (c[middlex][middley] != 'o')
					continue;
				//char[][] nuevo_hijo = c.clone();
				char[][] nuevo_hijo = new char[c.length][c[0].length];
				for(int k = 0; k < nuevo_hijo.length; k++)
					for(int j = 0; j < nuevo_hijo[0].length; j++)
						nuevo_hijo[k][j] = c[k][j];
				nuevo_hijo[x][y] = '.';
				nuevo_hijo[middlex][middley] = '.';
				nuevo_hijo[nx][ny] = 'o';
				State hijo = pack(nuevo_hijo);
				if (visited.contains(hijo))
					continue;
				hijo.moves = current.moves + 1;
				ret.add(hijo);
			}
		}
		return ret;
	}
	
	
	static int min_legs, min_moves;
	static HashSet<State> visited = new HashSet<State>();
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		int casos = sc.nextInt();
		for(int c = 0; c < casos; c++){
			if (c > 0)
				sc.br.readLine();
			char[][] current = new char[5][];
			for(int i = 0; i < 5; i++)
				current[i] = sc.br.readLine().toCharArray();
			State cs = pack(current);
			min_legs = cs.array.length;
			min_moves = 0;
			visited.clear();
			visited.add(cs);
			LinkedList<State> queue = new LinkedList<State>();
			queue.add(cs);
			while(!queue.isEmpty()){
				cs = queue.pollFirst();
				if (cs.array.length < min_legs){
					min_legs = cs.array.length;
					min_moves = cs.moves;
				}
				LinkedList<State> hijos_no_visitados = hijos(cs);
				for(State h: hijos_no_visitados){
					queue.add(h);
					visited.add(h);
				}
			}
			System.out.println(min_legs+" "+min_moves);
		}
		
	}

}
