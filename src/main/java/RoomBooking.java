import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RoomBooking {
    private int room;
    private Set<LocalDate> datesBooked;
    private ConcurrentHashMap<String,Set<LocalDate>> nameToDatesBookedMap;

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Set<LocalDate> getDatesBooked() {
        return datesBooked;
    }

    public void setDatesBooked(Set<LocalDate> datesBooked) {
        this.datesBooked = datesBooked;
    }

    public ConcurrentHashMap<String, Set<LocalDate>> getNameToDatesBookedMap() {
        return nameToDatesBookedMap;
    }

    public void setNameToDatesBookedMap(ConcurrentHashMap<String,Set<LocalDate>> nameToDatesBookedMap) {
        this.nameToDatesBookedMap = nameToDatesBookedMap;
    }
}
