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

  // List of observers (subscribers)
  private List<SocketObserver> observers;

  public SocketClient(String serverAddress, int serverPort, String jwtToken) {
    this.serverAddress = serverAddress;
    this.serverPort = serverPort;
    this.jwtToken = jwtToken;
    this.observers = new ArrayList<>();
  }

  public void addObserver(SocketObserver observer) {
    observers.add(observer);
  }

  // Remove an observer
  public void removeObserver(SocketObserver observer) {
    observers.remove(observer);
  }

  // Notify all observers of a new message
  public void notifyObservers(String message) {
    for (SocketObserver observer : observers) {
      observer.onMessageReceived(message);
    }
  }

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
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Restore interrupted status
      System.out.println("Thread was interrupted: " + e.getMessage());
    }
  }

  public void sendMessage(String message) {
    out.println(message);
    out.flush();
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