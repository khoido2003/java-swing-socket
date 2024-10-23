package dev.socket.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dev.socket.interfaces.SocketObserver;
import dev.socket.models.JwtToken;
import dev.socket.models.User;
import dev.socket.network.SocketClient;
import dev.socket.utils.JwtUtils;
import dev.socket.views.FriendListView;
import dev.socket.views.LeaderboardView;
import dev.socket.views.LobbyView;
import dev.socket.views.NewGameView;

public class LobbyController implements SocketObserver {

  private SocketClient socketClient;
  private List<User> userList = new ArrayList<>();

  private String curFriendID;
  private String userID;
  private String username;
  private String token;
  private LobbyView lobbyView;
  private JDialog waitingDialog;

  public LobbyController(SocketClient socketClient) {
    this.socketClient = socketClient;

    // Checking when user is turn off the application
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      onAppShutdown();
    }));

  }

  public void setLobbyView(LobbyView lobbyView) {
    this.lobbyView = lobbyView;
  }

  public String getUsername() {
    return username;
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

  // TEST GAME VIEW
  public void requestCreateNewGameMatch() {
    NewGameView newGameView = new NewGameView();
    this.lobbyView.setVisible(false);
    newGameView.setVisible(true);

    GameController gameController = new GameController(this, socketClient, newGameView, this.lobbyView, "");

    newGameView.setGameController(gameController);
    socketClient.addObserver(gameController);
  }

  private void onAppShutdown() {
    if (socketClient != null && userID != null) {
      socketClient.sendMessage("USER_DISCONNECTED: " + userID);
    }

  }

  @Override
  public void onMessageReceived(String message) {

    if (message.startsWith("FRIEND_OFFLINE")) {
      String userID = message.split(" ")[1];

      this.lobbyView.removeUserFromList(userID);
    }

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

    if (message.startsWith("RESPONSE_CREATE_NEW_ROOM:")) {
      String roomID = message.split(" ")[1];

      System.out.println(roomID);

      this.inviteFriendToGameMatch(roomID);
    }

    if (message.startsWith("RESPONSE_INVITE_TO_JOIN_MATCH:")) {
      String response = message.substring(31);
      System.out.println("Invite to join match from " + response);

      String roomID = message.split(" ")[1];

      NewGameView newGameView = new NewGameView();

      int userResponse = JOptionPane.showConfirmDialog(newGameView, "Do you want to accept the battle?",
          "Match Invitation", JOptionPane.YES_NO_CANCEL_OPTION);

      if (userResponse == JOptionPane.YES_OPTION) {
        // Notify the socket server that the invite was accepted
        socketClient.sendMessage("ACCEPT_MATCH_INVITE: " + userID + " " + response);

        GameController gameController = new GameController(this, socketClient, newGameView, this.lobbyView, roomID);
        newGameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newGameView.setLocationRelativeTo(null);

        newGameView.setGameController(gameController);
        socketClient.addObserver(gameController);

        // Hide the lobby view and show the new game view
        this.lobbyView.setVisible(false);
        newGameView.setVisible(true);
      } else if (userResponse == JOptionPane.NO_OPTION) {
        // Notify the socket server that the invite was declined
        socketClient.sendMessage("DECLINE_MATCH_INVITE: " + userID + " " + response);
      } else {
        socketClient.sendMessage("DECLINE_MATCH_INVITE: " + userID + " " + response);
      }
    }

    if (message.startsWith("RESPONSE_ACCEPT_MATCH_INVITE: ")) {

      String roomID = message.split(" ")[1];
      System.out.println(roomID);

      NewGameView newGameView = new NewGameView();

      this.waitingDialog.setVisible(false);
      this.lobbyView.setVisible(false);
      newGameView.setVisible(true);

      GameController gameController = new GameController(this, socketClient, newGameView, this.lobbyView, roomID);

      newGameView.setGameController(gameController);
      newGameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      newGameView.setLocationRelativeTo(null);
      socketClient.addObserver(gameController);
    }

    if (message.startsWith("RESPONSE_DECLINE_MATCH_INVITE: ")) {
      this.waitingDialog.setVisible(false);
    }

    if (message.startsWith("RESPONSE_JOIN_NEW_MATCH:")) {

      String roomID = message.split(" ")[1];
      System.out.println("Invite to join match from " + roomID);

      NewGameView newGameView = new NewGameView();

      GameController gameController = new GameController(this, socketClient, newGameView, this.lobbyView, roomID);
      newGameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      newGameView.setLocationRelativeTo(null);

      newGameView.setGameController(gameController);
      socketClient.addObserver(gameController);

      // Hide the lobby view and show the new game view
      this.lobbyView.setVisible(false);
      this.waitingDialog.setVisible(false);
      newGameView.setVisible(true);
    }

  }

  ////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////
  /// /////////////////////////////////////////////////////////

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

  public void requestCreateNewGameMatch(String friendID) {
    // Send the request to the server
    socketClient.sendMessage("REQUEST_CREATE_NEW_ROOM: " + this.userID);

    this.curFriendID = friendID;

    // Show a non-blocking waiting dialog (JDialog)
    waitingDialog = new JDialog();
    waitingDialog.setTitle("Waiting for another player...");
    waitingDialog.setSize(300, 150);
    waitingDialog.setLocationRelativeTo(null);
    waitingDialog.setModal(false);
    waitingDialog.setVisible(true);

    // You can add other UI elements inside the dialog if needed (e.g., loading
    // spinner)
  }

  public void inviteFriendToGameMatch(String roomID) {
    socketClient.sendMessage("REQUEST_INVITE_FRIEND_TO_MATCH: " + this.curFriendID + " " + roomID);
  }

  public void requestJoinNewMatch() {
    socketClient.sendMessage("REQUEST_JOIN_NEW_MATCH");

    waitingDialog = new JDialog();
    waitingDialog.setTitle("Tìm trận mới....");
    waitingDialog.setSize(300, 150);
    waitingDialog.setLocationRelativeTo(null);
    waitingDialog.setModal(false);
    waitingDialog.setVisible(true);
  }

  public void showLeaderboardView() {
    LeaderboardController leaderboardController = new LeaderboardController(socketClient);
    socketClient.addObserver(leaderboardController);

    LeaderboardView leaderboardView = new LeaderboardView(leaderboardController);

    leaderboardController.setLeaderboardView(leaderboardView);
    leaderboardController.setLobbyView(lobbyView);

    leaderboardView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    leaderboardView.setVisible(true);
    lobbyView.setVisible(false);
    leaderboardView.setLocationRelativeTo(null);
  }

  public void showFriendListView() {
    FriendListController friendListController = new FriendListController(socketClient, userID);

    socketClient.addObserver(friendListController);

    FriendListView friendListView = new FriendListView(friendListController);

    friendListController.setFriendListView(friendListView);
    friendListController.setLobbyView(lobbyView);

    friendListView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    friendListView.setVisible(true);
    lobbyView.setVisible(false);
    friendListView.setLocationRelativeTo(null);
  }
}
