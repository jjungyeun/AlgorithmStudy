import java.util.LinkedList;
import java.util.Queue;

public class P240111 {
    private static final int[] dr = new int[]{0,1,-1,0}, dc = new int[]{1,0,0,-1};

    public static void main(String[] args) {
        int[][] board = new int[][] {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 1}, {1, 1, 1, 0, 0}};
        System.out.println(solution(board)); // 답: 3000
    }

    // 최단거리X, 최소비용O
    // 이동한 방향에 따라 중간 비용이 더 커도 최종 비용은 작아질 수 있기 때문에, 방향마다 방문 및 최소비용을 저장한다.
    // 또한 방문한 지점(좌표 및 방향 일치)도 최소비용을 갱신하는 경우 재방문한다.
    public static int solution(int[][] board) {
        int N = board.length;
        int[][][] cost = new int[N][N][4];
        int answer = Integer.MAX_VALUE;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,0,-1}); // 행, 열, 현재까지 비용, 방향(0,1,2,3)
        cost[0][0][0] = 1; cost[0][0][1] = 1;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int nowR = current[0], nowC = current[1];
            int nowCost = current[2], nowDirection = current[3];

            if (nowR == N-1 && nowC == N-1) {
                answer = Integer.min(answer, nowCost);
            }

            for (int direction = 0; direction < 4; direction++) {
                // 다음 좌표 구하기
                int nextR = nowR + dr[direction];
                int nextC = nowC + dc[direction];
                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
                    continue;
                }

                // 벽인 경우 패스
                if (board[nextR][nextC] == 1) {
                    continue;
                }

                // 다음 비용 구하기
                int nextCost = nowCost + 100;   // 직선 도로 추가
                if (hasCorner(nowDirection, direction)) {
                    nextCost += 500; // 방향이 바뀌면 코너 추가
                }

                // 이미 방문한 지점인 경우, 최소 비용을 갱신하지 못하면 패스
                if (cost[nextR][nextC][direction] > 0 && cost[nextR][nextC][direction] <= nextCost) {
                    continue;
                }

                // 방문 처리
                queue.add(new int[]{nextR, nextC, nextCost, direction});
                cost[nextR][nextC][direction] = nextCost;
            }
        }

        return answer;
    }

    private static boolean hasCorner(int nowDirection, int direction) {
        return nowDirection >= 0 && nowDirection != direction;
    }

}
