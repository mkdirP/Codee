package lab.Classes;

public class ValueTooBigException extends RuntimeException {
    /**
     * constructor
     * @param message
     */
    public ValueTooBigException(String message) {
        super(message);
    }
}
