import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P1043 {

    private static int[] parents;
    private static boolean[] isTruth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 상위 노드 표시 배열 초기화
        parents = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        // 진실을 아는 사람 입력 받고 배열에 표시
        st = new StringTokenizer(br.readLine());
        isTruth = new boolean[N+1];
        int truthCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < truthCnt; i++) {
            int truth = Integer.parseInt(st.nextToken());
            isTruth[truth] = true;
        }

        // 파티별 참여자 입력 받고 리스트에 저장
        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            List<Integer> party = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int firstPerson = Integer.parseInt(st.nextToken());
            party.add(firstPerson);
            for (int j = 1; j < cnt; j++) {
                int participant = Integer.parseInt(st.nextToken());
                party.add(participant);
                // 같은 파티에 참여한 사람들을 모두 같은 집합으로 묶기
                union(firstPerson, participant);
            }
            parties.add(party);
        }

        int answer = 0;
        for (List<Integer> party : parties) {
            int partyTop = find(party.get(0));
            if (!isTruth[partyTop]) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    // 합집합 - 두 노드의 대표 노드를 연결
    // 두 노드 중 진실을 아는 사람이 있다면 무조건 그쪽을 기준으로 합치기
    // -> 진실된 사람과 연결된 파티에 포함된 사람의 대표 노드는 무조건 진실된 사람이 된다.
    private static void union(int a, int b) {
        int topA = find(a);
        int topB = find(b);
        if (topA != topB) {
            int l, r;
            if (isTruth[topA] && isTruth[topB]) {
                l = Integer.min(topA, topB);
                r = Integer.max(topA, topB);
            } else if (isTruth[topB]) {
                l = topB; r = topA;
            } else  {
                l = topA; r = topB;
            }
            parents[r] = l;
        }
    }

    // 대표 노드 찾기 및 배열 업데이트
    private static int find(int node) {
        if(parents[node] == node) {
            return node;
        } else {
            return parents[node] = find(parents[node]);
        }
    }
}
