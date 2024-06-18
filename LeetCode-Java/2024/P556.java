import java.util.*;

class P556 {

    public int nextGreaterElement(int n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        int l = 0, r = sb.length(); // 문자열 비교 범위

        while(l < r) {
            // 맨 처음에만 더 커지기 위한 조건, 그 이후로는 더 작아지기 위한 조건
            // 더 커지거나 작아지기 위해 바꿔야 하는 숫자의 인덱스를 구함
            int[] change = (l == 0) ? findChangeToGreater(sb.substring(l, r))
                    : findChangeToSmaller(sb.substring(l, r));

            // 더 커지거나 작아질 수 없으면 종료
            if (change[0] == -1 || change[1] == -1) break;

            // 위에서 구한 결과에 맞춰 숫자를 바꾸기
            changeChar(sb, l+change[0], l+change[1]);

            // 바뀐 부분만 다시 검사하도록 범위 조정
            l += (change[0] + 1);
        }

        // 최종적으로 구한 결과가 정상적인 범위 안이면 답으로 반환, 아니면 -1 반환
        long nextNumber = Long.parseLong(sb.toString());
        return nextNumber <= Integer.MAX_VALUE && nextNumber > n ? (int) nextNumber : -1;
    }

    // 숫자로 구성된 문자열에서, 두 숫자를 바꿀 경우 전체 숫자가 커지는 부분을 구함
    private int[] findChangeToGreater(String str) {
        int len = str.length();
        char[] charArr = str.toCharArray();
        int l = -1, r = -1;

        for(int s=1; s<len; s++) {
            for(int d=0; d<s; d++) {
                if (charArr[d] < charArr[s] && l <= d) {
                    l = d; r = s;
                }
            }
        }

        return new int[]{l, r};
    }

    // 숫자로 구성된 문자열에서, 두 숫자를 바꿀 경우 전체 숫자가 작아지는 부분을 구함
    private int[] findChangeToSmaller(String str) {
        int len = str.length();
        char[] charArr = str.toCharArray();
        int l = -1, r = -1;

        for(int s=1; s<len; s++) {
            for(int d=s-1; d>=0; d--) {
                if (charArr[d] > charArr[s]) {
                    l = d; r = s;
                }
            }
        }

        return new int[]{l, r};
    }

    // 문자열에서 b위치의 문자를 a위치로 이동
    private String changeChar(StringBuilder sb, int a, int b) {
        return sb.insert(a, sb.charAt(b))
                .deleteCharAt(b+1)
                .toString();
    }


/*
    // DFS를 이용하여 모든 가능한 숫자를 구하여 정답을 구하는 방식

    private int answer = 0;
    private char[] charArray;
    private boolean[] visited;
    private final Set<String> nums = new HashSet<>();
    private long cnt = 0;

    public int nextGreaterElement(int n) {

        if (n == Integer.MAX_VALUE) return -1;

        charArray = String.valueOf(n).toCharArray();
        visited = new boolean[charArray.length];

        dfs(String.valueOf(n), new StringBuilder(), false);

        System.out.println("cnt: " + cnt);

        return answer != 0 ? answer : -1;
    }

    private void dfs(String target, StringBuilder sb, boolean isGreater) {
        cnt++;
        int len = charArray.length;

        if (sb.length() == len) {
            long num = Long.parseLong(sb.toString());
            if (num <= Integer.MAX_VALUE && num > Integer.parseInt(target)
                    && (answer == 0 || num < answer)) {
                answer = (int) num;
            }
            return;
        }

        if (len == 10) {
            String maxStr = String.valueOf(Integer.MAX_VALUE).substring(0, sb.length());
            if (sb.toString().compareTo(maxStr) > 0) {
                return;
            }
        }

        char nextChar = target.charAt(sb.length());
        for(int i=0; i < len; i++) {
            if (!visited[i] && (isGreater || nextChar <= charArray[i])) {
                sb.append(charArray[i]);
                if (!nums.contains(sb.toString())) {
                    nums.add(sb.toString());
                    visited[i] = true;
                    dfs(target, sb, isGreater || nextChar < charArray[i]);
                    visited[i] = false;
                }
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }*/
}