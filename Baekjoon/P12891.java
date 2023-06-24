import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

class P12891 {

    private static final Map<Character, Integer> dna2idx = Map.of('A', 0, 'C', 1, 'G', 2, 'T', 3);
    private static final int DNA_TYPE_NUM = dna2idx.size();

    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st1.nextToken());
        int P = Integer.parseInt(st1.nextToken());
        char[] DNA = br.readLine().toCharArray();

        int[] condition = new int[DNA_TYPE_NUM];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < DNA_TYPE_NUM; i++) {
            condition[i] = Integer.parseInt(st2.nextToken());
        }

        // 윈도우 설정 및 현재 상태 파악
        int s = 0, e = P-1;
        int[] current = new int[DNA_TYPE_NUM];
        for (int i = 0; i < P; i++) {
            current[dna2idx.get(DNA[i])]++;
        }

        // 윈도우 밀면서 답 찾기
        int ans = 0;
        while (e < S) {
            if (isAvailablePassword(current, condition)){
                ans++;
            }
            current[dna2idx.get(DNA[s])]--;
            s++; e++;
            if (e < S) current[dna2idx.get(DNA[e])]++;
        }

        System.out.println(ans);
        br.close();
    }

    private static boolean isAvailablePassword(int[] current, int[] condition) {
        for (int i = 0; i < DNA_TYPE_NUM; i++) {
            if (current[i] < condition[i])
                return false;
        }
        return true;
    }
}