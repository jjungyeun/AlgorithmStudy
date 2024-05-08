import java.util.Scanner;

public class P1016 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long min = scanner.nextLong();
        long max = scanner.nextLong();

        boolean[] isSquareNum = new boolean[(int) (max-min) + 1];
        for (int num = 2; (long) num * num <= max; num++) {
            long squareNum = (long) num * num;
            for (long i = min/squareNum; i <= max/squareNum ; i++) {
                if (i * squareNum < min) continue;
                isSquareNum[(int) ((i * squareNum) - min)] = true;
            }
        }

        int cnt = 0;
        for (boolean b : isSquareNum) {
            if (!b) cnt++;
        }
        System.out.println(cnt);
    }

}
