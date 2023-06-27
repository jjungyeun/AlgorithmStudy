import java.io.*;
import java.util.PriorityQueue;

public class P11286 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<AbsVal> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            if (num != 0) {
                pq.add(new AbsVal(num));
            } else if (!pq.isEmpty()) {
                bw.write(Long.toString(pq.poll().val));
                bw.newLine();
            } else {
                bw.write("0");
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    private static class AbsVal implements Comparable<AbsVal> {

        long val;

        public AbsVal(long val) {
            this.val = val;
        }

        @Override
        public int compareTo(AbsVal other) {
            if (Math.abs(this.val) < Math.abs(other.val)) return -1;
            else if (Math.abs(this.val) > Math.abs(other.val)) return 1;
            else return this.val < other.val? -1 : 1;
        }
    }
}
