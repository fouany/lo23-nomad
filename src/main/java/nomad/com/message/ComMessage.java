package nomad.com.message;

import java.io.Serializable;

/**
 * Communication Message abstract class
 */
public abstract class ComMessage implements Serializable {
    /**
     * process
     */
    public abstract void process();
}