#include <stdio.h>
#include <stdlib.h>

/* 2.30 Determine wether arguments can be added without overflow */ 

// Does not work as intended since the overflow values changes before run time

int tadd_ok(int x, int y);

int main(void) 
{
    int x = 2;
    int y = -45;
    printf(" %d ", tadd_ok(x, y));

    return 0;
}

int tadd_ok(int x, int y) 
{

     int sum = x + y; 

     // printf("%d", sum);
     // printf("%d",x);
     // printf("%d",y);

     if(x > 0 && y > 0 && sum < 0) 
     {
         return 0;
     } 
     if(x < 0 && y < 0 && sum > 0) 
     {
         return 0; 
     }
     else 
     {
         return 1; 
     }
} 

/* Løsning fra bog

int tadd_ok(int x, int y) {
int sum = x+y;
int neg_over = x < 0 && y < 0 && sum >= 0;
int pos_over = x >= 0 && y >= 0 && sum < 0;
return !neg_over && !pos_over;
}

*/
