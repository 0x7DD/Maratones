using namespace std;
#include<bits/stdc++.h>
#define D(x) cout<< #x " = "<<(x)<<endl

char voc[] = {'a','i','y','e','o','u'};
char con[] = {'b', 'k', 'x', 'z' ,'n', 'h', 'd', 'c', 'w', 'g', 'p', 'v', 'j', 'q', 't', 's', 'r', 'l', 'm', 'f'};

int getv(char c){
    for(int i = 0; i< 6; ++i){
        if(c==voc[i])return i;
    }
}

int getc(char c){
    for(int i = 0; i< 20; ++i){
        if(c==con[i])return i;
    }
}

int a(char x){
    return (x=='a' or x=='e' or x=='i'  or x=='y' or x=='o' or x=='u');
}

string solve(string cad){
    string lo = cad;
    string ans = cad;
    for(int i = 0; i <lo.size(); ++i)lo[i] = tolower(lo[i]);
    for(int i = 0; i< cad.size(); ++i){
        if(!isalpha(cad[i])){
            ans[i] = cad[i];
            continue;
        }
        if(a(lo[i])){
            ans[i] = voc[(getv(lo[i]) + 3)%6];
        }else{
            ans[i] = con[(getc(lo[i]) + 10)%20];
        }
        if(isupper(cad[i])) ans[i] = toupper(ans[i]);
    }
    return ans;
}

int main(){
    string cad;
    while(getline(cin,cad)){
        cout<<solve(cad)<<endl;
    }
    return 0;
}


