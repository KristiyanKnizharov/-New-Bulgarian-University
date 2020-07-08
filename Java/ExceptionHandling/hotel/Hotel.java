package hotel;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kostadinova
 */
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
