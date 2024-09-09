package dev.socket.services;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject; // Include the org.json library

import dev.socket.models.User;

public class AuthService {

  public String signIn(User user) {
    try {
      URL url = new URL("http://localhost:8081/login");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setDoOutput(true);

      // Create the JSON request body
      String requestBody = "{\"email\":\"" + user.getEmail() + "\",\"password\":\"" + user.getPassword() + "\"}";
      OutputStream os = conn.getOutputStream();
      os.write(requestBody.getBytes());
      os.flush();
      os.close();

      int responseCode = conn.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        Scanner scanner = new Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        // Parse the response as a JSON object
        JSONObject jsonResponse = new JSONObject(response);
        if (jsonResponse.getBoolean("success")) {
          // Extract the token from the "data" field
          String token = jsonResponse.getString("data");

          // Set the token in the user object
          user.setJwtToken(token);

          System.out.println("Token: " + token); // Just to verify
          return token; // Return the token if needed
        } else {
          System.out.println("Login failed: " + jsonResponse.getString("message"));
        }
      } else {
        System.out.println("Login failed with response code: " + responseCode);
      }

    } catch (

    Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
