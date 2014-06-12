// http://algospot.com/judge/problem/read/FIXPAREN

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string.h>
#include <stack>
#include <limits>
#include <set>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
char gS[101];
char gPriority[5];
int gCache[100][101];


void initCache(){
//    memset(gCache, -1, sizeof(gCache));
}

int getPriority(char c){
    for (int i=0;i<4;i++){
        if (c==gPriority[i])
            return i;
    }
    return -1;
}

char getPairOf(char c){
    if(c=='{')
        return '}';
    if (c=='<')
        return '>';
    if (c=='(')
        return ')';
    if (c=='[')
        return ']';
    if(c=='}')
        return '{';
    if(c=='>')return '<';
    if(c==')') return '(';
    if(c==']')return '[';
    
    return 'G';
}

void solve(){
    
    int n = strlen(gS);
    stack<int> s;
    char c,p;
    for(int i=0;i<n;i++){
        c = gS[i];
        if(c=='{' || c=='<' || c=='(' || c=='['){
            s.push(i);
        }else{
            int idxOpen  = s.top();            s.pop();
            p = gS[idxOpen];
            if(p=='{' && c=='}'){
            }else if(p=='<' && c=='>'){
            }else if(p=='(' && c==')'){
            }else if(p=='[' && c==']'){
            }else{
                // fix
                int pOpen = getPriority(p);
                char pairCurrent = getPairOf(c);
                int pClose = getPriority(pairCurrent);
                if(pOpen<pClose){
                    gS[i] = getPairOf(p);
                }else{
                    gS[idxOpen] = getPairOf(c);
                }
            }
            
        }
    }

    if(s.size()>0)
        printf("strange\n");
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


void test(){
   
}


int main(){
    char fn[] = "fixparen.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }

    initCache();
    //test();
    
    int count, p,j,k;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%s %s", gS, gPriority);
                
        solve();
        printf("%s\n", gS);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

