// http://algospot.com/judge/problem/read/CHINATRIP
// category: 

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
int gMat[1001][1001];
int gVisited[1001];
int gParent[1001];

class Node{
public:    
    Node(int c, int d, int p){
        city=c;
        dist=d;
        parent =p;
    }
    int city;
    int dist;
    int parent;
};
class Comparator{
public:
    bool operator() (const Node& a, const Node& b){
//        if (a.city==b.city)
//            return a.dist >b.dist;
//        return a.city > b.city;
        return a.dist > b.dist;
    }
};

vector<int> constructPath(Node& n){
   vector<int> path;   
   path.push_back(n.city);
   int next = gParent[n.city];
   while (next!=0) {
       path.push_back(next);
       next = gParent[next];
   }
   
   std::reverse(path.begin(), path.end());
   return path;
}

int solve(){
    priority_queue<Node, vector<Node>, Comparator> q;
    Node start(1,0,0);
    q.push(start);
    
    int ret=H_MAX;
    vector<int> path;
    memset(gVisited, -1, sizeof(gVisited));
    while (q.size()>0) {
        Node cur = q.top();
        q.pop();

        if (gVisited[cur.city]!=-1)
            continue;
        if( cur.dist>ret)
            continue;


        gParent[cur.city] = cur.parent;
        //printf("%d-%d=%d\n", cur.city, cur.parent, cur.dist);
        
        if (cur.city==gN) {
            if (ret == cur.dist) {
                vector<int> np = constructPath(cur);
                for (int i = 0; i < np.size(); i++){
                    if(i>=path.size() || path[i]>np[i]){                    
                        path=np;
                        break;                            
                    }
                }                
            }else if(ret > cur.dist){
                ret = cur.dist;                
                path = constructPath(cur);
                
            }
//            for (int i = 0; i < path.size(); i++){
//                printf("%d-", path[i]);
//            }
//            printf(" :%d\n", cur.dist);
            continue;
        }
        gVisited[cur.city]=1;
        for (int i=1; i<=gN; i++) {
            if (gVisited[i]!=-1) {
                continue;
            }
            if (gMat[cur.city][i]>0){
                q.push( Node(i, cur.dist+gMat[cur.city][i], cur.city) );
            }
        }        
    }    

    printf("%d\n", ret);
    for (int i = 0; i < path.size(); i++){
        printf("%d ", path[i]);
    }
    printf("\n");
    

    return ret;
}

void check(int expected, int actual){
    if (expected!=actual) {
        printf("failed expected=%d, actual=%d\n", expected, actual);
    }
}


void test(){

}

int main(){
    char fn[] = "chinatrip.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }

    
    //test();

    // handling input
    int count, p,j,k;
    scanf("%d", &count);
    for (int p = 0; p < count; p++){

        int edge;
        scanf("%d %d", &gN, &edge);
        for (int j = 0; j < edge; j++){
            int from, to, d;
            scanf("%d %d %d", &from, &to, &d);
            gMat[from][to] = d;
            gMat[to][from] = d;
        }
        
        solve();
        memset(gMat, -1, sizeof(gMat));        
    }    
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}





