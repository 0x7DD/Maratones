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
        vector<int> whites(n);
        vector<int> blacks(n);
        for(int i = 0; i < n; ++i)
            cin >> whites[i];
        for(int i = 0; i < n; ++i)
            cin >> blacks[i];
        vector<int> myPiles(n);
        for(int i = 0; i < n; ++i)
            myPiles[i] = blacks[i] - whites[i] - 1;
        
        int mio = myPiles[0];
        for(int i = 1; i < myPiles.size(); ++i)
            mio ^= myPiles[i];
        
        cont++;
        if(mio) cout<<"Case "<<cont<<": white wins"<<endl;
        else cout<<"Case "<<cont<<": black wins"<<endl;
    }
    return 0;
}
