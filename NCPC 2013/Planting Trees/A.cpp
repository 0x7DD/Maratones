using namespace std;
#include<bits/stdc++.h>
#define D(x) cout<< #x " = "<<(x)<<endl

int main(){
    int n;
    cin>>n;
    vector<int> nums(n);
    for(int i = 0; i < n; ++i)
        cin>>nums[i];
        
    sort(nums.rbegin(), nums.rend());
    int ans = 0;
    for(int i = 0; i < n; ++i)
        ans = max(ans,nums[i]+i+1);
    cout<<ans+1<<endl; 
    return 0;
    
}

