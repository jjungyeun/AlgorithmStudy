import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

class P2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 단어 배열 입력 받기 (중복된 단어는 한번만 받기)
        Map<String, Word> wordMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            wordMap.putIfAbsent(word, new Word(i, word));
        }

        // 단어 배열 사전순으로 정렬하기
        List<Word> words = wordMap.keySet().stream()
                .sorted()
                .map(wordMap::get)
                .collect(Collectors.toList());

        // 정렬 후 인접한 단어들끼리 얼마나 비슷한지 확인
        // 전체에서 가장 비슷한 단어들을 저장
        String maxPrefix = null;
        List<Word> maxPrefixGroup = new ArrayList<>();
        List<List<Word>> answerCandidates = new ArrayList<>();

        for (int i = 0; i < words.size()-1; i++) {
            // 인접한 두 단어의 접두사를 구함
            Word word1 = words.get(i);
            Word word2 = words.get(i + 1);
            String prefix = getPrefix(word1.word, word2.word);

            // 1. 맨 처음에는 무조건 갱신
            if (maxPrefix == null) {
                maxPrefix = prefix;
                maxPrefixGroup.add(word1);
                maxPrefixGroup.add(word2);
            }
            // 2. 현재 가장 긴 접두사와 이번 접두사가 같은 경우
            // 새로운 단어를 정답 그룹에 추가
            else if (prefix.equals(maxPrefix)) {
                maxPrefixGroup.add(word2);
            }
            // 3. 현재 가장 긴 접두사보다 이번 접두사가 더 긴 경우
            // 가장 긴 접두사 및 정답 그룹 업데이트
            // + 현재까지의 정답 후보 그룹 초기화
            else if (prefix.length() > maxPrefix.length()) {
                answerCandidates.clear();
                maxPrefix = prefix;
                maxPrefixGroup.clear();
                maxPrefixGroup.add(word1);
                maxPrefixGroup.add(word2);
            }
            // 4. 현재 가장 긴 접두사와 이번 접두사의 길이가 같은 경우
            // 이전까지의 정답 그룹과 새로운 정답 그룹의 인덱스를 비교해야 함
            // 정답 후보 그룹에 지금까지의 정답 그룹을 넣고, 새로운 정답 그룹을 계속해서 구함
            else if (prefix.length() == maxPrefix.length()) {
                answerCandidates.add(new ArrayList<>(maxPrefixGroup));
                maxPrefix = prefix;
                maxPrefixGroup.clear();
                maxPrefixGroup.add(word1);
                maxPrefixGroup.add(word2);
            }
        }
        // 모든 단어 확인이 끝나면, 현재 정답 그룹을 후보 리스트에 추가
        answerCandidates.add(new ArrayList<>(maxPrefixGroup));

        // 정답 후보 리스트 중 가장 빠르게 등장한 단어가 있는 그룹이 정답임
        int minIdx = Integer.MAX_VALUE;
        for (List<Word> candidates : answerCandidates) {
            candidates.sort(Comparator.comparingInt(o -> o.no));
            if (candidates.get(0).no < minIdx) {
                minIdx = candidates.get(0).no;
                maxPrefixGroup = candidates;
            }
        }

        // 정답 중에서 등장 순으로 빠른 2개만 출력
        for (Word s : maxPrefixGroup.subList(0, 2)) {
            System.out.println(s.word);
        }
    }

    private static String getPrefix(String w1, String w2) {
        int minLen = Integer.min(w1.length(), w2.length());
        for (int i = 0; i < minLen; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                return w1.substring(0, i);
            }
        }
        return w1.substring(0, minLen);
    }

    public static class Word {
        int no;
        String word;

        public Word(int no, String word) {
            this.no = no;
            this.word = word;
        }
    }
}