#include <stdio.h>
#define ARRAY_SIZE 20
int print_array(long int _nums[]);

//Write a function printArray with parameter type long array, 
//that prints the resulting value as a long integer value and a long hexidecimal value respectively 
//(using %lu respectively %lx as placeholders in printf, 
//and use it to print a sample array "a" containing 20 
//elements each initialized with the value from 10+i (where i is the index). Ie 10,11,...,29

int main(void)
{
    long int numbers[ARRAY_SIZE] = {0};
    print_array(numbers);
    return 0;
}

int print_array(long int _nums[])
{
    int i = 0;
    
    for( i = 0; i <= 19; i++ )
    {
        _nums[i] = 10 + i; 
        printf("Long interger value: %lu\n" , _nums[i]);
        printf("Long hex value: %lx \n", _nums[i]);
        printf("---------------------------\n");
    }
}