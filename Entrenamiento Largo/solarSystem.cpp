# include <bits/stdc++.h>
# define pi acos(-1.0)
using namespace std;

double a1,b1,t1,a2,b2,t,e2;

#include <iostream>
#include <vector>
#include <cmath>
#include <cassert>

using namespace std;

const double INF = 1e100;
const double EPS = 1e-5;
const double N = 10000.0;


struct PT {
  double x, y;
  PT() {}
  PT(double x, double y) : x(x), y(y) {}
  PT(const PT &p) : x(p.x), y(p.y) {}
  PT operator + (const PT &p) const { return PT(x+p.x, y+p.y); }
  PT operator - (const PT &p) const { return PT(x-p.x, y-p.y); }
  PT operator * (double c) const { return PT(x*c, y*c ); }
  PT operator / (double c) const { return PT(x/c, y/c ); }
};

// rotate a point CCW or CW around the origin
PT RotateCCW90(PT p) { return PT(-p.y,p.x); }
PT RotateCW90(PT p) { return PT(p.y,-p.x); }
PT RotateCCW(PT p, double t) {
  return PT(p.x*cos(t)-p.y*sin(t), p.x*sin(t)+p.y*cos(t));
}

double dot(PT p, PT q) { return p.x*q.x+p.y*q.y; }
double dist2(PT p, PT q) { return dot(p-q,p-q); }
double cross(PT p, PT q) { return p.x*q.y-p.y*q.x; }
ostream &operator<<(ostream &os, const PT &p) {
  os << "(" << p.x << "," << p.y << ")";
}



double elipseAt(double x){
    return (b2/a2)*sqrt((a2*a2) - (x*x));
}

double polarElipseAt(double angle){
    return (a2*(1.0 - (e2*e2)))/(1.0 + e2*cos(angle));
}

double myFun(double angle1, double angle2){
    double delta = (angle2 - angle1)/N;
    double area = 0.0;
    while(angle1 < angle2){
        double w = angle1 + (delta/2.0);
        area += 0.5*(polarElipseAt(w)*polarElipseAt(w))*delta;
        angle1 += delta;
    }
    return area;
}


int main(){
    int cont = 0;
    while(cin >> a1 >> b1 >> t1 >> a2 >> b2 >> t and (a1 + b1 + t1 + a2 + b2 + t)){
    
        double t2 = t1*sqrt((a2/a1)*(a2/a1)*(a2/a1));
        double c2 = sqrt(a2*a2 - b2*b2);
        e2 = sqrt(1.0 - ((b2*b2)/(a2*a2)));
        double totalarea = pi*a2*b2;
        double sectionPerDay = totalarea/t2;
        double target = ((t <= t2)? t:fmod(t,t2))*sectionPerDay;//if the time to determine the position of p2 is greater than t2 then the time is t%t2.
        double low = 0.0;
        double high =4.0*pi;
        double val = high;
        //cout<<target<<endl;
        while(true){
            val = (low + ((high - low)/2.0));
            double eval = myFun(0.0,val);
            //cout<<val<<" "<<target<<" "<<eval<<endl;
            if(fabs(eval - target) < EPS) break;
            if(eval < target)low = val;
            else high = val;
        }
        if(fabs(val - pi) < EPS){
                printf("Solar System %d: %.3lf %.3lf\n", cont++, -a2, 0.000);
                continue;        
        }
        if(fabs(val - 2.0*pi) < EPS){
                printf("Solar System %d: %.3lf %.3lf\n", cont++, a2, 0.000);
                continue;        
        }
        if(fabs(val - (pi/2.0)) < EPS){
                printf("Solar System %d: %.3lf %.3lf\n", cont++, b2, 0.000);
                continue;        
        }
        if(fabs(val - ((3.0*pi)/2.0)) < EPS){
                printf("Solar System %d: %.3lf %.3lf\n", cont++, -b2, 0.000);
                continue;        
        }
        //computing the solution after finding the angle which subtends the area that i'm looking for
        double A = ((tan(val)*tan(val)) + ((b2*b2)/(a2*a2)));
        double B = 2.0*tan(val)*tan(val)*c2;
        double C = ((tan(val)*tan(val)*c2*c2)) - (b2*b2);
        double X1 = (-B + sqrt(B*B - (4.0*A*C)))/(2.0*A);//compunting the possible values of x, must be two.
        double X2 = (-B - sqrt(B*B - (4.0*A*C)))/(2.0*A);
        
        //if(fabs(val - pi) < EPS)
        //computing the positive values of y
        double Y1 = elipseAt(X1);
        double Y2 = elipseAt(X2);
        
        double Y11 = -Y1;
        double Y22 = -Y2;
        
        PT ans;
        
        if(val > 0.0 && val < pi){
            PT p1 = PT(X1, Y1);
            PT p2 = PT(X2, Y2);
            PT focus = PT(-c2,0.0);
            p1 = p1 - focus;
            p2 = p2 - focus;
            PT mio1 = PT(X1, 0.0);
            PT mio2 = PT(X2, 0.0);
            if(dot(RotateCCW(mio1, val), p1) < EPS) ans = PT(X1, Y1);
            else if(dot(RotateCCW(mio2, val), p2) < EPS) ans = PT(X2, Y2);
        }
        else if(val > pi && val < 2.0*pi){
            PT p1 = PT(X1, Y11);
            PT p2 = PT(X2, Y22);
            PT focus = PT(-c2,0.0);
            p1 = p1 - focus;
            p2 = p2 - focus;
            PT mio1 = PT(X1, 0.0);
            PT mio2 = PT(X2, 0.0);
            if(dot(RotateCCW(mio1, val), p1) < EPS) ans = PT(X1, Y11);
            else if(dot(RotateCCW(mio2, val), p2) < EPS) ans = PT(X2, Y22);
        }
        cout<<"Solar System "<<cont++<<": "<<ans.x<<" "<<ans.y<<endl;
    }
    return 0;
}
