import java.io.*;
import java.util.StringTokenizer;

public class P1717 {

    private static int[] parents;
    private static final int UNION = 0, SAME = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 상위 노드 표시 배열 초기화
        parents = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 연산 수행
            if(op == UNION) {
                union(a, b);
            } else if (op == SAME) {
                if (find(a) == find(b)) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }

        bw.flush();
        bw.close();
    }

    // 합집합 - 두 노드의 대표 노드를 연결
    private static void union(int a, int b) {
        int topA = find(a);
        int topB = find(b);
        parents[topB] = topA;
    }

    // 대표 노드 찾기 및 배열 업데이트
    private static int find(int node) {
        if(parents[node] == node) {
            return node;
        } else {
            return parents[node] = find(parents[node]);
        }
    }
}
