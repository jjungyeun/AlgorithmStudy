import java.util.*;

class P240604 {
    public int solution(int n, int[][] costs) {

        // 엣지 리스트를 가중치 오름차순으로 정렬
        Arrays.sort(costs, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // 유니온 파인드 배열 초기화
        int[] uf = new int[n];
        for(int i=0; i<n; i++) {
            uf[i] = i;
        }

        int edgeCnt = 0;
        int answer = 0;

        // 최소 엣지부터 연결
        // 연결 시 사이클이 생기는 경우 연결 안함
        for(int[] edge: costs) {
            int s = edge[0], d = edge[1], cost = edge[2];
            if(find(uf, s) != find(uf, d)) {
                union(uf, s, d);
                edgeCnt++;
                answer += cost;
            }

            if(edgeCnt == n-1) {
                break;
            }
        }

        return answer;
    }

    // 노드 b의 대표노드를 노드 a의 대표노드로 변경
    private void union(int[] arr, int a, int b) {
        arr[find(arr, b)] = find(arr, a);
    }

    // node의 대표노드를 반환
    // 대표노드를 구하는 과정에서 그 경로에 있는 노드들의 대표노드도 업데이트
    private int find(int[] arr, int node) {
        if(arr[node] != node) {
            return arr[node] = find(arr, arr[node]);
        }
        return arr[node];
    }
}