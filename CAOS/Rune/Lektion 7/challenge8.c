#include <stdio.h>
#include <stdlib.h>
#include <x86intrin.h>

void average_array(long *arr, int size, long* res) {
    int i;

    for (i = 0; i <size; i++) {
        *res = *res + arr[i];
    }
    *res = *res / size;
}

//#define SIZE 1024*1024*32
#define SIZE 1024
long the_array[SIZE];

void init_array(long *arr, int size) {
    int i;
    for (i = 0; i < size; i++)
        arr[i] = rand() % 1024;
}


int main() {
    init_array(the_array, SIZE);
    long avg = 0;

    unsigned long t1,t2;
    
    t1 = __rdtsc();
    average_array(the_array, SIZE, &avg);
    t2 = __rdtsc();
    printf("Time %lu cycles \t Average: %ld\n", t2-t1, (long) avg);
  
    return 0;
}