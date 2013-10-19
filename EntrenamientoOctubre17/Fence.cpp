#include <bits/stdc++.h>

#define MAX_VALUE 0x7fffffff

using namespace std;
	
int N, A, B;
	
int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    cin >> N >> A >> B;
    int array[N];
    int integral[N];
    int ant = 0;
    for(int i = 0; i < N; i++){
	    cin >> array[i];
	    ant += array[i];
	    integral[i] = ant;
    }
    int dp[N + 1][A + 1][2];
    for(int i = 0 ; i <= A; i++){
	    dp[N][i][0] = 0;
	    dp[N][i][1] = 0;
    }
    for(int i = N - 1; i >= 1; i--){
	    for(int j = 0; j <= A; j++){
		    for(int k = 0; k < 2; k++){
			    dp[i][j][k] = MAX_VALUE;
			    int total = integral[i - 1];
			    int Aavailable = A - j;
			    int Bavailable = B - (total - j);
			    if (array[i] <= Aavailable){
				    int out = dp[i + 1][j + array[i]][0];
				    if (out != MAX_VALUE)
					    out += ((k == 0)? 0: min(array[i - 1], array[i]));
				    dp[i][j][k] = out;
			    }
			    if (array[i] <= Bavailable){
				    int out = dp[i + 1][j][1];
				    if (out != MAX_VALUE)
					    out += ((k == 1)? 0: min(array[i - 1], array[i]));
				    dp[i][j][k] = min( out, dp[i][j][k]);
			    }
		    }
	    }
    }
    int sol = MAX_VALUE;
    if (integral[N - 1] <= (A+B)){	
	    if (array[0] <= A)
		    sol = dp[1][array[0]][0];
	    if (array[0] <= B)
		    sol = min(sol, dp[1][0][1]);
    }
    int ans = (sol == MAX_VALUE)? -1: sol;
    cout << ans << endl;
}
