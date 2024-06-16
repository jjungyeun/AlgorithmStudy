import java.util.*;

class P2861 {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int answer = 0;
        for (int i = 0; i < k; i++) {
            int left = 1, right = 200000000;
            int best = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                long nowCost = getCost(mid, composition.get(i), stock, cost);
                if (nowCost > budget) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                    best = mid;
                }
            }
            answer = Integer.max(answer, best);
        }
        return answer;
    }

    private long getCost(int current, List<Integer> composition, List<Integer> stock, List<Integer> cost) {
        long totalCost = 0;
        for (int j = 0; j < composition.size(); j++) {
            long need = (long) current * composition.get(j) - stock.get(j);
            totalCost += Long.max(need, 0) * cost.get(j);
        }
        return totalCost;
    }
}