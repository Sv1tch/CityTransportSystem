package CityTransportSystem.transport;

import CityTransportSystem.Passanger.Passenger;
import CityTransportSystem.employee.Driver;
import CityTransportSystem.transport.Exceptions.*;
import CityTransportSystem.transport.route.Route;
import CityTransportSystem.transport.enums.TransportStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Transport {
    protected String id;
    protected String model;
    protected String brand;
    protected int maxSpeed;
    protected int capacity;
    protected int productionYear;

    protected Route assignedRoute;
    protected int currentStopIndex;

    protected TransportStatus transportStatus;
    protected boolean isAirConditioningOn;
    protected boolean isDoorOpen;
    protected boolean isEngineOn;

    protected boolean isBroken;
    protected Driver assignedDriver;

    protected List<Passenger> passengers;

    public Transport(
            String id,
            String model,
            String brand,
            int maxSpeed,
            int capacity,
            int productionYear,
            Route assignedRoute,
            Driver assignedDriver
    ){
        this.id = id;
        this.model = model;
        this.brand = brand;

        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
        this.productionYear = productionYear;

        this.assignedRoute = assignedRoute;
        this.assignedDriver = assignedDriver;

        this.currentStopIndex = 0;

        this.transportStatus = TransportStatus.IN_SERVICE;

        this.isAirConditioningOn = false;
        this.isDoorOpen = false;
        this.isEngineOn = false;

        this.passengers = new ArrayList<>();
    }

    public abstract void move();

    public abstract void stop();

    //Passanger Logic
    public void boardPassanger(Passenger passenger){
        if(!isDoorOpen){
            throw new TransportLogicException("Cannot board passenger while doors are closed.");
        }

        if(passengers.size() >= capacity){
            throw new TransportFullException("Transport is full.");
        }
    }

    public void removePassenger(Passenger passenger){
        if(!isDoorOpen){
            throw new TransportLogicException("Cannot remove passenger while doors are closed.");
        }

        passengers.remove(passenger);
    }

    //Door logic

    public void openDoors(){
        if(transportStatus == TransportStatus.ON_ROUTE){
            throw new TransportLogicException("Cannot open doors while transport is moving.");
        }

        isDoorOpen = true;
    }

    public void closeDoors(){
        isDoorOpen = false;
    }

    //Air Conditioning
    public void enableAirConditioning(){
        isAirConditioningOn = true;
    }

    public void disableAirConditioning(){
        isAirConditioningOn = false;
    }

    //Route Logic

    public void changeRoute(Route newRoute){
        if(newRoute == null){
            throw new IllegalRouteException("Route cannot be null.");
        }

        this.assignedRoute = newRoute;
        this.currentStopIndex = 0;
    }


    //Getters
    public String getId(){
        return id;
    }
    public String getModel(){
        return model;
    }
    public String getBrand(){
        return brand;
    }
    public int getMaxSpeed(){
        return maxSpeed;
    }
    public int getCapacity(){
        return capacity;
    }
    public int getProductionYear(){
        return productionYear;
    }

    public Route getAssignedRoute(){
        return assignedRoute;
    }
    public int getCurrentStopIndex(){
        return currentStopIndex;
    }
    public TransportStatus getTransportStatus(){
        return transportStatus;
    }
    public boolean isAirConditioningOn(){
        return isAirConditioningOn;
    }
    public boolean isDoorOpen(){
        return isDoorOpen;
    }
    public boolean isEngineOn(){
        return isEngineOn;
    }
    public boolean isBroken(){
        return isBroken;
    }
    public Driver getAssignedDriver(){
        return assignedDriver;
    }
    public List<Passenger> getPassengers(){
        return Collections.unmodifiableList(passengers);
    }

}
