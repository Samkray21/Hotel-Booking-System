import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingManagerImplTest {

    @Test
    public void isRoomAvailableTrue() {
        Hotel hotel = new Hotel();

        ConcurrentHashMap<Integer,RoomBooking> roomToAvailMap = new ConcurrentHashMap<>();
        roomToAvailMap.put(101, new RoomBooking());
        roomToAvailMap.put(102, new RoomBooking());
        roomToAvailMap.put(201, new RoomBooking());
        roomToAvailMap.put(203, new RoomBooking());

        hotel.setRoomToAvailMap(roomToAvailMap);

        BookingManager bm = new BookingManagerImpl(hotel);
        LocalDate today = LocalDate.parse("2012-07-21");
        boolean actual = bm.isRoomAvailable(101, today);

       assertEquals(true, actual);

    }

    @Test
    public void isRoomAvailableThrowException() {
        Hotel hotel = new Hotel();

        ConcurrentHashMap<Integer,RoomBooking> roomToAvailMap = new ConcurrentHashMap<>();
        roomToAvailMap.put(101, new RoomBooking());
        roomToAvailMap.put(102, new RoomBooking());
        roomToAvailMap.put(201, new RoomBooking());
        roomToAvailMap.put(203, new RoomBooking());

        hotel.setRoomToAvailMap(roomToAvailMap);

        BookingManager bm = new BookingManagerImpl(hotel);
        LocalDate today = LocalDate.parse("2012-07-21");
        bm.addBooking("Smith", 101, today);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> bm.addBooking("Jones", 101, today));
        assertEquals("Date not available for this room", exception.getMessage());
    }

    @Test
    public void getAvailableRoomsTest() {
        Hotel hotel = new Hotel();

        ConcurrentHashMap<Integer,RoomBooking> roomToAvailMap = new ConcurrentHashMap<>();
        roomToAvailMap.put(101, new RoomBooking());
        roomToAvailMap.put(102, new RoomBooking());
        roomToAvailMap.put(201, new RoomBooking());
        roomToAvailMap.put(203, new RoomBooking());

        hotel.setRoomToAvailMap(roomToAvailMap);

        BookingManager bm = new BookingManagerImpl(hotel);
        LocalDate today = LocalDate.parse("2012-07-21");
        bm.addBooking("Smith", 101, today);

        Iterable<Integer> actualList =  bm.getAvailableRooms(today);

        List<Integer> expectedList = new ArrayList<Integer>();
        expectedList.add(102);
        expectedList.add(201);
        expectedList.add(203);

        assertEquals(expectedList, actualList);
    }


    @Test
    public void isRoomAvailableFalse() {
        Hotel hotel = new Hotel();

        ConcurrentHashMap<Integer,RoomBooking> roomToAvailMap = new ConcurrentHashMap<>();
        roomToAvailMap.put(101, new RoomBooking());
        roomToAvailMap.put(102, new RoomBooking());
        roomToAvailMap.put(201, new RoomBooking());
        roomToAvailMap.put(203, new RoomBooking());

        hotel.setRoomToAvailMap(roomToAvailMap);

        BookingManager bm = new BookingManagerImpl(hotel);
        LocalDate today = LocalDate.parse("2012-07-21");
        bm.addBooking("Smith", 101, today);
        boolean actual = bm.isRoomAvailable(101, today);

        assertEquals(false, actual);

    }


}
