package nomad.common.interfaces.main;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.User;

public interface IhmMainToDataInterface {
  void updateObservable(Session session);
  void updateObservable(Game game);

  void updateGameCreated(Game game);

  void updateAcceptOpponent(Game game);

  void askApproval(Game game);

  void updateRejectOpponent(Game game);

  void updateObservable(User user);
}
