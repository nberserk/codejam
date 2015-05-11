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
//#include "../myutil.h"


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
#define MOD 100000 
typedef long long ll;


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

//#define MAX_N 1000001
#define MAX_N 500000
bool gDebug;
int gN;
struct Price{
	int a,b;	
};

Price price[MAX_N];
int gMinAFromBack[MAX_N];
int gMinAMinusB[MAX_N];
long long gSumB[MAX_N];

void swap(int i,int j){
	Price t = price[i];
	price[i]= price[j];
	price[j]=t;
}

int partition(int s, int e){
	int p = price[e].b;

	int j=s-1;
	for(int i=s;i<e;i++){
		if(price[i].b < p){
			j++;
			swap(i,j);
		}
	}
	swap(e, j+1);
	return j+1;
}

void qsort(int s, int e){
	if (s<e){
		int m = partition(s,e);
		qsort(s,m-1);
		qsort(m+1,e);
	}
}

// case 1: sum(1-k) min(-Bt+At)
// case 2: sum(1-(k-1)) + minA(k-N)
void solve(){	
	qsort(0, gN-1);

    gSumB[0] = price[0].b;    
    int m=price[0].a-price[0].b;
    gMinAMinusB[0] = m;
    for (int i = 1; i < gN; i++){
        gSumB[i]= (gSumB[i-1] + price[i].b);
        int d = price[i].a-price[i].b;
        if (d<m){
            m=d;            
        }
        gMinAMinusB[i]=m;
    }
    
    m =price[gN-1].a;
    gMinAFromBack[gN-1]=m;
    for (int i = gN-2; i >= 0; i--){
        if (price[i].a < m){
            m=price[i].a;
        }
        gMinAFromBack[i]=m;
    }    

    ll r=gMinAFromBack[0]; // K=1
    r+=gSumB[gN-1]+gMinAMinusB[gN-1] ;//K=N
    ll a,b,d;
    for (int i = 1; i < gN-1; i++){
        a = gSumB[i]+gMinAMinusB[i];
        b = gSumB[i-1] + gMinAFromBack[i+1];
        d = a>b ? b:a;        
        r += d;
        r %=MOD;
    }    
    printf("%d\n", r);
}

void test(){
  
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
        //test();
    //precalc();
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {    
		scanf("%d", &gN);
		for(int i=0;i<gN;i++){
			scanf("%d %d", &price[i].a, &price[i].b);			
		}       
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

