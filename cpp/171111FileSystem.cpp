//
//  171111FileSystem.cpp
//  codejam
//
//  Created by Darren ha on 11/11/17.
//  Copyright © 2017 Darren ha. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>

char disk[65535*512];

char filename[100][32];
char filedata[100][65535];
char filesize[100];

void main(){
    
    for (int i=0; i<100; i++) {
        int len = 16+rand()%16;
        for(int j=0;j<len;j++){
            filename[i][j] = 'A' + rand()%32;
        }
        filename[i][len]=0;
    }
    
    for (int i=0; i<25000; i++) {
        int mode = i<5000? 10: rand()%10;
        int file = rand()%10;
        int pos = rand()%65535;
        int size = rand()%(65535-pos);
        
        char filename[32];
        char data[65535];
        char _data[65535];
        
        if(mode<9){
            file_read(file, pos, data, size);
        }else{
            file_write();
        }
        
        for (int j=0; j<20; j++) {
            char err = rand()%256;
            int pos = rand()%(65536*512);
            disk[pos]=err;
        }
        
    }
}