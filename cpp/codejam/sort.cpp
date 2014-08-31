// all kind of sort

#include <algorithm>
#include <unistd.h>
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
#include <limits>
#include <set>
#include <iostream>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);



bool gDebug;
int gN;
int gLen[100];

void printArray(int *a, int len){
   for (int i = 0; i < len; i++){
        printf("%d ", a[i]);
    }
    printf("\n");
}

void maxHeapify(int* a, int len, int i){
    int l = i*2+1;
    int r = i*2+2;
    int largest = i;
    if (l<len && a[l]>a[i]){
        largest = l;
    }
    if (r<len && a[r]>a[largest]){
        largest = r;
    }

    if (i!=largest){
        swap(a[i], a[largest]);
        maxHeapify(a, len, largest);
    }    
}

void buildMaxHeap(int*a, int len){
    for (int i = len/2-1; i >=0; i--){
        maxHeapify(a, len, i);
        printArray(a, len);
    }
}

void heapsort(int*a, int len){    
    buildMaxHeap(a, len);    
    int size=len-1;
    swap(a[0], a[len-1]);
    for (int i = size; i >=2 ; i--){
        maxHeapify(a, i, 0);
        swap(a[0], a[i-1]);
    }
    
    printArray(a, len);
}

// l, r : inclusive
int partition(int* a, int l, int r){
    int p = a[r];
    int i = l-1;
    for (int j = l; j <= r-1; j++){
        if (a[j]<=p){
            i++;
            swap(a[j], a[i]);
        }
    }
    swap(a[r], a[i+1]);
    return i+1;
}

// l, r : inclusive
void quicksort(int* a, int l, int r){
    if (l<r){
        int i = partition(a, l, r);
        printArray(a, 10);
        quicksort(a, l, i-1);
        quicksort(a, i+1, r);
    }
}

void qsort2(int* arr, int left, int right){
    int pivot = arr[(left+right)/2];
    int i=left, j=right;
    int tmp;

    while(i<=j){
        while(arr[i]<pivot)
            i++;
        while(arr[j]>pivot)
            j--;
        if (i<=j){
            tmp=arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
            i++;
            j--;
        }
    }

    if (left<j){
        qsort2(arr, left, j);
    }
    if (right>i){
        qsort2(arr, i, right);
    }
}

// b : extra buffer,
// right : exclusive
void mergesort(int* a, int left, int right, int* b){
    if(right-left <2) // already sorted
        return;
    
    int mid = (left +right)/2;

    mergesort(a,left, mid, b);
    mergesort(a,mid, right, b);

    int l=left;
    int r=mid;
    for (int i = left; i < right; i++){
        if ( r<right && (l >=mid || a[l]>a[r] ) ){
            b[i]=a[r];
            r++;
        }else{
            b[i]=a[l];
            l++;
        }
    }

    for (int i = left; i < right; i++){
        a[i] = b[i];
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
    int aa[] = {4,1,3,2,16,9,10,14,8,7};
    
    quicksort(aa, 0, 9);
    //buildMaxHeap(aa, 10);
    heapsort(aa, 10);

    
    int a[3] = {3,2,1};
    int b[3];
    mergesort(a, 0, 3, b);
    
    
    int a1[4] = {3,1,2,4};
    int b1[4];
    mergesort(a1, 0, 4, b1);
    
    
}

int main(){
    
    test();
    
    return 0;
    
}

