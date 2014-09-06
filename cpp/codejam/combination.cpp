#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string>
#include <string.h>
#include <stack>
#include <limits>
#include <set>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_MOD 10000000
typedef long long ll;


int gCount = 0;

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


void combination(int*a, int n, int k, int i){
    if (i==k){
        printf("%d: ",gCount++);
        for (int i = 0; i < k; i++){
            printf ("%d ", a[i]);
        }
        printf("\n");
    }
    for (int j = i; j < n; j++){
        if (i!=0 && a[j]<a[i-1]   ) {
            continue;
        }
        swap(a[i], a[j]);
        // a[j] : chose
        combination(a, n,k, i+1);
        swap(a[i], a[j]);
    }
}


void permutation(int*a, int n, int i){
    if (i==n){
        printf("%d: ",gCount++);
        for (int i = 0; i < n; i++){
            printf ("%d ", a[i]);
        }
        printf("\n");
    }
    for (int j = i; j < n; j++){
        swap(a[i], a[j]);
        permutation(a, n, i+1);
        swap(a[i], a[j]);
    }
}

void combination2 (int v[], int start, int n, int k, int maxk) {
    int     i;
    
    /* k here counts through positions in the maxk-element v.
     * if k > maxk, then the v is complete and we can use it.
     */
    if (k > maxk) {
        /* insert code here to use combinations as you please */
        for (i=1; i<=maxk; i++) printf ("%i ", v[i]);
        printf ("\n");
        return;
    }
    
    /* for this k'th element of the v, try all start..n
     * elements in that position
     */
    for (i=start; i<=n; i++) {
        
        v[k] = i;
        
        /* recursively generate combinations of integers
         * from i+1..n
         */
        combination2 (v, i+1, n, k+1, maxk);
    }
}



int main(){

    int a[] = {1,2,3,4,5,6,7,8,9,10};
    //combination2(a, 1, 5, 1, 3);
    combination(a, 5,3, 0);
    //combination(a, 10, 3, 0);
    //permutation(a, 10, 0);
  
    return 0;
    
}

