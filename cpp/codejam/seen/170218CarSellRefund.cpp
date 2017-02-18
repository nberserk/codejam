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
optimize test function withn 10 sec
 */


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001

#define SWAP(a,b) do{int t=a;a=b;b=t;} while(0)

#define HDEBUG
#ifdef HDEBUG
#define hprint(fmt, args...) printf(fmt, ##args)
#define hassert(a) {if(a){}else{hprint("error: %d", __LINE__);}}
#else
#define hprint(fmt,args...)
#define hassert(a)
#endif

#define MAX_CAR 1000000

struct CAR{
    int age;        // 0 - 19
    int person;     // 2-12
    int engine;     // 1000~4999
    int price;      // 10000~39999
};

CAR car[MAX_CAR];
int cari;

int age[2], person[2], engine[2], price[2];

#define MAX_PRC_CNT     1000
int priceHash[30000][MAX_PRC_CNT];
int priceCnt[30000];
int remain;

#define MAX_SELL 100000
#define MAX_SELL_ITEM   9000

int trans[MAX_SELL][MAX_SELL_ITEM];
int transCnt[MAX_SELL];
int transOffset;

int orderNum;


void addHash(CAR& c, int ci){
    int pi = c.price-30000;
    hassert(pi>=0 && pi<30000);
    priceHash[pi][priceCnt[pi]++] = ci;
    
}

void buy(CAR c){
    car[cari] = c;
    
    
    addHash(car[cari], cari);
    cari++;
    remain++;
}

void filter_age(int from , int to){
    if(from>to){
        SWAP(from, to);
    }
    age[0]=from;
    age[1]=to;
}

void filter_person(int from , int to){
    if(from>to){
        SWAP(from, to);
    }
    person[0]=from;
    person[1]=to;
}

void filter_engine(int from , int to){
    if(from>to){
        SWAP(from, to);
    }
    engine[0]=from;
    engine[1]=to;
}

void filter_price(int from , int to){
    if(from>to){
        SWAP(from, to);
    }
    price[0]=from;
    price[1]=to;
}

int sell(){
    for (int i=price[0]-10000; i<=price[1]-10000; i++) {
        for (int j=0; j<priceCnt[i]; j++) {
            int ci = priceHash[i][j];
            CAR& c = car[ci];
            if(c.age < age[0] || c.age> age[1])continue;
            if(c.person<person[0] || c.person>person[1])continue;
            if(c.engine<engine[0] || c.engine>engine[1])continue;
            
            // save
            if (transCnt[orderNum]>=MAX_SELL_ITEM) {
                printf("transCnt overflow\n");
            }
            trans[orderNum][transCnt[orderNum]++] = ci;
            
            
            // remove from hash
            priceHash[i][j] = priceHash[i][priceCnt[i]-1];
            
            priceCnt[i]--;
            j--;
            remain--;
        }
    }
    int ret = orderNum;
    orderNum++;
    if (orderNum>=MAX_SELL) {
        printf("trans overflow\n");
    }
    return ret;
}

int empty(){
    int ret = remain;
    
    for (int i=0; i<30000; i++) {
        priceCnt[i]=0;
    }
    
    for (int i=0; i<MAX_SELL; i++) {
        transCnt[i]=0;
    }

    // reset
    remain=0;
    orderNum=0;

    return ret;
}

void refund(int order_number){
    for (int i=0; i<transCnt[order_number]; i++) {
        int ci = trans[order_number][i];
        CAR& c = car[ci];
        addHash(c, ci);
        remain++;
    }
    //hprint("refund:%d\n", order_number);
}

int main(){
    srand(3);    

    int order_number = -1;
    h_startTimeMeasure();
    for (int  t=0; t<10; t++) {
        for(int i=0;i<MAX_CAR;i++){
            CAR c;
            c.age = rand()%20;
            c.person = 2+ rand()%11;
            c.engine = 1000+rand()%4000;
            c.price = 30000 + rand()%30000;

            buy(c);
            if(rand()%100 == 0){
                filter_age(rand()%20, rand()%20);
                filter_person(2+rand()%11, 2+rand()%11 );
                filter_engine(1000 + rand()%4000, 1000+rand()%4000 );
                filter_price(10000+ rand()%30000, 10000+rand()%30000 );
                int on = sell();
                if(rand()%10==0) order_number=on;
            }
            
            if(rand()%10000 ==0){
                if(order_number!=-1){
                    refund(order_number);
                    order_number=-1;
                }
            }
            
        }
        
        int car_count = empty();
        order_number=-1;
        hprint("%d: SCORE=%d: \n", t+1, car_count);
    }    
   
    h_endTimeMeasure();
    /**
     on Mac
     1: SCORE=35345:
     2: SCORE=35905:
     3: SCORE=33744:
     4: SCORE=32241:
     5: SCORE=35073:
     6: SCORE=38425:
     7: SCORE=35242:
     8: SCORE=33299:
     9: SCORE=32700:
     10: SCORE=33215:
     elapsed time: 24658ms
     Program ended with exit code: 0
     */
    
}

