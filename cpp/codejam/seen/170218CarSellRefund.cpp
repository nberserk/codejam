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
 time limit:  10 sec
 memory limit: 512M
 
 
 with 1d hash:
     1: SCORE=35345:
     2: SCORE=35404:
     3: SCORE=32947:
     4: SCORE=31412:
     5: SCORE=34339:
     6: SCORE=37790:
     7: SCORE=34568:
     8: SCORE=32912:
     9: SCORE=32733:
     10: SCORE=33047:
     elapsed time: 19149ms


 with tree(4d hash):
     1: SCORE=35345:
     2: SCORE=35404:
     3: SCORE=32947:
     4: SCORE=31412:
     5: SCORE=34339:
     6: SCORE=37790:
     7: SCORE=34568:
     8: SCORE=32912:
     9: SCORE=32733:
     10: SCORE=33047:
     elapsed time: 2230ms
 
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
#define hassert(a)
#endif

#define MAX_CAR 1000000
#define TREE


void hassert(bool a){
    if(!a){
        hprint("error_line: %d\n", __LINE__);
    }
}

struct CAR{
    int age;        // 0 - 19
    int person;     // 2-12
    int engine;     // 1000~4999
    int price;      // 10000~39999
};

CAR car[MAX_CAR];
int cari;

int age[2], person[2], engine[2], price[2];

#ifdef TREE
    // tree
    bool treeMode=false;
    const int  TREE_ENG=10;
    const int  TREE_PRC=10;
    const int MAX_TREE_ITEM=1000;
    int    tree[20][13][TREE_ENG][TREE_PRC][MAX_TREE_ITEM];
    int treeCnt[20][13][TREE_ENG][TREE_PRC];
#else
    #define MAX_PRC_CNT     1000
    int priceHash[30000][MAX_PRC_CNT];
    int priceCnt[30000];
#endif
int remain;

#define MAX_SELL 100000
#define MAX_SELL_ITEM   9000

int trans[MAX_SELL][MAX_SELL_ITEM];
int transCnt[MAX_SELL];
int transOffset;

int orderNum;


void addHash(CAR& c, int ci){
#ifdef TREE
    int i3 = (c.engine-1000)/400;
    int i4 = (c.price-10000)/3000;

    hassert(i3<10 && i4<10);
    hassert(treeCnt[c.age][c.person][i3][i4]< MAX_TREE_ITEM);
    tree[c.age][c.person][i3][i4][ treeCnt[c.age][c.person][i3][i4]++ ] = ci;
#else
    int pi = c.price-10000;
    hassert(pi>=0 && pi<30000);
    priceHash[pi][priceCnt[pi]++] = ci;
#endif
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
#ifdef TREE
    int es = (engine[0]-1000)/400;
    int ee = (engine[1]-1000)/400;
    int ps = (price[0]-10000)/3000;
    int pe = (price[1]-10000)/3000;

    hassert(es>=0 && ee<TREE_ENG);
    hassert(ps>=0 && pe<TREE_PRC);
    for (int a=age[0]; a<=age[1]; a++) {
        for (int pr=person[0]; pr<=person[1]; pr++) {
            for (int e=es;e<=ee ; e++) {
                for(int p=ps;p<=pe;p++){
                    for(int i=0;i<treeCnt[a][pr][e][p];i++){
                        int ci = tree[a][pr][e][p][i];
                        CAR& c = car[ci];
                        if(c.engine<engine[0] || c.engine>engine[1])continue;
                        if(c.price<price[0] || c.price > price[1]) continue;
                       
                        hassert(transCnt[orderNum]<MAX_SELL_ITEM);
                        trans[orderNum][ transCnt[orderNum]++ ] = ci;
                        
                        hassert(c.age>=age[0] && c.age<=age[1]);
                        hassert(c.person>=person[0] && c.person<=person[1]);
                        hassert(c.engine>=engine[0] && c.engine<=engine[1]);
                        hassert(c.price>=price[0] && c.price<=price[1]);
                        tree[a][pr][e][p][i] = tree[a][pr][e][p][ treeCnt[a][pr][e][p]-1 ];
                        
                        treeCnt[a][pr][e][p]--;
                        i--;
                        remain--;
                    }
                }
            }
        }
    }
    
#else
    for (int i=price[0]-10000; i<=price[1]-10000; i++) {
        for (int j=0; j<priceCnt[i]; j++) {
            int ci = priceHash[i][j];
            CAR& c = car[ci];
            if(c.age < age[0] || c.age> age[1])continue;
            if(c.person<person[0] || c.person>person[1])continue;
            if(c.engine<engine[0] || c.engine>engine[1])continue;
            
            // save
            hassert(transCnt[orderNum]<MAX_SELL_ITEM);
            trans[orderNum][transCnt[orderNum]++] = ci;
            
            
            // remove from hash
            priceHash[i][j] = priceHash[i][priceCnt[i]-1];
            
            priceCnt[i]--;
            j--;
            remain--;
        }
    }
#endif
    
    int ret = orderNum;
    orderNum++;
    if (orderNum>=MAX_SELL) {
        printf("trans overflow\n");
    }
    return ret;
}

int empty(){
    int ret = remain;
    
#ifdef TREE
    //int treeCnt[20][13][TREE_ENG][TREE_PRC];
    for (int a=0; a<=19; a++) {
        for (int pr=2; pr<=12; pr++) {
            for (int e=0;e<TREE_ENG ; e++) {
                for(int p=0;p<TREE_PRC;p++){
                    treeCnt[a][pr][e][p]=0;
                }
            }
        }
    }
#else
    for (int i=0; i<30000; i++) {
        priceCnt[i]=0;
    }
#endif
    for (int i=0; i<MAX_SELL; i++) {
        transCnt[i]=0;
    }

    // reset
    remain=0;
    orderNum=0;
    cari=0;

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
            c.price = 10000 + rand()%30000;

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

