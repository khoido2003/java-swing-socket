package dev.socket.controllers;

import dev.socket.interfaces.SocketObserver;
import dev.socket.network.SocketClient;
import dev.socket.views.FriendListView;
import dev.socket.views.LobbyView;

public class FriendListController implements SocketObserver {
  private SocketClient socketClient;
  private String userId;
  private LobbyView lobbyView;
  private FriendListView friendListView;

  public FriendListController(SocketClient socketClient, String userId) {
    this.socketClient = socketClient;
    this.userId = userId;

  }

  public void setFriendListView(FriendListView friendListView) {
    this.friendListView = friendListView;
  }

  public void setLobbyView(LobbyView lobbyView) {
    this.lobbyView = lobbyView;
    requestFriendList();
  }

  @Override
  public void onMessageReceived(String message) {
    if (message.startsWith("RESPONSE_ALL_FRIEND_LIST:")) {
      String response = message.substring(26).trim();
      System.out.println(response);

      friendListView.parseUserData(response);
    }
  }

  public void requestFriendList() {
    socketClient.sendMessage("REQUEST_ALL_FRIEND_LIST: " + userId);
  }

  public void showLobbyView() {
    this.friendListView.setVisible(false);
    this.lobbyView.setVisible(true);
  }

}
