# include <cstdio>
# include <cstdlib>
# include <iostream>
# include <cmath>

using namespace std;

int main(){
    int t,cont = 0;
    scanf("%d", &t);
    while(t--){
        int n,m;
        scanf("%d %d", &n, &m);
        int nim = 0;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
                int pile;
                scanf("%d", &pile);
                int dis = (n - i - 1) + (m - j -1);
                if(dis%2)
                    nim ^= pile;                    
            }
        }
        cont++;
        if(nim) printf("Case %d: win\n", cont);
        else printf("Case %d: lose\n", cont);
    }
    return 0;
}
