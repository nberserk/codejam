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
#include "myutil.h"

using namespace std;
#define MAX_N 17
#define MAX_PERMUTATION 131072 // 2^17
int gN;
int cache[MAX_N][MAX_PERMUTATION];
int parent[MAX_N][MAX_PERMUTATION];
int gCost[MAX_N][MAX_N];
bool gDebug;
struct Node{
    int g[MAX_N];
    int f;
};

int rand2(){
    static unsigned int next=1;
    next = next*987654321 + 12347;
    return (unsigned int)next>>16;
}

int rand(){
    static unsigned int next=1;
    static unsigned int m=(1<<17) -1;
    next ^= next >> 6;
    next ^= next << 13;
    next ^= next >> 19;
    next *= m;
    return (unsigned int)next>>2;
}

double randDouble(){
    double r = (rand()%10001)/10000.0;
    return r;
}

int tsp(int cost[MAX_N][MAX_N], int n, int end){
	int min = INT_MAX, cur;
	int p;
    //int n = powl(2, N);

	if(n==0){
		return cost[end][0];
	}

	if (cache[end][n]!= -1) {
        return cache[end][n];
    }

	int i, mask;
	int setCount = 0;


	for(i=0;i<MAX_N;i++){
		mask = 1<<i;
		int temp = n&mask;
		if(temp == mask){
			cur = cost[end][i] + tsp(cost, n-mask, i);
			if(cur < min){
				min = cur;
				parent[end][n]= i;
			}
		}		
	}

	//printf("%d,%d=%d\n", end,n,min);
	cache[end][n] = min;
	return min;
}

void solveDP(){
    int i, j;
    for(i=0;i<gN;i++){
        for (j=0;j<MAX_PERMUTATION;j++ ){
            cache[i][j]=-1;
        }
    }
    int permu = (1<<gN)-2;
    int min = tsp(gCost, permu, 0);
    printf("%d\n", min);


    int curP = permu;
    int cur = 0;
    int next;
    for (i = 0; i < gN; ++i) {
        next = parent[cur][curP];
        printf("%d ", next);
        curP -= 1<<next;
        cur = next;
    }
    printf("\n");
}

void fitness(Node& n){
    int s=gCost[gN][n.g[0]] + gCost[n.g[gN-1]][gN];
    for (int i = 0; i < gN-1; i++){
        s += gCost[n.g[i]][n.g[i+1]];
    }
    n.f = s;
}

Node pop[100]; //TODO: proper POP_SIZE ?
int select(){
    int r=rand()%100;
    int best=pop[r].f;

    for (int i = 0; i < 4; i++){
        int t = rand()%100;
        if (pop[t].f < best){
            best = pop[t].f;
            r=t;
        }
    }    

    return r;
}

void printNode(Node& n){
    for (int i=0; i<gN; i++) {
        printf("%d ", n.g[i]);
    }
    printf("%d", gN);//because of (gN--)
    printf("\n");
}

void crossover(Node& n){
    if (randDouble()< 0.8){
        int s = rand()%gN;
        int e = rand()%gN;
        while (s==e){
            e = rand()%gN;
        }
        if (e<s) {
            swap(s,e);
        }

        Node bac = n;
        int fromBack=e;
        for (int i = s; i <= e; i++){
            n.g[i] = bac.g[fromBack];
            fromBack--;
        }        
        //printNode(bac);
        //printNode(n);
    }    
}

void mutate(Node& n){
    if (randDouble()<0.02){
        int s = rand()%gN;
        int e = rand()%gN;
        while (s==e){
            e = rand()%gN;
        }
        swap(n.g[s], n.g[e]);
    }
}

void solveGenetic(){
    gN--; // starts : (gN-1) ends : (gN-1)
    Node temp[100];
        
    for (int i = 0; i < 100; i++){
        for (int j = 0; j < gN; j++){
            pop[i].g[j]=j;
        }
        for (int j = 0; j < gN-1; j++){
            int s = rand()%(gN);
            int d = rand()%(gN);
            if(s==d)
                continue;                
            swap(pop[i].g[s], pop[i].g[d]);
        }
        fitness(pop[i]);
    }
    

    int gen =0;
    int best=987654321;
    int bestIdx;
    int retryMax=1000;
    int retry=0;
    while (1){
        int cBest=987654321;
        for (int i = 0; i < 100; i++){
            if (cBest>pop[i].f){
                cBest=pop[i].f;
                bestIdx=i;
            }
        }

        if (cBest<best){
            best=cBest;
            retry=0;
        }else{
            retry++;
        }
        //printf("gen %d - %d, ", gen, best);
        //printNode(pop[bestIdx]);
        gen++;
        if (retry>=retryMax){
            break;
        }

        //crossover
        int index=0;
        temp[0] = pop[bestIdx];
        for (int i = 1; i < 100; i++){
            int t1 = select();
            Node t = pop[t1];
            crossover(t);
            mutate(t);
            fitness(t);
            
            temp[i]= t;            
        }

        for (int i = 0; i < 100; i++){
            pop[i] = temp[i];
        }                
    }

    printf("%d\n", best);
    printNode(pop[bestIdx]);
    gN++; // restore gN
}


int distance(int* cur){
    int d=gCost[cur[gN-1]][cur[0]];
    for (int i = 0; i < gN-1; i++){
        d+= gCost[cur[i]][cur[i+1]];
    }
    return d;
}

int neighbor(int* cur, int* next){
    for (int i = 0; i < gN; i++){
        next[i] = cur[i];
    }
    int s = rand()%gN;
    int d = rand()%gN;
    swap(next[s], next[d]);
    return distance(next);
}

void take(int* src, int* dest){
    for (int i = 0; i < gN; i++){
        dest[i] = src[i];
    }
}

double myexp(double x){
    x = 1+x/512.0;
    x *=x; x*=x;   x *=x; x*=x;
    x *=x; x*=x;   x *=x; x*=x;
    x*=x;
    return x;
}

void solveSA(){
    int cur[MAX_N];
    int next[MAX_N];
    for (int i = 0; i < gN; i++){
        cur[i] = i;
    }
    double t = 400;
    double epsilon = 0.001;
    double alpha = 0.999;
    int dist = distance(cur);
    int ndistance;
    int iter=0;
    int best=dist;
    
    while (t>epsilon){
        ndistance = neighbor(cur, next);
        int delta = ndistance - dist;
        
        if (delta<0){
            take(next, cur);
            dist = ndistance;
            
            best = min(dist, best);
        }else {
            double bias = myexp(-delta/t);
            //printf("%lf\n",bias);
            if (randDouble() < bias ){
                take(next, cur);
                dist = ndistance;
                best=min(dist, best);
            }
        }
        t*=alpha;
        iter++;
        //if(iter%400==0)
        //   printf("%d ", dist);
        
    }

    printf("%d(%d) - %d\n", dist, best, iter);
    for (int i = 0; i < gN; i++){
        printf("%d ", cur[i]);
    }
    printf("\n");
}

void test(){
    for (int i=0; i<100; i++) {
        double d = randDouble();
        printf("%f\n", d);
    }
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
    //test();
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d ", &gN);        
        
        for (i=0; i<gN; i++) {
            for (int j = 0; j < gN; j++){
                scanf("%d", &gCost[i][j]);    
            }
        }
        h_startTimeMeasure();
        solveDP();
        printf("DP- ");
        h_endTimeMeasure();
        h_startTimeMeasure();
        solveGenetic();
        printf("Genetic- ");
        h_endTimeMeasure();

        h_startTimeMeasure();
        solveSA();
        printf("SA- ");
        h_endTimeMeasure();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
}
