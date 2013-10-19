using namespace std;
#include<bits/stdc++.h>
#define D(x) cout<< #x " = "<<(x)<<endl

template<class T> string to_str(T t){
    stringstream ss;
    ss<<t;
    return ss.str();
}

string to_bin(long long a){
    string cad;
    while(a){
        if(a%2)
            cad = "1" + cad;
        else 
            cad = "0" + cad;
        a/=2;
    }
    
    return cad;
}

string number;

long long dp[64][64][2];

long long solve(int index, int cuenta,int tope){
    if(dp[index][cuenta][tope]!=-1)return dp[index][cuenta][tope];
    if(index == number.size()){
        if(cuenta != 0 and cuenta%3==0) return dp[index][cuenta][tope] = 1;
        return dp[index][cuenta][tope] = 0;
    }
    
    long long ans = 0 ;
    if(!tope or number[index]=='1'){
        ans +=  solve(index + 1,cuenta + 1, tope and number[index]=='1');
    }
    ans += solve(index +1 , cuenta, tope and number[index]=='0');
     
    return dp[index][cuenta][tope] = ans;
}

int main(){
    long long num;
    while(cin>>num){
        memset(dp,-1,sizeof dp);
        number = to_bin(num);
        cout<<"Day "<<num<<": Level = ";
        cout<<solve(0,0,1)<<endl;
    }
    return 0;
}


