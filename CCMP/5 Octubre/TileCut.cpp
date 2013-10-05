using namespace std;
#include<bits/stdc++.h>
#define D(x) cout<< #x " = "<<(x)<<endl

vector<string> mat;

typedef long long LL;

struct Edge {
  int from, to, cap, flow, index;
  Edge(int from, int to, int cap, int flow, int index) :
    from(from), to(to), cap(cap), flow(flow), index(index) {}
};

struct PushRelabel {
  int N;
  vector<vector<Edge> > G;
  vector<LL> excess;
  vector<int> dist, active, count;
  queue<int> Q;

  PushRelabel(int N) : N(N), G(N), excess(N), dist(N), active(N), count(2*N) {}

  void AddEdge(int from, int to, int cap) {
    G[from].push_back(Edge(from, to, cap, 0, G[to].size()));
    if (from == to) G[from].back().index++;
    G[to].push_back(Edge(to, from, 0, 0, G[from].size() - 1));
  }

  void Enqueue(int v) { 
    if (!active[v] && excess[v] > 0) { active[v] = true; Q.push(v); } 
  }

  void Push(Edge &e) {
    int amt = int(min(excess[e.from], LL(e.cap - e.flow)));
    if (dist[e.from] <= dist[e.to] || amt == 0) return;
    e.flow += amt;
    G[e.to][e.index].flow -= amt;
    excess[e.to] += amt;    
    excess[e.from] -= amt;
    Enqueue(e.to);
  }
  
  void Gap(int k) {
    for (int v = 0; v < N; v++) {
      if (dist[v] < k) continue;
      count[dist[v]]--;
      dist[v] = max(dist[v], N+1);
      count[dist[v]]++;
      Enqueue(v);
    }
  }

  void Relabel(int v) {
    count[dist[v]]--;
    dist[v] = 2*N;
    for (int i = 0; i < G[v].size(); i++) 
      if (G[v][i].cap - G[v][i].flow > 0)
	dist[v] = min(dist[v], dist[G[v][i].to] + 1);
    count[dist[v]]++;
    Enqueue(v);
  }

  void Discharge(int v) {
    for (int i = 0; excess[v] > 0 && i < G[v].size(); i++) Push(G[v][i]);
    if (excess[v] > 0) {
      if (count[dist[v]] == 1) 
	Gap(dist[v]); 
      else
	Relabel(v);
    }
  }

  LL GetMaxFlow(int s, int t) {
    count[0] = N-1;
    count[N] = 1;
    dist[s] = N;
    active[s] = active[t] = true;
    for (int i = 0; i < G[s].size(); i++) {
      excess[s] += G[s][i].cap;
      Push(G[s][i]);
    }
    
    while (!Q.empty()) {
      int v = Q.front();
      Q.pop();
      active[v] = false;
      Discharge(v);
    }
    
    LL totflow = 0;
    for (int i = 0; i < G[s].size(); i++) totflow += G[s][i].flow;
    return totflow;
  }
};

int dx[] = {0,0,1,-1};
int dy[] = {1,-1,0,0};

long long solve(){
    int n = mat.size();
    int m = mat[0].size();
    
    /*for(int i = 0 ; i <n; ++i){
        for(int j = 0; j< m; ++j)
            cout<<mat[i][j]<<" ";
        cout<<endl;
    }*/
        
    int _id = 0 ;
    int source = _id++;
    int sink = _id++;
    vector<vector<int> > node(n, vector<int>(m,0));
    vector<vector<int> > iout(n, vector<int>(m,0));
    
    for(int i = 0 ; i <n; ++i){
        for(int j = 0; j< m; ++j){
            node[i][j] = _id++;
            if(mat[i][j] == 'I'){
                iout[i][j] = _id++;
            }
        }
    }
    
    PushRelabel graph(_id);
    
    for(int i = 0 ; i <n; ++i)
        for(int j = 0; j< m; ++j)
            if(mat[i][j] == 'W'){
                graph.AddEdge(source, node[i][j], 1);
                for(int  k = 0; k < 4; ++k){
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if(x >= 0 and x < n  and y >= 0  and y < m)
                        if(mat[x][y] == 'I')
                            graph.AddEdge(node[i][j], node[x][y],1);
                    
                }
            }
    
    for(int i = 0 ; i <n; ++i)
        for(int j = 0; j< m; ++j)
            if(mat[i][j] == 'I'){
                graph.AddEdge(node[i][j],iout[i][j],1);
                for(int  k = 0; k < 4; ++k){
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if(x >= 0 and x < n  and y>= 0  and y < m)
                        if(mat[x][y] == 'N')
                            graph.AddEdge(iout[i][j], node[x][y], 1);
                }
            }
    
    for(int i = 0 ; i <n; ++i)
        for(int j = 0; j< m; ++j)
            if(mat[i][j] == 'N')
                graph.AddEdge(node[i][j], sink,1);

    return graph.GetMaxFlow(source,sink);
}


int main(){
    string line;
    
    while(getline(cin,line)){
        int blank = 1;
        for(int i = 0; i< line.size(); ++i){
            if(line[i] == 'W' or line[i] == 'I' or  line[i] == 'N' )
                blank = 0;
        }
        if(blank){
            cout<<solve()<<endl;
            mat.clear();
        }else{
            mat.push_back(line);
        }
    }
    if(mat.size()!= 0)cout<<solve()<<endl;
    return 0;
}


