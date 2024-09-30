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
  private User user;
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

      System.out.println(response);
      if (response.length() > 2) {

        userList = User.dejsonlizeArray(response);
        for (User user : userList) {
          System.out.println(user.getUsername());
        }
        lobbyView.addFriendToList(userList);
      }

    }

    if (message.startsWith("UPDATE_FRIEND_ONLINE:")) {
      String response = message.substring(23);
      System.out.println("New user: " + response);

      lobbyView.updateFriendList(User.dejsonlizeObject(response));

    }
  }

  public void sendRequestFriendList() {
    ;
    JwtToken token = JwtUtils.decodeToken(this.token);

    // Store the userID
    String clientID = token.getUserId();
    socketClient.sendMessage("REQUEST_FRIEND_LIST: " + clientID);
  }

}
