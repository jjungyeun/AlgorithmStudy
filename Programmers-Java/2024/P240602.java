import java.util.*;

class P240602 {

    Map<String, List<String>> adjMap = new HashMap<>();
    Map<String, Boolean> visited = new HashMap<>();
    int answer = 100;

    public int solution(String begin, String target, String[] words) {

        // 타겟이 words에 없는 경우 0 반환
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }

        int N = words.length;
        for (String word : words) {
            adjMap.put(word, new ArrayList<>());
            visited.put(word, false);
        }

        // 각 단어들끼리 서로 변환 가능한지 확인하고 인접 리스트 맵 만들기
        for(int i=0; i<N-1; i++) {
            String wi = words[i];
            for (int j=i+1; j<N; j++) {
                String wj = words[j];
                if(canChange(wi, wj)) {
                    adjMap.get(wi).add(wj);
                    adjMap.get(wj).add(wi);
                }
            }
        }

        // begin에 대해 인접 리스트 맵 정보 추가
        adjMap.put(begin, new ArrayList<>());
        for (String w : words) {
            if (canChange(begin, w)) {
                adjMap.get(begin).add(w);
            }
        }

        dfs(begin, target, 0);

        return answer != 100? answer : 0;
    }

    // 길이가 같은 두 단어가 딱 한글자만 다를 때 true 반환
    private boolean canChange(String w1, String w2) {
        int len = w1.length();
        int diff = 0;

        for(int i=0; i<len; i++) {
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(i);
            if(c1 != c2) {
                diff++;
            }
        }

        return diff == 1;
    }

    // 방문하지 않은 인접한 단어를 방문하는 DFS 메서드
    private void dfs(String here, String target, int cnt) {
        if (here.equals(target)) {
            answer = Integer.min(answer, cnt);
            return;
        }

        for(String next: adjMap.get(here)) {
            if (!visited.get(next)) {
                visited.put(next, true);
                dfs(next, target, cnt+1);
                visited.put(next, false);
            }
        }
    }
}