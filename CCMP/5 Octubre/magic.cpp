using namespace std;
#include<bits/stdc++.h>
#define D(x) cout<< #x " = "<<(x)<<endl

int vis[10];

int all_digits(long long a){
    while(a){
        vis[a%10]++;
        a/=10;
    }
    for(int i = 0; i< 10;++i){
        if(vis[i]==0)return 0;
    }
    return 1;
}

int main(){
    long long num;
    while(cin>>num){
        memset(vis,0,sizeof vis);
        long long ans = 1;
        while(!all_digits(num*ans))
            ans++;
        
        cout<<ans<<endl;
    }
    return 0;
}


