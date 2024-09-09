package dev.socket;

import dev.socket.controllers.AuthController;
import dev.socket.services.AuthService;
import dev.socket.views.LobbyView;
import dev.socket.views.SignInView;

public class AppIndex {
  private SignInView signInView;
  private AuthService authService;
  private LobbyView lobbyView;

  public AppIndex() {
    // Initialize models
    authService = new AuthService();

    // Initialize views
    signInView = new SignInView();
    lobbyView = new LobbyView();

    // Initialize controllers
    initAuthController();
  }

  private void initAuthController() {
    new AuthController(signInView, authService, this); // Passing `this` for navigation
    signInView.setVisible(true);
  }

  // Method to move to lobby
  public void showLobby() {
    signInView.dispose(); // Close sign-in view
    lobbyView.setVisible(true); // Show lobby view
    setupLobbyActions();
  }

  // Set up actions for the Lobby
  private void setupLobbyActions() {
    lobbyView.setJoinRoomAction(event -> {
      // Handle joining room logic here, including WebSocket integration
      System.out.println("Joining room...");
    });
  }
}
