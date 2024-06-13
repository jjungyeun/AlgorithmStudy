class P1971 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 목적지가 출발노드라면 바로 true 반환하고 종료
        if (source == destination) return true;

        // 한 정점과 이어진 다른 정점들의 리스트를 담는 Map
        Map<Integer, List<Integer>> connections = new HashMap<>();

        // 정점 연결정보 구하기
        for(int[] edge: edges) {
            int u = edge[0], v = edge[1];

            connections.putIfAbsent(u, new ArrayList<>());
            connections.putIfAbsent(v, new ArrayList<>());

            connections.get(u).add(v);
            connections.get(v).add(u);
        }

        // BFS를 위한 방문정보 집합 및 큐
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();

        // 시작점 넣고 BFS 시작
        visited.add(source);
        q.add(source);

        while(!q.isEmpty()) {
            int here = q.poll();

            for(int next: connections.getOrDefault(here, new ArrayList<>())) {
                // 다음 노드가 목적지라면 true 반환하고 종료
                if (next == destination) {
                    return true;
                }

                // 현재 노드에 연결된 아직 방문하지 않은 정점 방문하기
                if (!visited.contains(next)) {
                    visited.add(next);
                    q.add(next);
                }
            }
        }

        // 목적지를 방문하지 못했으므로 false 반환
        return false;
    }
}