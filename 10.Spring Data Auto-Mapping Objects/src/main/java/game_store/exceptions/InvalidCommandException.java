package game_store.exceptions;

public class InvalidCommandException extends RuntimeException {

    /*
    *serialVersionUID*
    is used during deserialization to verify that the
    sender and receiver of a serialized object
    have loaded classes for that object that are compatible
    with respect to serialization.
     */
    private static final long serialVersionUID = 5876844115664006274L;

    public InvalidCommandException(final String message) {
        super(message);
    }
}
