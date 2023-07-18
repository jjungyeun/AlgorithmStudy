import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        QuickSort quickSort = new QuickSort(N, K-1);
        for (int i = 0; i < N; i++) {
            quickSort.setKth(i, Long.parseLong(st.nextToken()));
        }
        quickSort.sort();
        System.out.println(quickSort.getGoal());
    }

    // 퀵소트 직접 구현
    private static class QuickSort {
        private final long[] nums;
        private final int goalIdx;

        public QuickSort(int N, int goalIdx) {
            this.nums = new long[N];
            this.goalIdx = goalIdx;
        }

        public void setKth(int K, long value) {
            this.nums[K] = value;
        }

        public long getGoal() {
            return nums[goalIdx];
        }

        public void sort() {
            innerSort(0, nums.length-1);
        }

        private void innerSort(int start, int end) {
            if (start >= end)
                return;
            // 매 재귀마다 pivot을 기준으로 좌우를 나누기 위해 pivot의 위치를 구함
            int pivot = getPivot(start, end);
            // pivot은 이미 정렬된 위치인데. 정답을 위한 값이 pivot인 경우 바로 종료.
            if (pivot == goalIdx)
                return;
            // pivot 기준 좌우를 각각 재귀로 정렬
            innerSort(start, pivot-1);
            innerSort(pivot+1, end);
        }

        private int getPivot(int start, int end) {
            // 데이터가 대부분 정렬되어 있는 경우(또는 반대로 대부분 정렬되어 있는 경우),
            // pivot을 맨 앞이나 맨 뒤로 설정할 경우 불필요한 연산이 많아지므로 mid를 pivot으로 설정
            // 그리고 left, right 이동을 편하게 하기 위해 pivot을 맨 뒤로 이동
            int mid = (start + end) / 2;
            long pivot = nums[mid];
            nums[mid] = nums[end];
            nums[end] = pivot;

            int left = start, right = end-1;
            while(left < right) {
                // left가 pivot보다 커질때까지 이동
                while(left < right && nums[left] < pivot) {
                    left++;
                }
                // right가 pivot보다 작아질때까지 이동
                while(right > start && nums[right] > pivot) {
                    right--;
                }

                // left와 right가 만나지 않은 경우 swap && 둘다 이동
                if (left < right) {
                    long tmp = nums[right];
                    nums[right] = nums[left];
                    nums[left] = tmp;
                    left++; right--;
                } else { // left와 right가 만난 경우 이동 종료
                    break;
                }
            }

            // left와 right가 만난 위치의 값을 기준으로 pivot으로 분리
            if (nums[left] < pivot) {
                shift(end, left+1);
                return left+1;
            } else {
                shift(end, left);
                return left;
            }
        }

        private void shift(int start, int dest) {
            long tmp = nums[start];
            for (int idx = start; idx > dest; idx--) {
                nums[idx] = nums[idx-1];
            }
            nums[dest] = tmp;
        }
    }

}
