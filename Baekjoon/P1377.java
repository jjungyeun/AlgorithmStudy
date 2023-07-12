import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1377 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Element[] arr = new Element[N];

        for (int i = 0; i < N; i++) {
            arr[i] = new Element(Integer.parseInt(br.readLine()), i);
        }
        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            Element element = arr[i];
            ans = Math.max(ans, element.index - i);
        }
        System.out.println(ans+1);
    }

    private static class Element implements Comparable<Element> {
        int value;
        int index;

        public Element(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Element other) {
            return this.value - other.value;
        }
    }
}
