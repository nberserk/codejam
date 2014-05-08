//
//  boardcover.cpp
//  codejam
//  algospot-boardcover
//  Created by Darren ha on 4/24/14.
//  Copyright (c) 2014 Darren ha. All rights reserved.
//

#include <unistd.h>
#include <stdio.h>

char m[20][20];
int H, W;
bool debug = true;

// 0     1
// 00 , 11 , 22 , 33
//            2   3
int dx[4][3] = {{0,0,1}, {0,-1,0}, {0,1,1}, {0,1,0}};
int dy[4][3] = {{0,1,1}, {0, 1,1}, {0,0,1}, {0,0,1}};


int solve(char c[20][20])
{
    int x,y;
    // find left top empty space
    bool found = false;
    for (y=0; y<H; y++) {
        for (x=0; x<W; x++) {
            if (c[y][x] == '.') {
                found = true;
                break;
            }
        }
        if (found) {
            break;
        }
    }
    
    // if not found
    if (!found) {
        return 1;
    }
    
    int count = 0, tx,ty, i,j;
    // for each possible cases
    for (i=0; i<4; i++) {
        bool match = true;
        for (j=0;j<3;j++){
            tx = x + dx[i][j];
            ty = y + dy[i][j];
            if (tx < 0 || ty <0 || tx >= W || ty >= H) {
                match = false;
                break;
            }
            if (c[ty][tx] == '#') {
                match = false;
                break;
            }
        }
        if (!match) {
            continue;
        }
        
        
        
        for (j=0;j<3;j++){
            tx = x + dx[i][j];
            ty = y + dy[i][j];
            c[ty][tx] = '#';
        }
        
        count += solve(c);
        
        // reset
        for (j=0;j<3;j++){
            tx = x + dx[i][j];
            ty = y + dy[i][j];
            c[ty][tx] = '.';
        }
    }    
    
    
    return count;
}


int main(){
    FILE *fp;
    if (debug) {
        fp = freopen("boardcover.in", "r", stdin);
    }
    
    int count=-1, i, p;
    scanf("%d", & count);
    
    for (p=0; p<count; p++) {
        scanf("%d %d", &H, &W);
        for (i=0; i<H; i++) {
            scanf("%s", m+i);
        }
        
        int r = solve(m);
        printf("%d\n", r);
    }
    
    if (debug) {
        fclose(fp);
    }
    return 0;
    
}


