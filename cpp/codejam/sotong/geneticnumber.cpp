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

int gR;
struct Node{
    int c[CHRONO_LENGTH];
    float f;
};
bool gDebug;
Node gCandidate[POP_SIZE];


int randInt(int m){
    return rand() % m;
}

double randDouble(double m){
    return (randInt(100001)/10000.0)*m;
}

void node_generate(Node& n){
    for (int i = 0; i < CHRONO_LENGTH; i++){
        n.c[i] = randInt(2);
    }
}

void node_fitness(Node& n){
    float cur=0;
    int bOperator=false;
    int op=10; // 10+, 11-, 12*, 13/
    
    for (int i = 0; i < 9; i++){
        int num=0;
        int th=1;
        for (int j = i*4; j < i*4+4; j++){
            num+= n.c[j]*th;
            th*=2;
        }
        if (num >=14)continue;
        
        if(bOperator){
            if(num<10)continue;
            op = num;
            bOperator = false;
        }else{
            if(num>=10)continue;
            if (op==10){
                cur+=num;
            }else if (op==11){
                cur-= num;
            }else if (op==12)
                cur*= num;
            else if (op==13){
                if(num==0)
                    cur += num;
                else
                    cur/= num;
            }
            bOperator=true;
        }
    }
    if (cur ==gR){
        n.f = 1;
    }else{
        double diff = gR-cur;
        if(diff<0) diff=-diff;
        n.f = 1/diff;            
    }    
}

void printNode(Node& n){
    for (int i = 0; i < 9; i++){
        int num=0;
        int th=1;
        for (int j = i*4; j < i*4+4; j++){
            num+= n.c[j]*th;
            th*=2;
        }
        if (num<10){
            printf("%d ", num);
        }else{
            char c;
            if (num==10){
                c='+';
            }else if (num==11){
                c='-';
            }else if (num==12)
                c='*';
            else if (num==13){
                c='/';
            }
            printf("%c ", c);
        }
    }
    printf("\n");
}

int select(float total){
    int fSum = 0.0f;
    float r = randDouble(total);

    for (int i = 0; i < POP_SIZE; i++){
        fSum += gCandidate[i].f;
        if (fSum >= r){
            return i;
        }
    }
    return -1;
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

    int m = randInt(CHRONO_LENGTH);
    for (int i = m; i < CHRONO_LENGTH; i++){
        swap(n.c[i], o.c[i]);
    }    
}

void mutate(Node& n){
    for (int i = 0; i < CHRONO_LENGTH; i++){
        if (randDouble(1)<0.002){
            n.c[i] = n.c[i]==0?1:0;
        }
    }    
}
void solve(){
    // init
    for (int i = 0; i < POP_SIZE; i++){
        node_generate(gCandidate[i]);
        node_fitness(gCandidate[i]);
    }
    
    int gMaxGen = 400;
    int gen=0;
    
    while (1) {
        float totalFitness = 0.0f;
        for (int i = 0; i < POP_SIZE; i++){
            if (gCandidate[i].f == 1.0f){
                printNode(gCandidate[i]);
                return;
            }
            totalFitness += gCandidate[i].f;
        }

        Node t[POP_SIZE];
        int index=0;        
        while(index<POP_SIZE){
            int p1 = select(totalFitness);
            int p2 = select(totalFitness);
            Node t1, t2;

            copyChromo(gCandidate[p1], t1);
            copyChromo(gCandidate[p2], t2);

            crossover(t1, t2);
            mutate(t1);
            mutate(t2);

            t[index++] = t1;
            t[index++] = t2;            
        }
        for (int i = 0; i < POP_SIZE; i++){
            gCandidate[i] = t[i];
        }        
        gen++;
    }    
}

void test(){
    
    Node n;
    for (int i=0; i<10; i++) {
        node_generate(n);
        node_fitness(n);        
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
    //h_generateRandomMap(100);
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gR);        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

