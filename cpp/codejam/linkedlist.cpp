#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string>
#include <string.h>
#include <stack>
#include <limits>
#include <set>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_MOD 10000000
typedef long long ll;


class Node{
public :
    Node(int v){
        value = v;
        pNext = pPrev = 0;
    }    
    
    int value;
    Node *pNext, *pPrev;

    void append(Node* n){
        n->pPrev = this;
        n->pNext = this->pNext;

        if (this->pNext){
            this->pNext->pPrev = n;
        }
        this->pNext = n;
    }

    void deleteNode(){
        if (pPrev){
            pPrev->pNext = pNext;
        }
        if (pNext){
            pNext->pPrev = pPrev;
        }
    }

    Node* find(int v){
        if (v==value){
            return this;
        }

        if (pNext){
            return pNext->find(v);
        }
        return 0;
    }

    void print(){
        printf("%d -> ", value);
        if (pNext){
            pNext->print();
        }else{
            printf("\n");
        }
    }
};


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

int main(){

    Node *root = new Node(1);
    Node *ten = new Node(10);
    root->append(ten);
    root->print();

    check(root->pNext==ten);
    check(ten->pPrev == root);

    root->append(new Node(5));
    root->print();

    Node* target = root->find(5);
    if (target){
        target->deleteNode();
        root->print();        
    }
    
    target = root->find(10);
    if (target) {
        target->deleteNode();
        root->print();
        check(root->pNext==0);
        check(root->pPrev==0);
    }
    
    
    return 0;
    
}

