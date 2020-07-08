package hotel;

public class InsufficientNumberOfRoomsException extends Exception {
    private final int numberOfRooms;

    public InsufficientNumberOfRoomsException(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    @Override
    public String toString() {
        return "InsufficientNumberOfRoomsException{" +
                "numberOfRooms=" + numberOfRooms +
                '}';
    }
}
