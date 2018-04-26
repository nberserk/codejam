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
int approximateMove;

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

        approximateMove += distance(pt,dest);

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

    printf("#appMove=%d ", approximateMove);
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
struct Cell{
    Point pt;
    int depth;
    int ti;
    int dir;
    Cell* parent;
    bool visited;
};
Cell cell[SIZE][SIZE];
Point queue[2000000];
int qs, qe;

void addQueue(Point& pt){
    queue[qe] = pt;
    qe++;
    hassert(qe<2000000);
}

 Point popQueue(){
     Point& r = queue[qs++];
     return r;
 }

 void bfs(int m[SIZE][SIZE], Bin* pStart){

    for (int y=0; y<SIZE; y++) {
        for (int x=0; x<SIZE; x++) {
            cell[y][x].visited=false;
        }
    }

    qs = qe=0;
    addQueue(pStart->pt);
    Cell* pCell = &cell[pStart->pt.y][pStart->pt.x];
    pCell->parent=0;
    pCell->pt = pStart->pt;

    int remaining=pStart->tc;
    int dir[4][2] = {{0,1}, {0,-1},{1,0},{-1,0} };
    while(remaining>0){
        hassert(qe>qs);
        Point pt = popQueue();

        Cell& c = cell[pt.y][pt.x];
        if(c.visited)continue;
        c.visited=true;

        if (m[pt.y][pt.x]==-1) {
            // move to bin
            Cell* t = &c;
            while (t->parent!=0) {
                move_trash(t->pt.y, t->pt.x, t->dir);
                t = t->parent;
            }

			m[pt.y][pt.x] = 0;
            remaining--;
            //hprint("consumed(%d,%d), %d,%d \n", pt.x, pt.y, consumed, totalConsumed);
        }

        Point np;
        for (int j=0; j<4; j++) {
            int nx = pt.x + dir[j][0];
            int ny = pt.y + dir[j][1];
            if(nx<0||ny<0||nx>=SIZE||ny>=SIZE) continue;
            Cell* nc = &cell[ny][nx];
            if(nc->visited || m[ny][nx]>0) continue;
            nc->parent = &c;
            nc->depth = c.depth+1;
            nc->pt.x = nx;
            nc->pt.y = ny;
            nc->dir = j;

            np.x = nx;
            np.y = ny;
            addQueue(np);
        }
    }
}

Bin gBin[3];
Trash gTrash[10000];
int (*gM)[SIZE];

void move_4(int& y, int& x, int dir, int bi){
    int ox = x;
    int oy = y;
    switch (dir) {
        case 0: y--; break;
        case 1: y++; break;
        case 2: x--; break;
        case 3: x++; break;
    }

    if(gM[y][x]==0 || gM[y][x]==bi+1){
        move_trash(oy,ox,dir);
        return;
    }

    //if( gM[y][x]==-1 || (gM[y][x]>0 && gM[y][x]!=bi+1) )
    {
        x=ox;
        y=oy;
        Point& dest = gBin[bi].pt;
        switch (dir) {
            case 0:
                if (x<dest.x) {
                    move_4(y, x, 3, bi);
                    move_4(y,x,0,bi);
                }else if(x>dest.x){
                    move_4(y,x,2,bi);
                    move_4(y,x,0,bi);
                }else{
                    hassert(0);
                }
                break;
            case 1:
                if (x<dest.x) {
                    move_4(y,x,3,bi);
                    move_4(y,x,dir,bi);
                }else if(x>dest.x){
                    move_4(y,x,2,bi);
                    move_4(y,x,dir,bi);
                }else{
                    hassert(0);
                }
                break;
            case 2:
               if(y>dest.y){
                    move_4(y, x, 0, bi);
                    move_4(y,x,dir,bi);
                }else if(y<dest.y){
                    move_4(y,x,1,bi);
                    move_4(y,x,dir,bi);
                }else{
                    move_4(y, x, 0, bi);
                    move_4(y,x,dir,bi);
                    move_4(y,x,dir,bi);
                    move_4(y, x, 1, bi);
                }
                break;
            case 3:
               if(y>dest.y){
                    move_4(y,x,0,bi);
                    move_4(y,x,dir,bi);
                }else if(y<dest.y){
                    move_4(y,x,1,bi);
                    move_4(y,x,dir,bi);
                }else{
                    move_4(y, x, 0, bi);
                    move_4(y,x,dir,bi);
                    move_4(y,x,dir,bi);
                    move_4(y, x, 1, bi);
                }
                break;
        }
    }
}


