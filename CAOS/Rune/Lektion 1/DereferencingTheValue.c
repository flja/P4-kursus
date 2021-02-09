#include <stdio.h>
#define ARRAY_SIZE 20
int print_array(long int _nums[]);

// Print the value pointed by x by dereferencing x

int main(void)
{
    long int numbers[ARRAY_SIZE] = {0};
    print_array(numbers);

    long int *p = &numbers[3];
    
    printf("Dereferencing adresse to index 3 is: %d", *p);

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