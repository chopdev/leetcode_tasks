/*
2402. Meeting Rooms III
https://leetcode.com/problems/meeting-rooms-iii/
*/

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        int[] meetingCount = new int[n];

        // [endTime, roomNumber]
        PriorityQueue<long[]> usedRooms = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1])
        );

        PriorityQueue<Integer> unusedRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) unusedRooms.offer(i);

        Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= start) {
                int room = (int) usedRooms.poll()[1];
                unusedRooms.offer(room);
            }

            if (!unusedRooms.isEmpty()) {
                int room = unusedRooms.poll();
                usedRooms.offer(new long[]{end, room});
                meetingCount[room]++;
            } else {
                long roomAvailability = usedRooms.peek()[0];
                int room = (int) usedRooms.poll()[1];
                usedRooms.offer(new long[]{roomAvailability + end - start, room});
                meetingCount[room]++;
            }
        }

        int maxRoom = 0;
        for (int i = 1; i < n; i++) {
            if (meetingCount[i] > meetingCount[maxRoom]) maxRoom = i;
        }
        return maxRoom;
    }
}

class Meeting {
    public int[] timeRange;
    public int room;

    public Meeting(int[] timeRange, int room) {
        this.timeRange = timeRange;
        this.room = room;
    }
}

class Solution2222 {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) freeRooms.offer(i);

        PriorityQueue<Meeting> busyRooms = new PriorityQueue<>(
            (m1, m2) -> m1.timeRange[1] != m2.timeRange[1] ? m1.timeRange[1] - m2.timeRange[1]
                                                           : m1.room - m2.room
        );

        int[] roomCount = new int[n];

        for (int[] meet : meetings) {
            int start = meet[0];
            int end = meet[1];

            while (!busyRooms.isEmpty() && busyRooms.peek().timeRange[1] <= start) {
                freeRooms.offer(busyRooms.poll().room);
            }

            int assignedRoom;
            int actualStart = start;
            if (!freeRooms.isEmpty()) {
                assignedRoom = freeRooms.poll();
            } else {
                Meeting nextFree = busyRooms.poll();
                actualStart = nextFree.timeRange[1];
                assignedRoom = nextFree.room;
            }

            int actualEnd = actualStart + (end - start);
            busyRooms.offer(new Meeting(new int[]{actualStart, actualEnd}, assignedRoom));
            roomCount[assignedRoom]++;
        }

        int res = 0;
        for (int i = 1; i < n; i++) {
            if (roomCount[i] > roomCount[res]) res = i;
        }
        return res;
    }
}
