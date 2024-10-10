package dev.socket.controllers;

import dev.socket.interfaces.SocketObserver;
import dev.socket.network.SocketClient;
import dev.socket.views.LobbyView;
import dev.socket.views.NewGameView;

public class GameController implements SocketObserver {

  private LobbyController lobbyController;
  private SocketClient socketClient;
  private NewGameView newGameView;
  private LobbyView lobbyView;

  public GameController(LobbyController lobbyController, SocketClient socketClient, NewGameView newGameView,
      LobbyView lobbyView) {
    this.lobbyController = lobbyController;
    this.socketClient = socketClient;
    this.newGameView = newGameView;
    this.lobbyView = lobbyView;

    setUserName(this.lobbyController.getUsername());
  }

  @Override
  public void onMessageReceived(String message) {
    if (message.startsWith("")) {
    }
  }

  public void leaveRoom() {
    this.newGameView.setVisible(false);
    this.lobbyView.setVisible(true);
  }

  public void setUserName(String userName) {
    this.newGameView.getjLabel2().setText(userName);
  }

}
