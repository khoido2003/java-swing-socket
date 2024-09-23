package dev.socket.views;

import javax.swing.*;

import dev.socket.controllers.AuthController;
import dev.socket.models.User;

public class SignInView extends JFrame {

  private AuthController authController;

  private JTextField emailField;
  private JPasswordField passwordField;
  private JButton loginButton;

  public SignInView(AuthController authController) {

    this.authController = authController;

    setTitle("Sign In");
    setSize(300, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel();

    JLabel userLabel = new JLabel("Email:");
    panel.add(userLabel);

    emailField = new JTextField(20);
    panel.add(emailField);

    JLabel passwordLabel = new JLabel("Password:");
    panel.add(passwordLabel);

    passwordField = new JPasswordField(20);
    panel.add(passwordField);

    loginButton = new JButton("Sign In");
    loginButton.addActionListener(e -> signInEvent());
    panel.add(loginButton);

    JButton returnBtn = new JButton("Return");
    returnBtn.addActionListener(e -> returnToAuthView());
    panel.add(returnBtn);

    add(panel);
  }

  public void signInEvent() {
    String email = emailField.getText();
    String password = new String(passwordField.getPassword());

    User user = new User(email, password);
    authController.signInAction(user, this);
  }

  public void returnToAuthView() {
    dispose();
    new AuthView(authController).setVisible(true);
  }
}
