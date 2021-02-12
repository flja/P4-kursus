#include <stdio.h>
#include <stdlib.h>
void exercise2_1();
void quicksort();

int main()
{
    exercise2_1();
    return 0;
}


/*
 *  1. To understand the value of records in a programming language, write 	a small
 *  program in a C - based language that uses an array of structs that store student
 *  information, including name, age, GPA as a float, and grade level as a string(e.g., “freshmen, ” etc.).Also, write the same program in the same language
 *  without using structs. 
 */
 struct Student
 {
    char name[50];
    int age;
    float gpa;
    char gradeLevel[50];
 } student;

 void exercise2_1_struct()
 {
     struct Student students[1];
     strcpy(students[0].name, "Rune");
     students[0].age = 29;
     students[0].gpa = 4.0f;
     strcpy(students[0].gradeLevel, "sophomore");
    
    printf("Name: %s\nAge: %i\nGPA: %f\nGrade level: %s\n", students[0].name, students[0].age, students[0].gpa, students[0].gradeLevel);
 }

 void exercise2_1_array()
 {
     char name[1][50];
     int age[1];
     float gpa[1];
     char gradeLevel[1][50];
 }

 /* 2. To understand the value of recursion in a programming language, write a program
     that implements quicksort, first using recursion and then without recursion.*/

 void quicksort(int number[25], int first, int last) 
 {
     int i, j, pivot, temp;
     if (first < last) {
         pivot = first;
         i = first;
         j = last;
         while (i < j) {
             while (number[i] <= number[pivot] && i < last)
                 i++;
             while (number[j] > number[pivot])
                 j--;
             if (i < j) {
                 temp = number[i];
                 number[i] = number[j];
                 number[j] = temp;
             }
         }
         temp = number[pivot];
         number[pivot] = number[j];
         number[j] = temp;
         quicksort(number, first, j - 1);
         quicksort(number, j + 1, last);
     }
 }