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
optimize test function withn 5 sec
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

#define MAX 1000000

struct Item{
  float pos, val;
};

Item item[MAX];


int testNaive(Item* item, int max){
    bool alive[MAX];
    for (int i = 0; i < max; i++){
        alive[i]=true;
    }

    float result=0;
    for (int i = 0; i < max; i++){
        float minPos = 100000.0f;
        int min=0;
        for (int j = 0; j < max; j++){
            if (alive[j]==true && item[j].pos < minPos){
                minPos = item[j].pos;
                min=j;
            }
        }

        alive[min]=false;
        if (i%2==0){
            result += item[min].val;
        }else{
            result -= item[min].val;
        }
    }
    return (int)result;
}

int idx[MAX];
int partition(int s, int e){
    float p = item[idx[e]].pos;
    int pi = idx[e];
    int i=s-1;
    for (int j = s; j < e; j++){
        if (item[idx[j]].pos > p || (item[idx[j]].pos == p && pi<idx[j] )){
            i++;
            int t = idx[j];
            idx[j] = idx[i];
            idx[i]=t;
        }
    }

    int t = idx[i+1];
    idx[i+1] = idx[e];
    idx[e]=t;
    return i+1;
}

void qsort(int s, int e){
    if (s<e){
        int p = partition( s,e);
        qsort( s, p-1);
        qsort(p+1, e);
    }
}



int testSort(Item* item,int max){
    for (int i = 0; i < max; i++){
        idx[i]=i;
    }
    qsort( 0, max-1);
    float r = 0;
    for (int i = 0; i < max; i++){
        if (i%2==0){
            r+=item[idx[i]].val;
        }else{
            r-=item[idx[i]].val;
        }
    }
    return (int)r;
}

int main(){
    srand(0);    

    int max =100;
    h_startTimeMeasure();
    for (int p=0; p<30; p++) {
    //for (int p=0; p<50; p++) {

        int result =0;
        for (int j = 0; j < 5; j++){

            for (int k = 0; k < max; k++){
                item[j].pos = (rand()%20000) -10000 + (rand()%10000)/10000.f;
                item[j].val = (rand()%20000) -10000 + (rand()%10000)/10000.f;                
            }
            //result += testNaive(item, max);
            result += testSort(item, max);
        }

        max *= 1.25;
        if (max>MAX){
            max=MAX;
        }

        printf("%d: %d(max=%d)\n", p+1, result, max);
    }    
   
    h_endTimeMeasure();
    
}

