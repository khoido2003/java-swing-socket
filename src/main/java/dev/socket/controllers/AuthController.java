package dev.socket.controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import dev.socket.constants.Const;
import dev.socket.models.User;
import dev.socket.network.SocketClient;
import dev.socket.services.AuthService;
import dev.socket.views.AuthView;
import dev.socket.views.LobbyView;
import dev.socket.views.SignInView;
import dev.socket.views.SignUpView;

public class AuthController {
  private AuthService authService = new AuthService();
  private AuthView authView;

  public void signUpAction(User user, SignUpView signUpView) {
    String result = authService.signUp(user);

    if (result == "success") {
      JOptionPane.showMessageDialog(signUpView, "Successfully sign up!");
    } else {
      JOptionPane.showMessageDialog(signUpView, "Failed to sign up!");
    }
  }

  public void signInAction(User user, SignInView signInView) {
    String result = authService.signIn(user);

    if (result == "success") {
      JOptionPane.showMessageDialog(signInView, "Sign in successfully");

      SocketClient socketClient = new SocketClient("127.0.0.1", Const.port, authService.jwtToken);
      new Thread(() -> {
        socketClient.start();
      }).start(); // This will start the SocketClient in a new thread

      LobbyController lobbyController = new LobbyController(socketClient);
      LobbyView lobbyView = new LobbyView(lobbyController);
      lobbyController.setLobbyView(lobbyView);
      lobbyController.setToken(authService.jwtToken);
      lobbyController.sendRequestFriendList();

      // Add the observer to the socket client to receive updates from the server
      socketClient.addObserver(lobbyController);

      // Update the UI on the EDT
      SwingUtilities.invokeLater(() -> {
        signInView.dispose();
        lobbyView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lobbyView.pack();
        lobbyView.setVisible(true);
      });

    } else {
      JOptionPane.showMessageDialog(signInView, "Invalid username or password");
    }
  }

  public void showSignInView() {
    SignInView signInView = new SignInView(this);
    signInView.setVisible(true);
  }

  public void showSignUpView() {
    SignUpView signUpView = new SignUpView(this);
    signUpView.setVisible(true);
  }

  public void showAuthView() {
    this.authView.setVisible(true);
  }

  public void setAuthView(AuthView authView) {
    this.authView = authView;
  }
}
