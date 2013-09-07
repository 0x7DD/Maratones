using namespace std;
#include<bits/stdc++.h>
#define foreach(i,x) for(typeof(x.begin()) i =  x.begin(); i != x.end(); ++i)
#define D(x) cout<< #x " = "<<(x)<<endl

template<class T>
int to_int(T t){ stringstream ss; ss<<t; int r;ss>>r; return r;}

typedef long long LL;

LL inf = 1000000;

struct Edge{
    int from, to, cap, flow, index;
    Edge(int from, int to, int cap, int flow, int index): from(from), to(to), cap(cap), flow(flow), index(index){}
};

struct PushRelabel{
    int N;
    vector<vector<Edge> > G;
    vector<LL> excess;
    vector<int> dist, active,count;
    queue<int> Q;

    PushRelabel(int N): N(N), G(N),excess(N), dist(N), active(N), count(2*N){}
    
    void AddEdge(int from, int to, int cap){
        G[from].push_back(Edge(from, to, cap, 0, G[to].size()));
        if(from == to) G[from].back().index++;
        G[to].push_back(Edge(to, from, 0,0, G[from].size() - 1));
    }
    
    void Enqueue(int v){
        if(!active[v] && excess[v] > 0){active[v] = true; Q.push(v);}
    }
    
    void Push(Edge &e){
        int amt = int(min(excess[e.from], LL(e.cap - e.flow)));
        if(dist[e.from] <= dist[e.to] || amt == 0) return;
        e.flow += amt;
        G[e.to][e.index].flow -= amt;
        excess[e.to] += amt;
        excess[e.from] -= amt;
        Enqueue(e.to);
    }
    
    void Gap(int k){
        for(int v = 0; v< N; ++v){
            if(dist[v] < k) continue;
            count[dist[v]]--;
            dist[v] = max(dist[v], N+1);
            count[dist[v]]++;
            Enqueue(v);
        }
    }
    
    void Relabel(int v){
        count[dist[v]]--;
        dist[v] = 2*N;
        for(int i = 0; i< G[v].size(); ++i)
            if(G[v][i].cap - G[v][i].flow > 0 )
                dist[v] =  min(dist[v], dist[G[v][i].to] +1);
        count[dist[v]]++;
        Enqueue(v);
    }
    
    void Discharge(int v){
        for(int i = 0; excess[v] > 0 && i< G[v].size(); ++i) Push(G[v][i]);
        if(excess[v]  > 0){
            if(count[dist[v]] == 1)
                Gap(dist[v]);
            else
                Relabel(v);
        }
    }
    
    LL GetMaxFlow(int s, int t){
        count[0] = N-1;
        count[N] = 1;
        dist[s] = N;
        active[s] = active[t] = true;
        for(int i = 0; i< G[s].size(); ++i){
            excess[s] += G[s][i].cap;
            Push(G[s][i]);
        }
        
        while(!Q.empty()){
            int v = Q.front();
            Q.pop();
            active[v] = false;
            Discharge(v);
        }
        
        LL totflow = 0;
        for(int i = 0; i< G[s].size(); ++i) totflow +=  G[s][i].flow;
        return totflow;
    }
};

struct info{
    int from, to, cap;
    string name;
    info(){}  
};


int main(){
    int n;
    string line;
    getline(cin, line);
    n = to_int(line);
        
    for(int i = 0 ; i< n; ++i){
        int nodes,edges;
        getline(cin, line);
        stringstream ss(line);
        ss>>nodes>>edges;
        
        vector<info> todo(edges);
        set<string> streets;
        
        PushRelabel original(nodes);
        
        for(int j = 0; j< edges; ++j){
            info &t = todo[j];
            getline(cin,line);
            int index;
            for(int k = 0;  k < line.size(); ++k){
                if(line[k]==','){
                    index = k;
                    line[k] = ' ';
                 }
            }
           
            
            t.name = line.substr(index+1);
            stringstream ss(line);
            ss>>t.from>>t.to>>t.cap;
            
            original.AddEdge(t.from,t.to,t.cap);
            streets.insert(t.name);
            
        }
        
        LL original_flow = original.GetMaxFlow(0,nodes-1);
        LL ans = -(1<<30);
        string cad;
        
        /*
        for(int j = 0; j < edges; ++j){
            D(todo[j].from);
            D(todo[j].to);
            D(todo[j].cap);
            D(todo[j].name);
            
        }*/
        
        foreach(s, streets){
            string mio = *s;
            PushRelabel actual(nodes);
            for(int j = 0; j< edges; ++j){
                if(todo[j].name == mio){
                    //D(todo[j].name);
                    actual.AddEdge(todo[j].from, todo[j].to,inf);
                }else{
                    //D(todo[j].name);
                    actual.AddEdge(todo[j].from, todo[j].to,todo[j].cap);
                }
            }
            LL temporal = actual.GetMaxFlow(0,nodes-1);
            if((temporal - original_flow) > ans ){
                ans = temporal - original_flow;
                cad = mio;
            }
            
        }
        cout<<cad<<" "<<ans<<endl;
            
    }

    return 0;
}
