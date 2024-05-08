import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class P1744 {

    private static long sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        boolean hasZero = false;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                positives.add(num);
            } else if (num < 0) {
                negatives.add(num);
            } else {
                hasZero = true;
            }
        }

        getPositiveSum(positives);
        getNegativeSum(negatives, hasZero);
        System.out.println(sum);
    }

    private static void getPositiveSum(List<Integer> positives) {
        positives.sort(Comparator.reverseOrder());
        for (int i = 0; i < positives.size(); i+=2) {
            int num1 = positives.get(i);
            // 마지막 숫자면 그냥 더하기
            if (i == positives.size() - 1) {
                sum += num1;
                break;
            }
            // 두번째 수가 1이면 안 묶고 더하기
            int num2 = positives.get(i+1);
            if (num2 == 1) sum += (num1 + num2);
            else sum += ((long) num1 * num2);
        }
    }

    private static void getNegativeSum(List<Integer> negatives, boolean hasZero) {
        negatives.sort(Comparator.naturalOrder());
        for (int i = 0; i < negatives.size(); i+=2) {
            int num1 = negatives.get(i);
            // 마지막 숫자면 0이랑 묶거나 그냥 더하기
            if (i == negatives.size() - 1) {
                if (!hasZero) sum += num1;
                break;
            }
            int num2 = negatives.get(i+1);
            sum += ((long) num1 * num2);
        }
    }
}
