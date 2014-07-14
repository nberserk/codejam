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
char dragon_k(string in, int generation, int k)
{    
    if (generation<0) {
        return 'z';
    }

    //printf("%s\n",in.c_str());
    
    int half = gLength[generation];
    int org_k = k;
    
    int size= in.size();
    if(generation==0){
        return in[k-1];
    }    
    for(int i=0;i<size;i++){
        if(in[i]=='X'){
            if(k>half)
                k-=half;
            else{
                return dragon_k("X+YF", generation-1, k);                
            }            
        }else if(in[i]=='Y'){
            if(k>half)
                k-=half;
            else{
                return dragon_k("FX-Y", generation-1, k);                
            }            
        }else{
            k--;
        }

        if (k==0) {
            //printf("%s, gen=%d,k=%d  --> %c\n", in.c_str(), generation, org_k, in[i]);
            return in[i];            
        }
    }
    
    return 'z';
}

void solve(){

    char c;
    for(int i=0;i<gL;i++){
        c = dragon_k("FX", gN, gP+i);
        printf("%c", c);
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

void check(char expected, char actual){
    if (expected!=actual) {
        printf("failed expected=%c, actual=%c\n", expected, actual);
    }
}


void test(){
    
    check('F', dragon_k("FX",0,1));
    check('X', dragon_k("FX",0,2));

    check('F', dragon_k("FX", 1, 1));
    check('X', dragon_k("FX", 1, 2));
    check('+', dragon_k("FX", 1, 3));
    check('Y', dragon_k("FX", 1, 4));
    check('F', dragon_k("FX", 1, 5));

    check('F', dragon_k("FX", 2, 1));
    check('X', dragon_k("FX", 2, 2));
    check('+', dragon_k("FX", 2, 3));
    check('Y', dragon_k("FX", 2, 4));
    check('F', dragon_k("FX", 2, 5));
    check('+', dragon_k("FX", 2, 6));
    check('F', dragon_k("FX", 2, 7));
    check('X', dragon_k("FX", 2, 8));
    check('-', dragon_k("FX", 2, 9));
    check('Y', dragon_k("FX", 2, 10));
    check('F', dragon_k("FX", 2, 11));
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
    
    // calc length cache
    int xy=1;
    gLength[0] = 1;
    for(int i=1;i<51;i++){
        gLength[i] = gLength[i-1]+xy*3;
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

    // test
    //test();

    // handling input
    int count, p,j,k,n, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {
        scanf("%d %d %d", &gN ,&gP, &gL);        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

