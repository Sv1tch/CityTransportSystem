package CityTransportSystem.transport.Tram;

import CityTransportSystem.employee.Driver;
import CityTransportSystem.transport.route.Route;
import CityTransportSystem.transport.Transport;
import CityTransportSystem.transport.enums.PantographStatus;

public class Tram extends Transport {
    private double electricityConsumption;
    private PantographStatus pantographStatus;
    private int voltageLevel;

    private double railGauge;
    private int currentTrackNumber;

    private int sectionCount;

    private boolean isConnectedToGrid;

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
        super(id, model, brand, maxSpeed, capacity, productionYear, assignedRoute, assignedDriver);
        this.electricityConsumption = electricityConsumption;
        this.pantographStatus = pantographStatus;
        this.railGauge = railGauge;
        this.currentTrackNumber = currentTrackNumber;
        this.sectionCount = sectionCount;

        this.isConnectedToGrid = false;
    }

    public void connectToPowerGrid(){
        if(!isConnectedToGrid){
            isConnectedToGrid = true;
        }
    }

    public void disconnectPowerGrid(){
        if(isConnectedToGrid){
            isConnectedToGrid = false;
        }
    }

    public void emergencyBrake(){
        isConnectedToGrid = false;
        isEngineOn = false;
    }

    public void switchTrack(int numberOfTrack){
        currentTrackNumber = numberOfTrack;
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
