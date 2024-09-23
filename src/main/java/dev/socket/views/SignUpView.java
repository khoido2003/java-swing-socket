package dev.socket.views;

import javax.swing.*;

import dev.socket.controllers.AuthController;
import dev.socket.models.User;

public class SignUpView extends JFrame {

  private AuthController authController;

  private JLabel emailLabel;
  private JTextField emailField;
  private JLabel passwordLabel;
  private JPasswordField passwordField;
  private JLabel usernameLabel;
  private JTextField usernameField;

  public SignUpView(AuthController authController) {
    this.authController = authController;

    setTitle("Sign Up");
    setSize(300, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    emailLabel = new JLabel("Email:");
    emailField = new JTextField(20);
    passwordLabel = new JLabel("Password:");
    passwordField = new JPasswordField(20);
    usernameLabel = new JLabel("Username:");
    usernameField = new JTextField(20);

    JButton signUpBtn = new JButton("Sign Up");
    signUpBtn.addActionListener(e -> signUpEvent());

    JButton returnBtn = new JButton("Return");
    returnBtn.addActionListener(e -> returnToAuthView());

    JPanel panel = new JPanel();
    panel.add(emailLabel);
    panel.add(emailField);
    panel.add(passwordLabel);
    panel.add(passwordField);
    panel.add(usernameLabel);
    panel.add(usernameField);
    panel.add(signUpBtn);
    panel.add(returnBtn);
    add(panel);

  }

  private void signUpEvent() {
    String email = emailField.getText();
    String password = new String(passwordField.getPassword());
    String username = usernameField.getText();

    User user = new User(email, password, username);

    authController.signUpAction(user, this);
  }

  private void returnToAuthView() {
    dispose();
    authController.showAuthView();
  }

}
