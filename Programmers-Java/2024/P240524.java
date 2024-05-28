import java.util.*;

class P240524 {
    public int solution(int[][] jobs) {
        int N = jobs.length;

        // 작업 목록을 요청 시간별로 map으로 묶음
        Map<Integer, List<Integer>> jobTimeMap = new HashMap<>();
        for(int[] job: jobs) {
            int enterT = job[0], jobT = job[1];
            jobTimeMap.putIfAbsent(enterT, new ArrayList<>());
            jobTimeMap.get(enterT).add(jobT);
        }

        // 시간의 흐름에 따라 작업 요청, 작업 시작 및 종료 확인
        int t = 0, timeSum = 0, restJob = N;
        Task nowTask = null;
        PriorityQueue<Task> tasks = new PriorityQueue<>();
        while (restJob > 0) {
            // 현재 시간에 요청된 작업을 pq에 삽입
            if (jobTimeMap.containsKey(t)) {
                for(int jobT : jobTimeMap.get(t)) {
                    tasks.add(new Task(t, jobT));
                }
            }

            // 현재 작업이 끝났는지 확인
            if (nowTask != null && nowTask.isJobDone(t)){
                timeSum += nowTask.getWaitTime();
                restJob--;
                nowTask = null;
            }

            // 현재 진행중인 작업이 없으면 가능한 작업 중
            // 작업 시간이 가장 짧은걸로 시작
            if (nowTask == null && !tasks.isEmpty()) {
                nowTask = tasks.poll();
                nowTask.setStartT(t);
            }

            t++;
        }

        return (int) timeSum / N;
    }

    class Task implements Comparable<Task> {
        int enterTime;
        int jobTime;
        int startTime;

        public Task (int enterTime, int jobTime) {
            this.enterTime = enterTime;
            this.jobTime = jobTime;
        }

        public void setStartT(int t) {
            this.startTime = t;
        }

        public boolean isJobDone(int nowT) {
            return nowT - startTime >= jobTime;
        }

        public int getWaitTime() {
            return startTime + jobTime - enterTime;
        }

        @Override
        public int compareTo(Task other) {
            if (this.jobTime == other.jobTime) {
                return this.enterTime - other.enterTime;
            }
            return this.jobTime - other.jobTime;
        }
    }
}