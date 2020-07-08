package hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int numberOfRooms;
    private List<Integer> rooms;

    public Hotel(int numberOfRooms) {

        this.numberOfRooms = numberOfRooms;
        this.rooms = new ArrayList<>();
    }

    public void rentRoom(int roomNumber) throws RoomIsRentedException, InsufficientNumberOfRoomsException {

        if (rooms.size() >= numberOfRooms) {
            throw new InsufficientNumberOfRoomsException(numberOfRooms);
        }
        else {
            if (!rooms.contains(roomNumber)) {
                rooms.add(roomNumber);
            }
            else {
                throw new RoomIsRentedException(roomNumber);
            }
        }
    }

    @Override
    public String toString() {
        return "hotel.Hotel{" +
                "numberOfRooms=" + numberOfRooms +
                ", rooms=" + rooms +
                '}';
    }
}
