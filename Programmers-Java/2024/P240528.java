import java.util.*;

class P240528 {
    String[] alp = {"A", "E", "I", "O", "U"};
    List<String> dict = new ArrayList<>();
    Boolean hasAnswer = false;

    public int solution(String word) {
        hasAnswer = false;
        dfs("", word);
        return dict.size();
    }

    public void dfs(String now, String target) {
        // 타겟 단어를 찾으면 탐색을 멈춘다.
        if (now.equals(target)) {
            hasAnswer = true;
        }
        if (hasAnswer) return;

        dict.add(now);

        // 현재 단어에 알파벳을 하나씩 추가하며 탐색한다.
        if (now.length() < 5) {
            for (int i = 0; i < 5; i++) {
                String next = now + alp[i];
                dfs(next, target);
            }
        }
    }
}
