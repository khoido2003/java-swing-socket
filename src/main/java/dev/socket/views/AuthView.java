package dev.socket.views;

import java.awt.FlowLayout;

import javax.swing.*;

import dev.socket.controllers.AuthController;

public class AuthView extends JFrame {

  private AuthController authController;

  public AuthView(AuthController authController) {

    this.authController = authController;

    setTitle("User Authentication");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JButton loginBtn = new JButton("Login");
    JButton signUpBtn = new JButton("Sign Up");

    loginBtn.addActionListener(e -> showLoginForm());
    signUpBtn.addActionListener(e -> showSignUpForm());

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.add(loginBtn);
    panel.add(signUpBtn);

    add(panel);

  }

  private void showLoginForm() {
    authController.showSignInView();
    this.setVisible(false);

  }

  private void showSignUpForm() {
    authController.showSignUpView();
    this.setVisible(false);
  }

}
