using namespace std;
#include<bits/stdc++.h>
#define MNODES 40


int p[MNODES];

int find_set(int x){
    if(x == p[x]) return x;
    else return p[x] = find_set(p[x]);
}

void link(int x, int y){
    p[find_set(x)] = find_set(y);
}



int main(){
    int n;cin>>n;
    for(int i = 0 ; i< n; ++i){
        int nmalos;cin>>nmalos;
        set<string> malos;
        string name;
        for(int i = 0; i < nmalos; ++i){
            cin>>name;
            malos.insert(name);
        }
        int n,m;
        cin>>n>>m;
        for(int i = 0; i< n+1; ++i)
            p[i] = i;
            
        for(int i = 0; i< m; ++i){
            int a,b;string mio;
            cin>>a>>b>>mio;
            if(malos.count(mio)==0){
                link(a,b);
            }
        }
        
        if(find_set(0) == find_set(n-1))
            cout<<1<<endl;
        else
            cout<<0<<endl;
            
    }
        
    return 0;
}
