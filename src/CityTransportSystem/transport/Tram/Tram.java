package CityTransportSystem.transport.Tram;

import CityTransportSystem.employee.Driver;
import CityTransportSystem.transport.Exceptions.TransportLogicException;
import CityTransportSystem.transport.enums.TransportStatus;
import CityTransportSystem.transport.route.Route;
import CityTransportSystem.transport.Transport;
import CityTransportSystem.transport.enums.PantographStatus;

public class Tram extends Transport {
    private double electricityConsumption;
    private PantographStatus pantographStatus;
    private final int voltageLevel;

    private final double railGauge;
    private int currentTrackNumber;

    private int sectionCount;


    public Tram(
            String id,
            String model,
            String brand,
            int maxSpeed,
            int capacity,
            int productionYear,
            Route assignedRoute,
            Driver assignedDriver,
            double electricityConsumption,
            PantographStatus pantographStatus,
            int voltageLevel,
            double railGauge,
            int currentTrackNumber,
            int sectionCount
    ){
        super(id, model, brand, maxSpeed, productionYear, assignedRoute, assignedDriver);
        this.capacity = capacity;
        this.electricityConsumption = electricityConsumption;
        this.pantographStatus = pantographStatus;
        this.railGauge = railGauge;
        this.voltageLevel = voltageLevel;
        this.currentTrackNumber = currentTrackNumber;
        this.sectionCount = sectionCount;
    }

    // Move logic

    @Override
    public void move(){
        if(transportStatus == TransportStatus.BROKEN){
            throw new TransportLogicException("Cannot move broken tram.");
        }

        if(!isEngineOn){
            throw new TransportLogicException("Engine is off.");
        }

        if(isDoorOpen){
            throw new TransportLogicException("Cannot move while doors are opened.");
        }

        if(!pantographStatus.equals(PantographStatus.IN_USE)){
            throw new TransportLogicException("Tram is not connected to power grid.");
        }

        if(assignedRoute == null){
            throw new TransportLogicException("Tram has no assigned route.");
        }

        if(currentStopIndex >= assignedRoute.getStopsCount() - 1){
            throw new TransportLogicException("Tram already reached final stop.");
        }

        if(assignedDriver == null){
            throw new TransportLogicException("No driver assigned.");
        }

        currentStopIndex++;

        transportStatus = TransportStatus.ON_ROUTE;
    }

    @Override
    public void stop(){
        transportStatus = TransportStatus.ON_STOP;
    }

    //Grid logic

    public void connectToPowerGrid(){
        if(!(pantographStatus.equals(PantographStatus.IN_USE))){
            pantographStatus = PantographStatus.IN_USE;
        }
    }

    public void disconnectPowerGrid(){
        if(pantographStatus.equals(PantographStatus.IN_USE)){
            pantographStatus = PantographStatus.DISCONNECTED;
        }
    }

    //Engine logic

    public void startEngine(){
        if(transportStatus == TransportStatus.BROKEN){
            throw new TransportLogicException("Cannot start broken tram.");
        }

        if(!(pantographStatus.equals(PantographStatus.IN_USE))){
            throw new TransportLogicException("Tram is not connected to power grid.");
        }

        isEngineOn = true;
    }

    public void stopEngine(){
        isEngineOn = false;
    }

    //Track logic
    public void switchTrack(int newNumberOfTrack){
        if(newNumberOfTrack <= 0){
            throw new IllegalArgumentException("Track number cannot be <= 0.");
        }
        currentTrackNumber = newNumberOfTrack;
    }

    //Emergency logic
    public void emergencyBrake(){
        isEngineOn = false;

        transportStatus = TransportStatus.ON_STOP;
    }

    public void breakdown(){
        transportStatus = TransportStatus.BROKEN;
    }

    public void repair(){
        transportStatus = TransportStatus.IN_SERVICE;
    }

    //Getters
    public double getElectricityConsumption(){
        return electricityConsumption;
    }
    public PantographStatus getPantographStatus(){
        return pantographStatus;
    }
    public int getVoltageLevel(){
        return voltageLevel;
    }
    public double getRailGauge(){
        return railGauge;
    }
    public int getCurrentTrackNumber(){
        return currentTrackNumber;
    }
    public int getSectionCount(){
        return sectionCount;
    }
}
