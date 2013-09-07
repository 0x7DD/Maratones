using namespace std;
#include<bits/stdc++.h>


int main(){
    int n;cin>>n;
    for(int i = 0 ; i< n; ++i){
        int a,b;
        cin>>a>>b;
        cout<<a<<" "<<b<<endl;
        cout<<(b + (a-1)*(b-2))<<endl;
        
    }
        
    return 0;
}
