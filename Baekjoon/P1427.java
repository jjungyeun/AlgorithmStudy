import java.io.IOException;
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
        SelectionSort sorter = new SelectionSort();
        sorter.sortDesc(arr);

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i]);
        }
    }

    private static class SelectionSort {

        public void sortDesc(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int maxVal = arr[i];
                int maxIdx = i;

                for (int j = i+1; j < arr.length; j++) {
                    if (arr[j] > maxVal) {
                        maxVal = arr[j];
                        maxIdx = j;
                    }
                }

                if (maxIdx != i) {
                    arr[maxIdx] = arr[i];
                    arr[i] = maxVal;
                }
            }
        }
    }

}
