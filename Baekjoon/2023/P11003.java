import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class P11003 {
    public static void main(String[] args) throws IOException {
        // N, L 입력 및 출력 버퍼 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Deque<Pair> deque = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long newValue = Long.parseLong(st.nextToken());

            while (!deque.isEmpty() && deque.getLast().value > newValue) {
                deque.removeLast();
            }
            deque.addLast(new Pair(newValue, i));

            while (!deque.isEmpty() && deque.getFirst().idx < i - L + 1) {
                deque.removeFirst();
            }

            bw.write(deque.getFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }

    private static class Pair {
        long value;
        int idx;

        public Pair(long value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }
}

//class P11003 {
//    public static void main(String[] args) throws IOException {
//        // N, L 입력 및 출력 버퍼 세팅
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st1 = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st1.nextToken());
//        int L = Integer.parseInt(st1.nextToken());
//
//        // 첫번째값 입력
//        StringTokenizer st2 = new StringTokenizer(br.readLine());
//        int s, minIdx = 0;
//        long min = Long.parseLong(st2.nextToken());
//        bw.write(Long.toString(min));
//
//        // 우선순위큐에 하나씩 넣으면서 최솟값 구하기
//        PriorityQueue<Pair> pq = new PriorityQueue<>();
//        for (int e = 1; e < N; e++) {
//            s = Math.max(0, e-L+1);
//            long newValue = Long.parseLong(st2.nextToken());
//            if (newValue < min) {
//                min = newValue; minIdx = e;
//            } else {
//                pq.add(new Pair(newValue, e));
//                if (minIdx < s) {
//                    while (!pq.isEmpty() && minIdx < s) {
//                        Pair pair = pq.poll();
//                        min = pair.value;
//                        minIdx = pair.idx;
//                    }
//                }
//            }
//            bw.write(" " + min);
//        }
//
//        bw.flush();
//        bw.close();
//    }
//
//    private static class Pair implements Comparable<Pair>{
//        long value;
//        int idx;
//
//        public Pair(long value, int idx) {
//            this.value = value;
//            this.idx = idx;
//        }
//
//        @Override
//        public int compareTo(Pair pair) {
//            if (this.value < pair.value) return -1;
//            else if (this.value == pair.value) {
//                if (this.idx < pair.idx) return -1;
//                else if (this.idx == pair.idx) return 0;
//            }
//            return 1;
//        }
//    }
//}