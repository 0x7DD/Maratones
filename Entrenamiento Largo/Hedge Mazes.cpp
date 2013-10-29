using namespace std;
#include<bits/stdc++.h>
#define foreach(x,v) for(typeof ((v).begin()) x = (v).begin(); x != (v).end() ; ++x)
#define ___ ios_base::sync_with_stdio(false);cin.tie(NULL);

const int MP = 10010;

int visited[MP];
int prev[MP], low[MP], d[MP];
vector< vector<int> > g;
vector< pair<int,int> > bridges;
int n, ticks;

void dfs(int u){
    visited[u] = true;
    d[u] = low[u] = ticks++;
    for (int i = 0; i < g[u].size(); ++i){
        int v = g[u][i];
        if (prev[u] != v){
            if(!visited[v]){
               prev[v] = u;
               dfs(v);
               if (d[u] < low[v]){
                   bridges.push_back(make_pair(min(u,v),max(u,v)));
               }
               low[u] = min(low[u], low[v]);
            }else{
               low[u] = min(low[u], d[v]);
            }
        }
    }
}

int p[MP];

int find_set(int x){
    return ((x==p[x]) ? (x) : (p[x] = find_set(p[x])) );
}

void link(int x, int y){
    p[find_set(x)] = find_set(y);
}

int main(){ ___

    int n,e,q;
    while(cin>>n>>e>>q and (n+e+q)){
        memset(visited,false,sizeof(visited));
        memset(prev,-1,sizeof(prev));
        g.assign(n, vector<int>());
        bridges.clear();
        int u,v;
        for(int i = 0 ; i< e; ++i){
            cin>>u>>v;
            u--;v--;
            g[u].push_back(v);     
            g[v].push_back(u);
        }
        
        ticks = 0;
        for (int i=0; i<n; ++i)
            if (!visited[i])
                dfs(i);
        
        sort(bridges.begin(), bridges.end());
        
        for(int i = 0; i < n+1; ++i)p[i] = i;
        
        foreach(p, bridges)
            link(p->first,p->second);
            
        for(int i = 0; i< q; ++i){
            cin>>u>>v;
            u--;v--;
            if(find_set(u) == find_set(v)) cout<<'Y'<<endl;
            else cout<<'N'<<endl;
        }
        cout<<'-'<<endl;
    }

    return 0;
}


