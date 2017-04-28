#include <stdio.h>
#include <string.h>

#define bit_RDRND   (1 << 30)

int main(int argc, char **argv)
{

    unsigned int eax;
    unsigned int ebx;
    unsigned int ecx;
    unsigned int edx;

    eax = 0x01;

    __asm__ __volatile__(
                         "cpuid;"
                         : "=a"(eax), "=b"(ebx), "=c"(ecx), "=d"(edx)
                         : "a"(eax)
                         );

    printf("The value of the ecx register is %08x.\n", ecx);

    if(ecx & bit_RDRND){
        //use rdrand
        printf("use rdrand\n");
    }
    else{
        //use mt19937
        printf("use mt19937");
    }

    return 0;
}
