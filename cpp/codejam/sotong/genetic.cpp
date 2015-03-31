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

using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
typedef long long ll;

#define CHRONO_LENGTH 36
#define POP_SIZE 100


int gN;
int gSol[CHRONO_LENGTH];
struct Node{
    int c[CHRONO_LENGTH];
    float f;
};
bool gDebug;
Node gCandidate[POP_SIZE];


int randInt(){
    static unsigned int next=1;
    next = next*87654321+12347;
    return (unsigned int)next>>16;    
}

double randDouble(double m){
    return (randInt()%10001/10000.0)*m;
}

void updateFitness(Node& n){
    int d=1;
    for (int i = 0; i < gN; i++){
        if (gSol[i]!=n.c[i])
            d++;
    }
    if (d==1){
        n.f = 1.0f;
    }else{
        n.f = 1/(float)d;
    }
}

int select(){
    int r=randInt()%POP_SIZE;
    float v = gCandidate[r].f;    

    for (int i = 0; i < 5; i++){
        int c = randInt()%POP_SIZE;
        if ( gCandidate[c].f > v ){
            v = gCandidate[c].f;
            r = c;
        }        
    }
    
    return r;
}

void copyChromo(Node& src, Node& dest){
    for (int i = 0; i < CHRONO_LENGTH; i++){
        dest.c[i] = src.c[i];
    }
}

#define CROSSOVER_RATE 0.7
void crossover(Node& n, Node& o){
    if (randDouble(1)>CROSSOVER_RATE){
        return;
    }

    int m = randInt();
    for (int i = m; i < CHRONO_LENGTH; i++){
        int t = n.c[i];
        n.c[i] = o.c[i];
        o.c[i] = t;
    }    
}

void mutate(Node& n){
    for (int i = 0; i < gN; i++){
        if (randDouble(1)<0.015){
            n.c[i] = n.c[i]==0?1:0;
        }
    }    
}

void printNode(Node& n){
    for (int i = 0; i < gN; i++){
        printf("%d", n.c[i]);
    }
    printf("\n");
}
void solve(){
    // init population
    for (int i = 0; i < POP_SIZE; i++){
        for (int j = 0; j < gN; j++){
            gCandidate[i].c[j] = randInt()%2;    
        }
        updateFitness(gCandidate[i]);
    }

    int maxTry=100;
    int gen=0;
    float best;
    int bestIdx;
    Node t[POP_SIZE];
    while (gen<maxTry){
        best=0;
        for (int i = 0; i < POP_SIZE; i++){
            if (gCandidate[i].f ==1.0f){
                printNode(gCandidate[i]);
                return;
            }
            if (best<gCandidate[i].f){
                best = gCandidate[i].f;
                bestIdx=i;                
            }
        }

        int index =0;
        t[0] = gCandidate[bestIdx];

        for (int i = index; i < POP_SIZE; i++){
            int t1 = select();
            int t2 = select();
            for (int j = 0; j < gN; j++){
                if (randDouble(1)>0.7){
                    t[i].c[j] = gCandidate[t1].c[j];
                }else{
                    t[i].c[j] = gCandidate[t2].c[j];
                }
            }
            mutate(t[i]);
            updateFitness(t[i]);
        }
        
        for (int i = 0; i < POP_SIZE; i++){
            gCandidate[i] = t[i];
        }
        printf("generation %d - %f\n", gen, best);
        gen ++;
    }
    
}

void test(){
    
//    for (int i = 0; i < 100; i++){
//        int t = randInt()%100;
//        printf("%d\n", t);
//    }
    
    
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
    
    srand(time(NULL));
    test();
    //h_generateRandomMap(100);
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d ", &gN);
        char c;
        for (i=0; i<gN; i++) {
            scanf("%c", &c);
            gSol[i] = c=='0'? 0: 1;
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

