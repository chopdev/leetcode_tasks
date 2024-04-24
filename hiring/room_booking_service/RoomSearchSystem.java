import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 Write a program for searching for available meeting rooms based on multiple criteria. Search criteria would include things like floor/building, room capacity, availability during a time-window, and available equipment such as whiteboard or AV.

 (The evaluation criteria for the Maintainable part would include whether updating the search criteria or the available rooms/resources can be done without modifying the code too much.)
* */


interface Criteria<T> {
    boolean isMet(T room);
}

class Room {
    private int floor;
    private String building;
    private int capacity;
    private Set<String> equipment;
    private Set<TimeSlot> availableTimeSlots;

    public Room(int floor, String building, int capacity, List<String> equipment, List<TimeSlot> availableTimeSlots) {
        this.floor = floor;
        this.building = building;
        this.capacity = capacity;
        this.equipment = new HashSet<>(equipment);
        this.availableTimeSlots = new HashSet<>(availableTimeSlots);
    }


    public int getFloor() {
        return floor;
    }

    public String getBuilding() {
        return building;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<String> getEquipment() {
        return equipment;
    }

    public Set<TimeSlot> getTimeSlots() {
        return availableTimeSlots;
    }

    public void setTimeSlots(List<TimeSlot> availableTimeSlots) {
        this.availableTimeSlots = new HashSet<>(availableTimeSlots);
    }

    public boolean isAvailable(List<TimeSlot> timeSlots) {
        return availableTimeSlots.containsAll(timeSlots);
    }
}

class TimeSlot {
    private LocalDateTime start;
    private LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(TimeSlot other) {
        return start.isBefore(other.end) && end.isAfter(other.start);
    }
}

// Concrete specifications
class FloorCriteria implements Criteria<Room> {
    private int floor;

    public FloorCriteria(int floor) {
        this.floor = floor;
    }

    @Override
    public boolean isMet(Room room) {
        return room.getFloor() == floor;
    }
}

class CapacityCriteria implements Criteria<Room> {
    private int capacity;

    public CapacityCriteria(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean isMet(Room room) {
        return room.getCapacity() >= capacity;
    }
}

class EquipmentSpecification implements Criteria<Room> {
    private String equipment;

    public EquipmentSpecification(String equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean isMet(Room room) {
        return room.getEquipment().contains(equipment);
    }
}

class AvailabilityCriteria implements Criteria<Room> {
    private List<TimeSlot> timeSlots;

    public AvailabilityCriteria(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @Override
    public boolean isMet(Room room) {
        return room.isAvailable(timeSlots);
    }
}

class RoomRepository {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> search(List<Criteria<Room>> criterias) {
        Stream<Room> roomStream = rooms.stream();

        for (Criteria<Room> criteria : criterias) {
            roomStream = roomStream.filter(criteria::isMet);
        }

        return roomStream.collect(Collectors.toList());
    }
}

public class RoomSearchSystem {
    public static void main(String[] args) {

        // can be a separate producer for available time slots
        TimeSlot t1 = new TimeSlot(LocalDateTime.of(2024, 5, 1, 8, 0),
                LocalDateTime.of(2024, 5, 1, 8, 30));
        // ...

        RoomRepository repository = new RoomRepository();
        repository.addRoom(new Room(3, "Building A", 20, List.of("Whiteboard", "AV"), List.of(t1)));

        // User specified criterias, should come in a request
        List<Criteria<Room>> criteriaList = List.of(
                new FloorCriteria(3),
                new CapacityCriteria(10),
                new AvailabilityCriteria(List.of(t1)));

        List<Room> availableRooms = repository.search(criteriaList);
        System.out.println("Found " + availableRooms.size() + " available rooms.");
    }
}
