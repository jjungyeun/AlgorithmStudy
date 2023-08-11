import java.io.IOException;
import java.util.Scanner;

public class P1456 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        // false면 소수. true면 소수 아님.
        int MAX = 10000000;
        boolean[] isNotPrime = new boolean[MAX+1];
        isNotPrime[1] = true;
        for (int i = 2; i <= MAX; i++) {
            if (!isNotPrime[i]) {
                if (i >= N && isPalindrome(i)) {
                    System.out.println(i);
                    break;
                }
                for (int j = 2; i * j <= MAX ; j++) {
                    isNotPrime[i * j] = true;
                }
            }
        }
    }

    private static boolean isPalindrome(int num) {
        String numStr = String.valueOf(num);
        int len = numStr.length();
        for (int i = 0; i < len / 2; i++) {
            if (numStr.charAt(i) != numStr.charAt(len-i-1)){
                return false;
            }
        }
        return true;
    }
}
