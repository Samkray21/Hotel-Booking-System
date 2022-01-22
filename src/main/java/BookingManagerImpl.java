import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BookingManagerImpl implements BookingManager {

    private Hotel hotel;

    public BookingManagerImpl(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isRoomAvailable(Integer room, LocalDate date) {
        Set<LocalDate> roomsBookedSet = hotel.getRoomToAvailMap().get(room).getDatesBooked();
        if(roomsBookedSet==null) return true;

        return !roomsBookedSet.contains(date);
    }

    public void addBooking(String guest, Integer room, LocalDate date) {
        if(!isRoomAvailable(room, date)) {
            throw new IllegalArgumentException("Date not available for this room");
        }

        RoomBooking rb = new RoomBooking();

        if(rb.getNameToDatesBookedMap()==null) {
            ConcurrentHashMap<String,Set<LocalDate>> map = new ConcurrentHashMap<>();
            rb.setNameToDatesBookedMap(map);
        }

        Set<LocalDate> dateSet = rb.getNameToDatesBookedMap().getOrDefault(guest, ConcurrentHashMap.newKeySet());
        dateSet.add(date);
        rb.setRoom(room);
        rb.getNameToDatesBookedMap().put(guest, dateSet);
        rb.setDatesBooked(dateSet);
        this.hotel.getRoomToAvailMap().put(room, rb);
    }

    public Iterable<Integer> getAvailableRooms(LocalDate date) {
        List<Integer> listOfAvailRooms = new ArrayList<>();
        ConcurrentHashMap<Integer,RoomBooking> roomToAvailMap = hotel.getRoomToAvailMap();

        for(Map.Entry<Integer,RoomBooking> entry:roomToAvailMap.entrySet()) {
            if(entry.getValue().getDatesBooked()==null || !entry.getValue().getDatesBooked().contains(date)) {
                listOfAvailRooms.add(entry.getKey());
            }
        }

        return listOfAvailRooms;
    }
}
