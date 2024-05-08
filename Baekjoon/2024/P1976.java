import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1976 {

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 상위 노드 표시 배열 초기화
        parents = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        // 인접 행렬 입력 받기 & 연결된 도시 합집합 연산 수행
        int[][] adjs = new int[N+1][N+1];
        StringTokenizer st;
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                adjs[r][c] = Integer.parseInt(st.nextToken());
                if (r < c && adjs[r][c] == 1) {
                    union(r, c);
                }
            }
        }

        // 여행할 도시들이 모두 연결되어 있는지 확인
        st = new StringTokenizer(br.readLine());
        int topCity = -1;
        boolean canTravel = true;
        for (int i = 0; i < M; i++) {
            int city = Integer.parseInt(st.nextToken());
            int currentTop = find(city);

            if (topCity == -1) {
                topCity = currentTop;
            }

            if (currentTop != topCity) {
                canTravel = false;
                break;
            }
        }

        if (canTravel) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    // 합집합 - 두 노드의 대표 노드를 연결
    private static void union(int a, int b) {
        int topA = find(a);
        int topB = find(b);
        if (topA != topB){
            parents[topB] = topA;
        }
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
