#include <stdio.h>

int main()
{

    //Suppose that a and b have byte values 0x55 and 0x46, respectively. Fill in the
    //following table indicating the byte values of the different C expressions:

    int a = 0x55;
    int b = 0x46;

    printf("a & b: %x \n", a & b);      //44

    printf("a | b: %x \n", a | b);      //57

    printf("~a | ~b: %x \n", ~a | ~b);  //bb

    printf("a & !b): %x \n", a & !b);   //00

    printf("a && b: %x \n", a && b);    //01

    printf("a || b: %x \n", a || b);    //01

    printf("!a || !b: %x \n", !a || !b);//00

    printf("a && ~b: %x \n", a && ~b);  //01

     //This problem highlights the relation between bit-level Boolean operations and
    //logical operations in C. A common programming error is to use a bit-level operation
    // when a logical one is intended, or vice versa.
}