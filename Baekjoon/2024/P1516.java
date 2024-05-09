import java.io.*;
import java.util.*;

class P1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 위상 정렬에 필요한 자료구조 세팅
        int[] degrees = new int[N+1];
        int[] weights = new int[N+1];
        ArrayList<Integer>[] adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 간선을 입력받으며 간선 연결 리스트 저장 및 정점 진입차수 증가
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int prev = Integer.parseInt(st.nextToken());
                if (prev == -1) break;
                adjList[prev].add(i);
                degrees[i]++;
            }
        }

        // 위상 정렬용 큐 초기화 및 차수가 0인 정점 삽입
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (degrees[i] == 0) {
                q.add(i);
            }
        }

        // 위상 정렬을 하면서 각 노드의 결과 업데이트
        int[] answers = new int[N+1];
        while (!q.isEmpty()) {
            int here = q.poll();
            for (int next : adjList[here]) {
                // 연결된 정점의 시간 업데이트
                // -> 지금까지의 시간, 현재 노드의 시간 + 현재 노드의 건설 시간 중 큰 값으로 변경
                // 큰 값으로 하는 이유: 적어도 이 시간만큼은 무조건 걸리기 때문
                answers[next] = Integer.max(answers[next], answers[here] + weights[here]);
                degrees[next]--;
                if (degrees[next] == 0) {
                    q.add(next);
                }
            }
        }

        // 각 건물의 건설 시간을 추가하여 답으로 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= N; i++) {
            bw.write((answers[i] + weights[i]) + "\n");
        }
        bw.flush();
        bw.close();
    }
}