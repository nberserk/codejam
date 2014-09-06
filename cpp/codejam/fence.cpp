// algospot
// fence
// http://algospot.com/judge/problem/read/FENCE

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <iostream>
#include <ostream>

using namespace std;
int N;
int h[20000];

bool gDebug = true;

// start <= n < end
int maxAreaBrute(int start, int end){
    int maxValue = 0;
    
    int s, e, minHeight, cur;
    for (s=start; s<end; s++) {
        for (e=s; e<end; e++) {
            if (s==e) {
                minHeight = h[s];
            }else{
                minHeight = min(minHeight, h[e]);
                if (minHeight*(N-1-s+1)<=maxValue) {
                    //printf("break s=%d, minHeight=%d, N=%d, max=%d\n", s, minHeight, N, maxValue);
                    break;
                }
            }
            cur = minHeight*(e-s+1);
            
            maxValue = max(cur, maxValue);
        }
    }
    
    return maxValue;
}

// left <= n <= right
int maxAreaDivide(int left, int right){
    if (left==right) {
        return h[left];
    }
    int half = (left + right)/2;
    int ret = max( maxAreaDivide(left, half), maxAreaDivide(half+1, right));
    
    //
    int minHeight = min(h[half], h[half+1]);
    int l = half, r = half+1, cur;
    
    while (l>=left || r <=right) {
        cur = minHeight * (r-l+1);
        if (cur > ret) {
            ret = cur;
        }
        
        // exit condition
        if (l==left && r==right) {
            break;
        }
        
        // expand region
        if (r==right ) {
            l--;
            minHeight = min(minHeight, h[l]);
        }else if (l==left){
            r++;
            minHeight = min(minHeight, h[r]);
        }else if ( h[l-1] > h[r+1]) {
            // expand to left
            l--;
            minHeight = min(minHeight, h[l]);
        }else{
            r++;
            minHeight = min(minHeight, h[r]);
        }
        // debug
//        if (l<left || r > right) {
//            printf("error");
//        }
    }
    
    //
    return ret;
}

clock_t gMarkTime;
void mark(){
    gMarkTime = clock();
}

void endMark(){
    gMarkTime = clock() - gMarkTime;
    int ms = double(gMarkTime) / CLOCKS_PER_SEC * 1000;
    printf("elapsed time : %d\n",  ms);
}

int main(){
    FILE *fp;
    if (gDebug) {
        fp = freopen("fence.in", "r", stdin);
    }
    
    int count=-1, p,j;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%d", &N);
        for (j=0; j<N; j++) {
            scanf("%d", h+j);
        }
        //int s2 = maxAreaBrute(0, N);
        int s2 = maxAreaDivide(0, N-1);
        printf("%d\n", s2);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


