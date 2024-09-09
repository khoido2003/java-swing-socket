package dev.socket.controllers;

import javax.swing.JOptionPane;

import dev.socket.AppIndex;
import dev.socket.models.User;
import dev.socket.network.SocketClient;
import dev.socket.services.AuthService;
import dev.socket.views.SignInView;

public class AuthController {
  private SignInView signInView;
  private AuthService authService;
  private AppIndex app;

  public AuthController(SignInView signInView, AuthService authService, AppIndex app) {
    this.signInView = signInView;
    this.authService = authService;
    this.app = app;
    initController();
  }

  private void initController() {
    signInView.setSignInAction(e -> {
      String email = signInView.getEmail();
      String password = signInView.getPassword();
      User user = new User(email, password);

      String token = authService.signIn(user);
      if (token != null) {
        user.setJwtToken(token);
        JOptionPane.showMessageDialog(null, "Login Successful!");

        /////////////////////////////////////////////

        // Connect user to the socket server
        int port = 8082;
        SocketClient client = new SocketClient("127.0.0.1", port, token);
        new Thread(() -> client.start()).start();

        /////////////////////////////////////////////

        // Move to the lobby via the app
        app.showLobby();

      } else {
        JOptionPane.showMessageDialog(null, "Login Failed!");
      }
    });
  }
}
