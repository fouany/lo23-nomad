package nomad.data.server;

import nomad.common.interfaces.data.DataToIhmGameServerInterface;

/**
 * Concretization of the Ihm Game interface.
 */
public class DataToIhmGameConcrete implements DataToIhmGameServerInterface {

    private DataServerController dataServerController;

    public DataToIhmGameConcrete(DataServerController dataServerController){
        this.dataServerController = dataServerController;
    }
}
