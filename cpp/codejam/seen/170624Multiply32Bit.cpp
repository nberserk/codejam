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
// #define H_MAX 987654321
// #define H_MIN -987654321
// #define H_EPSILON 0.000001

#define SWAP(a,b) do{int t=a;a=b;b=t;} while(0)

#define HDEBUG
#ifdef HDEBUG
#define hprint(...) printf( __VA_ARGS__ )
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
            re[dj+di] += a*b;
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

#define STEP 5

void mul_step(char* result, char* h1, char* h2)
{
    int l1 = strlen(h1);
    int l2 = strlen(h2);
    long re[R_MAX+11]={0,};
    long n1[H_MAX], n2[H_MAX];
    long s[(H_MAX*2)/STEP]={0,};
    int n1_len=0, n2_len=0;    

    // 
    long sum=0;
    int count=0;
    long multi=1;
    for (int i = l1-1; i >=-1; i--){
        if (i==-1){
            if (sum>0){
                n1[n1_len++]=sum;
            }
            break;
        }
        sum+=  (TO_INT(h1[i])) * multi;
        multi*=36;
        count++;
        if (count==STEP){
            n1[n1_len++]=sum;
            sum=0;
            multi=1;
            count=0;
        }
    }
    multi=1;
    count=0;
    sum=0;
    for (int i = l2-1; i >= -1; i--){
        if (i==-1){
            if (sum>0){
                n2[n2_len++]=sum;
            }
            break;
        }
        sum+= (TO_INT(h2[i]))*multi;
        multi*=36;
        count++;
        if (STEP==count){
            n2[n2_len++]=sum;
            sum=0;
            multi=1;
            count=0;
        }
    }

//    hprint("n1_len:%d, n2_len=%d\n", n1_len, n2_len);
    //
    for ( int i = 0; i <n1_len; i++){      
        for ( int j = 0; j <n2_len; j++){            
            s[i+j] += n1[i]*n2[j];
        }        
    }

    int len=n1_len+n2_len;
    for (int i = 0; i < len; i++){
        long v =s[i];
        int base=i*STEP;
        while(v>0){                    
            re[base++]+=v%36;
            v/=36;
        }
        hassert(base<R_MAX);
        base=i*STEP;
        for (int j=0; j<STEP; j++) {
            hassert(base+j<R_MAX+10);
            if(re[base+j]>=36){
                int up=re[base+j]/36;
                re[base+j+1]+=up;
                re[base+j] %= 36;
            }
        }
    }

    int di=0;
    for (int i = R_MAX-1; i >=0; i--){
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
        for(int i=0;i<10000;i++){
            generate(gh1);
            generate(gh2);
//            mul_brute(gResult, gh1, gh2);
//            hprint("%s\n\n", gResult);
            mul_step(gResult, gh1, gh2);
 //           hprint("%s\n", gResult);
        }

        int len = strlen(gResult);
        for (int i = 0; i < len; i++){
            sum+=gResult[i];
        }
        
        printf("%d: %d\n", t+1, sum);
    }

    
   
    h_endTimeMeasure();
    
}



/**
performance analysis

with mul_brute
 1: 11109
 2: 23782
 3: 33956
 4: 46570
 5: 57585
 6: 69009
 7: 81634
 8: 95833
 9: 107134
 10: 122760
 11: 138006
 12: 153761
 13: 166138
 14: 179539
 15: 195694
 16: 206985
 17: 219610
 18: 235385
 19: 250931
 20: 266239
 21: 279902
 22: 296773
 23: 312701
 24: 328426
 25: 339219
 26: 350796
 27: 363514
 28: 376891
 29: 391410
 30: 405358
 31: 414536
 32: 428391
 33: 440747
 34: 455435
 35: 470595
 36: 484601
 37: 496382
 38: 509068
 39: 523720
 40: 535541
 41: 550357
 42: 563254
 43: 576803
 44: 589507
 45: 605032
 46: 615186
 47: 626887
 48: 640362
 49: 655155
 50: 667674
 elapsed time: 26485ms


 with mul_step
 1: 11109
 2: 23782
 3: 33956
 4: 46570
 5: 57585
 6: 69009
 7: 81634
 8: 95833
 9: 107134
 10: 122760
 11: 138006
 12: 153761
 13: 166138
 14: 179539
 15: 195694
 16: 206985
 17: 219610
 18: 235385
 19: 250931
 20: 266239
 21: 279902
 22: 296773
 23: 312701
 24: 328426
 25: 339219
 26: 350796
 27: 363514
 28: 376891
 29: 391410
 30: 405358
 31: 414536
 32: 428391
 33: 440747
 34: 455435
 35: 470595
 36: 484601
 37: 496382
 38: 509068
 39: 523720
 40: 535541
 41: 550357
 42: 563254
 43: 576803
 44: 589507
 45: 605032
 46: 615186
 47: 626887
 48: 640362
 49: 655155
 50: 667674
 elapsed time: 8913ms

 */
