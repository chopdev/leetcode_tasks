package other.car_schedules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/*
Carrier scheduling is organized as a series of travels.
Every travel defines the following information: carrier name,
departure/arrive time, and destinations in form of string codes.
Can you write the functions and components that would find 
potential problems in carriers' schedule? 
One example of a problem might be long gaps between stops. 
There might be other problem types in the future.

Example of data for gaps:
Ivan: VAN 10am - DUG 11am
Bob: LDL 5am - GGG 6am
Ivan: VIC 1am - VAN 2am

Explanation: the schedule contains 2 travels  for the same driver (Ivan),
but the time between the first arrival (2am) and the second departure (10am) is more
than 5 hours, which Ivan will need to waste.
*/


class Travel {
    String name;
    Stop from;
    Stop to;
}

class Stop {
    String location;
    int time;
}

enum ProblemType {
    BIG_GAP,
    INVALID_NEXT_LOCATION,
    INVALID_NEXT_TIME;
}

class Problem {
    ProblemType type;
    Travel travel;
}

/*
Potential problems
1) big gaps
2) arrival location != departure location of a next movement
3) arrival time > departure time of a next movement

4) validate that locations are correct
5) time is valid
*/

class ProblemHandler {
    ProblemType type;
    BiFunction<List<Travel>, Integer, Boolean> detector;
}

// My solution
// O(~NlogN) - sorting, carrier * M*logM
// O(N * H) - main algo, where H - count of problem handlers
// total time O(~ NlogN + N * H)
public class Solution {
    public static void main(String[] args) {
        ProblemDefinition definitions = new ProblemDefinition();
        List<ProblemHandler> handlers = new ArrayList<>();
        ProblemHandler bigGap = new ProblemHandler();
        bigGap.type = ProblemType.BIG_GAP;
        bigGap.detector = definitions::detectBigGap;
        handlers.add(bigGap);
        Solution sol = new Solution(handlers);

        List<Travel> travels = new ArrayList<>();
        Travel t1 = new Travel();
        Travel t2 = new Travel();
        travels.add(t2);
        travels.add(t1);

        t1.name = "Ivan";
        t1.from = new Stop();
        t1.from.location = "VAN";
        t1.from.time = 10;
        t1.to = new Stop();
        t1.to.location = "NYY";
        t1.to.time = 11;

        t2.name = "Ivan";
        t2.from = new Stop();
        t2.from.time = 20;
        t2.from.location = "NYY";
        t2.to = new Stop();
        t2.to.location = "VAN";
        t2.to.time = 21;

        List<Problem> res = sol.getProblems(travels);
        System.out.println(res.get(0).type);
        System.out.println(res.get(0).travel.from.location);
        System.out.println(res.get(0).travel.from.time);
    }

    List<ProblemHandler> handlers;
    public Solution(List<ProblemHandler> handlers) {
        this.handlers = handlers;
    }

    public List<Problem> getProblems(List<Travel> travels) {
        Map<String, List<Travel>> carrierToTravels = travels.stream()
            .collect(Collectors.groupingBy(t -> t.name));
        sortTravelsByDepartureTime(carrierToTravels);

        List<Problem> res = new ArrayList<>();
        for (String carrier : carrierToTravels.keySet()) {
            for (ProblemHandler handler : handlers) {
                List<Problem> problems = detectProblems(carrierToTravels.get(carrier), handler);
                res.addAll(problems);
            }
        }
        
        return res;
    }

    private void sortTravelsByDepartureTime(Map<String, List<Travel>> carrierToTravels) {
        for (String key : carrierToTravels.keySet()) {
            Collections.sort(carrierToTravels.get(key), Comparator.comparingInt(t -> t.from.time));
        }
    }

    private List<Problem> detectProblems(List<Travel> sortedDriverTravels, ProblemHandler handler) {
           List<Problem> res = new ArrayList<>();
           for (int i = 0; i< sortedDriverTravels.size(); i++) {
               boolean invalid = handler.detector.apply(sortedDriverTravels, i);
               if (invalid) {
                   Problem p = new Problem();
                   p.travel = sortedDriverTravels.get(i);
                   p.type = handler.type;
                   res.add(p);
               }
           }
           return res;
    }
}

class ProblemDefinition {
    public Boolean detectBigGap(List<Travel> travels, Integer currentTravel) {
        if (currentTravel >= travels.size() - 1) {
            return false; // last element
        }
        int nextTravel = currentTravel + 1;
        if (travels.get(currentTravel).to.time + 5 < travels.get(nextTravel).from.time) {
            return true;
        }
        return false;
    }
}