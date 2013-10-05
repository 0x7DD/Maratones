using namespace std;
#include<bits/stdc++.h>
#define ___ ios_base::sync_with_stdio(false);cin.tie(NULL);


int points[] = {0,0,0,1,1,2,3,5,11};

struct pos{
    int x, y;
    pos(){}
};

int _distance(pos a,pos b){
    return fabs(a.x - b.x) + fabs(a.y - b.y);
}

int p[110];

int find_set(int x){
    if(x == p[x]) return x;
    else return p[x] = find_set(p[x]);
}

void merge_set(int x, int y){
    p[find_set(x)] = find_set(y);
}

int main(){ ___
    int t;
    while(cin>>t){
        while(t--){
            int n;cin>>n;
            n+=2;
            vector<pos> points(n);
            for(int i = 0; i <n; ++i)
                cin>>points[i].x>>points[i].y;
            
            for(int i = 0; i < n; ++i) p[i] = i;
            
            for(int i = 0; i < n; ++i){
                for(int j = 0 ; j < n; ++j){
                    if(_distance(points[i],points[j]) <= 20*50){
                        merge_set(i,j);
                    }
                }
            }
            
            if(find_set(0)== find_set(n-1))cout<< "happy"<<endl;
            else cout<<"sad"<<endl;
       }
        
    }
    return 0;
}

