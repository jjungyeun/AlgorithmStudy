import java.util.Scanner;

class P2018 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long cnt = 0, s = 1, e = 1;
        long sum = 1;

        while (e <= N) {
            if (sum == N) cnt ++;
            if (sum >= N) {
                sum -= s;
                s++;
            } else {
                e++;
                sum += e;
            }
        }

        System.out.println(cnt);
    }
}