// http://algospot.com/judge/problem/read/MINASTIRITH
// category: greedy

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <map>
#include <queue>
#include <time.h>
#include <string>
#include <sstream>
#include <string.h>
#include <stack>
#include <limits>
#include <set>
#include <iostream>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.0000001
#define H_MOD 10000000
#define H_PI 3.14159265
typedef long long ll;

void check(bool ret);


bool gDebug;
int gN;
double gPoint[100][3];
double gAngle[100][2];
int gLen[100];

double getAngle(double x, double y){
    if (fabs(x)<=H_EPSILON) {
        if (y>=0) {
            return 90;
        }else
            return 270;
    }
    if (fabs(y)<=H_EPSILON) {
        if (x>=0) {
            return 0;
        }else
            return 180;
    }
    
    double ret;
    if (x>=0) {
        ret = atan(y/x)*180.0/H_PI;
        if (ret<0) {
            ret += 360;
        }
    }else{
        if (y>0) {
            ret = 180 - atan(-x/y)*180.0/H_PI ;
        }else{
            ret = 180 + atan(x/y)*180.0/H_PI ;
        }
    }
    
    return ret;
}

// assume a!=0
void calc(double a,double b, double r, int i){
    double t1 = r*r-a*a-b*b-64;
    if (fabs(a)<H_EPSILON){
        double y1=-t1/(2*b);
        double x1 = sqrt(64-y1*y1);
        double x2 = -x1;

        printf("%f %f\n", x1, y1);
        printf("%f %f\n", x2, y1);
        return;           
    }

    double a1=1.0+b*b/(a*a);
    double b1 = (b*t1)/(a*a);
    double c1 = t1*t1/(4*a*a)-64;

    double t2 = sqrt(b1*b1-4*a1*c1);    
    double y1 = (-b1+t2)/(2*a1);
    double y2 = (-b1-t2)/(2*a1);

    double x1 = (t1+2*b*y1)/-(2*a);
    double x2 = (t1+2*b*y2)/-(2*a);
    double angle[2];
    angle[0] = getAngle(x1, y1);
    angle[1] = getAngle(x2, y2);
    
    sort(angle, angle+1);
    printf("%f %f\n", angle[0], angle[1]);
    gAngle[i][0] = angle[0];
    double aa = atan2(y1, x1)*180/H_PI;
    gAngle[i][1] = angle[1];
    double aa2 = atan2(y2, x2)*180/H_PI;
    //printf("%f %f\n", x2, y2);
}

int solve(){

    for (int i=0; i<gN; i++) {
        calc(gPoint[i][1], gPoint[i][0], gPoint[i][2], i);
    }
    
    

    
    return 1;
}



void check(bool ret){
    if (ret==false) {
        printf("failed\n");
    }
}

void check(int expected, int actual){
    if (expected!=actual) {
        printf("failed expected=%d, actual=%d\n", expected, actual);
    }
}

void check(char expected, char actual){
    if (expected!=actual) {
        printf("failed expected=%c, actual=%c\n", expected, actual);
    }
}

void check(double expected, double actual){
    if (fabs(expected - actual)>H_EPSILON) {
        printf("failed expected=%lf, actual=%lf\n", expected, actual);
    }
}

void test(){

    check(45.0, atan(1.0)*180/H_PI);
    check(-45.0, atan(-1.0)*180/H_PI);
    
    
}

int main(){
    char fn[] = "minastirith.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    test();

    // handling input
    int count, p,j,k,n, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        for ( j = 0; j < gN; j++){            
            scanf("%lf", &gPoint[j][0]);
            scanf("%lf", &gPoint[j][1]);
            scanf("%lf", &gPoint[j][2]);
        }        
        int r = solve();        
        printf("%d\n", r);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

