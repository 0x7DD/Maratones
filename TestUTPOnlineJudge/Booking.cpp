using namespace std;
#include<bits/stdc++.h>
#define ___ ios_base::sync_with_stdio(false);cin.tie(NULL);
#define D(x) cout<< #x " = "<<(x)<<endl


//
// Time - Leap years
//
// A[i] has the accumulated number of days from months previous to i
const int A[13] = { 0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
// same as A, but for a leap year
const int B[13] = { 0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };
// returns number of leap years up to, and including, y
int leap_years(int y) { return y / 4 - y / 100 + y / 400; }
bool is_leap(int y) { return y % 400 == 0 || (y % 4 == 0 && y % 100 != 0); }
// number of days in blocks of years
const int p400 = 400*365 + leap_years(400);
const int p100 = 100*365 + leap_years(100);
const int p4 = 4*365 + 1;
const int p1 = 365;
int date_to_minutes(int d, int m, int y, int h, int min){
    return (((y - 1) * 365 + leap_years(y - 1) + (is_leap(y) ? B[m] : A[m]) + d)*24 + h)*60 + min ;
}


struct date{
    int a,b;
    date(int x, int y): a(x), b(y){}
    
    bool operator < (const date &o) const {
        return a < o.a;
    }  
};

int main(){
    int t;
    while(cin>>t){
        while(t--){
            int re, limp;
            cin>>re>>limp;
            vector<date> dates;
            for(int i = 0; i< re; ++i){
                string nada;cin>>nada;
                int y,m,d,h,min;
                scanf("%d-%d-%d",&y,&m,&d);        
                scanf("%d:%d",&h,&min);
                //D(date_to_minutes(d,m,y,h,min));
                dates.push_back(date(date_to_minutes(d,m,y,h,min),1));
                scanf("%d-%d-%d",&y,&m,&d);        
                scanf("%d:%d",&h,&min);
                //D(date_to_minutes(d,m,y,h,min)+limp);
                dates.push_back(date(date_to_minutes(d,m,y,h,min)+limp,0));
            }    
            sort(dates.begin(),dates.end());
            map<int,int> mp;
            int _index = 0;
            vector<int> ans(10001,0);
            for(int i = 0; i < dates.size(); ++i ){
                if(mp.count(dates[i].a) == 0){
                    mp[dates[i].a] = _index++;
                }
                ans[mp[dates[i].a]] += ((dates[i].b)? 1: -1);
            }
            
            int r = ans[0];
            for(int i = 1; i< 10001; ++i){
                ans[i] = ans[i-1] + ans[i];
                r = max(r,ans[i]);
            }
            cout<<r<<endl;
        }
    }
    return 0;
}

