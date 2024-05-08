import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1033 {

    private static Ratio[] materials;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        materials = new Ratio[N];
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int m1 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            long gcd = GCD(p, q);
            p /= gcd; q /= gcd;

            if (materials[m1] == null) {
                materials[m1] = new Ratio(1);
            }

            if (materials[m2] == null) {
                materials[m2] = new Ratio(1);
            }

            setMaterials(m1, m2, p, q);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(materials[i].value);
            if (i < N-1) System.out.print(" ");
        }

    }

    private static class Ratio {
        long value;
        Set<Integer> chain = new HashSet<>();

        public Ratio(long value) {
            this.value = value;
        }
    }

    private static void setMaterials(int m1, int m2, int p, int q) {
        long m1Val = materials[m1].value;
        long m2Val = materials[m2].value;
        long gcd = GCD(p * m2Val, q * m1Val);
        multipleVals(m1, (p * m2Val) / gcd);
        multipleVals(m2, (q * m1Val) / gcd);

        materials[m1].chain.addAll(materials[m2].chain);
        materials[m2].chain.addAll(materials[m1].chain);

        materials[m1].chain.add(m2);
        materials[m2].chain.add(m1);

    }

    private static void multipleVals(int mMain, long val) {
        materials[mMain].value *= val;
        for (int m : materials[mMain].chain) {
            materials[m].value *= val;
        }
    }

    private static long GCD(long A, long B) {
        long large = Long.max(A, B);
        long small = Long.min(A, B);

        while (large > 0 && small > 0) {
            long mod = large % small;
            if (mod == 0) break;
            large = small;
            small = mod;
        }

        return small;
    }
}
