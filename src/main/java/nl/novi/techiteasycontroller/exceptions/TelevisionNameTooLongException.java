package nl.novi.techiteasycontroller.exceptions;

public class TelevisionNameTooLongException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TelevisionNameTooLongException(){
        super();
    }

    public TelevisionNameTooLongException(String message){
        super(message);
    }
}
