#include <algorithm>
#ifdef _MSC_VER
#include <io.h>
#define F_OK 0
#else
#include <unistd.h>
#endif
#include <stdio.h>
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



using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001

#define SWAP(a,b) do{int t=a;a=b;b=t;} while(0)

#define HDEBUG
#ifdef HDEBUG
#define hprint(fmt, args...) printf(fmt, ##args)
#else
#define hprint(fmt,args...)
#define hassert(a)
#endif

#define MAX_CAR 1000000
#define TREE


void hassert(bool a){
    if(!a){
        hprint("error_line: %d\n", __LINE__);
    }
}



#define H_MIN 64
#define H_MAX 128
#define R_MAX 2*H_MAX

char gh1[H_MAX+1], gh2[H_MAX+1];
char gResult[R_MAX+1];


char generate_char(){
    int n = rand()%36;
    if(n<=9) return '0'+n;
    else return (n-10)+'A';
}

char generate_char_first(){
    int n = rand()%35;
    n++;
    if(n<=9) return '0'+n;
    else return (n-10)+'A';
}

void generate(char* ch){
    int len = rand()%(H_MAX-H_MIN) + H_MIN;
    ch[0] = generate_char_first();
    for (int i = 1; i < len; i++){
        ch[i]= generate_char();
    }
    ch[len]=0;    
}


//////////////////////////////////////////////////

#define TO_INT(ch) ch>'9' ? ch-'A'+10 : ch-'0'
#define TO_CHAR(in) in>9 ? 'A'+in-10 : '0'+in

void mul_brute(char* result, char* h1, char* h2)
{
    int l1 = strlen(h1);
    int l2 = strlen(h2);
    int re[R_MAX+1]={0,};

    int di=0;
    for ( int i = l1-1; i >=0; i--){
        int dj=0;
        for ( int j = l2-1; j >=0; j--){
            int a = TO_INT(h1[i]);
            int b = TO_INT(h2[j]);
            re[dj+di] = a*b;
            dj++;
        }
        di++;
    }

    int len=l1+l2;
    for (int i = 0; i < len; i++){
        if (re[i]>=36){
            int up = re[i]/36;
            re[i] %= 36;
            re[i+1]+=up;        
        }
    }

    di=0;
    for (int i = len; i >=0; i--){
        if(re[i]==0) continue;
        result[di++] = TO_CHAR(re[i]);
    }
    result[di]=0;    
}

//////////////////////////////////////////////////

void test(){
    // for(int i=0;i<100;i++){
    //     char c = generate_char_first();
    //     printf("%c, ", c);
    // }

    hassert(TO_INT('A')==10);
    hassert(TO_INT('1')==1);
    hassert(TO_CHAR(0)=='0');
    hassert(TO_CHAR(35)=='Z');
    
}

int main(){
    srand(3);

    test();

    int sum=0;
    h_startTimeMeasure();
    for (int  t=0; t<50; t++) {
        for(int i=0;i<1000;i++){
            generate(gh1);
            generate(gh2);
            mul_brute(gResult, gh1, gh2);
        }

        int len = strlen(gResult);
        for (int i = 0; i < len; i++){
            sum+=gResult[i];
        }
        
        printf("%d: %d\n", t+1, sum);
    }

    
   
    h_endTimeMeasure();
    
}

