import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;


public class TrendingTopic {
	
	static class Scanner{
		BufferedReader rd;
		StringTokenizer tk;
		public Scanner(){
			rd = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() throws IOException{
			while(tk == null || !tk.hasMoreTokens()){
				String cad = rd.readLine();
				if (cad == null)
					return null;
				tk = new StringTokenizer(cad);
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
	
	
	
	static class Pair implements Comparable<Pair>{
		String cad;
		int times;
		public Pair(String c, int t){
			cad = c;
			times = t;
		}
		@Override
		public int compareTo(Pair o) {
			if (o.times == times)
				return cad.compareTo(o.cad);
			return o.times - times;
		}
	}
	
	
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	@SuppressWarnings("unchecked")
	static HashMap<String, Integer>[] array  = new HashMap[7];
	
	
	static void query(int N){
		Pair[] tmp = new Pair[map.size()];
		int idx = 0;
		for(String c: map.keySet())
			tmp[idx++] = new Pair(c, map.get(c));
		Arrays.sort(tmp);
		idx = 0;
		int i = 0;
		System.out.println("<top "+ N +">");
		int last = 0;
		for(i = 0; i < tmp.length && i < N; i++){
			System.out.println(tmp[i].cad+" "+tmp[i].times);
			last = tmp[i].times;
		}
		while(i < tmp.length && tmp[i].times == last){
			System.out.println(tmp[i].cad+" "+tmp[i].times);
			i++;
		}
		System.out.println("</top>");
	}
	
	
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner();
		int dia = 0;
		for(int i = 0; i < 7; i++)
			array[i] = new HashMap<String , Integer>();
		while(true){
			String command = sc.next();
			if (command == null)
				break;
			if (command.equals("<top")){
				int N = sc.nextInt();
				sc.next();
				query(N);
				continue;
			}
			// quitar las viejas
			HashMap<String, Integer> mapadia = array[dia % 7];
			Set<String> ss = mapadia.keySet();
			for(String c: ss){
				int nuevo = map.get(c) - mapadia.get(c);
				if (nuevo == 0)
					map.remove(c);
				else
					map.put(c, nuevo);
			}
			// limpiar
			mapadia.clear();
			String cad = sc.next();
			while(!cad.equals("</text>")){
				if (cad.length() >= 4){
					if (mapadia.containsKey(cad))
						mapadia.put(cad, mapadia.get(cad) + 1);
					else
						mapadia.put(cad, 1);
				}
				cad = sc.next();
			}
			// agregar las nuevas
			ss = mapadia.keySet();
			for(String c: ss){
				if (map.containsKey(c))
					map.put(c, map.get(c) + mapadia.get(c));
				else
					map.put(c, mapadia.get(c));
			}
//			System.out.println("dia " + dia);
//			System.out.println(map);
//			System.out.println("mapa dia " + dia);
//			System.out.println(mapadia);
			dia++;
		}
		
	}

}
