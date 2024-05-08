import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P2023 {

    private static final List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        dfs(0, N);
        for (int num : ans) {
            System.out.println(num);
        }
    }

    private static void dfs(int num, int rest) {
        if (rest == 0) {
            ans.add(num);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            int next = num * 10 + i;
            if (isPrimeNum(next)) {
                dfs(next, rest-1);
            }
        }
    }

    private static boolean isPrimeNum(int num) {
        if (num <= 0 || num == 1) return false;
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
