#include<stdio.h>

int global = 5;

int main(){

   for(int i=0; i<10; i++)
   {
        if(i%2 == 0)
            printf("HI");
   }

   if(global < 10){
        printf("yes");
   }
   else{
        printf("no");
   }

   return 0;
}
