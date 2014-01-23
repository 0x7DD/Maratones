# include <bits/stdc++.h>

using namespace std;

int main(){
    int targetScore;
    while(cin >> targetScore){
        int myGames [9][2];
        int tenthGame [1][3];
        myGames[9][0] = 0;
        myGames[9][1] = 0;
        tenthGame[10][0] = 0;
        tenthGame[10][1] = 0;
        tenthGame[10][2] = 0;
        for(int i = 0; i < 8; ++i){
            int a,b = 0;
            cin >> a;
            if(a != 10)
                cin >> b;
            myGames[i][0] = a;
            myGames[i][1] = b;
        }
        int partialScore = 0;
        for(int i = 0; i < 8; ++i){
            if(myGames[i][0] == 10){
                if(i == 7) partialScore = myGames[i][0];
                else partialScore += myGames[i][0] + myGames[i+1][0] + ((myGames[i+1][0] == 10)? mygames[i+2][0]:myGames[i+1][1]);
            }
            else if(myGames[i][0] + myGames[i][1] == 10)
                partialScore += myGames[i][0] + myGames[i+1][0];
            else
                partialScore += myGames[i][0] + myGames[i][1];
            
        }
    }
    return 0;    
}
