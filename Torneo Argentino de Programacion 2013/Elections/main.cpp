using namespace std;
#include<bits/stdc++.h>

int main(){
    int n;
    while(cin>>n){
        vector<double> votes(n);
        double total = 0;
        for(int i = 0; i< n; ++i){
            cin>>votes[i];
            total += votes[i];
        }
        
        int ans = 2;
        for(int i = 0; i< n; ++i){
            if(votes[i]/total >= 0.45){
                ans = 1;
            }
            if(votes[i]/total >= 0.4){
                int ok = 1;
                for(int j = 0; j< n; ++j){
                    if(i!=j and ((votes[i]/total) - (votes[j]/total)) < 0.1){
                        ok = 0;
                    }
                }
                if(ok) ans = 1;
            }
        }
        
        cout<<ans<<endl;
        
    }
    return 0;
}
