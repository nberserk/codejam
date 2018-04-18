#include <algorithm>
#ifdef _MSC_VER
#include <io.h>
#define F_OK 0
#else
#include <unistd.h>
#endif
#include "stdio.h"
#include <math.h>
#include <vector>
#include <map>
#include <queue>
#include <time.h>
#include <string>
#include <sstream>
#include <string.h>
#include <stack>
#include <list>
#include <limits>
#include <set>
#include <iostream>
#include "../myutil.h"

/*
1000x1000 cell, each cell default value is 0
10000 trash : -1
3 trash bin : 1,2,3
move_trash(y,x,dir)


test_3 result:
#call=3824023
#call=4283547
#call=3791827
#call=5921104
#call=4192411
#call=4926693
#call=5569157
#call=4476165
#call=5253270
#call=4244935
result=46484589

*/

#define SWAP(a,b) do{int t=a;a=b;b=t;} while(0)
#define MIN(a,b) ((a)>(b)?(a):(b))

#define HDEBUG
#ifdef HDEBUG
#define hprint(...) printf( __VA_ARGS__ )
#else
#define hprint(...)
#define hassert(a)
#endif


void hassert(bool a){
    if(!a){
        hprint("error_line: %d\n", __LINE__);
    }
}

#define SIZE 1000
#define MAX_TRASH 10000

int trash_map[SIZE][SIZE];
int org_trash_map[SIZE][SIZE];
int trash_bin[3];
int result;
int gMoveTrashCount;

void gen_trash_map(int m[SIZE][SIZE]){
    for (int y=0; y<SIZE; y++) {
        for (int x=0; x<SIZE; x++) {
            m[y][x]=0;
        }
    }
    for (int i=0; i<10000; ) {
        int y = rand()%SIZE;
        int x = rand()%SIZE;

        if(m[y][x]==0){
            m[y][x] = -1;
            i++;
        }
    }
    for(int i=0;i<3;){
        int y = rand()%SIZE;
        int x = rand()%SIZE;
        if(m[y][x]==0){
            m[y][x] = i+1;
            i++;
        }
    }

    for (int y=0; y<SIZE; y++) {
        for (int x=0; x<SIZE; x++) {
            org_trash_map[y][x]=m[y][x];
        }
    }

    for(int i=0;i<3;i++){
        trash_bin[i]=0;
    }
}

void move_trash(int y,int x, int d){
    int ox = x;
    int oy = y;

    switch (d) {
        case 0: y--; break;
        case 1: y++; break;
        case 2: x--; break;
        case 3: x++; break;
    }

    if(x<0 || y<0 || x>=SIZE || y>=SIZE){
        hassert(0);
        return;
    }

    if(org_trash_map[oy][ox]!=-1 || org_trash_map[y][x]==-1){
        hassert(0);
        return;
    }

    if (org_trash_map[y][x]==0) {
        org_trash_map[y][x]=-1;
        org_trash_map[oy][ox]=0;
    }else{
        int i=org_trash_map[y][x]-1;
		hassert(i < 3);
		hassert(trash_bin[i] < 3500);

        trash_bin[i]++;
        org_trash_map[oy][ox]=0;
    }
    gMoveTrashCount++;
}

// start of user code
struct Point{
    int x,y;
	int bin;
};

/////////////////////////////////////////////////
// try 3
Point bin[3];
Point trash[10000];
int consumed[3];
struct Dist{
    int ti, dist, bi;
    int bti, idxMid;
};

int distance(Point& from, Point& to){
    int dx = from.x-to.x;
    if(dx<0) dx=-dx;
    int dy = from.y-to.y;
    if(dy<0) dy=-dy;
    return dx+dy;
}

int partition(Dist d[30000], int s, int e){
    int pv = d[e].dist;
    int j=s-1;
    for(int i=s;i<e;i++){
        if(d[i].dist<pv){
            Dist t = d[i];
            d[i]=d[j+1];
            d[j+1]=t;
            j++;
        }
    }
    j++;
    Dist t=d[j];
    d[j]=d[e];
    d[e]=t;
    return j;
}

void qsort(Dist d[30000], int s, int e){
    if(s<e){
        int p = partition(d,s,e);
        qsort(d, s, p-1);
        qsort(d, p+1,e);
    }
}

bool onWay(int y, int x, int exclude){
    for(int i=0;i<3;i++){
        if(i==exclude) continue;
        if(bin[i].x==x && bin[i].y==y){ //conflict
            return true;
        }
    }
    return false;
}

