//  algospot-clocksync

#include <unistd.h>
#include <stdio.h>
#include <math.h>

#define MAX_VALUE  987654321

bool gDebug = true;
int gButtonMat[10][5] = { {0,1,2,-1,-1}, {3,7,9,11,-1} , {4,10,14,15,-1}, {0,4,5,6,7}, {6,7,8,10,12},
    {0,2,14,15,-1}, {3,14,15,-1,-1}, {4,5,7,14,15}, {1,2,3,4,5}, {3,4,5,9,13} };
int gMin = MAX_VALUE;

void pressButton2(int m[16], int btn){
    int cell;
    for (int j=0;j<5;j++){
        cell =gButtonMat[btn][j];
        if (cell==-1) {
            break;
        }
        m[cell] ++;
        m[cell] %= 4;
    }
}


void pressButton(int* m, int btn){
    int cell;
    for (int j=0;j<5;j++){
        cell =gButtonMat[btn][j];
        if (cell==-1) {
            break;
        }
        m[cell] ++;
        m[cell] %= 4;
    }
}

int solve2 (int m[16], int count, int idxButton){
    int i, targetCell=-1;
    // is every clock is zero ?
    for (i=0; i<16;i++){
        if (m[i]!=0) {
            targetCell = i;
            break;
        }
    }
    
    // exit condtions : all 0 or no more button to press
    if (targetCell==-1) {
        return count;
    }
    if (idxButton==10) {
        return MAX_VALUE;
    }

    
    int min = MAX_VALUE, cur,j;
    for (i=0; i<4; i++) {
        cur =solve2(m, count + i, idxButton+1) ;
        min = cur < min ? cur : min ;
        
//        int cell;
//        for (j=0;j<5;j++){
//            cell = gButtonMat[idxButton][j];
//            if (cell==-1) {
//                break;
//            }
//            m[cell] ++;
//            m[cell] %= 4;
//        }
        pressButton2(m, idxButton);
    }

    return min;
}

int main(){
    FILE *fp;
    if (gDebug) {
        fp = freopen("clocksync.in", "r", stdin);
    }
    
    int count=-1, i, p, d;
    scanf("%d", & count);
    int m[16];
    int btn[10];
    
    for (p=0; p<count; p++) {
        // reset btn
        for (i=0; i<10; i++) {
            btn[i] = 3;
        }
        gMin = MAX_VALUE;

        for (i=0;i<16;i++){
            scanf("%d", &d);
            switch (d) {
                case 12:
                    m[i] = 0;
                    break;
                case 3:
                    m[i] = 1;
                    break;
                case 6:
                    m[i] = 2;
                    break;
                case 9 :
                    m[i] = 3;
                    break;
                default:
                    printf("error\n");
            }
        }
        int r = solve2(m,0, 0);
        if (r==MAX_VALUE) {
            r=-1;
        }
        printf("%d\n", r);
        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


