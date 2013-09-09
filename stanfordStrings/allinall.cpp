# include <string>
# include <iostream>
# include <cstdio>
# include <cstdlib>

using namespace std;

int main(){
    string pattern, other;
    while(cin >> pattern >> other){
        int i = 0,j = 0;
        while(i < pattern.size() && j < other.size()){
            if(pattern[i] == other[j]){
                i++;
                j++;
            }
            else j++;
        }
        if(i == pattern.size()) cout<<"Yes"<<endl;
        else cout<<"No"<<endl;
    }
    return 0;
}
