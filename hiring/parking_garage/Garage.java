import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Given a parking garage with two functions, implement enter and exit functions
 * Garage size: 5 floors, 20 spots on the floor
 *
 *
 * Good qustions to ask:
 * - can we have different car sizes?
 * - what is the spot assignment strategy? e.g. random, closest to the entrance, least occupied floor etc.
 * - how many entrances can we have? (if more than two concurrency is important)
 *
 *
 * good video:
 * https://www.youtube.com/watch?v=tVRyb4HaHgw&ab_channel=ThinkSoftware
 * */
public class Garage {

    class Spot {
        String id;
        int floor;

        public Spot(String id, int floor) {
          this.id = id;
          this.floor = floor;
        }
    }

    Deque<Spot> allSpots = new LinkedList<>();
    Map<Ticket, Spot> occupied = new HashMap<>();

    public Garage(Map<Integer, List<String>> floorToSpots) {
        for (Integer floor : floorToSpots.keySet()) {
            for (String spot : floorToSpots.get(floor)) {
                allSpots.add(new Spot(spot, floor));
            }
        }
    }

    public Ticket enter() {
        if (allSpots.isEmpty()) return null; // or exception

        Spot spotToTake = allSpots.removeFirst();
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), spotToTake.floor);
        occupied.put(ticket, spotToTake);
        return ticket;
    }

    public void exit(Ticket ticket) {
        if (!occupied.containsKey(ticket)) return; // or exception

        Spot freeSpot = occupied.remove(ticket);
        allSpots.offer(freeSpot);
    }

}
