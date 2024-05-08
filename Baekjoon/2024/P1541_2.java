import java.util.Scanner;

class P1541_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        int answer = 0;
        boolean isFirst = true;

        for (String exp : inputStr.split("-")) {
            int subSum = 0;
            for (String numStr : exp.split("\\+")) {
                subSum += Integer.parseInt(numStr);
            }
            if (isFirst) {
                answer += subSum;
                isFirst = false;
            } else {
                answer -= subSum;
            }
        }
        System.out.println(answer);
    }
}