void test_4(int m[SIZE][SIZE]) {
    gM=m;
    for (size_t i = 0; i < 3; i++){
		gBin[i].tc = 0;
	}

    int ti=0, bi=0;
    for (int y=0; y<SIZE; y++) {
        for (int x=0; x<SIZE; x++) {
            if (m[y][x]>0) {
                hassert(m[y][x]>0&&m[y][x]<=3);
                bi = m[y][x]-1;
                gBin[bi].pt.x = x;
                gBin[bi].pt.y = y;
            }else if(m[y][x]==-1){
                gTrash[ti].p.x=x;
                gTrash[ti].p.y=y;
                cell[y][x].ti=ti;
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
    printf("min=%d,max=%d,mid=%d\n",min,max, mid);

    Dist dist[MAX_TRASH];
    if (gBin[0].tc > 3500 || gBin[1].tc > 3500 || gBin[2].tc > 3500) {
        // TODO: overflow to low

        // move mid to min
        if(gBin[mid].tc > 3500){
            Bin* pbin = gBin+mid;
            hassert(pbin->tc<MAX_TRASH);
            for(int i=0;i<pbin->tc;i++){
                int ti = pbin->trash[i];
                dist[i].bti = i;
                dist[i].dist = -gTrash[ti].d[mid]+gTrash[ti].d[min];
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
                dist[i].dist = -gTrash[ti].d[max]+gTrash[ti].d[mid];
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
        Dist distMaxToMin[MAX_TRASH];
        Dist distMaxToMid[3500];
        Dist distMidToMin[3500];
        hassert(middle->tc<=3500 && dest->tc<=3500);

        for(int i=0;i<src->tc;i++){
            int ti = src->trash[i];
            distMaxToMin[i].bti=i;
            distMaxToMin[i].dist = -gTrash[ti].d[max]+gTrash[ti].d[min];

            distMaxToMid[i].bti=i;
            distMaxToMid[i].dist = -gTrash[ti].d[max]+gTrash[ti].d[mid];
        }
        qsort(distMaxToMin,0, src->tc-1);
        qsort(distMaxToMid,0, src->tc-1);

        for(int i=0;i<middle->tc;i++){
            int ti = middle->trash[i];
            distMidToMin[i].bti=i;
            distMidToMin[i].idxMid=-1;
            distMidToMin[i].dist = -gTrash[ti].d[mid]+gTrash[ti].d[min];
        }
        qsort(distMidToMin, 0, middle->tc-1);

        bool used[MAX_TRASH] = {0, };
        int remain=src->tc-3500;
        int iMaxToMin=0;
        int iMaxToMid=0;
        int iMidToMin=0;
        while(remain>0){
            while(used[distMaxToMin[iMaxToMin].bti])
                iMaxToMin++;
            while(used[distMaxToMid[iMaxToMid].bti])
                iMaxToMid++;
            while(used[distMidToMin[iMidToMin].bti])
                iMidToMin++;
            hassert(iMaxToMid<3500&&iMaxToMin<3500&&iMidToMin<src->tc);

            if(distMaxToMin[iMaxToMin].dist < distMaxToMid[iMaxToMid].dist + distMidToMin[iMidToMin].dist){
                int ti = src->trash[distMaxToMin[iMaxToMin].bti];
                dest->add(ti);
                src->remove(distMaxToMin[iMaxToMin].bti);
                used[ti]=true;
            }else{
                int ti = src->trash[distMaxToMid[iMaxToMid].bti];
                middle->add(ti);
                src->remove(distMaxToMid[iMaxToMid].bti);
                used[ti]=true;
                ti = middle->trash[distMidToMin[iMidToMin].bti];
                dest->add(ti);
                middle->remove(distMidToMin[iMidToMin].bti);
                used[ti]=true;
            }
            hassert(middle->tc==3500);
            remain--;
        }
    }else{
        hassert(0);
    }


    // move

    for(int k=0;k<3;k++){
        Bin* pbin = gBin+k;
        for(int i=0;i<pbin->tc;i++){
            int ti=pbin->trash[i];
            approximateMove+=gTrash[ti].d[k];
        }
        bfs(m, pbin);
        /*
        for(int i=0;i<pbin->tc;i++){
            int ti = pbin->trash[i];
            dist[i].ti = ti;
            dist[i].dist = gTrash[ti].d[k];
        }
        qsort(dist, 0, pbin->tc-1);
        for(int i=0;i<pbin->tc;i++){
            int ti = dist[i].ti;
            int bi = k;

            Point& pt = gTrash[ti].p;
            int tx = pt.x;
            int ty = pt.y;
            Point& dest = gBin[bi].pt;

            while(tx!= dest.x || ty!=dest.y){
                if (dest.y-ty>0){
                    move_4(ty, tx, 1,bi);
                    continue;
                }
                if (dest.y-ty<0){
                    move_4(ty, tx, 0,bi);
                    continue;
                }
                if(dest.x-tx>0){
                    move_4(ty,tx,3,bi);
                    continue;
                }
                if(dest.x-tx<0){
                    move_4(ty,tx,2,bi);
                    continue;
                }
            }
            m[pt.y][pt.x]=0;
        }*/
    }
    printf("#appMove=%d ", approximateMove);
}
/////////////////////////////////////////////////

int main(){
    srand(3);

    for (int i = 0; i < 10; i++) {
        gen_trash_map(trash_map);

        clock_t start = clock();
		test_3(trash_map);
//		test_4(trash_map);
        result += clock()-start;

        //result += gMoveTrashCount;
		printf(" #call=%d\n", gMoveTrashCount);
        for (size_t y = 0; y < SIZE; y++)
        {
            for (size_t x = 0; x < SIZE; x++)
            {
                if(org_trash_map[y][x] == -1)
                    result += 10000;
            }
        }
    }

    result += gMoveTrashCount;
    printf("result=%d, #move=%d\n", result, gMoveTrashCount);
}



