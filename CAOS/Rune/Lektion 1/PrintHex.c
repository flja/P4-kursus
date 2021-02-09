#include <stdio.h>
#define ARRAY_SIZE 20
int print_array(long int _nums[]);

// Print the value pointed by x by dereferencing x

int main(void)
{
    long int numbers[ARRAY_SIZE] = {0};
    print_array(numbers);

    long int *p = &numbers[3];
    
    long y=*(p+9);      // 9 = 16, 100 = 0, 10000 = 2

    printf("Value: %x",y);  

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
