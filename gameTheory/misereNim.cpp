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
        vector<int> heaps;
        int heapsOne = 0;
        for(int i = 0; i < n; ++i){
            int a; cin >> a;
            heapsOne += (a == 1);
            heaps.push_back(a);
        }
        cont++;
        if(heaps.size() - heapsOne >= 1){
            int mio = heaps[0];
            for(int i = 1; i < heaps.size(); ++i)
                mio ^= heaps[i];
            if(mio) cout<<"Case "<<cont<<": Alice"<<endl;
            else cout<<"Case "<<cont<<": Bob"<<endl;
        }
        else{
            if(heapsOne%2) cout<<"Case "<<cont<<": Bob"<<endl;
            else cout<<"Case "<<cont<<": Alice"<<endl;
        }        
    }
    return 0;
}
