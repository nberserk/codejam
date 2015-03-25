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

bool gDebug;
int gN;
int gm[100];
int gCache[5][5][5][5][5];
set<string> gVisited;


struct Node{
    string c;
    int p;
    int e; // empty pos
    int count;
};

class Comparator{
public:
    bool operator() (const Node& a, const Node& b){
        return a.p + a.count > b.p + b.count;
//        if (a.count==b.count) {
//            return (a.p > b.p);
//        }
//        return (a.count > b.count);
    }
};

void fillValidCount(Node& n){
    int d=0;
    char b[3];
    int t;
    for (int i=0; i<gN*gN; i++) {
        strncpy(b, n.c.c_str() + i*2, 2);
        t  = atoi(b);
        if(t==0) t = gN*gN;
        if (i+1==t)continue;
        t--;
        int r = i/gN;
        int c = i%gN;
        int tr = t/gN;
        int tc = t%gN;
        d+= abs(r-tr) + abs(c-tc);
    }
    n.p = d;
}

void solve(){
    Node root;

    char buf[3];
    for (int i=0; i<gN*gN; i++) {
        if (gm[i]==0) {
            root.e=i;
        }
        sprintf(buf, "%02d", gm[i]);
        root.c += buf;
    }
    
    string t;
    t = "dddddddffdfafda";
    t.replace(0, 2, "ab");
    
    priority_queue<Node, vector<Node>, Comparator> pq;
    fillValidCount(root);
    root.count=0;
    pq.push(root);
    set<string> visited;
    
    int d[4]= {-gN, gN, 1, -1};
    int ret = H_MAX;
    while (pq.size()>0) {
        Node n = pq.top();
        pq.pop();
        
        if (visited.find(n.c)!=visited.end()) {
            //printf("%s skip \n", s.c_str());
            continue;
        }
        visited.insert(n.c);
        if (n.count >= ret) {
            continue;
        }
        //printf("%s-%d-%d\n", n.c.c_str(), n.p, n.count);
        if (n.p==0) {
            ret = n.count;
            printf("%s done(%d)\n", n.c.c_str(), n.count);
            break;
        }
        for (int i=0; i<4; i++) {
            int t = n.e + d[i];
            if (t<0 || t>=gN*gN)continue;
            
            Node nn;
            nn.c = n.c;
            swap(nn.c[t*2], nn.c[n.e*2]);
            swap(nn.c[t*2+1], nn.c[n.e*2+1]);
            nn.e = t;
            nn.count = n.count+1;
            fillValidCount(nn);
            pq.push(nn);
        }
    }
    
    
    
    printf("%d\n", ret);
}

void test(){
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
    //h_generateRandomMap(100);
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);

        for (int i = 0; i < gN*gN; i++){                        
            scanf("%d", &gm[i]);            
        }        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

