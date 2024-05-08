import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int next = 1, before = 0;
        boolean isSuccess = true;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (!isSuccess) continue;
            if (num > before) {
                while (next <= num) {
                    stack.push(next++);
                    sb.append("+\n");
                }
            }
            Integer out = stack.pop();
            if (out == null || out != num) {
                isSuccess = false;
            } else {
                sb.append("-\n");
            }
            before = num;
        }
        if (isSuccess) {
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }
    }

//    private static class Stack {
//        int[] stack;
//        int top = -1;
//
//        public Stack(int size) {
//            this.stack = new int[size];
//        }
//
//        public boolean isEmpty() {
//            return top < 0;
//        }
//
//        public void push(int val) {
//            stack[++top] = val;
//        }
//
//        public Integer peek() {
//            return this.isEmpty()? null : stack[top];
//        }
//
//        public Integer pop() {
//            if (this.isEmpty()) {
//                return null;
//            } else {
//                top--;
//                return stack[top+1];
//            }
//        }
//    }
}
