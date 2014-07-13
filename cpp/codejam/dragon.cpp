// http://algospot.com/judge/problem/read/DRAGON

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
int gN, gP, gL;
int gCache[501];
int gCount[501];
int gLength[51];


void reconstruct(int start, int k, vector<int>& v){
}

int dragon(string in, int generation)
{
    if(generation==0){
        printf("%s", in.c_str());
        return 1;
    }
    
    int size= in.size();
    for(int i=0;i<size;i++){
        if(in[i]=='X'){
            dragon("X+YF", generation-1);
        }else if(in[i]=='Y'){
            dragon("FX-Y", generation-1);
        }else{
            printf("%c", in[i]);
        }
    }

    return 1;
}

// return kth char 
void dragon_k(string in, int generation, int k)
{
    if (generation<0) {
        return;
    }
    
    int half = gLength[generation];
    
    int size= in.size();
    if(k<size){
        printf("%c", in[k]);
        return;
    }
    for(int i=0;i<size;i++){
        if(in[i]=='X'){
            if(k>half)
                k-=half;
            else
                dragon_k("X+YF", generation-1, k);
        }else if(in[i]=='Y'){
            if(k>half)
                k-=half;
            else
                dragon_k("FX-Y", generation-1, k);            
        }else{
            k--;            
        }
    }
}

void solve(){

    for(int i=0;i<gL;i++){
        dragon_k("FX", gN, gP+i-1);
        printf("\n");
    }    
    printf("\n");

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


void test(){
    
    
}


int main(){
    char fn[] = "dragon.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
  
    //test();
    int count, p,j,k,n, i;
    scanf("%d", &count);

    // calc length cache
    int xy=1;
    gLength[0] = 0;
    
    for(int i=1;i<51;i++){
        gLength[i] = xy*3;
        if (gLength[i] >= 1000000000){
            gLength[i]=   1000000000;
            for (int j=i; j<51; j++) {
                gLength[j] = gLength[i];
            }
            break;
        }
        
        //printf("%d,%d\n", gLength[i],xy);
        xy*=2;
    }
    
    
    for (p=0; p<count; p++) {
        scanf("%d %d %d", &gN ,&gP, &gL);        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

