#include <stdio.h>

int main()
{
    // Opgave 1

    int x = 0x87654321;
    x = x & 0xFF;   // Husk at udkommentere 

    // Opgave 2

    x = x ^~ 0xFF;  // Husk at udkommentere 

    // Opgave 3

    x |= 0xFF;

    printf("x: %02x\n", x);
}
