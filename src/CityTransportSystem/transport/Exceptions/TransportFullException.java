package CityTransportSystem.transport.Exceptions;

public class TransportFullException extends RuntimeException {
    public TransportFullException(String message) {
        super(message);
    }
}
