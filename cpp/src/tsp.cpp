
#include <stdio.h>
#include <math.h>
#include <limits.h>

#define MAX_N 10
int N=4;
int cache[MAX_N][1024];

int tsp(int cost[4][4], int n, int end){
	int min = INT_MAX, cur;
    //int n = powl(2, N);

	if(n==0){
		return cost[end][0];
	}

	int i, mask;

	for(i=0;i<N;i++){
		mask = 1<<i;
		int temp = n&mask;
		if(temp == mask){
			cur = cost[end][i] + tsp(cost, n-mask, i);
			if(cur < min){
				min = cur;
			}
		}		
	}


	printf("%d,%d=%d\n", end,n,min);
	return min;
}



int main(){
    int c[4][4] = {
            {0, 12, 11, 16},
            {15, 0, 15, 10},
            {8,14,0,8},
            {9,11,17,0}
    };

    int min = tsp(c, pow((long double)2, 4) -2, 0);
    printf("%d\n", min);
}
