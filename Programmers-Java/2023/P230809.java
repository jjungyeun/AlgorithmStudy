import java.util.Arrays;
import java.util.Comparator;

public class P230809 {
    public static void main(String[] args) {
        int[] A = {2,2,2};
        int[] B = {1,1,2};
        System.out.println(solution(A, B));
    }

    public static int solution(int[] A, int[] B) {
        // 각 팀 선수들을 내림차순으로 정렬
        Integer[] teamA = Arrays.stream(A).boxed().toArray(Integer[]::new);
        Integer[] teamB = Arrays.stream(B).boxed().toArray(Integer[]::new);
        Arrays.sort(teamA, Comparator.reverseOrder());
        Arrays.sort(teamB, Comparator.reverseOrder());

        int playerA = 0, playerB = 0;
        int scoreB = 0;
        while(playerA < A.length && playerB < B.length) {
            // 1. B선수가 이김
            if (teamA[playerA] < teamB[playerB]) {
                scoreB++;
                playerA++;
                playerB++;
            }
            // 2. B선수가 지거나 비김
            // -> 현재 선수가 가장 센 선수. 이길때까지 기다림.
            else {
                playerA++;
            }
        }
        return scoreB;
    }
}
