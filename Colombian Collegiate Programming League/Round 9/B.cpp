# include <cstdio>
# include <cstdlib>
# include <iostream>
# include <map>
# include <vector>
#define D(x) cout<<#x " = "<<(x)<<endl
using namespace std;

int main(){
    int n;
    while(cin >> n){
        string b;
        int a;
        map< string,int > mymap;
        for(int i = 1; i <= n; ++i){
            cin >> b;
            mymap[b] = i;  
        }    
        cin >> a;
        
        for(int i = 0; i < a; ++i){
            vector<string> mio;
            string word;
            while(word != "-1"){
                cin >> word;
                if(word=="-1")continue;
                if(!mymap[word]) mio.push_back(word);            
            }
            if(mio.size()!=0){
                cout<<"Email "<<i+1<<" is not spelled correctly."<<endl;
                for(int j = 0; j < mio.size(); ++j)
                    cout<<mio[j]<<endl;
            }
            else cout<<"Email "<<i+1<<" is spelled correctly."<<endl;
        }
        cout<<"End of Output"<<endl;
    }
    return 0;
}
