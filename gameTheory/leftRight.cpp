# include <cstdio>
# include <iostream>
# include <cstdlib>
# include <vector>
using namespace std;

int main(){
    int t; cin >> t;
    int cont = 0;
    while(t--){
        int n; cin >> n;
        n *= 2;
        int tmp;
        vector<int> myPiles;
        for(int i = 0; i < n; ++i){
            int k; cin >> k;
            if(i%2 == 0) tmp = k;
            else myPiles.push_back(k - tmp - 1);
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
