public class P240508 {

    public static void main(String[] args) {
        System.out.println(solution(555)); // 답: 14
    }

    public static int solution(int storey) {
        int answer = 0;

        // 일의자리부터 한자리씩 확인
        while (storey > 0) {
            int now = storey % 10;

            // 일의자리가 5인 경우는 십의자리를 고려해서 처리
            if (now == 5 && storey / 10 > 0) {
                int next = (storey/10) % 10;
                // 숫자를 더하는게 좋은 경우 or 빼는게 좋은 경우
                if (next >= 5) {
                    storey += (10-now);
                    answer += (10-now);
                } else {
                    storey -= now;
                    answer += now;
                }
            } else if (now < 6) { // 일의 자리가 0~5인 경우 빼서 처리
                storey -= now;
                answer += now;
            } else { // 일의 자리가 6~9인 경우 더해서 처리
                storey += (10-now);
                answer += (10-now);
            }

            storey /= 10;
        }

        return answer;
    }
}