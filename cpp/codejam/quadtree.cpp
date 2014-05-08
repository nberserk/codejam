//  algospot-quadtree , http://algospot.com/judge/problem/read/QUADTREE

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <iostream>
#include <ostream>

using namespace std;

#define MAX_VALUE  987654321

bool gDebug = true;
char seq[1000];


class Node{
public:
    Node(){
        v=0;
        c.reserve(4);
    }
    char v;
    vector<Node*> c;
    
    void print(){
        if (v!=0) {
            cout << v;
        }else{
            cout << "x";
//            for (int i=0; i<4; i++) {
//                c[i]->print();
//            }
            c[2]->print();
            c[3]->print();
            c[0]->print();
            c[1]->print();
        }
    }
};

int makeTree (char* s, Node* n){
    if (*s==0) {
        return 0;
    }
    
    int offset = 0;
    for (int i=0; i<4; i++) {
        if (s[offset]=='x') {
            offset ++;
            Node* nn = new Node();
            offset+= makeTree(s+offset, nn);
            n->c[i] = nn;
        }else{
            Node* nn = new Node();
            nn->v = s[offset++];
            n->c[i] = nn;
        }
    }
    return offset;
}

int main(){
    FILE *fp;
    if (gDebug) {
        fp = freopen("quadtree.in", "r", stdin);
    }
    
    int count=-1, p;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%s", seq);
        
        Node root;
        if (seq[0]!='x') {
            root.v = seq[0];
        }
        makeTree(seq+1, &root);
        root.print();
        cout << endl;
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


