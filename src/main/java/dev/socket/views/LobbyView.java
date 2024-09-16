package dev.socket.views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LobbyView extends JFrame {
  private JButton joinRoomButton;
  private JList<String> onlineFriendsList;
  private DefaultListModel<String> friendsListModel;

  public LobbyView() {
    setTitle("Lobby");
    setSize(300, 300); // Increased the size to accommodate the friends list
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    setContentPane(panel);
    panel.setLayout(null);

    joinRoomButton = new JButton("Join Room");
    joinRoomButton.setBounds(10, 200, 150, 25);
    panel.add(joinRoomButton);

    // Add a JList to show online friends
    friendsListModel = new DefaultListModel<>();
    onlineFriendsList = new JList<>(friendsListModel);
    JScrollPane scrollPane = new JScrollPane(onlineFriendsList);
    scrollPane.setBounds(10, 10, 260, 180);
    panel.add(scrollPane);
  }

  // Method to update the friends list in the lobby view
  public void updateOnlineFriendsList(String[] friends) {
    friendsListModel.clear();
    for (String friend : friends) {
      friendsListModel.addElement(friend);
    }
  }

  public void setJoinRoomAction(ActionListener actionListener) {
    joinRoomButton.addActionListener(actionListener);
  }
}