void move(int& y, int& x, int dir, int bi){
    int ox = x;
    int oy = y;
    switch (dir) {
        case 0: y--; break;
        case 1: y++; break;
        case 2: x--; break;
        case 3: x++; break;
    }

    if(onWay(y,x, bi)){
        x=ox;
        y=oy;
        Point& dest = bin[bi];
        switch (dir) {
            case 0:
                if (y-1==dest.y) {
                    if (x<dest.x) {
                        move(y, x, 3, bi);
                        move(y,x,0,bi);
                    }else{
                        move(y,x,2,bi);
                        move(y,x,0,bi);
                    }
                }else{
                    if(x==0){
                        hassert(0);
                    }else{
                        move(y,x,2,bi);
                        move(y,x,0,bi);
                        move(y,x,0,bi);
                        move(y,x,3,bi);
                    }
                }
                break;
            case 1:
                if(y+1==dest.y){
                    if (x<dest.x) {
                        move(y,x,3,bi);
                        move(y,x,dir,bi);
                    }else{
                        move(y,x,2,bi);
                        move(y,x,dir,bi);
                    }
                }else{
                    hassert(x!=0);
                    move(y,x,2,bi);
                    move(y,x,dir,bi);
                    move(y,x,dir,bi);
                    move(y,x,3,bi);
                }
                break;
            case 2:
                if(x-1==dest.x){
                    if(y>dest.y){
                        move(y, x, 0, bi);
                        move(y,x,dir,bi);
                    }else{
                        move(y,x,1,bi);
                        move(y,x,dir,bi);
                    }
                }else{
                    hassert(y!=0);
                    move(y, x, 0, bi);
                    move(y,x,dir,bi);
                    move(y,x,dir,bi);
                    move(y,x,1,bi);
                }
                break;
            case 3:
                if(x+1==dest.x){
                    if(y>dest.y){
                        move(y,x,0,bi);
                        move(y,x,dir,bi);
                    }else{
                        move(y,x,1,bi);
                        move(y,x,dir,bi);
                    }
                }else{
                    hassert(y!=0);
                    move(y, x, 0, bi);
                    move(y,x,dir,bi);
                    move(y,x,dir,bi);
                    move(y,x,1,bi);
                }
                break;
        }
        return;
    }

    move_trash(oy,ox,dir);
}

void test_3(int m[SIZE][SIZE]){
    for (size_t i = 0; i < 3; i++){
		consumed[i] = 0;
	}

    int ti=0, bi=0;
    for (int y=0; y<SIZE; y++) {
        for (int x=0; x<SIZE; x++) {
            if (m[y][x]>0) {
                bin[bi].x = x;
                bin[bi].y = y;
				bin[bi].bin = bi;
                bi++;

            }else if(m[y][x]==-1){
                trash[ti].x=x;
                trash[ti].y=y;
                ti++;
            }
        }
    }

    Dist d[30000];
    int di=0;
    for(int i=0;i<10000;i++){
        for(int j=0;j<3;j++){
            d[di].ti = i;
            d[di].bi = j;
            d[di].dist = distance(bin[j], trash[i]);
            di++;
        }
    }
    qsort(d, 0, 29999);

    bool used[10000] = {0,};
    for(int i=0;i<30000;i++){
        bi = d[i].bi;
        ti = d[i].ti;
        if(used[ti]) continue;
        if(consumed[bi]>=3500) continue;

        Point& pt = trash[ti];
        int tx = pt.x;
        int ty = pt.y;
        Point& dest = bin[bi];

        //
        while (dest.y-ty>0){
            move(ty, tx, 1,bi);
        }
        while (dest.y-ty<0){
            move(ty, tx, 0,bi);
        }
        while(dest.x-tx>0){
            move(ty,tx,3,bi);
        }
        while(dest.x-tx<0){
            move(ty,tx,2,bi);
        }

        consumed[bi]++;
        used[ti]=true;
    }
}
// end of try3
/////////////////////////////////////////////////

/////////////////////////////////////////////////
// try4
struct Trash{
    Point p;
    int d[3];
    int bi;
};
struct Bin{
    Point pt;
    int trash[MAX_TRASH];
    int tc;

    void add(int ti){
        trash[tc++]=ti;
    }
    void remove(int bti){
        trash[bti]=trash[tc-1];
        tc--;
    }
};

Bin gBin[3];
Trash gTrash[10000];

