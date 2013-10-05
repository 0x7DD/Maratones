using namespace std;
#include<bits/stdc++.h>

double solve(double r){
    double side = 2.0*(r + sqrt(3.0)*r + sqrt(2.0)*r);
    return side*side/2.0;
}

int main(){
    int n;cin>>n;
    for(int i = 0 ; i< n; ++i){
        double r;cin>>r;
        
        printf("%.5lf\n",solve(r));
    }
        
    return 0;
}
