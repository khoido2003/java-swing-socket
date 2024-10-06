package dev.socket.models;

import java.util.ArrayList;
import java.util.List;

public class User {
  private String userId;
  private String username;
  private String password;
  private String email;
  private int totalPoints;
  private String jwtToken;

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public User(String email, String password, String username) {
    this.email = email;
    this.password = password;
    this.username = username;
  }

  public User(String userId, String username, String password, String email, int totalPoints) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.email = email;
    this.totalPoints = totalPoints;

  }

  public static User dejsonlizeObject(String jsonString) {
    // Remove the curly braces and split by comma to get individual key-value pairs
    jsonString = jsonString.replace("{", "").replace("}", "");
    String[] pairs = jsonString.split(",");

    String userId = null, username = null, password = null, email = null;
    int totalPoints = 0;

    // Parse each key-value pair
    for (String pair : pairs) {
      String[] keyValue = pair.split(":");
      String key = keyValue[0].trim().replace("\"", "");
      String value = keyValue[1].trim().replace("\"", "");

      switch (key) {
        case "userId":
          userId = value;
          break;
        case "username":
          username = value;
          break;
        case "password":
          password = value;
          break;
        case "email":
          email = value;
          break;
        case "totalPoints":
          totalPoints = Integer.parseInt(value);
          break;
      }
    }
    // Create a new User object from the parsed values
    return new User(userId, username, password, email, totalPoints);
  }

  public static List<User> dejsonlizeArray(String json) {
    List<User> users = new ArrayList<User>();

    if (json.equals("[]")) {
      return users;
    }

    json = json.trim();
    json = json.substring(1, json.length() - 2);

    String[] objects = json.split("}, ");
    for (int i = 0; i < objects.length; i++) {
      if (!objects[i].endsWith("}")) {
        objects[i] = objects[i] + "}";
      }
      User user = dejsonlizeObject(objects[i]);
      users.add(user);
    }
    return users;
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
