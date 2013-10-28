using namespace std;
#include<bits/stdc++.h>
#define ___ ios_base::sync_with_stdio(false);cin.tie(NULL);
#define foreach(x, v) for (typeof (v).begin() x=(v).begin(); x !=(v).end(); ++x)
#define D(x) cout << #x " is " << x << endl

const int MAXS = 1000010; 
const int MAXC = 26; // Number of characters in the alphabet.

vector<int> out[MAXS]; // Output for each state, as a bitwise mask.
                       // Bit i in this mask is on if the keyword with index i appears when the
                       // machine enters this state.

// Used internally in the algorithm.
int f[MAXS]; // Failure function
int g[MAXS][MAXC]; // Goto function, or -1 if fail.

// Builds the string matching machine.
//
// words - Vector of keywords. The index of each keyword is important:
// "out[state] & (1 << i)" is > 0 if we just found word[i] in the text.
// lowestChar - The lowest char in the alphabet. Defaults to 'a'.
// highestChar - The highest char in the alphabet. Defaults to 'z'.
// "highestChar - lowestChar" must be <= MAXC, otherwise we will
// access the g matrix outside its bounds and things will go wrong.
//
// Returns the number of states that the new machine has.
// States are numbered 0 up to the return value - 1, inclusive.
int buildMatchingMachine(const vector<string> &words, char lowestChar = 'a', char highestChar = 'z') {

    for(int i = 0 ; i< MAXS; ++i)
        out[i].clear();
        
    memset(f, -1, sizeof f);
    memset(g, -1, sizeof g);
    
    int states = 1; // Initially, we just have the 0 state
        
    for (int i = 0; i < words.size(); ++i) {
        const string &keyword = words[i];
        int currentState = 0;
        for (int j = 0; j < keyword.size(); ++j) {
            int c = keyword[j] - lowestChar;
            if (g[currentState][c] == -1) { // Allocate a new node
                g[currentState][c] = states++;
            }
            currentState = g[currentState][c];
        }
        //out[currentState].set(i); // There's a match of keywords[i] at node currentState.
        out[currentState].push_back(i);
    }
    
    // State 0 should have an outgoing edge for all characters.
    for (int c = 0; c < MAXC; ++c) {
        if (g[0][c] == -1) {
            g[0][c] = 0;
        }
    }

    // Now, let's build the failure function
    queue<int> q;
    for (int c = 0; c <= highestChar - lowestChar; ++c) { // Iterate over every possible input
        // All nodes s of depth 1 have f[s] = 0
        if (g[0][c] != -1 and g[0][c] != 0) {
            f[g[0][c]] = 0;
            q.push(g[0][c]);
        }
    }
    while (q.size()) {
        int state = q.front();
        q.pop();
        for (int c = 0; c <= highestChar - lowestChar; ++c) {
            if (g[state][c] != -1) {
                int failure = f[state];
                while (g[failure][c] == -1) {
                    failure = f[failure];
                }
                failure = g[failure][c];
                f[g[state][c]] = failure;
                //out[g[state][c]] |= out[failure]; // Merge out values
                out[g[state][c]].insert(out[g[state][c]].end(), out[failure].begin(), out[failure].end());
                q.push(g[state][c]);
            }
        }
    }

    return states;
}


int findNextState(int currentState, char nextInput, char lowestChar = 'a') {
    int answer = currentState;
    int c = nextInput - lowestChar;
    while (g[answer][c] == -1) answer = f[answer];
    return g[answer][c];
}


vector<int> graph[10001];
int dp[10001];

int solve(int node){
    if(dp[node] != -1) return dp[node];
    int ans = 1;
    for(int i = 0; i< graph[node].size(); ++i)
        ans = max(ans,solve(graph[node][i])+1);
    return dp[node] = ans;
}


int main(){ ___
    
    int n_words;
    
    while(cin>>n_words and n_words){
                
        vector<string> keywords(n_words);
        
        
                
        for(int i = 0 ; i< n_words; ++i){
            cin>>keywords[i];
            graph[i].clear();
        }
        memset(dp,-1,sizeof dp);
        
        buildMatchingMachine(keywords, 'a', 'z');
        
        for(int i = 0; i< n_words; ++i){
            int state = 0;
            for(int j = 0; j < keywords[i].size(); ++j){
                state = findNextState(state,keywords[i][j]);
                for(int k = 0; k < out[state].size(); ++k){
                    int to = out[state][k];
                    if(keywords[to].size() < keywords[i].size()) // don't connect yourself.
                        graph[i].push_back(to);
                }
            }
        }
        
        
        int ans = 0;
        for(int i = 0; i < n_words; ++i)
            ans = max(ans,solve(i));
            
        cout<<ans<<endl;
   }
   
   return 0;
   
}
