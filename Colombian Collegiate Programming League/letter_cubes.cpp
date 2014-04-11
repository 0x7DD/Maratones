# include <bits/stdc++.h> 

using namespace std;

map< char, set<int> > different_cubes;
set<char> letters;
string my_letters;
int K;
vector<string> solution;

string to_string(char a){
	stringstream ss;
	string s;
	ss << a;
	ss >> s;
	return s;
}

void solve(int idx, vector<string> &tmp){
	if(idx == 6*K){
		solution = tmp;
		return;
	}
	else{
		for(int i = 0; i < tmp.size(); ++i){
			bool valid_cube =  true;
			for(int j = 0; j < tmp[i].size(); ++j){
				if(different_cubes[my_letters[idx]].find(tmp[i][j]) != different_cubes[my_letters[idx]].end()){
					valid_cube = false;
					break;
				}
			}
			if(valid_cube){
				tmp[i].push_back(my_letters[idx]);
				solve(idx + 1, tmp);
				tmp[i].erase(tmp[i].size() - 1); 
			}
		}
	}
	if(tmp.size() < K){
		string new_word = to_string(my_letters[idx]);
		tmp.push_back(new_word);
		solve(idx + 1, tmp);
		tmp.pop_back();
	}

}

int main(){
	int n; char other;
	while(cin >> n){
		if(n == 0)break;
		cin >> other;
		letters.clear();
		my_letters.clear();
		different_cubes.clear();
		solution.clear();
		if(other != '-') letters.insert(other);
		for(int i = 0; i < n; ++i){
			string word; cin >> word;
			K = word.size();
			for(int j = 0; j < word.size(); ++j){
				letters.insert(word[j]);
				for(int k = 0; k < word.size(); ++k){
					if(k != j){
						different_cubes[word[j]].insert(word[k]);
						different_cubes[word[k]].insert(word[j]);
					}
				}
			}
		}
		my_letters = string(letters.begin(), letters.end());
		vector<string> my_sol;
		solve(0, my_sol);
		for(int i = 0; i < solution.size(); ++i)
			cout<<solution[i]<<((i != solution.size() - 1)? " ":"");
		cout<<endl;
	}
	
	return 0;
}
