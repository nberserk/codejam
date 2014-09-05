#include <stdio.h>
#include <stdlib.h>

#define SIZE 1000

int run_test(const unsigned char cloud[SIZE][SIZE]);

static unsigned char cloud[10][SIZE][SIZE];

#define SIZE 1000


int run_test(const unsigned char cloud[SIZE][SIZE])
{
    // WRITE YOUR CODES HEAR

    return 0; // 구름의 최대 높이
}


void build_cloud(void)
{   
	for (int k = 0; k < 10; k++)
	{
		for(int j = 0; j < SIZE; j++)
			for(int i = 0; i < SIZE; i++)
				cloud[k][j][i] = 1;
		
		for(int i = 0; i < SIZE * 2; i++)
			cloud[k][rand() % SIZE][rand() % SIZE] = 0;
	}
}



int largestSquare(unsigned char *c){
    bool gVisited[SIZE][SIZE];
    memset(gVisited, false, sizeof(gVisited));

    int ret = 0;
    for (int i = 0; i < SIZE; i++){
        for (int j = 0; j < SIZE; j++){
            if (gVisited[i][j]){
                continue;
            }
            if (c[i][j]==1){
                for (int s = 1; s <= SIZE; s++){
                    
                }
            }
        }
    }
    
    int ret 
}


void solve(unsigned char* c){
    
    int v = -987654321;
    for (int i = 0; i < SIZE; i++){
        for (int j = 0; j < SIZE; j++){
            cur = largestSquare(c, i,j,0);
            v = max(v, cur);
        }
    }
    
}
void check(bool ret){
    if (ret==false) {
        printf("failed\n");
    }
}

void check(int expected, int actual){
    if (expected!=actual) {
        printf("failed expected=%d, actual=%d\n", expected, actual);
    }
}

void check(char expected, char actual){
    if (expected!=actual) {
        printf("failed expected=%c, actual=%c\n", expected, actual);
    }
}

void test(){
    for (int i = 0; i < 1000; i++){
        for (int j = 0; j < 1000; j++){
            cloud[0][i][j]=0;
        }
    }

    cloud[0][0][0] =  cloud[0][1][0] =  cloud[0][2][0] = 1;
    cloud[0][0][1] =  cloud[0][1][1] =  cloud[0][2][1] = 1;
    cloud[0][0][2] =  cloud[0][1][2] =  cloud[0][2][2] = 1;
    cloud[0][1][3] =  cloud[0][2][3] = 1;

    process(cloud[0]);    
}


int main(void)
{
    test();
    
    build_cloud();

    for (int c = 0; c < 10; c++)
        printf("%d\n", run_test(cloud[c]));


    return 0;
}
