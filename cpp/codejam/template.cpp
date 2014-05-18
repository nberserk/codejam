// algospot
// 
// http://algospot.com/judge/problem/read/

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>

using namespace std;
bool gDebug ;

int solve(){
  int ret;
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
  char fn[] = ".in";
  if (access(fn, F_OK)!=-1) {
	gDebug = true;
  }
  
    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    int count, p,j;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        // scanf("%d", &N);
        // for (j=0; j<N; j++) {
        //     scanf("%d", h+j);
        // }
        //int s = maxAreaBrute(0, N);
        int s = solve();
        printf("%d\n", s);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


