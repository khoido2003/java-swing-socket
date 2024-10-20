package dev.socket.controllers;

import dev.socket.interfaces.SocketObserver;
import dev.socket.network.SocketClient;
import dev.socket.views.LobbyView;
import dev.socket.views.LeaderboardView;

public class LeaderboardController implements SocketObserver {
  private SocketClient socketClient;
  private LobbyView lobbyView;
  private LeaderboardView leaderboardView;

  public LeaderboardController(SocketClient socketClient) {
    this.socketClient = socketClient;

  }

  public void setLobbyView(LobbyView lobbyView) {
    this.lobbyView = lobbyView;
    requestLeaderboardList();
  }

  public void setLeaderboardView(LeaderboardView leaderboardView) {
    this.leaderboardView = leaderboardView;
  }

  @Override
  public void onMessageReceived(String message) {
    if (message.startsWith("RESPONSE_LEADERBOARD_LIST:")) {
      String response = message.substring(27).trim();
      leaderboardView.parseUserData(response);
    }
  }

  public void requestLeaderboardList() {
    socketClient.sendMessage("REQUEST_LEADERBOARD_LIST");
  }

  public void showLobbyView() {
    this.leaderboardView.setVisible(false);
    this.lobbyView.setVisible(true);
  }

}
