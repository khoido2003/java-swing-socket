package dev.socket.controllers;

import java.util.ArrayList;
import java.util.List;

import dev.socket.interfaces.SocketObserver;
import dev.socket.models.JwtToken;
import dev.socket.models.User;
// import dev.socket.models.User;
import dev.socket.network.SocketClient;
import dev.socket.utils.JwtUtils;
import dev.socket.views.LobbyView;

public class LobbyController implements SocketObserver {

  private SocketClient socketClient;
  private List<User> userList = new ArrayList<>();

  private String userID;
  private String username;
  private String token;
  private LobbyView lobbyView;

  public LobbyController(SocketClient socketClient) {
    this.socketClient = socketClient;
  }

  public void setLobbyView(LobbyView lobbyView) {
    this.lobbyView = lobbyView;
  }

  public void setToken(String token) {
    this.token = token;
    JwtToken jwtToken = JwtUtils.decodeToken(token);

    // Store the userID
    this.userID = jwtToken.getUserId();
    this.username = jwtToken.getUsername();

    lobbyView.setUserId(userID);
    lobbyView.setUserName(username);
    lobbyView.updateUserInfo();
  }

  @Override
  public void onMessageReceived(String message) {
    if (message.startsWith("RESPONSE_FRIEND_LIST:")) {
      // Find the index of the first bracket '['
      int startIndex = message.indexOf("[");

      // Find the index of the last bracket ']' (if needed)
      int endIndex = message.lastIndexOf("]") + 1; // to include the closing bracket

      // Extract the substring that contains the JSON array
      String response = message.substring(startIndex, endIndex);
      response.replace("\"", "");

      if (response.length() > 2) {

        userList = User.dejsonlizeArray(response);
        lobbyView.addFriendToList(userList);
      }

    }

    if (message.startsWith("UPDATE_FRIEND_ONLINE:")) {
      String response = message.substring(23);
      lobbyView.updateFriendList(User.dejsonlizeObject(response));
    }

    if (message.startsWith("RESPONSE_FIND_USER_LIST:")) {
      String response = message.substring(25);
      response.trim();

      System.out.println(response);
      List<User> userList = User.dejsonlizeArray(response);
      lobbyView.addUserToList(userList);
    }

    if (message.startsWith("NEW_FRIEND_REQUEST:")) {
      String response = message.substring(20);
      System.out.println("New friend request from " + response);

      User user = User.dejsonlizeObject(response);
      this.lobbyView.addFriendRequestToPendingList(user);
    }

    if (message.startsWith("RESPONSE_PENDING_FRIEND_REQUEST_LIST:")) {
      String response = message.substring(40);

      if (response.length() > 2) {

        List<User> userList = User.dejsonlizeArray(response);
        this.lobbyView.addPendingFriendRequestList(userList);
      }
    }

    if (message.startsWith("RESPONSE_ACCEPT_FRIEND_REQUEST:")) {
      String response = message.substring(31);
      System.out.println("Friend request accepted by " + response);

      User user = User.dejsonlizeObject(response);
      this.lobbyView.updateFriendList(user);
    }
  }

  public void sendRequestFriendList() {
    JwtToken token = JwtUtils.decodeToken(this.token);

    // Store the userID
    String clientID = token.getUserId();
    socketClient.sendMessage("REQUEST_FRIEND_LIST: " + clientID);
  }

  public void sendFindUser(String username) {
    socketClient.sendMessage("REQUEST_FIND_USER_LIST: " + username);
  }

  public void createFriendRequest(String userId) {
    socketClient.sendMessage("CREATE_FRIEND_REQUEST: " + userId);
  }

  public void getPendingFriendList() {
    socketClient.sendMessage("GET_PENDING_FRIEND_LIST: " + this.userID);
  }

  public void acceptFriendRequest(String userId, String friendId) {
    socketClient.sendMessage("REQUEST_ACCEPT_FRIEND_REQUEST: " + userId + " " + friendId);
  }
}
