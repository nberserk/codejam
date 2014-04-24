//
//  boardcover.cpp
//  codejam
//
//  Created by Darren ha on 4/24/14.
//  Copyright (c) 2014 Darren ha. All rights reserved.
//

#include <stdio.h>

int m[20][20];


int main(){
    FILE *fp;
    fp =        freopen("boardcover.in", "r", stdin);
    
    int count;
    scanf("%d", & count);
    
    fclose(fp);
    
    return 1;
    
}


