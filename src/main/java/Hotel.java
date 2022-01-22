import java.util.concurrent.ConcurrentHashMap;

public class Hotel {

    private String name;
    private ConcurrentHashMap<Integer,RoomBooking> roomToAvailMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConcurrentHashMap<Integer,RoomBooking> getRoomToAvailMap() {
        return this.roomToAvailMap;
    }

    public void setRoomToAvailMap(ConcurrentHashMap<Integer,RoomBooking> roomToAvailMap) {
        this.roomToAvailMap = roomToAvailMap;
    }
}
