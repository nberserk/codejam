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


void numMate(vector<pair<int, int>> made, int mIdx){
    if (mIdx>= M) {
        if (made.size() == N/2) {
            printVP(made);
            ::count ++;
        }
        return;
    }
    
    pair<int,int> p;
    for(int i=mIdx; i<pairs.size(); i++){
        p = pairs[i];
        if (contain(made, p) ) {
            numMate(made, i+1);
        }else{
            vp cp(made);
            cp.push_back(p);
            numMate(cp, i+1);

            
        }

    }
}

void wrapper(int n, int m, vp p){
    pairs = p;
    N = n;
    M = m;
    pairs = p;
    ::count = 0;

    vp made;
    numMate(made, 0);
    cout << ::count << endl;
}

int main(int argc, const char * argv[])
{

    vp pair;
    pair.push_back( make_pair(0, 1));
    wrapper(2, 1, pair );


    pair.clear();
    pair.push_back( make_pair(0, 1));
    pair.push_back( make_pair(1, 2));
    pair.push_back( make_pair(2, 3));
    pair.push_back( make_pair(3, 0));
    pair.push_back( make_pair(0, 2));
    pair.push_back( make_pair(1, 3));
    wrapper(4, 6, pair);
    
    return 0;
}

