// algospot
// fanmeeting
// http://algospot.com/judge/problem/read/FANMEETING

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>

using namespace std;
bool gDebug = true;
char member[200000];
char fan[200000];
int f[200000];

void buildF()   {
    int n = strlen(member);
    
    int j =0;
    f[0] =0;
    for (int i=1; i<n ;i++){
        while (j>0 && member[j]!= member[i]) {
            j = f[j-1];
        }
        
        if (member[i] == member[j]) {
            j++;
        }else if(j>0){
            j=0;
            //j = f[i-1];
        }
        
        f[i] = j;
        //printf("%d ", j);
    }
}

int solve(){
    int ret=0;
    
    buildF();
    
    int n = strlen(fan);
    int m = strlen(member);
    int j =0;
    for (int i=0; i<n; i++) {
        while (j>0 && fan[i]!=member[j]) {
            j=f[j-1];
        }
        if (fan[i]==member[j]) {
            j++;
        }
        
        if (j==m) {
            ret++;
            //printf("matched %d\n", (i-j+1));
            j=f[j-1];
        }
    }
    
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
        fp = freopen("fanmeeting.in", "r", stdin);
    }
    
    int count, p,j, size;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%s", member );
        size = strlen(member);
        for (j=0; j<size; j++) {
            if (member[j] == 'F') {
                member[j] = 'M';
            }else{
                member[j] = 'F';
            }
        }
        scanf("%s", fan);
        int s = solve();
        printf("%d\n", s);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


