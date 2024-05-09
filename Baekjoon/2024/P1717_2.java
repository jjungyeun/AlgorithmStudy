import java.io.*;
import java.util.*;

class P1717_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] groups = new int[N+1];
        for (int i = 0; i <= N; i++) {
            groups[i] = i;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int t = 0; t < M; t++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) {
                union(groups, a, b);
            } else if (command == 1) {
                if (find(groups, a) == find(groups, b)) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }

    private static void union(int[] groups, int a, int b) {
        int topA = find(groups, a);
        int topB = find(groups, b);
        groups[topB] = topA;
    }

    private static int find(int[] groups, int a) {
        if (groups[a] != a) {
            groups[a] = find(groups, groups[a]);
        }
        return groups[a];
    }
}