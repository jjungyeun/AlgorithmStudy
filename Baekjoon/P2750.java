import java.io.*;

public class P2750 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Sort sorter = new BubbleSort();
        sorter.sort(arr);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int num : arr) {
            bw.write(num+"\n");
        }
        bw.flush();
        bw.close();
    }

    interface Sort {
        void sort(int[] arr);
    }


    static class BubbleSort implements Sort{
        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                boolean isSwapped = sortPart(arr, 0, arr.length - i - 1);
                if (!isSwapped) break;
            }
        }

        private boolean sortPart(int[] arr, int start, int end) {
            boolean isSwapped = false;
            if (start <= end) {
                for (int idx = start; idx < end; idx++) {
                    if (arr[idx] > arr[idx+1]) {
                        int tmp = arr[idx];
                        arr[idx] = arr[idx+1];
                        arr[idx+1] = tmp;
                        isSwapped = true;
                    }
                }
            }
            return isSwapped;
        }
    }
}
