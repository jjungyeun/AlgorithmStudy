import java.util.*;

class P240612 {

    private final int[][] drdc = {{1,1,0,-1,-1,-1,0,1}, {0,1,1,1,0,-1,-1,-1}};
    private final int[] opposite = {4,5,6,7,0,1,2,3};

    public int solution(int[] arrows) {
        int answer = 0;

        // 각 점에 연결된 선 정보를 저장할 Map
        // Map의 Key들 = 방문한 Point들
        Map<Point, Lines> lines = new HashMap<>();

        // 시작점 방문 표시
        Point now = new Point(0,0);
        lines.put(now, new Lines());

        for(int arrow: arrows) {
            // 선으로 이어진 다음 좌표
            Point next = now.getNext(arrow);

            // 현재 선이 이미 그은 선인 경우 좌표 정보만 갱신하고 넘어가기
            Lines nowLines = lines.get(now);
            if (nowLines.hasLine(arrow)) {
                now = next;
                continue;
            }

            // 이미 방문했던 좌표를 다시 방문하는 경우 도형 추가
            if (lines.containsKey(next)) {
                answer++;
            }

            // 대각선인 경우 (1,3,5,7)
            if (arrow % 2 == 1) {
                // 이미 그어진 선과 교차되면 도형 추가
                Point cross = new Point(now.r, next.c);
                Lines crossLines = lines.get(cross);
                if (crossLines != null && crossLines.hasLine(8-arrow)) {
                    answer++;
                }
            }

            // 기존 좌표에 새로운 선 정보 저장
            nowLines.addLine(arrow);

            // 다음 좌표 방문 표시 및 선 정보 저장
            Lines nextLines = lines.getOrDefault(next, new Lines());
            nextLines.addLine(opposite[arrow]);
            lines.put(next, nextLines);

            // 다음 좌표로 이동
            now = next;
        }

        return answer;
    }

    // 점에 연결된 선 정보를 저장할 클래스
    private class Lines {
        // 해당 점에 연결된 선의 방향
        private Set<Integer> directions = new HashSet<>();

        public Lines() { }

        public void addLine(int arrow) {
            directions.add(arrow);
        }

        public boolean hasLine(int arrow) {
            return directions.contains(arrow);
        }
    }

    // 객체가 아닌 데이터로서의 Point 클래스 정의
    private class Point {
        int r; int c;

        public Point (int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point getNext(int arrow) {
            return new Point(r + drdc[0][arrow], c + drdc[1][arrow]);
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || this.getClass() != o.getClass()) return false;

            Point other = (Point) o;

            return other.r == this.r && other.c == this.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}