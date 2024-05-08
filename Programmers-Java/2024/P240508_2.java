import java.util.*;

public class P240508_2 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{8,6,3,7,2,5,1,4})); // 답: 12
    }

    public static int solution(int[] cards) {
        int answer = 0;

        // 각 그룹별 개수 구하기
        List<Integer> groupCnts = new ArrayList<>();
        for(int i=0; i < cards.length; i++) {
            int prev = i;
            int cnt = 0;
            while(cards[prev] > 0) {
                int next = cards[prev]-1;
                cards[prev] = 0;
                cnt++;
                prev = next;
            }

            if (cnt > 0) {
                groupCnts.add(cnt);
            }
        }

        // 그룹이 2개 이상인 경우, 가장 개수가 많은 두 그룹의 개수를 곱한 값이 답
        if (groupCnts.size() > 1) {
            groupCnts.sort(Comparator.reverseOrder());
            answer = groupCnts.get(0) * groupCnts.get(1);
        }

        return answer;
    }
}