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
  private String roomID;

  public GameController(LobbyController lobbyController, SocketClient socketClient, NewGameView newGameView,
      LobbyView lobbyView, String roomID) {
    this.lobbyController = lobbyController;
    this.socketClient = socketClient;
    this.newGameView = newGameView;
    this.lobbyView = lobbyView;
    this.roomID = roomID;

    setUserName(this.lobbyController.getUsername());
    requestStartGame();
  }

  @Override
  public void onMessageReceived(String message) {
    if (message.startsWith("QUESTION:")) {

      String question = message.split(" ")[1];
      String index = message.split(" ")[2];

      this.newGameView.getLblQuestion().setText(question);
      ;
      this.newGameView.getLblNumberQuestion().setText(index);
      this.newGameView.StartTime(6);
    }

    if (message.startsWith("HIDE_QUESTION:")) {
      this.newGameView.getLblQuestion().setText("");
      ;
      this.newGameView.StartTime(11);
    }
  }

  public void leaveRoom() {
    this.newGameView.setVisible(false);
    this.lobbyView.setVisible(true);
  }

  public void setUserName(String userName) {
    this.newGameView.getjLabel2().setText(userName);
  }

  public void requestStartGame() {
    this.socketClient.sendMessage("START_GAME: " + roomID);
  }

  public void answerQuestion(String answer) {

    this.socketClient.sendMessage("ANSWER_QUESTION: " + roomID + " " + answer);
  }

}
