#include <stdio.h>
#define ARRAY_SIZE 20
int print_array(long int _nums[]);

// Make a pointer x to the 4th element (index 3), and print out its address (use %p).

int main(void)
{
    long int numbers[ARRAY_SIZE] = {0};
    print_array(numbers);

    long int *p = &numbers[3];


    // Same result
    printf("Adresse to index 3 is: %p", &numbers[3]);
    
    printf("Adresse to index 3 is: %p", p);

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