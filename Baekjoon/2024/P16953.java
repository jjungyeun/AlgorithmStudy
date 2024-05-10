import java.util.*;

class P16953 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();

        boolean[] visited = new boolean[B+1];
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{A, 1});
        visited[A] = true;

        while (!queue.isEmpty()) {
            Integer[] here = queue.poll();
            int hereNum = here[0];
            int hereCnt = here[1];

            if (hereNum == B) {
                System.out.println(hereCnt);
                return;
            }

            for (int i = 0; i < 2; i++) {
                long next = operate(hereNum, i);
                if (next <= B) {
                    int nextNum = (int) next;
                    if (!visited[nextNum]) {
                        visited[nextNum] = true;
                        queue.add(new Integer[]{nextNum, hereCnt + 1});
                    }
                }
            }
        }
        System.out.println("-1");
    }

    private static long operate(int num, int cmd) {
        switch (cmd) {
            case 0: return num * 2L;
            case 1: return num * 10L + 1;
            default: return -1;
        }
    }
}