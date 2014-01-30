
public class gencode {

	public static void main(String arg[]) {
		int n = 4;
		System.out.println(n);
		for (int i = 1; i < n; ++i) {
			for (int j = i + 1; j <= n; ++j) {
				System.out.println(i + "-" + j);
			}
		}
	}
}
