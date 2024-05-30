import java.util.*;

public class P240530 {

    public static void main(String[] args) {
        System.out.println(solution(
                new int[][]{{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}},
                new int[][]{{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}}
        )); // 답: 14
    }

    private static final int[][] drdc = {{0, 0, 1, -1}, {1, -1, 0, 0}};

    public static int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        int N = game_board.length;

        // 보드 빈칸들 구하기
        Map<Integer, List<Block>> blanks = new HashMap<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (game_board[r][c] == 0) {
                    Block blank = getBlock(game_board, r, c, 0);
                    blanks.putIfAbsent(blank.cnt, new ArrayList<>());
                    blanks.get(blank.cnt).add(blank);
                }
            }
        }

        // 블록 조각들 구하기
        List<Block> blocks = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (table[r][c] == 1) {
                    blocks.add(getBlock(table, r, c, 1));
                }
            }
        }

        // 각 블록 조각과 사이즈가 같은 빈칸을 맞춰보기
        for (Block block : blocks) {
            Block matched = null;
            if (blanks.containsKey(block.cnt)) {
                for (Block blank : blanks.get(block.cnt)) {
                    if (isSameBlock(game_board, table, blank, block)) {
                        matched = blank;
                        break;
                    }
                }
            }

            // 빈칸에 블록이 맞으면 정답 카운트 추가 및 빈칸 삭제
            if (matched != null) {
                answer += block.cnt;
                blanks.get(block.cnt).remove(matched);
            }
        }

        return answer;
    }

    // 보드에서 (r,c)칸을 포함하는 블록을 구하는 메서드 (BFS 이용)
    private static Block getBlock(int[][] board, int r, int c, int flag) {
        int cnt = 0;
        int tlR = r, tlC = c, brR = r, brC = c;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        board[r][c] = 2;

        while (!q.isEmpty()) {
            int[] here = q.poll();
            int hereR = here[0], hereC = here[1];

            // 블록의 칸 개수, 좌측상단, 우측하단 좌표도 함께 구함
            cnt++;
            tlR = Integer.min(tlR, hereR);
            tlC = Integer.min(tlC, hereC);
            brR = Integer.max(brR, hereR);
            brC = Integer.max(brC, hereC);

            // 현재 좌표에서 인접한, 블록에 포함되는 공간을 방문
            for (int d = 0; d < 4; d++) {
                int nextR = hereR + drdc[0][d];
                int nextC = hereC + drdc[1][d];

                if (nextR >= 0 && nextR < board.length
                        && nextC >= 0 && nextC < board.length
                        && board[nextR][nextC] == flag) {
                    q.add(new int[]{nextR, nextC});
                    board[nextR][nextC] = 2;
                }
            }
        }

        return new Block(r, c, tlR, tlC, brR, brC, cnt);
    }

    // 두 보드의 각 블록이 같은 모양을 가지고 있는지 확인하는 메서드
    private static boolean isSameBlock(int[][] boardA, int[][] boardB, Block a, Block b) {
        int aH = a.getHeight(), aW = a.getWidth();
        int bH = b.getHeight(), bW = b.getWidth();

        // 블록 크기에 따라 회전 방향을 결정
        // 블록 크기가 다르면 바로 false 반환
        int[] rotates;
        if (aH == bH && aW == bW) {
            if (aH == aW) {
                rotates = new int[]{0, 1, 2, 3};
            } else {
                rotates = new int[]{0, 2};
            }
        } else if (aH == bW && aW == bH) {
            rotates = new int[]{1, 3};
        } else {
            return false;
        }

        boolean canMatch = false;

        // B블록을 회전하면서 A블록과 동일한 모양인지 확인
        for (int i : rotates) {
            boolean isMatched = true;
            for (int r = 0; r < aH; r++) {
                for (int c = 0; c < aW; c++) {
                    int aR = a.tlR + r, aC = a.tlC + c;
                    int bR = b.tlR + getRotate(r, c, bH, bW, i)[0];
                    int bC = b.tlC + getRotate(r, c, bH, bW, i)[1];
                    if ((boardA[aR][aC] == 2 && boardB[bR][bC] != 2)
                            || (boardB[bR][bC] == 2 && boardA[aR][aC] != 2)) {
                        isMatched = false;
                        break;
                    }
                }
                if (!isMatched) break;
            }
            if (isMatched) {
                canMatch = true;
                break;
            }
        }

        return canMatch;
    }

    // 0, 90, 180, 270도 회전했을 때 좌표를 구하는 메서드
    private static int[] getRotate(int r, int c, int H, int W, int d) {
        switch (d) {
            case 0:
                return new int[]{r, c};
            case 1:
                return new int[]{H - c - 1, r};
            case 2:
                return new int[]{H - r - 1, W - c - 1};
            case 3:
                return new int[]{c, W - r - 1};
        }
        return new int[]{r, c};
    }

    private static void printBlock(int[][] board, Block block) {
        System.out.println();
        System.out.print("   ");
        for(int c=block.tlC; c<= block.brC; c++) {
            System.out.print(c + "  ");
        }
        System.out.println();

        int r = block.tlR;
        for(int[] row: Arrays.copyOfRange(board, block.tlR, block.brR+1)) {
            System.out.print(r++ + " ");
            for(int c: Arrays.copyOfRange(row, block.tlC, block.brC+1)) {
                if (c == 2) {
                    System.out.print("⬛ ");
                } else {
                    System.out.print("⬜ ");
                }
            }
            System.out.println();
        }
    }

    static class Block {
        int r;
        int c;
        int tlR;
        int tlC;
        int brR;
        int brC;
        int cnt;

        public Block(int r, int c, int tlR, int tlC, int brR, int brC, int cnt) {
            this.r = r;
            this.c = c;
            this.tlR = tlR;
            this.tlC = tlC;
            this.brR = brR;
            this.brC = brC;
            this.cnt = cnt;
        }

        public int getWidth() {
            return brC - tlC + 1;
        }

        public int getHeight() {
            return brR - tlR + 1;
        }
    }
}