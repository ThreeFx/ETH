#include "stdio.h"
#include "pthread.h"

volatile int x, y;

void* t1(void* param) {
    int r1;
    y = 1;
    asm ("mfence");
    r1 = x;
    printf("%d", r1);
    fflush(stdout);
}

void* t2(void* param) {
    int r2;
    x = 1;
    asm ("mfence");
    r2 = y;
    printf("%d", r2);
    fflush(stdout);
}

int main() {
    pthread_t thread1, thread2;

    x = 0;
    y = 0;

    pthread_create(&thread1, NULL, t1, NULL);
    pthread_create(&thread2, NULL, t2, NULL);

    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);

    printf("\n");
    fflush(stdout);

    return 0;
}

