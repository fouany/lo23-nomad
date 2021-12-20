package nomad.common.data_structure;

/**
 * Represents a skip Exception
 */
public class SkipException extends Exception{

    public SkipException() {
        super();
    }

    public SkipException(String s) {
        super(s);
    }
}
