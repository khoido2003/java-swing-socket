package dev.socket.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class LobbyView extends JFrame {
  private JButton joinRoomButton;

  public LobbyView() {
    setTitle("Lobby");
    setSize(300, 150);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    setContentPane(panel);
    panel.setLayout(null);

    joinRoomButton = new JButton("Join Room");
    joinRoomButton.setBounds(10, 50, 150, 25);
    panel.add(joinRoomButton);
  }

  public void setJoinRoomAction(ActionListener actionListener) {
    joinRoomButton.addActionListener(actionListener);
  }
}
