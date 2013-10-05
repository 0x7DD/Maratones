using namespace std;
#include<bits/stdc++.h>
#define D(x) cout<< #x " = "<<(x)<<endl

long long val[] =  {1,2,3,3,4,10};
long long val2[] = {1,2,2,2,3,5,10};
 
int main(){
    int a;cin>>a;
    int ba = 1;
    while(a--){
        long long t, b = 0,m= 0;
        for(int i = 0; i< 6; ++i){
            cin>>t;
            b+=(t*val[i]);
        }
        for(int i = 0; i< 7; ++i){
            cin>>t;
            m+=(t*val2[i]);
        }
        cout<<"Battle "<<ba++<<": ";
        if(b > m){
            cout<<"Good triumphs over Evil"<<endl;
        }else if( m > b){
            cout<<"Evil eradicates all trace of Good"<<endl;
        }else{
            cout<<"No victor on this battle field"<<endl;
        }
    }
    return 0;
}


