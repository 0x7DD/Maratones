using namespace std;
# include <cstdio>
# include <cstdlib>
# include <iostream>
# include <cstring>

int main(){
    int n; cin >> n;
    string a;
    string b;
    cin >> a;
    cin >> b;
    string anegada = a;
    for(int i = 0; i < a.size(); ++i){
        if(anegada[i] == '1') anegada[i]='0';
        else if(anegada[i] == '0') anegada[i] = '1';
    }
    if(n%2 == 0){
        if(a == b) cout<<"Deletion succeeded"<<endl;
        else cout<<"Deletion failed"<<endl;
    }
    else{
        if(anegada == b) cout<<"Deletion succeeded"<<endl;
        else cout<<"Deletion failed"<<endl;
    }
    return 0;    
}

