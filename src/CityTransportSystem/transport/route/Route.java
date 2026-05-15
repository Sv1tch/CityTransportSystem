package CityTransportSystem.transport.route;

import java.util.Collections;
import java.util.List;

public class Route {
    private String routeId;
    private String routeName;

    private List<Stop> stops;

    private double totalDistance;

    public Route(String routeId, String routeName, List<Stop> stops){
        this.routeId = routeId;
        this.routeName = routeName;
        this.stops = stops;

        this.totalDistance = calculateTotalDistance();
    }

    public Stop getStop(int index){
        if(index < 0 || index >= stops.size()){
            return null;
        }

        return stops.get(index);
    }

    public int getStopsCount(){
        return stops.size();
    }

    public List<Stop> getStops(){
        return Collections.unmodifiableList(stops);
    }

    public double getTotalDistance(){
        return totalDistance;
    }

    public double calculateTotalDistance(){
        double distance = 0;

        for(int i = 0; i < stops.size() - 1; i++){
            Stop current = stops.get(i);
            Stop next = stops.get(i + 1);

            distance += current.distanceTo(next);
        }

        return distance;
    }

    public String getRouteId(){
        return routeId;
    }
    public String getName(){
        return routeName;
    }
}
