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
  dog : 5000
person : 5000
  
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

#define N 5000

struct Dog{
    int hair, age,  weight, height;    
};

struct Person{
    int donation;
    int hair[2];
    int age[2];
    int weight[2];
    int height[2];    
};


int gScore;
Dog gDog[N];
Person gPerson[N];



void build(){
    for (int i = 0; i < N; i++){
        gDog[i].hair = rand()%10;
        gDog[i].age = rand()%10;
        gDog[i].height = rand()%40;
        gDog[i].weight = rand()%50;

        gPerson[i].donation = 10 + rand()%990;
        gPerson[i].hair[0] = rand()%10;
        gPerson[i].hair[1] = rand()%10;
        gPerson[i].age[0] = rand()%10;
        gPerson[i].age[1] = rand()%10;
        gPerson[i].height[0] = rand()%40;
        gPerson[i].height[1] = rand()%40;
        gPerson[i].weight[0] = rand()%50;
        gPerson[i].weight[1] = rand()%50;
    }
}

bool match(int dog, int p[]){
    if(p[0]>p[1]){
        if (p[1]<=dog && dog<=p[0]){
            return true;
        }
    }else{
        if (p[0]<=dog && dog<=p[1]){
            return true;
        }
    }
    return false;
}

void adopt(int dog, int person){
    if ( !match(gDog[dog].age, gPerson[person].age)) return;
    if ( !match(gDog[dog].hair, gPerson[person].hair)) return;
    if ( !match(gDog[dog].weight, gPerson[person].weight)) return;
    if ( !match(gDog[dog].height, gPerson[person].height)) return;

    gScore += gPerson[person].donation;
    gPerson[person].donation=0;
}

void test(){
    for (int i = 0; i < N; i++)
        adopt(i,i);
}

int main(){   
    
    // handling input
    int count=10;    
    for (int p=0; p<count; p++) {

        build();
        test();
        //solve();
        printf("Score:%d\n", gScore);
    }    
   
    
}

