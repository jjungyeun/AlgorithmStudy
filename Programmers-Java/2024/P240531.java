import java.util.*;

class P240531 {

    private Map<String, List<Ticket>> ticketMap = new HashMap<>();
    private boolean[][] used;
    private List<String> answer;
    private boolean foundAnswer = false;

    public List<String> solution(String[][] tickets) {

        // 티켓 정보를 인접 리스트 맵으로 변경
        for(String[] ticket: tickets) {
            String s = ticket[0], d = ticket[1];
            ticketMap.putIfAbsent(s, new ArrayList<>());
            ticketMap.get(s).add(new Ticket(s, d));
        }

        // 각 인접 리스트를 알파벳순으로 정렬
        for(String key: ticketMap.keySet()) {
            Collections.sort(ticketMap.get(key));
        }

        // DFS를 이용해 경로 찾기
        List<String> route = new ArrayList<>();
        route.add("ICN");
        dfs(tickets.length, "ICN", route);

        return answer;
    }

    private void dfs(int N, String here, List<String> route) {
        // 가장 사전순으로 빠른 정답만 찾으면 되므로
        // 정답을 이미 찾은 경우에는 건너뜀
        if (foundAnswer) {
            return;
        }

        // N개의 티켓을 모두 사용한 경우 정답 저장
        if (route.size() == N+1) {
            answer = new ArrayList<>(route);
            foundAnswer = true;
            return;
        }

        // 현재 위치에서 사용 가능한 티켓을 사용하고 루트에 추가하여 다음 재귀에 들어감
        if (ticketMap.containsKey(here)) {
            for(Ticket ticket: ticketMap.get(here)) {
                if(!ticket.used) {
                    ticket.used = true;
                    route.add(ticket.dest);
                    dfs(N, ticket.dest, route);
                    ticket.used = false;
                    route.remove(route.size()-1);
                }
            }
        }
    }

    class Ticket implements Comparable<Ticket> {
        String source;
        String dest;
        boolean used = false;

        public Ticket(String source, String dest) {
            this.source = source;
            this.dest = dest;
        }

        @Override
        public int compareTo(Ticket other) {
            return this.dest.compareTo(other.dest);
        }
    }
}