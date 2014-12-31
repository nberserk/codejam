// https://algospot.com/judge/problem/read/NERD2
// category: ??

#include <algorithm>
#include <unistd.h>
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
#define H_MOD 10000000
typedef long long ll;


void check(bool ret);

bool gDebug;
int gN;
int gA [50000], gB[50000];

class Nerd{
public:
    Nerd(int _a, int _b){
        a=_a; b=_b;
    }
    int a,b;
};

struct compareA{    
    bool operator() (const Nerd& left, const Nerd& right){
        return left.a < right.a;
    }
//    bool operator() (const Nerd& left,  int a){
//        return left.a < a;
//    }
//    bool operator() ( int a, const Nerd& right){
//        return a < right.a;
//    }    
};

void solve(){
    set<Nerd, compareA> a;
    int ret=1;
    a.insert(Nerd(gA[0], gB[0]));
    set<Nerd>::iterator itEnd, itStart,it;
    pair<set<Nerd>::iterator, bool> r;
    for (int i = 1; i < gN; i++){
        Nerd n(gA[i], gB[i]);
        r = a.insert(n);
        if (r.second==false){
            printf("error\n");
        }
        itEnd = r.first;
        if (itEnd==a.begin()){
            it = itEnd;
            it++;
            if (it!=a.end() && n.b < it->b) {
                a.erase(itEnd);
            }
        }else{
//           printf("%d\n", itEnd->a);
            itStart = itEnd;
            itStart--;
            while(true){           
                if (itStart->b > n.b){
                    itStart++;
                    break;
                }
                if (itStart==a.begin()){
                    break;
                }
                itStart--;           
            }
            a.erase(itStart, itEnd);
        }
//        for ( it = a.begin(); it !=a.end(); it++){
//            printf("%d/%d, ", it->a, it->b);
//        }
//        printf("\n");
        ret += a.size();
    }
    printf("%d\n", ret);
}

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

void test(){
    set<Nerd, compareA> a;
    a.insert(Nerd(9,1));
    a.insert(Nerd(7,2));
    a.insert(Nerd(1,9));
    
    set<Nerd>::iterator it;
    it = a.upper_bound(Nerd(-2,1));
    printf("%d\n", (*it).a);
    
    
    std::set<int> myset;
    std::set<int>::iterator itlow,itup;
    
    for (int i=1; i<10; i++) myset.insert(i*10); // 10 20 30 40 50 60 70 80 90
    
    itlow=myset.lower_bound (30);                //       ^
    itup=myset.upper_bound (60);                 //                   ^
    printf("%d\n", *itlow);
    printf("%d\n", *itup);

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
    
    //test();
    
    // handling input
    int count, p,j,k, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        for (int i = 0; i < gN; i++){
            scanf("%d %d",gA+i, gB+i);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

