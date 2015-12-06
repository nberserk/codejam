#include <algorithm>
#ifdef _MSC_VER
#include <io.h>
#define F_OK 0
#else
#include <unistd.h>
#endif
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
#include <list>
#include <limits>
#include <set>
#include <iostream>
#include "../myutil.h"

/*
  N : 500
  
 */
using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001

#define SWAP(a,b) do{int t=a;a=b;b=t;} while(0)

#define HDEBUG
#ifdef HDEBUG
#define hprint(fmt, args...) printf(fmt, ##args)
#else
#define hprint(fmt,args...)
#endif


class Rect{
public:
    Rect(){
        l = r = t = b = -1;
        merged = false;
    }
    int l,r,t,b;
    bool merged;
};


typedef long long ll;
#define N 500
int m[N][N];

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


bool gDebug;

void printMap(int n){
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            printf("%d", m[i][j]);
        }
        printf("\n");
    }
}

void solve(){

    // fill Rect
    
    // merge rect
    // idea : when 2 rect adjacent, check can be merged
    
}

void bp(){
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            m[i][j] = 0;
        }
    }
}


// draw rectangle
void dr(int sx, int sy, int ex, int ey){
    if(sx>ex) SWAP(sx,ex);
    if(sy>ey) SWAP(sy,ey);

    for (int y = sy; y <= ey; y++){
        for (int x = sx; x <= ex; x++){
            m[y][x]=1;
        }
    }
}


struct Point{
    int x,y;
};

/*
if not enclosed by black area, ff ignored.

 */
void ff(int x, int y){
    if(m[y][x]==1) return;
    
    Point s[20000];
    Point path[20000];
    int pc=0;
    s[0].x = x;
    s[0].y = y;
    int sc=1;

    bool v[N][N];
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            v[i][j]=false;
        }
    }

    int dir[4][2] = { {-1,0}, {1,0}, {0,-1}, {0,1}};

    while (sc>0){
        int cx = s[sc-1].x;
        int cy = s[sc-1].y;
        sc--;
        
        if(cx<0 || cy<0 || cx>=N || cy>=N){
            hprint("ff ignored %d,%d\n", x,y);
            return;
        }
        
        if(v[cy][cx]==true || m[cy][cx]==1) continue;
        v[cy][cx]=true;
        path[pc].x = cx;
        path[pc].y = cy;
        pc++;        
        
        for (int i = 0; i < 4; i++){
            int nx = cx + dir[i][0];
            int ny = cy + dir[i][1];

            s[sc].x = nx;
            s[sc].y = ny;
            sc++;
        }        
    }    
    
    for (int i = 0; i < pc; i++){
        m[path[i].y][path[i].x] = 1;
    }
}

void testFF_positive(){
    dr(1,1,1,5);
    dr(1,5,5,5);
    dr(5,5,5,1);
    dr(5,1,1,1);
    printMap(10);

    ff(2,2);
    printMap(10);
}

void testFF_negative(){
    dr(1,1,1,5);
    dr(1,5,5,5);
    dr(5,5,5,1);
    dr(5,1,1,1);
    printMap(10);
    
    ff(0,0);
    printMap(10);
}


int main(){
    string fn = __FILE__;
    size_t pos = fn.find(".cpp");
    fn = fn.substr(0,pos) + ".txt";
    if (access(fn.c_str(), F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn.c_str(), "r", stdin);
    }

    //h_generateRandomMap(100);
    //testFF_negative();
    //precalc();
    
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    char buf[100];
    for (p=0; p<count; p++) {

        int sx,sy,ex,ey;
        while(1){
            scanf("%s", buf);
            if (buf[0] == 'B'){
                bp();
            }else if(buf[0] =='D'){
                scanf("%d %d %d %d", &sx,&sy,&ex,&ey);
                dr(sx,sy,ex,ey);
            }else if(buf[0] =='F'){
                scanf("%d %d", &sx, &sy);
                ff(sx,sy);
            }else if (buf[0] =='E'){// end
                break;
            }
        }        
        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

