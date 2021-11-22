package nomad.data.server;

import nomad.common.interfaces.data.DataToIhmMainServeurInterface;

/**
 * Concretization of the Ihm Main interface.
 */
public class DataToIhmMainConcrete implements DataToIhmMainServeurInterface {

    private DataServerController dataServerController;

    public DataToIhmMainConcrete(DataServerController dataServerController){
        this.dataServerController = dataServerController;
    }
}
