import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class P1427 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String numStr = sc.next();
        long inputNum = Long.parseLong(numStr);

        int N = numStr.length();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[N-i-1] = (int) (inputNum % 10);
            inputNum /= 10;
        }
        Arrays.sort(arr);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = N-1; i >= 0; i--) {
            bw.write(String.valueOf(arr[i]));
        }
        bw.flush();
        bw.close();
    }
}
