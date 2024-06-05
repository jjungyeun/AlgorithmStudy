class P240605 {
    public int solution(String name) {
        int answer = 0;
        int N = name.length();

        // 알파벳을 하나씩 확인하면서 필수적인 알파벳 이동 값을 답에 더해줌
        int lastNonA = -1;
        for(int i=0; i<N; i++) {
            char alp = name.charAt(i);
            answer += getAlphabetMove(alp);

            if (alp != 'A') {
                lastNonA = i;
            }
        }

        // "A"*n 문자열이 포함되어있는지 확인하고,
        // 있으면 해당 문자열을 기준으로 오른쪽부터 갔다가 왼쪽으로 가는거랑
        // 왼쪽부터 (거꾸로) 갔다가 오른쪽으로 가는 것 중에 더 짧은 방법을 확인
        int minMove = 21;
        for(int i=1; i<=N; i++) {
            String find = "A".repeat(i);
            int idx = name.indexOf(find);
            if (idx == -1) break;
            if (idx + i < N && name.charAt(idx + i) == 'A') {
                continue;
            }

            int original = Integer.max(0, idx-1);
            int reverse = N-idx-i;
            int left = original*2 + reverse;
            int right = reverse*2 + original;
            minMove = Integer.min(minMove, Integer.min(left, right));
        }

        // 위에서 구한 값과, 그냥 오른쪽으로 쭉 이동하는 것 중에 더 짧은 거리를 답에 더함
        int right = Integer.max(0, lastNonA);
        answer += Integer.min(right, minMove);

        return Integer.max(0, answer);
    }

    private int getAlphabetMove(char alp) {
        int diff = alp - 'A';
        if (diff <= 13) return diff;
        else return 26 - diff;
    }
}