#include <stdlib.h>
#include <time.h>
#include <stdio.h>
#include <stdint.h>

int main() {
    long runs = 100000000;
    int min = 0x7FFFFFFF;
    int max = 0;
    long sum = 0;
    int nums[40];
    for (int i = 0; i < 40; i++) {
        nums[i] = 0;
    }
    srand(time(NULL));

    for (int it = 0; it < runs; it++) {
        int x = 1;
        int idx = 0;
        int rand_v = rand();
        while ((rand_v & (1 << idx)) != 0) {
            x++;
            idx++;
            if (idx >= 30) {
                idx = 0;
                rand_v = rand();
            }
            //crypto_random_rdrand64_step(rand);
        }

        sum += x;
        if (x < min) {
            min = x;
        }
        if (x > max) {
            max = x;
        }
        nums[x]++;
    }

    printf("avg: %f\n", sum * 1.0 / runs);
    printf("max: %d\n", max);
    printf("min: %d\n", min);

    for (int i = 0; i < 40; i++) {
        printf("%d: %d\n", i, nums[i]);
    }
}

