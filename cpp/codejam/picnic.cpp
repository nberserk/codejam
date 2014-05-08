//
//  main.cpp
//  codejam
//
//  Created by Darren ha on 4/21/14.
//  Copyright (c) 2014 Darren ha. All rights reserved.
//

#include <iostream>
#include <utility>
#include <vector>

using namespace std;

int N, M;
int count;
typedef vector<pair<int,int>> vp;
vector<pair<int,int>> pairs;
#define MAX 10
bool n[10];

bool contain(vector<pair<int,int>> made, pair<int,int> cur){
    int size = made.size();
    for (int i=0; i<size; i++) {
        if (made[i].first == cur.first || made[i].second == cur.first
            || made[i].second == cur.first || made[i].second == cur.second) {
            return true;
        }
    }
    return false;
}

void printVP(vp p){
    int size = p.size();
    for (int i=0; i<size; i++) {
        cout << "(" << p[i].first << ","  << p[i].second<< ")";
    }
    cout << endl;
}


void numMate(bool n[MAX]){
    int i;
    for( i=0; i<N; i++){
        if (n[i]==false) {
            break;
        }
    }
    if (i==N) {
        ::count++;
        return;
    }

    pair<int,int> p;
    int start = i;
    for(int i=0; i<pairs.size(); i++){
        p = pairs[i];
        if (p.first == start && n[p.second] == false) {
            n[p.first] = true;
            n[p.second] = true;
            numMate(n );
            n[p.first] = false;
            n[p.second] = false;
            
        }
        
    }
}

void wrapper(int n2, int m, vp p){
    pairs = p;
    N = n2;
    for (int i=0; i<N; i++) {
        n[i] = false;
    }
    M = m;
    pairs = p;
    ::count = 0;

    numMate(n);
    cout << ::count << endl;
}

int main2(int argc, const char * argv[])
{

    vp pair;
    pair.push_back( make_pair(0, 1));
    wrapper(2, 1, pair );


    pair.clear();
    pair.push_back( make_pair(0, 1));
    pair.push_back( make_pair(1, 2));
    pair.push_back( make_pair(2, 3));
    pair.push_back( make_pair(0, 3));
    pair.push_back( make_pair(0, 2));
    pair.push_back( make_pair(1, 3));
    wrapper(4, 6, pair);
    
    pair.clear();
    pair.push_back( make_pair(0, 1));
    pair.push_back( make_pair(0, 2));
    pair.push_back( make_pair(1, 2));
    pair.push_back( make_pair(1, 3));
    pair.push_back( make_pair(1, 4));
    pair.push_back( make_pair(2, 3));
    pair.push_back( make_pair(2, 4));
    pair.push_back( make_pair(3, 4));
    pair.push_back( make_pair(3, 5));
    pair.push_back( make_pair(4, 5));
    wrapper(6, 10, pair);
    
    return 0;
}

