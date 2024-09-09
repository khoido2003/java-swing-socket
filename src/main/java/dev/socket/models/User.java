package dev.socket.models;

public class User {
  private String userId;
  private String username;
  private String password;
  private String email;
  private int totalPoints;
  private String status;
  private String jwtToken; // Add JWT token field

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public User(String userId, String username, String password, String email, int totalPoints, String status) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.email = email;
    this.totalPoints = totalPoints;
    this.status = status;
  }

  // Add jwtToken getter and setter
  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  // Getters and setters for other fields
  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public String getStatus() {
    return status;
  }

  public String getUserId() {
    return userId;
  }

  public String getPassword() {
    return password;
  }

  public int getTotalPoints() {
    return totalPoints;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setTotalPoints(int totalPoints) {
    this.totalPoints = totalPoints;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
