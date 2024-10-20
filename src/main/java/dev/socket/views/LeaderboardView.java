package dev.socket.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dev.socket.controllers.LeaderboardController;
import dev.socket.models.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardView extends JFrame {

  private LeaderboardController leaderboardController;
  private JTable leaderboardTable;
  private DefaultTableModel tableModel;

  public LeaderboardView(LeaderboardController leaderboardController) {
    this.leaderboardController = leaderboardController;

    // Set the frame title
    setTitle("Leaderboard");

    // Set the close operation
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Set layout to BorderLayout
    setLayout(new BorderLayout());

    // Create title label with styling
    JLabel titleLabel = new JLabel("Leaderboard", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
    titleLabel.setForeground(new Color(58, 134, 255));
    titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    add(titleLabel, BorderLayout.NORTH);

    // Create table data for the leaderboard
    String[] columns = { "Rank", "Player", "Score" };
    tableModel = new DefaultTableModel(columns, 0);

    // Create the table with no data initially
    leaderboardTable = new JTable(tableModel) {
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    // Style the table
    leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 16));
    leaderboardTable.setRowHeight(30);
    leaderboardTable.setGridColor(Color.GRAY);

    // Set table header styling
    JTableHeader header = leaderboardTable.getTableHeader();
    header.setFont(new Font("Arial", Font.BOLD, 18));
    header.setBackground(new Color(58, 134, 255));
    header.setForeground(Color.WHITE);

    // Add the table to a scroll pane
    JScrollPane scrollPane = new JScrollPane(leaderboardTable);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    add(scrollPane, BorderLayout.CENTER);

    // Add a footer or "Back" button at the bottom
    JPanel footerPanel = new JPanel();
    footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    JButton backButton = new JButton("Quay lại");
    backButton.setFont(new Font("Arial", Font.PLAIN, 18));
    backButton.setBackground(new Color(58, 134, 255));
    backButton.setForeground(Color.WHITE);

    // Add MouseAdapter to handle the mouse click event
    backButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        leaderboardController.showLobbyView();
      }
    });

    footerPanel.add(backButton);
    add(footerPanel, BorderLayout.SOUTH);

    // Set window size and make it visible
    setSize(500, 600);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void parseUserData(String jsonData) {
    try {

      List<User> usersNode = User.dejsonlizeArray(jsonData);

      // Ensure the UI update is done on the Event Dispatch Thread
      SwingUtilities.invokeLater(() -> {
        // Clear any previous data in the table
        tableModel.setRowCount(0);

        // Populate the table with the user data
        List<Object[]> rowData = new ArrayList<>();
        for (int i = 0; i < usersNode.size(); i++) {
          User user = usersNode.get(i);
          String username = user.getUsername();
          int totalPoints = user.getTotalPoints();

          // Add to the table model
          rowData.add(new Object[] { i + 1, username, totalPoints });
        }

        // Update the table with new data
        for (Object[] row : rowData) {
          tableModel.addRow(row);
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
