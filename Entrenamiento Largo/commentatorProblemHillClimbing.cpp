# include <cstdio>
# include <iostream>
# include <cmath>
# include <vector>
# include <cstdlib>
# include <algorithm>
# define S(x) ((x)*(x))
# define EPS 1e-7
using namespace std;

double x_1,x_2,x_3,y_1,y_2,y_3,r1,r2,r3;
double stepSize = 1.2;
double dx[4]={0.0,1.0,0.0,-1.0},dy[4]={1.0,0.0,-1.0,0.0};

struct PT { 
  double u, v; 
  PT() {}
  PT(double u, double v) : u(u), v(v) {}
  PT(const PT &p) : u(p.u), v(p.v)    {}
  PT operator + (const PT &p)  const { return PT(u+p.u, v+p.v); }
  PT operator - (const PT &p)  const { return PT(u-p.u, v-p.v); }
  PT operator * (double c)     const { return PT(u*c,   v*c  ); }
  PT operator / (double c)     const { return PT(u/c,   v/c  ); }
};

double dot(PT p, PT q)     { return p.u*q.u+p.v*q.v; }
double dist2(PT p, PT q)   { return dot(p-q,p-q); }

double myFun(double x, double y){
    //Error in function of the proportions of the triangles formed by x_1,x_2,x_3,r1,r2,r3
    double d1 = sqrt(S(x - x_1) + S(y - y_1));
    double d2 = sqrt(S(x - x_2) + S(y - y_2));
    double d3 = sqrt(S(x - x_3) + S(y - y_3));
    return  S((d1*r2) - (r1*d2)) + S((d1*r3) - (r1*d3)) + S((d2*r3) - (r2*d3));
}


/*double myFun(double x, double y){
    //Error in function of the proportions of the triangles formed by x_1,x_2,x_3,r1,r2,r3
    double d1 = S(x - x_1) + S(y - y_1);
    double d2 = S(x - x_2) + S(y - y_2);
    double d3 = S(x - x_3) + S(y - y_3);
    return  S((d1*d1*r2*r2) - (r1*r1*d2*d2)) + S((d1*d1*r3*r3) - (r1*r1*d3*d3)) + S((d2*d2*r3*r3) - (r2*r2*d3*d3));
}*/

int main(){
    
    cin >> x_1 >> y_1 >> r1;
    cin >> x_2 >> y_2 >> r2;
    cin >> x_3 >> y_3 >> r3;
    
    //PT current(10000.0, 10000.0);
    PT current((x_1+x_2+x_3)/3.0,(y_1+y_2+y_3)/3.0);
    int cont = 0;
    
    while(stepSize > EPS ){
        double currentEval = myFun(current.u, current.v);
        int best = -1;
        //cout<<stepSize<<endl;
        //if (cont == 1000000) break;
        for(int i = 0; i < 4; ++i){
            double newEval = myFun(current.u + stepSize*dx[i], current.v + stepSize*dy[i]);
            if(newEval < currentEval){
                currentEval = newEval;
                best = i;     
            }
        }
        if(best == -1) stepSize *= 0.7;
        else{
            current.u = current.u + stepSize*dx[best];
            current.v = current.v + stepSize*dy[best];
        }
        cont++;
    }
    if(myFun(current.u, current.v) < 1e-5)      
        printf("%.5lf %.5lf\n", current.u, current.v);
    return 0;
}
