package nomad.common.interfaces.game;

import nomad.common.data_structure.Game;

/**
 * Expose methods of the {@code nomad.game} package to {@code nomad.data}.
 *
 * <br>Expose the {@code updateObservable} methods to acknowledge changes on observed objects.
 */
public interface IhmGameToDataInterface {

    /**
     * Update {@code Game} observed object when changed.
     * @param game The {@code Game} object to be updated by data module.
     */
    void updateObservable(Game game);
}
