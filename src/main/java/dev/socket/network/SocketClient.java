package dev.socket.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
  private String serverAddress;
  private int serverPort;
  private String jwtToken;

  public SocketClient(String serverAddress, int serverPort, String jwtToken) {
    this.serverAddress = serverAddress;
    this.serverPort = serverPort;
    this.jwtToken = jwtToken;
  }

  public void start() {
    try (Socket socket = new Socket(serverAddress, serverPort)) {

      // Write the data to the server
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

      // Read data from the server
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      ///////////////////////////////////////////////////////

      // Send the jwt token to the server to check if it is valid
      out.println(jwtToken);
      out.flush();

      ////////////////////////////////////////////////////////

      // Get user input from the terminal
      BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
      String userInputStr;

      // Start a thread to listen for incoming messages
      new Thread(new IncomingMessageHandler(socket, in)).start();

      System.out.println("Connected to server. Type a message to send:");
      while ((userInputStr = userInput.readLine()) != null) {
        if (userInputStr.trim().isEmpty()) {
          continue; // Skip empty input
        }
        // Send message to server
        out.println(userInputStr);
        out.flush();
      }

    } catch (IOException e) {
      System.out.println("Client exception: " + e.getMessage());
    }
  }

  private static class IncomingMessageHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;

    public IncomingMessageHandler(Socket socket, BufferedReader in) {
      this.socket = socket;
      this.in = in;
    }

    public void run() {
      try {
        String message;
        while ((message = in.readLine()) != null) {
          System.out.println("Received from server: " + message);
        }
      } catch (IOException e) {
        System.out.println("Error reading from server: " + e.getMessage());
      }
    }
  }
}