package dev.socket.views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SignInView extends JFrame {
  private JTextField emailField;
  private JPasswordField passwordField;
  private JButton loginButton;

  public SignInView() {
    setTitle("Sign In");
    setSize(300, 150);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    setContentPane(panel);
    panel.setLayout(null);

    JLabel userLabel = new JLabel("Email:");
    userLabel.setBounds(10, 20, 80, 25);
    panel.add(userLabel);

    emailField = new JTextField(20);
    emailField.setBounds(100, 20, 165, 25);
    panel.add(emailField);

    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setBounds(10, 50, 80, 25);
    panel.add(passwordLabel);

    passwordField = new JPasswordField(20);
    passwordField.setBounds(100, 50, 165, 25);
    panel.add(passwordField);

    loginButton = new JButton("Sign In");
    loginButton.setBounds(10, 80, 80, 25);
    panel.add(loginButton);
  }

  public String getEmail() {
    return emailField.getText();
  }

  public String getPassword() {
    return new String(passwordField.getPassword());
  }

  public void setSignInAction(ActionListener actionListener) {
    loginButton.addActionListener(actionListener);
  }
}
