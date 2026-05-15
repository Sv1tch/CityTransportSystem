package CityTransportSystem.transport.route;

public class Stop {
    private String name;
    private double x;
    private double y;

    public Stop(String name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Stop other){
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

}
