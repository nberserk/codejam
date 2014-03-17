#include <stdio.h>
#include <vector>
#include <map>
#include <algorithm>
#include <math.h>
#include <limits.h>
#include <ctime>
#include <float.h>

using namespace std;

class Star{
public:
	int x, y;

	bool operator < (const Star& other) const{
		if(x != other.x){
			return (x < other.x);
		}else{
			return (y < other.y);
		}
	}
};

struct Result{
	int idxSmall, idxBig;
	float distance;
};


int N;
bool debug=false;
Star   star[100000];
int sortedX[100000]; // index of sorted by x coordinates

float dist(Star& a, Star& b){
	int dx = a.x-b.x;
	int dy = a.y - b.y;
	return sqrt((float)(dx*dx + dy*dy));
}

int dist_int(Star& a, Star& b){
	int dx = a.x-b.x;
	int dy = a.y - b.y;
	return dx*dx + dy*dy;
}

bool compareX (int i,int j) {
	return (star[i].x < star[j].x ); 
}

// i,j : sorted idx
bool compareY (int i,int j) {
	return (star[i].y < star[j].y ); 
}

void MergeResult(Result& src, float dist, int idxStart, int idxEnd){
	//if(idxStart == idxEnd){
	//	printf("\n");
	//}
	int small = idxStart > idxEnd ? idxEnd : idxStart;
	int big = idxStart > idxEnd ? idxStart : idxEnd ;

	if(src.distance > dist){
		src.distance = dist;
		src.idxSmall = small;
		src.idxBig = big;
	}else if (src.distance == dist){
		if(src.idxSmall > small
			|| (src.idxSmall == small && src.idxBig > big)){
			src.idxSmall = small;
			src.idxBig = big;
		}
	}
}

void MergeResult(Result& src, Result& other){
	MergeResult(src, other.distance, other.idxSmall, other.idxBig);
	/*
	if(src.distance > other.distance){
		src.distance = other.distance;
		src.idxSmall = other.idxSmall;
		src.idxBig = other.idxBig;
	}else if (src.distance == other.distance){
		if(src.idxSmall > other.idxSmall 
			|| (src.idxSmall == other.idxSmall && src.idxBig > other.idxBig)){
			src.idxSmall = other.idxSmall;
			src.idxBig = other.idxBig;
		}
	}*/
}



Result findMinBruteforce(int start, int end){
	
	Result r;
	r.idxSmall = -1;
	r.idxBig = -1;
	r.distance =  FLT_MAX;

	float  cur;

	for (int i=start;i<end;i++){
		for (int j=i+1; j<end;j++){
			if(j>=end) continue;			
			cur = dist(star[i], star[j]);
			//cur = dist_int(star[ sortedX[i] ], star[ sortedX[j] ]);
			MergeResult(r, cur, sortedX[i], sortedX[j]);
			/*
			if(cur < r.distance){
				r.distance = cur;
				int small = sortedX[i] > sortedX[j] ? sortedX[j] : sortedX[i];
				int big = sortedX[i] > sortedX[j] ? sortedX[i] : sortedX[j];
				
				r.idxSmall = small;
				r.idxBig = big;
				//if(dx==0 && dy==0){
				//	printf("smae points\n");
				//}
				//printf("new min : %d(%d,%d)\n", cur, i, j);
			}else if (cur == r.distance){
				int small = sortedX[i] > sortedX[j] ? sortedX[j] : sortedX[i];
				int big = sortedX[i] > sortedX[j] ? sortedX[i] : sortedX[j];
				if(r.idxSmall > small || ( r.idxSmall==small && r.idxBig > big) ){
					r.idxSmall = small;
					r.idxBig = big;
				}
			}*/
		}
	}

	return r;
}