void test_4(int m[SIZE][SIZE]) {
    for (size_t i = 0; i < 3; i++){
		gBin[i].tc = 0;
	}

    int ti=0, bi=0;
    for (int y=0; y<SIZE; y++) {
        for (int x=0; x<SIZE; x++) {
            if (m[y][x]>0) {
                gBin[bi].pt.x = x;
                gBin[bi].pt.y = y;
                bi++;
            }else if(m[y][x]==-1){
                gTrash[ti].p.x=x;
                gTrash[ti].p.y=y;
                ti++;
            }
        }
    }

    for(int i=0;i<10000;i++){
        int min = 1000000;
        int mini=0;
        for(int j=0;j<3;j++){
            gTrash[i].d[j]=distance(gBin[j].pt, gTrash[i].p);
            if(gTrash[i].d[j]<min){
                min=gTrash[i].d[j];
                mini=j;
            }
        }
        gBin[mini].add(i);
        gTrash[i].bi=mini;
    }

    int min=0;
    int max=0;
    int mid=0;
    for(int i=0;i<3;i++){
        if(gBin[i].tc<gBin[min].tc){
            min=i;
        }
        if(gBin[i].tc>gBin[max].tc){
            max=i;
        }
    }
    for(int i=0;i<3;i++){
        if(i==min || i == max) continue;
        mid=i;
    }

    // move mid to low
    Dist dist[MAX_TRASH];
    if(gBin[mid].tc > 3500){
        Bin* pbin = gBin+mid;
        hassert(pbin->tc<MAX_TRASH);
        for(int i=0;i<pbin->tc;i++){
            int ti = pbin->trash[i];
            dist[i].bti = i;
            dist[i].dist = gTrash[ti].d[mid]-gTrash[ti].d[min];
        }

        qsort(dist, 0, pbin->tc-1);

        Bin* destBin = gBin+min;
        int count = gBin[mid].tc-3500;
        for(int i=0;i<count;i++){
            int ti = pbin->trash[dist[i].bti];
            pbin->remove(dist[i].bti);
            destBin->add(ti);
        }
    }else{
        //move max to mid
        Bin* pbin = gBin+max;
        hassert(pbin->tc<MAX_TRASH);
        for(int i=0;i<pbin->tc;i++){
            int ti = pbin->trash[i];
            dist[i].bti = i;
            dist[i].dist = gTrash[ti].d[max]-gTrash[ti].d[mid];
        }
        qsort(dist, 0, pbin->tc-1);

        Bin* destBin = gBin+mid;
        int count = 3500-gBin[mid].tc;
        for(int i=0;i<count;i++){
            int ti = pbin->trash[dist[i].bti];
            pbin->remove(dist[i].bti);
            destBin->add(ti);
        }
    }

    // move max to min
    Bin* src = gBin+max;
    Bin* middle =gBin+mid;
    Bin* dest = gBin+min;
    for(int i=0;i<src->tc;i++){
        int ti = src->trash[i];
        dist[i].bti=i;
        dist[i].idxMid=-1;
        dist[i].dist = gTrash[ti].d[max]-gTrash[ti].d[min];
        hassert(gBin[mid].tc==3500);
        for(int j=0;j<3500;j++){
            int can = gBin[mid].trash[j];
            int t = gTrash[ti].d[max]+gTrash[can].d[mid]-gTrash[ti].d[mid]-gTrash[can].d[min];
            if(t<dist[i].dist){
                dist[i].idxMid=j;
                dist[i].dist=t;
            }
        }
    }
    qsort(dist,0, src->tc-1);
    int count = src->tc-3500;
    for(int i=0;i<count;i++){
        int ti = src->trash[dist[i].bti];
        if(dist[i].idxMid==-1){
            src->remove(dist[i].bti);
            dest->add(ti);
        }else{
            src->remove(dist[i].bti);
            middle->add(ti);
            ti = middle->trash[dist[i].idxMid];
            middle->remove(dist[i].idxMid);
            dest->add(ti);
            hassert(middle->tc==3500);
        }
    }
    hassert(gBin[max].tc==3500);


    // move
    for(int i=0;i<3;i++){
        Bin* pbin = gBin+i;

    }

}
/////////////////////////////////////////////////

int main(){
    srand(3);

    for (int i = 0; i < 10; i++) {

        gMoveTrashCount=0;
		clock_t start = clock();
        gen_trash_map(trash_map);
		//test(trash_map);
		test_4(trash_map);
        result += clock()-start;

        result += gMoveTrashCount;
		printf("#call=%d\n", gMoveTrashCount);
        for (size_t y = 0; y < SIZE; y++)
        {
            for (size_t x = 0; x < SIZE; x++)
            {
                if(org_trash_map[y][x] == -1)
                    result += 10000;
            }
        }
    }

    printf("result=%d\n", result);
}



