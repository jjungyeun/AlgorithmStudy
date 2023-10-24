import java.util.*;

class Solution {
    
    private boolean[][] candidates = new boolean[8][8];
    private Set<String> answers = new HashSet<>();
    private int userLen = 0, banLen = 0;
    
    public int solution(String[] user_id, String[] banned_id) {
        userLen = user_id.length;
        banLen = banned_id.length;
        
        // candidates 배열에 i번째 밴아이디에 j번째 사용자 아이디가 밴되는지 체크
        for(int i = 0; i < banLen; i++) {
            for(int j = 0; j < userLen; j++) {
                if (isBanned(banned_id[i], user_id[j])) {
                    candidates[i][j] = true;
                }
            }
        }
        
        // 매핑되는 경우의 수를 8자리 String으로 표현하여 구함
        getCases(new StringBuilder("00000000"), 0);
        
        return answers.size();
    }
    
    // banned_id에 user_id가 해당되는지 확인
    private boolean isBanned(String banned_id, String user_id) {
        if (banned_id.length() == user_id.length()) {
            for(int idx = 0; idx < banned_id.length(); idx++) {
                if (banned_id.charAt(idx) != '*'
                   && banned_id.charAt(idx) != user_id.charAt(idx)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    // visited: 현재까지 밴된 아이디들, hereBanIdx: 이번에 확인할 밴아이디
    private void getCases(StringBuilder visited, int hereBanIdx) {
        // 마지막 밴아이디까지 체크했으면 정답셋에 넣기
        if (hereBanIdx == banLen) {
            answers.add(visited.toString());
            return;
        }
        // 다음 밴아이디로 밴되는 사용자아이디 확인 후 방문
        for(int idx=0;idx<userLen;idx++) {
            if (candidates[hereBanIdx][idx] && visited.charAt(idx) == '0') {
                visited.replace(idx,idx+1,"1");
                getCases(visited, hereBanIdx+1);
                visited.replace(idx,idx+1,"0");
            }
        }
    }
}
