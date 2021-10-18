package nomad.common.data_structure;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class Move implements Serializable {

    private Timestamp s;

    public Timestamp getS() {
        return s;
    }

    public void setS(Timestamp s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Move{" +
                "s=" + s +
                '}';
    }
}
