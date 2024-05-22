import java.util.*;

class P240522_2 {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answers = new ArrayList<>();

        int N = progresses.length;
        int lastRelease = -1;

        while(lastRelease < N-1) {
            // 하루치 작업 진행
            for(int i=lastRelease+1; i<N; i++) {
                progresses[i] = Integer.min(100, progresses[i] + speeds[i]);
            }

            // 배포
            int todayRelease = 0;
            for(int i=lastRelease+1; i<N; i++) {
                if (progresses[i] == 100) {
                    todayRelease++;
                    lastRelease++;
                } else {
                    break;
                }
            }

            if (todayRelease > 0) {
                answers.add(todayRelease);
            }
        }

        return answers;
    }
}