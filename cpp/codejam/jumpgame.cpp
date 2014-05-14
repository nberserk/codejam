// algospot
// jumpgame
// http://algospot.com/judge/problem/read/JUMPGAME

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>

using namespace std;
bool gDebug;
int c[100][100];
int cache[100][100];
int N;

bool solve(int x, int y){
    
    if (x>=N || y>=N) {
        //printf("rejected\n");
        return false;
    }
    
    if (x==N-1 && y==N-1)
        return true;
    
    if (cache[y][x] !=-1) {
        return false;
    }
    
    //
    int moves = c[y][x] ;
    
    if (solve(x+moves, y))
        return true;
    
    if (solve(x, y+moves))
        return true;
    
    cache[y][x] = 0;
    return false;
}



int main(){
    char fn[] = "jumpgame.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }
    
    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    int count, p,y,x;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%d", &N );
        for (y=0; y<N; y++) {
            for (x=0;x<N;x++){
                scanf ("%d", &c[y][x]);
                cache[y][x] = -1;
            }
        }
        
        bool s = solve(0,0);
        printf("%s\n", s ? "YES" : "NO");
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


