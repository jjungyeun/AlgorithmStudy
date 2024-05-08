import java.util.Scanner;

public class P1541 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.next();

        int ans = 0;
        String[] splitByMinus = expression.split("-");
        boolean isFirst = true;
        for (String splitStr : splitByMinus) {
            int groupSum = 0;
            for (String numStr : splitStr.split("[+]")) {
                groupSum += Integer.parseInt(numStr);
            }
            if (isFirst) {
                ans += groupSum;
                isFirst = false;
            }
            else {
                ans -= groupSum;
            }
        }
        System.out.println(ans);
    }
}
