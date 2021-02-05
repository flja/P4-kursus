#include <stdio.h>
#define ARRAY_SIZE 20
int print_array(long int _nums[]);
int swap(long int *pointerOne, long int *pointerTwo);

// Write a function swap (taking to long pointers as argument) that swaps the appointed values. Apply it to swap the array elements with indexes 0 and 5.  print the Array

int main(void)
{
    long int numbers[ARRAY_SIZE] = {0};
    print_array(numbers);

    int i = 0;

    long int *_pointerOne = &numbers[0];
    long int *_pointerTwo = &numbers[5]; 

    swap(_pointerOne, _pointerTwo);

    for( i = 0; i <= 19; i++ )
    {       
        printf("Array after pointer swap: %d\n", numbers[i]);
    }

    return 0;
}

int print_array(long int _nums[])
{
    int i = 0;
    
    for( i = 0; i <= 19; i++ )
    {
        _nums[i] = i + 10; 
    }
}

int swap(long int *pointerOne, long int *pointerTwo)
{
    long int temp;

    temp = *pointerOne;

    *pointerOne = *pointerTwo;

    *pointerTwo = temp;
}