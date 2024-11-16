package nl.novi.techiteasycontroller.exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(){
        super("Television id not found");
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

}
