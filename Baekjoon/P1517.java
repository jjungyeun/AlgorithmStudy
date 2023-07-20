import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1517 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        MergeSort mergeSort = new MergeSort(N);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            mergeSort.setValue(i, Integer.parseInt(st.nextToken()));
        }
        mergeSort.sort(0, N-1);
        mergeSort.printSwap();
    }

    private static class MergeSort {
        private final int[] arr;
        private final int[] tmp;    // 매번 새 배열을 만들어 복사하면 메모리 낭비 크고 시간도 오래 걸림
        private long swap = 0;

        public MergeSort(int N) {
            this.arr = new int[N];
            this.tmp = new int[N];
        }

        public void setValue(int index, int value) {
            arr[index] = value;
        }

        public void printSwap() {
            System.out.println(swap);
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
                    swap += (right - current);
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
