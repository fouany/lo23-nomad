package nomad.common.ihm;

/**
 * Parent class of any controller that deals of a fxml view
 */
public abstract class IhmControllerComponent {
    /**
     * Screen controller of the current module (main or game)
     */
    protected IhmScreenController screenControl;

    /**
     * Constructor that link the screen controller to the component controller
     * @param screen
     */
    protected IhmControllerComponent(IhmScreenController screen){
        screenControl = screen;
    }
}
