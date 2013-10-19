import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Weather {
	
	
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		//System.setIn(new FileInputStream("input.txt"));
		//System.setOut(new PrintStream("output.txt"));
		Scanner sc = new Scanner(new FileReader("input.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		int N = sc.nextInt();
		int[] array = new int[N];
		for(int i = 0; i < N; i++)
			array[i] = sc.nextInt();
		int[] positive = new int[N];
		int[] negative = new int[N];
		for(int i = 0; i < N; i++){
			if (array[i] > 0)
				positive[i] = 1;
			if (array[i] < 0)
				negative[i] = 1;
		}
		int[] int_positive = new int[N];
		int_positive[0] = positive[0];
		for(int i = 1; i < N; i++)
			int_positive[i] = positive[i] + int_positive[i - 1];
		int[] int_negative = new int[N];
		int_negative[0] = negative[0];
		for(int i = 1; i < N; i++)
			int_negative[i] = negative[i] + int_negative[i - 1];
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N - 1; i++){
			int cleft = (i + 1) - int_negative[i];
			int cright = N - (i + 1) - (int_positive[N - 1] - int_positive[i]);
			min = Math.min(min, cleft + cright);
		}
		bw.write(min + "");
		bw.flush();
	}

}
