import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class P230807 {

    private static Map<Integer, ArrayList<Integer>> teams;
    private static int[][] DP;

    public static void main(String[] args) {
        int[] sales = {5, 6, 5, 3, 4};
        int[][] links = {{2, 3}, {1, 4}, {2, 5}, {1, 2}};
        int ans = solution(sales, links);
        System.out.println(ans);
    }

    public static int solution(int[] sales, int[][] links) {
        DP = new int[sales.length +1][2];

        teams = new HashMap<>();
        for (int[] link : links) {
            int leader = link[0], teammate = link[1];
            if (!teams.containsKey(leader)) {
                teams.put(leader, new ArrayList<>());
            }
            teams.get(leader).add(teammate);
        }

        dfs(1, sales);
        return Math.min(DP[1][0], DP[1][1]);
    }

    private static void dfs(int worker, int[] sales) {
        if (!teams.containsKey(worker)) {
            DP[worker][0] = 0;
            DP[worker][1] = sales[worker-1];
            return;
        }

        int teammateSum = 0;
        boolean hasParticipant = false;
        for (Integer teammate : teams.get(worker)) {
            dfs(teammate, sales);
            teammateSum += Math.min(DP[teammate][0], DP[teammate][1]);

            // 해당 팀원이 참석하는 경우 팀에 참석자가 있음을 확인
            if (DP[teammate][0] >= DP[teammate][1]) {
                hasParticipant = true;
            }
        }

        // 1. 팀장 자신이 참석하는 경우
        DP[worker][1] = sales[worker-1] + teammateSum;

        // 2-1. 팀장 자신이 참석하지 않고 팀원 한명이라도 참석한 경우
        if (hasParticipant) {
            DP[worker][0] = teammateSum;
        }
        // 2-2. 팀장 자신이 참석하지 않는데 팀원 아무도 참석 안하는 경우
        // -> 참석 했을 때와 참석 안했을 때의 차이가 가장 적은 팀원이 참석
        else {
            int minWorker = getMinWorkerOfTeam(worker);
            DP[worker][0] = teammateSum + DP[minWorker][1] - DP[minWorker][0];
        }
    }

    private static int getMinWorkerOfTeam(int teamNum) {
        int minWorker = teamNum;
        int minVal = Integer.MAX_VALUE;
        for (Integer worker : teams.get(teamNum)) {
            if (DP[worker][1] - DP[worker][0] < minVal) {
                minWorker = worker;
                minVal = DP[worker][1] - DP[worker][0];
            }
        }
        return minWorker;
    }
}

