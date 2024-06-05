import java.util.*;

class P240603 {

    private boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                answer++;
                bfs(i, computers);
            }
        }

        return answer;
    }

    private void bfs(int start, int[][] adjs) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int here = q.poll();

            for(int next=0; next<adjs.length; next++) {
                if (adjs[here][next]==1 && !visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }
}