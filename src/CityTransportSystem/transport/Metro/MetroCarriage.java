package CityTransportSystem.transport.Metro;

import CityTransportSystem.Passanger.Passenger;
import CityTransportSystem.transport.Exceptions.TransportLogicException;

import java.util.ArrayList;
import java.util.List;

public class MetroCarriage {
    private final int id;
    private final int maxCapacity;
    private List<Passenger> passengers;
    private boolean isDoorOpen;

    public MetroCarriage(int id, int capacity){
        this.id = id;
        this.maxCapacity = capacity;

        this.passengers = new ArrayList<>();
        this.isDoorOpen = false;
    }

    public void boardPassenger(Passenger passenger){
        if(passengers.size() >= maxCapacity){
            throw new TransportLogicException("Cannot board passenger, because of capacity limit.");
        }

        passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
    }

    public void openDoors(){
        isDoorOpen = true;
    }

    public void closeDoors(){
        isDoorOpen = false;
    }

    //Getters
    public int getMaxCapacity(){
        return maxCapacity;
    }

    public int getCapacity(){
        return passengers.size();
    }

    public boolean getIsDoorOpen(){
        return isDoorOpen;
    }
}
