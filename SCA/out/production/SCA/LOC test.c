/* This this my first c program
   Allah is almighty */

#include <stdio.h>

void my_function1(int a, int b);
int my_function2(char c, char d);

struct node{
    int aa = 100;
    float bb = 5.68;
    string str1 = "hehe";
};

int main() {
    aa = 8;
    char c; // declaring variable
    int lowercase_vowel, uppercase_vowel;
    printf("Enter an alphabet: ");
    scanf("%c", &c); // taking input

    // evaluates to 1 if variable c is a lowercase vowel
    lowercase_vowel = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');

    // evaluates to 1 if variable c is a uppercase vowel
    uppercase_vowel = (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');

    // evaluates to 1 (true) if c is a vowel
    if (lowercase_vowel || uppercase_vowel)
        printf("%c is a vowel.", c);
    else
        printf("%c is a consonant.", c);
        
    return 0;
}

int my_function1(int x, int y){
    int a;
}