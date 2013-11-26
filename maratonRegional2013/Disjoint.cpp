#include <cstdio>
#include <vector>

using namespace std;

vector<int> array[1010];
int C,P;
int memo[1010][1010];


bool dp(int i, int j){
    int p = min(i, j);
    int k = max(i, j);
    if (memo[p][k] >= 0)
        return memo[p][k];
    if (i == 0)
        return memo[p][k] = 1;
    if (i == j)
        return memo[p][k] = 0;
    for(int t = 0; t < array[k].size(); t++){
        if (dp(i, array[k][t]))
            return memo[p][k] = 1;
    }
    return memo[p][k] = 0; 
}

int main(){
    while(scanf("%d %d", &C, &P) != EOF){
        for(int i = 0; i < C; i++)
            array[i].clear();
        for(int i = 0; i < P; i++){
            int a, b;
            scanf("%d %d", &a, &b);
            a--;b--;
            array[b].push_back(a);
        }
        for(int i = 0; i < C; i++)
            for(int j = 0; j < C; j++)
                memo[i][j] = -1;
        int ans = 0;
        for(int i = 0; i < C; i++)
            for(int j = i + 1; j < C; j++)
                if (dp(i, j))
                    ans++;
        printf("%d\n", ans);
    }
    return 0;
}
