#pragma once

#include <chrono>


std::chrono::high_resolution_clock::time_point begin;

inline void h_startTimeMeasure(){
    begin = std::chrono::high_resolution_clock::now();
}

// return ms
inline void h_endTimeMeasure(){    
    auto end = std::chrono::high_resolution_clock::now();
    auto diff = std::chrono::duration_cast<std::chrono::milliseconds>(end-begin).count();    
    std::cout << "elapsed time: " << diff << "ms" << std::endl;
}

inline void h_generateRandomMap(int n){
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            int v = rand()%100 ;
            printf("%3d ", v);
        }
        printf("\n");
    }
}

inline void h_generateRow(int n, int low, int high){
    int range = high-low;
    for (int i = 0; i < n; i++){        
        int v = rand()%range + low ;
        printf("%d ", v);
    }
    printf("\n");
}

