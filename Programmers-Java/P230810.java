import java.util.Arrays;

public class P230810 {
    public static void main(String[] args) {
        int[][] routes = {{-20,-15}, {-14,-5}, {-14,-13}, {-5,-3}};
        System.out.println(solution(routes));
    }

    public static int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> {
            if (o1[1] < o2[1]) return -1;
            else if (o1[1] > o2[1]) return 1;
            else return o1[0] - o2[0];
        });

        int ans = 0;
        int lastCamera = -30001;
        for (int[] route : routes) {
            int in = route[0], out = route[1];
            if (in > lastCamera || lastCamera > out) {
                ans++;
                lastCamera = out;
            }
        }
        return ans;
    }
}
