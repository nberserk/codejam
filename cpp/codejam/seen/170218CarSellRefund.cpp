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
#else
#define hprint(fmt,args...)
#endif

#define MAX_CAR 1000000

struct CAR{
    int age;        // 0 - 19
    int person;     // 2-12
    int engine;     // 1000~4999
    int price;      // 30000~59999
};




int main(){
    srand(3);    

    int max =100;
    h_startTimeMeasure();
    for (int  try=0; try<10; try++) {
        for(int i=0;i<MAX_CAR;i++){
            CAR c;
            c.age = ;

            buy();

            if(rand()%100 == 0){
                filter_age(rand()%20, rand()%20);
                filter_person(2+rand()%11, 2+rand()%11 );
                filter_engine(1000 + rand()%4000, 1000+rand()%4000 );
                filter_price(30000+ rand()%30000, 30000+rand()30000 );
                sell();
            }
        }
        printf("%d: %d(max=%d)\n", p+1, result, max);
    }    
   
    h_endTimeMeasure();
    
}

