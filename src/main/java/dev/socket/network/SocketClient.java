package dev.socket.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import dev.socket.views.LobbyView;

public class SocketClient {
  private String serverAddress;
  private int serverPort;
  private String jwtToken;
  private LobbyView lobbyView;

  public SocketClient(String serverAddress, int serverPort, String jwtToken, LobbyView lobbyView) {
    this.serverAddress = serverAddress;
    this.serverPort = serverPort;
    this.jwtToken = jwtToken;
    this.lobbyView = lobbyView;
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
      new Thread(new IncomingMessageHandler(socket, in, lobbyView)).start();

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
    private LobbyView lobbyView;
    private List<String> onlineFriends;

    public IncomingMessageHandler(Socket socket, BufferedReader in, LobbyView lobbyView) {
      this.socket = socket;
      this.in = in;
      this.lobbyView = lobbyView;
      this.onlineFriends = new ArrayList<String>();

    }

    public void run() {
      try {
        String message;
        while ((message = in.readLine()) != null) {
          System.out.println("Received from server: " + message);

          // Check if the message is the online friends list
          if (message.startsWith("ONLINE_FRIENDS:")) {
            // Example: ONLINE_FRIENDS:friend1,friend2,friend3
            String[] friends = message.substring("ONLINE_FRIENDS:".length()).split(",");

            if (friends.length == 1 && friends[0].equals("None")) {
              friends = new String[] {}; // Handle the "None" case
            }

            System.out.println(friends);

            updateOnlineFriendsList(friends);

          } else if (message.startsWith("FRIEND_STATUS_CHANGE:")) {
            handleStatusChange(message);
          }
        }
      } catch (IOException e) {
        System.out.println("Error reading from server: " + e.getMessage());
      }
    }

    public void handleStatusChange(String message) {
      String[] parts = message.split(":");
      if (parts.length == 3) {
        String friendId = parts[1];
        String status = parts[2];

        if ("ONLINE".equals(status)) {
          if (!onlineFriends.contains(friendId)) {
            onlineFriends.add(friendId);
            // Update the friends list on the GUI
            SwingUtilities.invokeLater(() -> lobbyView.updateOnlineFriendsList(onlineFriends.toArray(new String[0])));
          }
        } else if ("OFFLINE".equals(status)) {
          onlineFriends.remove(friendId);
          SwingUtilities.invokeLater(() -> lobbyView.updateOnlineFriendsList(onlineFriends.toArray(new String[0])));
        }

      }
    }

    // Update the UI
    public void updateOnlineFriendsList(String[] friends) {
      if (onlineFriends != null) {
        onlineFriends.clear();
      }
      for (String friend : friends) {
        onlineFriends.add(friend);
      }
      SwingUtilities.invokeLater(() -> lobbyView.updateOnlineFriendsList(onlineFriends.toArray(new String[0])));
    }
  }
}