// end is not included
Result findMin(int start, int end){
	
	if (end-start <= 3 ){
		return findMinBruteforce(start,end);
	}	
	
	int i,j;	
	int mid = start + (end-start)/2;	
	int midX = star[sortedX[mid]].x;
	Result min = findMin(start, mid);
	Result minRight = findMin(mid, end);
	MergeResult(min, minRight);	

	// one point in left, the other in right
	vector<int> strip;
	for(i=mid-1;i>=start;i--){
		int dx =  midX - star[sortedX[i]].x;
		if( dx <= min.distance){	
			strip.push_back(sortedX[i]);
		}else{
			break;
		}
	}
	
	for(i=mid;i<end;i++){
		int dx = star[sortedX[i]].x - midX ;
		if( dx <= min.distance){
			strip.push_back(sortedX[i]);
		}else{
			break;
		}
	}


	std::sort(strip.begin(), strip.end(), compareY);
	//for(i=0;i<strip.size();i++){
	//	printf("++ %d \n", star[strip[i]].y);
	//}
	
	int size = strip.size();
	int diffY;
	for(i=0;i<size; i++){
		for(j=i+1; j<size;j++){
			if(j>=size) 
				break;
			diffY = star[strip[j]].y - star[strip[i]].y;
			if(diffY>min.distance){
				break;
			}

			float cur = dist(star[strip[j]], star[strip[i]]);
			MergeResult(min, cur, strip[j], strip[i]);
			/*
			if(cur<min.distance){
				min.distance = cur;

				int small = strip[i] > strip[j] ? strip[j] : strip[i];
				int big = strip[i] > strip[j] ? strip[i] : strip[j];
				
				min.idxBig = big;
				min.idxSmall = small;
			}*/

		}
	}

	//printf("[%d,%d] min=%d\n", start, end, min);
	return min;
}


int closestpair_main(void)
{
    int test_case;
    freopen("closestpair.txt", "r", stdin);
    setbuf(stdout, NULL);

	int problemCount;
	scanf("%d", &problemCount);
	if(debug)
	  printf("%d\n", problemCount);
	for(test_case = 0; test_case < problemCount; test_case++)
	{		
		/*
			Read each test case from standard input.
			The number of node is N[0], N[1], ..., N[9]		
		 */
		scanf("%d", &N);		
		for(int i=0;i<N;i++){
			scanf("%d %d", &star[i].x, &star[i].y);
			sortedX[i] = i;
		}

		sort(sortedX, sortedX+N, compareX);
		/*for(int i=0;i<N;i++){
			printf("%d \n", star[sortedX[i]]);
		}*/
		
		clock_t before = clock(), after;
		double elapsed_secs ;
		Result rb;
		if(debug){
			rb = findMinBruteforce(0, N);
			after = clock();
			elapsed_secs = double(after - before) / CLOCKS_PER_SEC;
			printf("%.4f:%d(%d,%d) %d(%d,%d) ", rb.distance, rb.idxSmall, star[rb.idxSmall].x, star[rb.idxSmall].y, rb.idxBig, star[rb.idxBig].x, star[rb.idxBig].y);
			printf("\t took %.2f \n", elapsed_secs);
		}		
		
		before = clock();
		Result r = findMin(0, N);
		after = clock();
		elapsed_secs = double(after - before) / CLOCKS_PER_SEC;
		printf("\t took %.4f \n", elapsed_secs);
		if (debug){
			printf("%.3f:%d(%d,%d) %d(%d,%d) ", r.distance, r.idxSmall, star[r.idxSmall].x, star[r.idxSmall].y, r.idxBig, star[r.idxBig].x, star[r.idxBig].y);
			printf("\t took %.4f \n", elapsed_secs);
			if(r.idxSmall!=rb.idxSmall || r.idxBig!=rb.idxBig){
				printf("!!!\n");
			}
		}

		// Print the answer to standard output(screen).
		//printf("%d : %d %d\n", test_case, first, second);
		printf("%d %d\n", r.idxSmall+1, r.idxBig+1);
		//break;

	}
	return 0;//Your program should return 0 on normal termination.
}
