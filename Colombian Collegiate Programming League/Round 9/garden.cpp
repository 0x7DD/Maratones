using namespace std;
#include<queue>
#include<map>
#include<cmath>
#include<iostream>
#include<cstring>
#define For(i,n) for(int i  =0 ; i< (n); ++i)
#define D(x) cout<< #x " = "<<(x)<<endl
#define MP 501
            
          // N, E, S, W 
int dx1[] = {1,0,-1,0, -1,-1,1,1};
int dy1[] = {0,1,0,-1, -1,1,-1,1};
int dx[] = {2,0,-2,0};
int dy[] = {0,2,0,-2};
int grid[MP][MP];
int visited[MP][MP];
int ans;

void flood_fill(int x, int y){
    queue<pair<int,int> > q;
    
    q.push(make_pair(x,y));
    
    while(!q.empty()){
        int ax = q.front().first;
        int ay = q.front().second;
        q.pop();
        
        if(visited[ax][ay])continue;
        visited[ax][ay] = 1;
        For(i,4){
            int nx = ax + dx1[i];
            int ny = ay + dy1[i];
            if(nx >= 0 and nx < MP and ny >= 0 and ny < MP){
                if(!grid[nx][ny])
                q.push(make_pair(nx,ny));
                else visited[nx][ny] = 1;
            }
        }
    }    
}

void solve2(int x, int y){
    queue<pair<int,int> > q;
    
    q.push(make_pair(x,y));
    
    while(!q.empty()){
        int ax = q.front().first;
        int ay = q.front().second;
        q.pop();
        
        if(visited[ax][ay])continue;
        visited[ax][ay] = 1;
        ans++;
        For(i,4){
            int nx = ax + dx[i];
            int ny = ay + dy[i];
            if(nx >= 0 and nx < MP and ny >= 0 and ny < MP){
                q.push(make_pair(nx,ny));
            }
        }
        For(i,8){
            int nx = ax + dx1[i];
            int ny = ay + dy1[i];
            if(nx >= 0 and nx < MP and ny >= 0 and ny < MP){
                visited[nx][ny] = 1;
            }
        }
       
    }    
}

void solve(){
    For(i,MP)
        For(j,MP)
            if(!visited[i][j] and !grid[i][j])
                solve2(i,j);//ans++;
}

int main(){ 
    int tc;cin>>tc;
    For(tid,tc){
        memset(grid,0,sizeof grid);
        memset(visited,0,sizeof visited);
        ans = 0 ;
        int x,y;
        cin>>x>>y;
        x++;y++;
        grid[x][y] = 1;
        int p;cin>>p;
        char anterior = 'K';
        For(i,p){
            char c;
            int cuanto;
            cin>>c>>cuanto;
            int index;
            if(c=='N')index = 0;
            if(c=='E')index = 1;
            if(c=='S')index = 2;
            if(c=='W')index = 3;
            if(cuanto < 0){
                if(c=='N')index = 2;
                if(c=='E')index = 3;
                if(c=='S')index = 0;
                if(c=='W')index = 1;
            }
            cuanto = fabs(cuanto);
            cuanto *= 2;
            For(j,cuanto){
                x+=dx1[index];
                y+=dy1[index];
                grid[x][y] = 1;
            }
            
        }
        
        For(i,MP){
            if(!visited[0][i])flood_fill(0,i);
            if(!visited[MP-1][i])flood_fill(MP-1,i);
            if(!visited[i][0])flood_fill(i,0);
            if(!visited[i][MP-1])flood_fill(i,MP-1);
        }
                
        
        solve();
        

        cout<<"Data Set "<<tid+1<<": ";
        cout<<ans<<" square feet."<<endl;
    }
    cout<<"End of Output"<<endl;
}
