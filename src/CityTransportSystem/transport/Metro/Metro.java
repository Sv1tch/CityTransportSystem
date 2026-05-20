package CityTransportSystem.transport.Metro;

import CityTransportSystem.employee.Driver;
import CityTransportSystem.transport.Transport;
import CityTransportSystem.transport.route.Route;

import java.util.ArrayList;
import java.util.List;

public class Metro extends Transport {
    private Route metroLine;
    private int currentStationIndex;

    private List<MetroCarriage> carriages;
    private int capacity;

    private boolean autopilotEnable;

    private boolean emergencySystemEnable;
    private boolean CCTVEnable;

    private double tunnelDepth;

    public Metro(
            String id,
            String model,
            String brand,
            int maxSpeed,
            int productionYear,
            Route metroLine,
            Driver assignedDriver,
            double tunnelDepth
    ){
        super(id, model, brand, maxSpeed, productionYear, metroLine, assignedDriver);
        this.carriages = new ArrayList<>();
        this.capacity = countOverallCapacity();

        this.tunnelDepth = tunnelDepth;
    }

    public int countOverallCapacity(){
        int cap = 0;
        for(MetroCarriage c : carriages){
            cap += c.getMaxCapacity();
        }

        return cap;
    }

    @Override
    public void move(){

    }

    @Override
    public void stop(){

    }
}
