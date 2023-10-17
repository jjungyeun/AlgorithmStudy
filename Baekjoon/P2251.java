import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class P2251 {

    private static int[] capacity = new int[3];
    private static int[][] pourablePair = new int[][] {{0,1}, {0,2}, {1,2}, {2,0}, {2,1}, {1,0}};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            capacity[i] = sc.nextInt();
        }

        Queue<State> queue = new LinkedList<>();
        Map<State, Boolean> visited = new HashMap<>();
        State initialState = new State(0, 0, capacity[2]);
        queue.add(initialState);
        visited.put(initialState, true);

        List<Integer> answers = new ArrayList<>();
        while (!queue.isEmpty()) {
            State nowState = queue.poll();
            if (nowState.getKth(0) == 0) {
                answers.add(nowState.getKth(2));
            }
            for (int[] pair : pourablePair) {
                State nextState = pourTo(nowState, pair[0], pair[1]);
                if (!visited.containsKey(nextState)) {
                    queue.add(nextState);
                    visited.put(nextState, true);
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        answers.sort(Comparator.naturalOrder());
        for (Integer num : answers) {
            bw.write(num + " ");
        }
        bw.flush();
        bw.close();
    }

    private static State pourTo(State current, int s, int d) {
        int xL = current.getKth(s);
        int yL = current.getKth(d);
        int pourAmount = Integer.min(xL, capacity[d]-yL);

        State newState = current.copy();
        newState.setKth(s, xL - pourAmount);
        newState.setKth(d, yL + pourAmount);
        return newState;
    }

    private static class State {
        int[] waters = new int[3];

        public State(int a, int b, int c) {
            waters[0] = a;
            waters[1] = b;
            waters[2] = c;
        }

        public State copy() {
            return new State(waters[0], waters[1], waters[2]);
        }

        public int getKth(int K) {
            return waters[K];
        }

        public void setKth(int K, int water) {
            waters[K] = water;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Arrays.equals(waters, state.waters);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(waters);
        }
    }
}
