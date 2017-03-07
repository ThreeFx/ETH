import java.util.*;

class Main {
    static Scanner s;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        int cases = s.nextInt();
        for (int c = 0; c < cases; c++) {
            solveCase();
        }
    }

    public static void solveCase() {
        int n = s.nextInt();
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += s.nextInt();
        }

        System.out.println(sum);
    }
}
