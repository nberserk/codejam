// http://algospot.com/judge/problem/read/FORTRESS
// category: tree

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

class Node{
public:
    int x,y,r;
    vector<Node> child;
    
    void add(Node& n){
        int size = child.size();
        bool added = false;
        for (int i = 0; i < size; i++){
            if (child[i].r > n.r){
                if (child[i].isInside(n)){
                    child[i].add(n);
                    added = true;
                    break;
                }
            }else{
                if (n.isInside(child[i])){
                    Node t = child[i];
                    child.erase(child.begin()+i);
                    n.child.push_back(t);
                    child.push_back(n);
                    added = true;
                    break;
                }
            }
            
        }
        if (added){
            return;
        }
        
        child.push_back(n);
    }
    
    bool isInside(Node o){
        float d1 = sqrt((x-o.x)*(x-o.x) + (y-o.y)*(y-o.y));
        float d2 = r + o.r;
        if (d1<d2)
            return true;
        return false;
    }
    int depth(){
        int ret = 1;
        int cur;
        for (int i = 0; i < child.size(); i++){
            cur = 1 + child[i].depth();
            ret = max(ret, cur);
        }
        return ret;
    }
};

void check(bool ret);

bool gDebug;
int gN;
Node gRoot;

void solve(){
    vector<int> depth;
    for (int i = 0; i < gRoot.child.size(); i++){
        depth.push_back(gRoot.child[i].depth());
    }
    int size = depth.size();
    sort(depth.begin(), depth.end());
    int ret;
    if (size==0){
        ret =0;
    }else if (size==1){
        ret = depth[0];
    }else{
        ret = depth[size-1] + depth[size-2];
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
}

int main(){
    char fn[] = "fortress.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    test();

    // handling input
    int count, p,j,k, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);

        gRoot.child.clear();
        scanf("%d %d %d", &gRoot.x, &gRoot.y, &gRoot.r);

        for (int j = 1; j < gN; j++){
            Node node;
            scanf("%d %d %d", &node.x, &node.y, &node.r);
            gRoot.add(node);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

