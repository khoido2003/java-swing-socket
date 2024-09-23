package dev.socket.controllers;

import javax.swing.JOptionPane;

import dev.socket.models.User;
import dev.socket.services.AuthService;
import dev.socket.views.AuthView;
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

      DashboardController dashboardController = new DashboardController();
      signInView.setVisible(false);

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
