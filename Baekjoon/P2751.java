import java.io.*;

public class P2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        MergeSort mergeSort = new MergeSort(N);
        for (int i = 0; i < N; i++) {
            mergeSort.setValue(i, Integer.parseInt(br.readLine()));
        }
        mergeSort.sort(0, N-1);
        mergeSort.printAll();
    }

    private static class MergeSort {
        private final int[] arr;
        private final int[] tmp;    // 매번 새 배열을 만들어 복사하면 메모리 낭비 크고 시간도 오래 걸림

        public MergeSort(int N) {
            this.arr = new int[N];
            this.tmp = new int[N];
        }

        public void setValue(int index, int value) {
            arr[index] = value;
        }

        public void printAll() throws IOException {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int num : arr) {
                bw.write(num + "\n");
            }
            bw.flush();
            bw.close();
        }

        public void sort(int start, int end) {
            if (start == end) return;

            int mid = (start + end) / 2;
            sort(start, mid);
            sort(mid+1, end);

            // merge
            int left = start, right = mid+1;
            int current = start;

            for (int i = start; i <= end; i++) {
                tmp[i] = arr[i];
            }

            while (left <= mid && right <= end) {
                if (tmp[left] <= tmp[right]) {
                    arr[current++] = tmp[left++];
                } else {
                    arr[current++] = tmp[right++];
                }
            }

            while (left <= mid) {
                arr[current++] = tmp[left++];
            }
            while (right <= end) {
                arr[current++] = tmp[right++];
            }
        }
    }
}
