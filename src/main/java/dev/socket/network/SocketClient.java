package dev.socket.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import dev.socket.interfaces.SocketObserver;

public class SocketClient {
  private String serverAddress;
  private int serverPort;
  private String jwtToken;
  PrintWriter out;
  BufferedReader in;
  private boolean isConnected = false;

  // List of observers (subscribers)
  private boolean isNotifying = false;
  private List<SocketObserver> observers = new ArrayList<>();
  private List<SocketObserver> observersToAdd = new ArrayList<>();
  private List<SocketObserver> observersToRemove = new ArrayList<>();

  public SocketClient(String serverAddress, int serverPort, String jwtToken) {
    this.serverAddress = serverAddress;
    this.serverPort = serverPort;
    this.jwtToken = jwtToken;
    this.observers = new ArrayList<>();
  }

  public synchronized void addObserver(SocketObserver observer) {
    if (isNotifying) {
      // Defer adding the observer until notification is done
      observersToAdd.add(observer);
    } else {
      observers.add(observer);
    }
  }

  public synchronized void removeObserver(SocketObserver observer) {
    if (isNotifying) {
      // Defer removing the observer until notification is done
      observersToRemove.add(observer);
    } else {
      observers.remove(observer);
    }
  }

  public synchronized void notifyObservers(String message) {
    isNotifying = true;

    try {
      for (SocketObserver observer : observers) {
        observer.onMessageReceived(message);
      }
    } finally {
      isNotifying = false;

      // Now process deferred adds and removes
      observers.addAll(observersToAdd);
      observersToAdd.clear();

      observers.removeAll(observersToRemove);
      observersToRemove.clear();
    }
  }

  /////////////////////////////////

  public void start() {
    try (Socket socket = new Socket(serverAddress, serverPort)) {

      // Write the data to the server
      this.out = new PrintWriter(socket.getOutputStream(), true);

      // Read data from the server
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      ///////////////////////////////////////////////////////

      System.out.println(jwtToken);
      // Send the jwt token to the server to check if it is valid
      out.println(jwtToken);
      out.flush();

      ////////////////////////////////////////////////////////

      // Start a thread to listen for incoming messages
      Thread incomingThread = new Thread(new IncomingMessageHandler(in));
      incomingThread.start(); // Start the incoming handler

      // Start handling user input in a separate thread
      Thread userInputThread = new Thread(new UserInputHandler(out));
      userInputThread.start(); // Start the user input handler in a new thread

      // Optionally, join both threads to wait for their completion
      incomingThread.join();
      userInputThread.join();

    } catch (IOException e) {
      System.out.println("Client exception: " + e.getMessage());
      reconnect();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Restore interrupted status
      System.out.println("Thread was interrupted: " + e.getMessage());
    }
  }

  public void sendMessage(String message) {
    out.println(message);
    out.flush();
  }

  //////////////////////////////////////////////////////////
  ///
  @SuppressWarnings("resource")
  private void reconnect() {
    int retryInterval = 5000; // 5 seconds delay between reconnection attempts
    boolean reconnecting = true;

    while (reconnecting) {
      try {
        System.out.println("Attempting to reconnect to the server...");
        // Reconnect logic
        Socket socket = new Socket(serverAddress, serverPort);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Resend the JWT token for authentication after reconnection
        out.println(jwtToken);
        out.flush();

        // Restart the incoming message handler
        Thread incomingThread = new Thread(new IncomingMessageHandler(in));
        incomingThread.start();

        reconnecting = false; // Successfully reconnected
        System.out.println("Reconnected to the server.");
      } catch (IOException e) {
        System.out.println("Reconnection failed. Retrying in " + retryInterval / 1000 + " seconds...");
        try {
          Thread.sleep(retryInterval); // Wait before retrying
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt(); // Restore interrupted status
          break;
        }
      }
    }
  }

  ///////////////////////////////////////////////////////////

  private class IncomingMessageHandler implements Runnable {
    private BufferedReader in;

    public IncomingMessageHandler(BufferedReader in) {
      this.in = in;
    }

    public void run() {
      try {
        String message;
        while ((message = in.readLine()) != null) {
          System.out.println("Received from server: " + message);

          notifyObservers(message);
        }
      } catch (IOException e) {
        System.out.println("Error reading from server: " + e.getMessage());
        reconnect();
      }
    }
  }

  ////////////////////////////////////////////////////////

  private class UserInputHandler implements Runnable {

    private PrintWriter out;

    public UserInputHandler(PrintWriter out) {
      this.out = out;
    }

    public void run() {
      try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
        String userInputStr;

        while ((userInputStr = userInput.readLine()) != null) {
          if (userInputStr.trim().isEmpty()) {
            continue;
          }
          // Send message to server
          out.println(userInputStr);
          out.flush();
        }
      } catch (IOException e) {
        System.out.println("Error reading user input: " + e.getMessage());
      }
    }
  }
}