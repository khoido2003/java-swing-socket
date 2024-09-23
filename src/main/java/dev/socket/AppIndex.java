package dev.socket;

import dev.socket.controllers.AuthController;
import dev.socket.views.AuthView;

public class AppIndex {

  private AuthController authController;

  public AppIndex() {
    this.authController = new AuthController();

    AuthView authView = new AuthView(authController);
    authController.setAuthView(authView);
    authView.setVisible(true);
  }

}
