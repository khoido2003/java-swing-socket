package dev.socket.models;

public class JwtToken {
  public String userId;
  public String email;
  public String username;

  public JwtToken(String userId, String email, String username) {
    this.userId = userId;
    this.email = email;
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public String getUserId() {
    return userId;
  }

}
