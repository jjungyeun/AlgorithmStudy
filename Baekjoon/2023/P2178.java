import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] maze = new int[N][M];
        for (int r = 0; r < N; r++) {
            String rowStr = br.readLine();
            char[] rowChars = rowStr.toCharArray();
            for (int c = 0; c < M; c++) {
                maze[r][c] = Integer.parseInt(String.valueOf(rowChars[c]));
            }
        }

        List<List<Integer>> drdcs = List.of(
                List.of(0, 1),
                List.of(0, -1),
                List.of(1, 0),
                List.of(-1, 0)
        );

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0, 1));

        while (!queue.isEmpty()) {
            Point here = queue.poll();

            if (here.r == N-1 && here.c == M-1) {
                System.out.println(here.dist);
            }

            for (List<Integer> drdc : drdcs) {
                int dr = drdc.get(0);
                int dc = drdc.get(1);

                int nextR = here.r + dr;
                int nextC = here.c + dc;

                if (isInMaze(nextR, nextC, N, M) && maze[nextR][nextC] == 1) {
                    maze[nextR][nextC] = 0;
                    queue.add(new Point(nextR, nextC, here.dist + 1));
                }
            }
        }
    }

    private static boolean isInMaze(int r, int c, int N, int M) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    private static class Point {
        int r;
        int c;
        int dist;

        public Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
