class P5 {
    public String longestPalindrome(String s) {
        int n = s.length();
        int startIdx = 0, longest = 1;

        for(int mid = 1; mid < n; mid++) {
            // 1. 홀수 펠린드롬
            int oddMax = 0;
            for(int dist = 1; mid-dist >= 0 && mid+dist < n; dist++) {
                if (s.charAt(mid-dist) != s.charAt(mid+dist)) {
                    break;
                }
                oddMax++;
            }

            if (oddMax*2+1 > longest) {
                startIdx = mid-oddMax;
                longest = oddMax*2 + 1;
            }

            // 2. 짝수 펠린드롬
            int evenMax = 0;
            for(int dist = 1; mid-dist >= 0 && mid+dist <= n; dist++) {
                if (s.charAt(mid-dist) != s.charAt(mid+dist-1)) {
                    break;
                }
                evenMax++;
            }

            if (evenMax*2 > longest) {
                startIdx = mid-evenMax;
                longest = evenMax*2;
            }
        }

        return s.substring(startIdx, startIdx + longest);
    }
}