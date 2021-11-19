package nomad.data.server;

import nomad.common.interfaces.data.DataToIhmMainServerInterface;

/**
 * Concretization of the Ihm Main interface.
 */
public class DataToIhmMainConcrete implements DataToIhmMainServerInterface {

    private DataServerController dataServerController;

    public DataToIhmMainConcrete(DataServerController dataServerController){
        this.dataServerController = dataServerController;
    }
}
