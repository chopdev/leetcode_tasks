import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    // task 1
    public int[] delta_encode(int[] array) {
        if(array == null) return null;
        if(array.length == 0) return new int[0];

        List<Integer> res = new ArrayList<>();
        res.add(array[0]);
        for (int i = 0, j = 1; i < array.length && j < array.length; i++, j++) {
            int diff = array[j] - array[i];
            if(diff < -127 || diff > 127) {
                res.add(-128);
            }
            res.add(diff);
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    // task 2
    int howManyAgentsToAdd(int noOfCurrentAgents, List<List<Integer>> callsTimes) {
        int[] start = new int[callsTimes.size()];
        int[] finish = new int[callsTimes.size()];

        for (int i = 0; i < callsTimes.size(); i++) {
            start[i] = callsTimes.get(i).get(0);
            finish[i] = callsTimes.get(i).get(1);
        }

        Arrays.sort(start);
        Arrays.sort(finish);

        int i = 0, j = 0, count = 0, max = 0;
        while (i < start.length && j < start.length) {
            if(start[i] <= finish[j]) {
                count++;
                i++;
                if(max < count) max = count;
            }
            else {
                count--;
                j++;
            }
        }

        if(max < 0 || noOfCurrentAgents >= max) return 0;
        return max - noOfCurrentAgents;
    }


    // Task 3
    List<Integer> sort_hotels(String keywords, List<Integer> hotel_ids, List<String> reviews) {
        if(keywords == null) return hotel_ids;
        String[] keys = keywords.split(" ");
        List<Hotel> hotels = new ArrayList<>();
        for (int id : hotel_ids) {
            hotels.add(new Hotel(id));
        }

        for (int i = 0; i < hotel_ids.size(); i++) {
            for (String word : keys) {
                if(containsWord(reviews.get(i), word))
                    hotels.get(i).count++;
            }
        }

        hotels.sort((a, b)-> Integer.compare(b.count, a.count));
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            res.add(hotels.get(i).id);
        }

        return res;
    }

    boolean containsWord(String review, String word) {
        Pattern p = Pattern.compile(word, Pattern.CASE_INSENSITIVE+Pattern.LITERAL);
        Matcher m = p.matcher(review);
        return m.find();
    }
}
