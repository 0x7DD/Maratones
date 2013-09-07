using namespace std;
#include<bits/stdc++.h>


int main(){
    int n;cin>>n;
    for(int i = 0 ; i< n; ++i){
        int a,b,c;
        cin>>a>>b>>c;
        cout<<a<<" " <<b<<" "<<c<<endl;
        int visited[a];
        memset(visited,0,sizeof visited);
        for(int i = b; i < a; i+=b)
            visited[i] = 1;
        for(int i = c; i < a; i+=c)
            visited[i] = 1;
            
        int ans =0 ;
        for(int i = 0; i< a; ++i)
            ans+= visited[i];
        cout<<ans<<endl;
    }
        
    return 0;
}
