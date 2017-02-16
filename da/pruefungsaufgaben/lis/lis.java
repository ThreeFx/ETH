import java.util.*;

class LIS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] smallestForLength = new int[n+1];
        int[] previousIndex = new int[n];

        int longestSoFar = 0;
        for (int i = 0; i < n; i++) {
            int current = a[i];

            int low = 1; int high = longestSoFar;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (a[smallestForLength[mid]] < current) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            int currentLength = low;

            previousIndex[i] = smallestForLength[currentLength - 1];
            smallestForLength[currentLength] = i;

            if (currentLength > longestSoFar) {
                longestSoFar = currentLength;
            }
        }

        int[] s = new int[longestSoFar];
        int curIndex = smallestForLength[longestSoFar];
        for (int i = 0; i < longestSoFar; i++) {
            s[longestSoFar - i - 1] = a[curIndex];
            curIndex = previousIndex[curIndex];
        }

        System.out.println("Input: " + Arrays.toString(a));
        System.out.println("LIS:   " + Arrays.toString(s));
    }
}
