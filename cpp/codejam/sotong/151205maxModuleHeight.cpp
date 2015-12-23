//
//  151205.cpp
//  codejam
//
//  Created by Darren ha on 12/6/15.
//  Copyright © 2015 Darren ha. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "../myutil.h"

using namespace std;

#define MAX 30000

#define SWAP(a,b) do{int t=a;a=b;b=t;} while(0)

#define HDEBUG
#ifdef HDEBUG
#define hprint(fmt, args...) printf(fmt, ##args)
#else
#define hprint(fmt,args...)
#endif

//00 : 0
//01 : 1
//10 : 2

// (0,0) (1,0) (2,0) (3,0)
// (0,1) (1,1) (2,1) (3,1)
// (0,2) (1,2) (2,2) (3,2)
// (0,3) (1,3) (2,3) (3,3)

int gM[][4][4][2] = {
    {
        { {3,0}, {2,0}, {1,0}, {0,0}},
        { {3,1}, {2,1}, {1,1}, {0,1}},
        { {3,2}, {2,2}, {1,2}, {0,2}},
        { {3,3}, {2,3}, {1,3}, {0,3}},
    },{
        { {0,0}, {0,1}, {0,2}, {0,3}},
        { {1,0}, {1,1}, {1,2}, {1,3}},
        { {2,0}, {2,1}, {2,2}, {2,3}},
        { {3,0}, {3,1}, {3,2}, {3,3}},
    },{
        { {0,3}, {1,3}, {2,3}, {3,3}},
        { {0,2}, {1,2}, {2,2}, {3,2}},
        { {0,1}, {1,1}, {2,1}, {3,1}},
        { {0,0}, {1,0}, {2,0}, {3,0}},
    },{
        { {3,3}, {3,2}, {3,1}, {3,0}},
        { {2,3}, {2,2}, {2,1}, {2,0}},
        { {1,3}, {1,2}, {1,1}, {1,0}},
        { {0,3}, {0,2}, {0,1}, {0,0}},
    },    
};

struct Match{
    int h;
    int s, d;
};

Match gMatch[MAX];
int gmcount;

int matchNaive(int m[][4][4], int s, int d){        
    for (int i = 0; i < 4; i++){
        bool match = true;
        int tx = gM[i][0][0][0];
        int ty = gM[i][0][0][1];
        int h = m[s][0][0] + m[d][ty][tx];
        
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                tx = gM[i][y][x][0];
                ty = gM[i][y][x][1];
                if (m[s][y][x] + m[d][ty][tx] != h){
                    match=false;
                    break;
                }                    
            }
            if (match==false) {
                break;
            }
        }

        if (match){
            return h;
        }
    }

    return 0;
}

int solveNaive(int module[][4][4]){
    h_startTimeMeasure();

    for (int i = 0; i < MAX; i++){
        for (int j = i+1; j < MAX; j++){
            int h = matchNaive(module, i, j);
            if (h==0){
                continue;
            }
            gMatch[gmcount].h = h;
            gMatch[gmcount].s = i;
            gMatch[gmcount].d = j;
            gmcount++;
            hprint("%d\n", h);
        }
    }
    h_endTimeMeasure();
    return gmcount;
}

struct Part{
    int c[3];
    int base;
    int bucket;
};
Part gPart[MAX];

struct Encode{
    int base;
    int org, inverted;
};

Encode


/*
strategy,
- find base, create matchable grid, convert it to int, rotate it 90, 180, 270 and store min value
- 
 */
int solveUsingHash(int module[][4][4]){
    h_startTimeMeasure();
    for (int i = 0; i < 16; i++){
        gbcount[i]=0;
    }
    
    for (int i = 0; i < MAX; i++){
        int sum=0;
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                sum += module[i][y][x];                
            }
        }

        int b = sum % 16;        

        gPart[i].bucket = b;
        gBucket[b][gbcount[b]]=i;
        gbcount[b]++;
        if (gbcount[b]==5000){
            hprint("strange");
        }
    }    

    gmcount=0;
    for (int i=0; i<MAX; i++) {
        int db = 16-gPart[i].bucket;
        if (db==16){
            db =0;
        }

        for (int j = 0; j < gbcount[db]; j++){
            int d = gBucket[db][j];
            if (d<=i){
                continue;
            }
            int h = matchNaive(module, i, d);
            if (h==0){
                continue;
            }
                
            gMatch[gmcount].h = h;
            gMatch[gmcount].s = i;
            gMatch[gmcount].d = d;
            gmcount++;

        }
    }
    
    h_endTimeMeasure();
    return 0;
}

int solveUsingPart(int module[][4][4]){
    h_startTimeMeasure();
    
    for (int i = 0; i < MAX; i++){
        int base=99999;
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                if (module[i][y][x]<base){
                    base= module[i][y][x];
                }
            }
        }

        gPart[i].base = base;
        gPart[i].c[0] = gPart[i].c[1] = gPart[i].c[2] = 0;
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                int d = module[i][y][x]-base;
                gPart[i].c[d]++;                    
            }
        }
    }    
    
    int matchcount = 0;
    for (int i = 0; i < MAX; i++){
        for (int j = i+1; j < MAX; j++){
            if (gPart[i].c[0]==gPart[j].c[2] &&
                gPart[i].c[1]==gPart[j].c[1] &&
                gPart[i].c[2]==gPart[j].c[0]){
                matchcount++;
                int h = matchNaive(module, i, j);
                if (h==0){
                    continue;
                }
                gMatch[gmcount].h = h;
                gMatch[gmcount].s = i;
                gMatch[gmcount].d = j;
                gmcount++;
                //hprint("%d\n", h);
            }
            
        }
    }
    h_endTimeMeasure();
    // matchcount : 11531481
    return gmcount;
}


int main(){
    srand(3);

    int module[MAX][4][4];

    for (int p = 0; p < 10; p++){
        for (int i = 0; i < MAX; i++){
            int base = 1 + rand()%6;
            for (int y = 0; y < 4; y++){
                for (int x = 0; x < 4; x++){
                    module[i][y][x] = base + rand()%3;
                }
            }
        }

        int s = solveUsingHash(module);
        solveUsingPart(module);
        printf("#%d: %d\n", p, s);
    }
}
