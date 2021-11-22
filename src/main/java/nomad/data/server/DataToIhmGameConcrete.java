package nomad.data.server;

import nomad.common.interfaces.data.DataToIhmGameServeurInterface;

/**
 * Concretization of the Ihm Game interface.
 */
public class DataToIhmGameConcrete implements DataToIhmGameServeurInterface {

    private DataServerController dataServerController;

    public DataToIhmGameConcrete(DataServerController dataServerController){
        this.dataServerController = dataServerController;
    }
}
