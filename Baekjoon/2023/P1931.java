import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class P1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meetings.add(new Meeting(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        meetings.sort(Comparator.naturalOrder());
        int meetingCnt = 0;
        int lastMeetingTime = 0;
        for (Meeting meeting : meetings) {
            // 회의 가능하면 회의 진행
            if (meeting.startTime >= lastMeetingTime) {
                lastMeetingTime = meeting.endTime;
                meetingCnt++;
            }
        }
        System.out.println(meetingCnt);
    }

    private static class Meeting implements Comparable<Meeting>{
        int startTime;
        int endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Meeting other) {
            // 회의 끝나는 시간 빠른 순으로 정렬
            if (this.endTime < other.endTime) return -1;
            else if (this.endTime > other.endTime) return 1;
            else return this.startTime - other.startTime;
        }
    }
}
