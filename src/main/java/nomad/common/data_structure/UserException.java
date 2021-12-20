package nomad.common.data_structure;

/**
 * Represents a user Exception
 */
public class UserException extends Exception {

    public UserException() {
        super();
    }

    public UserException(String s) {
        super(s);
    }
}