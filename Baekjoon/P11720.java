import java.util.Scanner;

class P11720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String numberString = sc.next();
        Solution solution = new Solution();
        solution.solve(num, numberString);
    }

    private static class Solution {
        public void solve(int N, String numArr) {
            long answer = 0;
            char[] charArr = numArr.toCharArray();
            for (char c: charArr) {
                answer += c - '0';
            }
            System.out.println(answer);
        }
    }
}