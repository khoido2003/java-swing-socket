package dev.socket.views;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

  public GameView() {
    // Set frame properties
    setTitle("Game View");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // "Số người chơi" label
    JLabel lblPlayers = new JLabel("Số người chơi: 2");
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    add(lblPlayers, gbc);

    // "Ô chữ cần ghi nhớ" label
    JLabel lblTextToRemember = new JLabel("Ô chữ cần ghi nhớ: ABCDEFGH");
    gbc.gridy = 1;
    add(lblTextToRemember, gbc);

    // Timer label (e.g., "10s")
    JLabel lblTimer = new JLabel("10s");
    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.EAST;
    add(lblTimer, gbc);

    // Text field for user input
    JTextField txtInput = new JTextField(20);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    add(txtInput, gbc);

    // Send button
    JButton btnSend = new JButton("Gửi");
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.EAST;
    add(btnSend, gbc);

    // Question progress label (e.g., "Câu 5/10")
    JLabel lblQuestionProgress = new JLabel("Câu 5/10");
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.anchor = GridBagConstraints.WEST;
    add(lblQuestionProgress, gbc);

    // Score label (e.g., "Điểm số 15")
    JLabel lblScore = new JLabel("Điểm số 15");
    gbc.gridy = 5;
    add(lblScore, gbc);

    // "Rời phòng" (Exit) button
    JButton btnExit = new JButton("Rời phòng");
    gbc.gridx = 1;
    gbc.gridy = 6;
    gbc.anchor = GridBagConstraints.EAST;
    add(btnExit, gbc);
  }

  public static void main(String[] args) {
    // Create and show the GameView
    SwingUtilities.invokeLater(() -> {
      GameView gameView = new GameView();
      gameView.setVisible(true);
    });
  }
}
