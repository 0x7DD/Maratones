using namespace std;
#include<bits/stdc++.h>
#define For(i,n) for(int i = 0; i< (n); ++i)
#define rall(x) x.rbegin(),x.rend()
#define all(x) x.begin(),end()
#define D(x) cout << #x << "=" << (x) <<endl;
int main(){
    int n;
    while(cin>>n){
        vector<int> malos(n);
        vector<int> buenos(n);
        For(i,n)cin>>malos[i];
        For(i,n)cin>>buenos[i];
        sort(rall(malos));
        sort(rall(buenos));
        int i = 0,j = 0, ans = 0;
        while(i != n and j != n){
            if(buenos[i] > malos[j]){
                i++;j++;ans++;
            }
            else j++;
        }
        cout<<ans<<endl;
    }
    return 0;
}
