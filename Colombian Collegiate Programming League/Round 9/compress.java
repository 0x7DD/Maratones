import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class compress {
	
	static char[] text;
	static int idx;
	
	
	
	static int gcd(int a,int b){
		int tmp;
		while(b > 0){
			a %= b;
			tmp = a;
			a = b;
			b = tmp;
		}
		return a;
	}
	
	static int lcm(int a,int b){
		return a * b / gcd(a,b);
	}
	
	static char[][] scale(char[][] m, int factor){
		char[][] ret = new char[m.length * factor][m[0].length * factor];
		for(int i = 0 ; i < m.length; i++)
			for(int j = 0; j < m[0].length; j++){
				int ni = i * factor;
				int nj = j * factor;
				for(int k = 0; k < factor; k++)
					for(int l = 0; l < factor; l++)
						ret[ni + k][nj + l] = m[i][j];
			}
		return ret;
	}
	
	static char[][] mergehorizontal(char[][] a, char[][] b){
		int columnsa = a[0].length;
		int columnsb = b[0].length;
		if (a.length != b.length)
			System.out.println(0/0);
		char[][] ret = new char[a.length][columnsa + columnsb];
		for(int i = 0; i < a.length; i++)
			for(int j = 0; j < columnsa; j++)
				ret[i][j] = a[i][j];
		for(int i = 0; i < b.length; i++)
			for(int j = 0; j < columnsb; j++)
				ret[i][j + columnsa] = b[i][j];
		return ret;
	}
	
	static char[][] mergevertical(char[][] a, char[][] b){
		int rowsa = a.length;
		int rowsb = b.length;
		if (a[0].length != b[0].length)
			System.out.println(0/0);
		char[][] ret = new char[rowsa + rowsb][a[0].length];
		for(int i = 0; i < rowsa; i++)
			for(int j = 0; j < a[0].length; j++)
				ret[i][j] = a[i][j];
		for(int i = 0; i < rowsb; i++)
			for(int j = 0; j < b[0].length; j++)
				ret[i + rowsa][j] = b[i][j];
		return ret;
	}
	
	static char[][] enmarcar(char[][] m){
		int r = m.length;
		int c = m[0].length;
		char[][] ret = new char[r + 2][c + 2];
		for(int j = 0; j < c + 2; j++){
			ret[0][j] = '-';
			ret[r + 1][j] = '-';
		}
		for(int i = 1; i < r + 1; i++){
			ret[i][0] = '|';
			ret[i][c + 1] = '|';
		}
		for(int i = 0; i < r; i++)
			for(int j = 0; j < c; j++)
				ret[i + 1][j + 1] = m[i][j];
		return ret;
	}
	
	static char[][] parse(){
		if (text[idx] == text[idx + 1]){
			// base case
			char[][] ret = new char[1][1];
			if (text[idx] == '1')
				ret[0][0] = ' ';
			else
				ret[0][0] = 'X';
			idx+=2;
			return ret;
		}
		else{
			char first = text[idx];
			idx++;
			char second = text[idx];
			idx++;
			char[][] uno = parse();
			char[][] dos = parse();
			if (first == '1'){
				//horizontal
				int nh = lcm(uno.length, dos.length);
				int f1 = nh / uno.length;
				int f2 = nh / dos.length;
				char[][] nuno = scale(uno, f1);
				char[][] ndos = scale(dos, f2);
				return mergehorizontal(nuno, ndos);
			}
			else{
				//vertical
				int nw = lcm(uno[0].length, dos[0].length);
				int f1 = nw / uno[0].length;
				int f2 =  nw / dos[0].length;
				char[][] nuno = scale(uno, f1);
				char[][] ndos = scale(dos, f2);
				return mergevertical(nuno, ndos);
			}
		}
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(true){
			idx = 0;
			String cad = br.readLine();
			if (cad == null)
				break;
			text = cad.toCharArray();
			char[][] m = parse();
			m = enmarcar(m);
			for(int i = 0; i < m.length; i++){
				for(int j = 0; j < m[0].length; j++)
					System.out.print(m[i][j]);
				System.out.println();
			}
			
		}
		bw.flush();
	}

}
