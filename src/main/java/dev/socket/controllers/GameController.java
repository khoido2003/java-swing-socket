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
  private int currentPoint = 0;
  private boolean resultRequested = false;

  public GameController(LobbyController lobbyController, SocketClient socketClient, NewGameView newGameView,
      LobbyView lobbyView, String roomID) {
    this.lobbyController = lobbyController;
    this.socketClient = socketClient;
    this.newGameView = newGameView;
    this.lobbyView = lobbyView;
    this.roomID = roomID;
    this.currentPoint = 0;

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

      this.newGameView.getBtnSent().setEnabled(false);
      this.newGameView.getTxtAnswer().setEnabled(false);
    }

    if (message.startsWith("HIDE_QUESTION:")) {
      this.newGameView.getLblQuestion().setText("");
      ;
      this.newGameView.StartTime(11);

      this.newGameView.getBtnSent().setEnabled(true);
      this.newGameView.getTxtAnswer().setEnabled(true);
    }

    if (message.startsWith("CORRECT:")) {
      String curIndex = message.split(" ")[1];
      this.currentPoint += (Integer.parseInt(curIndex) + 1) * 5;
      this.newGameView.getLblPoint().setText(String.valueOf(this.currentPoint));
      this.newGameView.getBtnSent().setEnabled(false);
      this.newGameView.getTxtAnswer().setEnabled(false);
    }

    if (message.startsWith("WRONG:")) {
      javax.swing.JOptionPane.showMessageDialog(null, "Your answer is wrong!", "Incorrect Answer",
          javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    if (message.startsWith("END:")) {
      // Only send "REQUEST_COMPARE_POINT" once
      if (!resultRequested) {
        this.socketClient.sendMessage("REQUEST_COMPARE_POINT: " + roomID + " " + currentPoint);
        resultRequested = true; // Set the flag to prevent multiple sends
      }
    }

    if (message.startsWith("RESULT:")) {
      String res = message.substring(8).trim();
      String trophies = "";
      switch (res) {
        case "You win!":
          trophies = "You gain 30 trophies.";
          break;
        case "You lose!":
          trophies = "You lost 30 trophies.";
          break;
        case "You win-out":
          trophies = "You gain 30 trophies.";
          this.newGameView.setVisible(false);
          this.lobbyView.setVisible(true);
          break;

        default:
          break;
      }

      javax.swing.JOptionPane.showMessageDialog(null,
          "Your final result is: " + this.currentPoint + "\n" + res + "\n" + trophies, res,
          javax.swing.JOptionPane.INFORMATION_MESSAGE);
      resultRequested = false; // Reset flag after result is received
    }
  }

  public void leaveRoom() {
    this.newGameView.setVisible(false);
    this.lobbyView.setVisible(true);
    this.socketClient.sendMessage("REMOVE_ROOM: " + roomID);
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
