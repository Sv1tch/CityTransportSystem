package CityTransportSystem.transport.Bus;



import CityTransportSystem.employee.Driver;
import CityTransportSystem.transport.Exceptions.*;
import CityTransportSystem.transport.Transport;
import CityTransportSystem.transport.enums.*;
import CityTransportSystem.transport.route.Route;
import CityTransportSystem.transport.route.Stop;

public class Bus extends Transport {
    private final FuelType fuelType;

    private double fuelCapacity;
    private double currentFuelLevel;

    private double fuelConsumptionPerKm;

    public Bus(String id,
               String model,
               String brand,
               int maxSpeed,
               int capacity,
               int productionYear,
               FuelType fuelType,
               double fuelCapacity,
               double currentFuelLevel,
               double fuelConsumptionPerKm,
               Route assignedRoute,
               Driver assignedDriver){
        super(id, model, brand, maxSpeed, productionYear, assignedRoute, assignedDriver);
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.fuelCapacity = fuelCapacity;
        this.currentFuelLevel = currentFuelLevel;
        this.fuelConsumptionPerKm = fuelConsumptionPerKm;
    }

    @Override
    public void move(){
        if(transportStatus == TransportStatus.BROKEN){
            throw new TransportLogicException("Cannot move broken bus.");
        }

        if(!isEngineOn){
            throw new TransportLogicException("Engine is off.");
        }

        if(isDoorOpen){
            throw new TransportLogicException("Cannot move while doors are open.");
        }

        if(assignedRoute == null){
            throw new TransportLogicException("Bus has no assigned route.");
        }

        if(currentStopIndex >= assignedRoute.getStopsCount() - 1){
            throw new TransportLogicException("Bus already reached final stop.");
        }

        double fuelNeeded = calculateFuelNeeded(currentStopIndex);

        if(currentFuelLevel < fuelNeeded){
            throw new TransportLogicException("Not enough fuel.");
        }

        currentFuelLevel -= fuelNeeded;

        currentStopIndex++;

        transportStatus = TransportStatus.ON_ROUTE;
    }

    public double calculateFuelNeeded(int current){
        Stop nextStop = assignedRoute.getStops().get(current + 1);

        double distance = assignedRoute.getStop((current)).distanceTo(nextStop);
        return fuelConsumptionPerKm * distance;
    }

    @Override
    public void stop(){
        transportStatus = TransportStatus.ON_STOP;
    }

    public void refuel(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Amount of fuel cannot be <= 0.");
        }

        if(currentFuelLevel + amount > fuelCapacity){
            throw new RefuelException("To big amount of fuel.");
        }

        currentFuelLevel += amount;
    }

    public void startEngine(){
        if(!(transportStatus.equals(TransportStatus.BROKEN)) && currentFuelLevel > 0){
            isEngineOn = true;
        }
    }

    public void breakdown(){
        transportStatus = TransportStatus.BROKEN;
        isEngineOn = false;
    }

    public void repair(){
        transportStatus = TransportStatus.IN_SERVICE;
    }

    //Getters

    public FuelType getFuelType(){
        return fuelType;
    }
    public double getFuelCapacity(){
        return fuelCapacity;
    }
    public double getCurrentFuelLevelO(){
        return currentFuelLevel;
    }
    public double getFuelConsumptionPerKm(){
        return fuelConsumptionPerKm;
    }

}
