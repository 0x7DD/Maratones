# include <cstdio>
# include <iostream>
# include <cstdlib>
# include <vector>
using namespace std;

int main(){
    int t; cin >> t;
    int cont = 0;
    while(t--){
        int n,m; cin >> n >> m;
        vector<int> myPiles(n);
        for(int i = 0; i < n; ++i){
            int sum = 0;
            for(int j = 0; j < m; ++j){
                int k; cin >> k;
                sum += k;
            }
            myPiles[i] = sum;
        }
        int mio = myPiles[0];
        for(int i = 1; i < myPiles.size(); ++i)
            mio ^= myPiles[i];
        
        cont++;
        if(mio) cout<<"Case "<<cont<<": Alice"<<endl;
        else cout<<"Case "<<cont<<": Bob"<<endl;
    }
    return 0;
}
