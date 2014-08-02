// http://algospot.com/judge/problem/read/TICTACTOE
// category: dp

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <map>
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
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
char gMap[3][4];
int gCache[1<<9][1<<9];
int gMask[9];

int getEmptyIndex(int v){
    int mask = 1<<0;
    if ((v&mask) != mask){
        return 0;
    }

    mask = 1<<1;
    if ((v&mask) != mask){
        return 1;
    }

    mask = 1<<2;
    if ((v&mask) != mask){
        return 2;
    }

    printf("error\n");
    return 0;
}

// return max matching count, idx = empty slot
// v: me
// o : other
int getCount(int v, int o, int& idx){
    int m[8][3] = {{0,1,2}, {3,4,5}, {6,7,8},
                   {0,3,6}, {1,4,7}, {2,5,8},
                   {0,4,8}, {2,4,6}};
    int maxCount=0;
    bool found2=false;
    int count;
    int emptyIdx;
    int mask;
    idx=-1;
    for (int i = 0; i < 8; i++){
        count=0;
        for (int j = 0; j < 3; j++){
            mask =gMask[m[i][j]];
            if ((v&mask) == mask){
                count++;
            }else if((o&mask) == mask)
                break;
            else
                emptyIdx = m[i][j];
        }
        if (count==3){
            return count;
        }else if (count==2 && !found2){
            found2=true;
            idx=emptyIdx;
        }
        if (count > maxCount) {
            maxCount = count;
        }
    }    
    
    return maxCount;
}

bool isWin(int v ){
    int m[8][3] = {{0,1,2}, {3,4,5}, {6,7,8},
        {0,3,6}, {1,4,7}, {2,5,8},
        {0,4,8}, {2,4,6}};
    
    int mask,count;
    for (int i = 0; i < 8; i++){
        count=0;
        for (int j = 0; j < 3; j++){
            mask =gMask[m[i][j]];
            if ((v&mask) == mask){
                count++;
            }
        }
        if (count==3)
            return true;
    }
    
    return false;
}


// 0: x win, 1:o win, 2:tie
int winner(int x, int y, int count){
    int& ret = gCache[x][y];
    if (ret!=-1){
        return ret;
    }
    int t;
    int turn = count%2;
       
    // check end condition    
    int me = turn==0? x:y;
    int you = turn==0? y:x;
    if (isWin(you)) {
        return !turn;
    }
    if (count>=9)
        return 2; //tie
    
    //ret = (turn+1)%2;
    int cur;
    for (int i = 0; i < 9; i++){
        if ((me&gMask[i])==0 && (you&gMask[i])==0){
            if (turn==0)
                cur = winner(x|gMask[i],y,count+1);
            else
                cur = winner(x,y|gMask[i], count+1);
                
            if (cur==turn){
                ret = turn; // best choice
                break;                
            }else{
                if (ret==-1) {
                    ret = cur;
                }else{
                    if (cur==2) {
                        ret=2;
                    }
                }
                
            }
        }
    }

    //printf("%d,%d,%d=%d\n", x,y,count, ret);
    return ret;
}

void solve(){
    int x, y;
         // 0=x,1=o
    int nth=0;
    int n=0;
    x=y=0;
    for (int i = 0; i < 3; i++){
        for (int j = 0; j < 3; j++){
            if (gMap[i][j]== 'x'){
                x |= 1<<n;
                nth++;
            }else if (gMap[i][j]=='o'){
                y|= 1<<n;
                nth++;
            }
            n++;
        }
    }
   
    int ret = winner(x,y, nth);
    string r;
    if (ret==0) {
        r = "x";
    }else if(ret==1){
        r = "o";
    }else if(ret==2)
        r = "TIE";
    printf("%s\n", r.c_str());
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

void test(){

    int t = 7;
    
    check(2, getCount(9,0,t));
    check(6, t);

    int mask = 1<<0 | 1<<1 | 1<<2;
    t=7;
    check((t&mask)==mask);
    t=8;
    check((t&mask)!=mask);

    
    check(3, getCount(7,0,t));
    check(2, getCount(6,0,t));
    check(0, t);
    
    check(3, getCount(73,0, t));
}

int main(){
    char fn[] = "tictactoe.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }

    for (int i = 0; i < 9; i++){
        gMask[i]=1<<i;
    }

    test();
    memset(gCache, -1, sizeof(gCache));


    // handling input
    int count, p,j,k,n, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {
        scanf("%s", gMap);
        scanf("%s", gMap+1);
        scanf("%s", gMap+2);

        solve();        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

