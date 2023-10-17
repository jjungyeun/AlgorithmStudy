import java.io.*;
import java.util.*;

public class P1325 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList.get(A).add(B);
        }

        // BFS로 한 컴퓨터에서 도달할 수 있는 컴퓨터의 개수를 구함
        int[] trustCnt = new int[N+1];
        for (int S = 1; S <= N; S++) {
            checkTrustComs(N, adjList, S, trustCnt);
        }
        int maxTrustCnt = Arrays.stream(trustCnt).max().getAsInt();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= N; i++) {
            if (trustCnt[i] == maxTrustCnt) bw.write(i + " ");
        }
        bw.flush();
        bw.close();
    }

    private static void checkTrustComs(int N, List<List<Integer>> adjList, int S, int[] trustCnt) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N +1];
        queue.add(S);
        visited[S] = true;

        while(!queue.isEmpty()) {
            int hereCom = queue.poll();
            trustCnt[hereCom]++;

            for (Integer nextCom : adjList.get(hereCom)) {
                if (!visited[nextCom]) {
                    visited[nextCom] = true;
                    queue.add(nextCom);
                }
            }
        }
    }
}
