package dev.socket.views;

import javax.swing.*;
import dev.socket.controllers.LobbyController;
import dev.socket.models.User;

import java.awt.*;

public class LobbyView extends JFrame {

  private LobbyController lobbyController;
  private JLabel userNameLabel;
  private JLabel userIdLabel;

  private JButton joinBattleButton;
  private JButton friendlyMatchButton;
  private JButton rankingsButton;

  private JTextField searchField;
  private JButton searchButton;
  private DefaultListModel<UserPanel> searchResultListModel;
  private JList<UserPanel> searchResultList;

  private DefaultListModel<FriendPanel> friendsListModel;
  private JList<FriendPanel> friendsList;

  public LobbyView(LobbyController lobbyController) {
    this.lobbyController = lobbyController;
    setTitle("Lobby");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1200, 1000); // Increased size
    setLocationRelativeTo(null); // Center the window

    // Main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    add(mainPanel);

    // ---------------------------------------------------

    // Left side with buttons
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    userNameLabel = new JLabel("Tên: Do Minh Khoi");
    userIdLabel = new JLabel("ID: wwvipw_930298");

    joinBattleButton = new JButton("Join Battle");
    friendlyMatchButton = new JButton("Friendly match");
    rankingsButton = new JButton("Bảng xếp hạng");

    leftPanel.add(userNameLabel);
    leftPanel.add(userIdLabel);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between elements
    leftPanel.add(joinBattleButton);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(friendlyMatchButton);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(rankingsButton);

    mainPanel.add(leftPanel, BorderLayout.WEST);

    // -----------------------------------------------------------------

    // Right side with friend list and search
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BorderLayout());

    // Top section - Online friends list using custom JPanel
    JPanel onlineFriendsPanel = new JPanel();
    onlineFriendsPanel.setLayout(new BorderLayout());
    onlineFriendsPanel.setBorder(BorderFactory.createTitledBorder("Friends Online"));

    friendsListModel = new DefaultListModel<>();
    friendsList = new JList<>(friendsListModel);
    friendsList.setCellRenderer(new FriendListRenderer());
    JScrollPane friendsScrollPane = new JScrollPane(friendsList);

    onlineFriendsPanel.add(friendsScrollPane, BorderLayout.CENTER);
    rightPanel.add(onlineFriendsPanel, BorderLayout.NORTH);

    // Bottom section - Search and add friends using custom JPanel
    JPanel searchPanel = new JPanel();
    searchPanel.setLayout(new BorderLayout());
    searchPanel.setBorder(BorderFactory.createTitledBorder("Search User"));

    JPanel searchFieldPanel = new JPanel(new FlowLayout());
    searchField = new JTextField(15);
    searchButton = new JButton("Tìm");

    searchFieldPanel.add(searchField);
    searchFieldPanel.add(searchButton);

    searchResultListModel = new DefaultListModel<>();
    searchResultList = new JList<>(searchResultListModel);
    searchResultList.setCellRenderer(new UserListRenderer());
    JScrollPane searchResultScrollPane = new JScrollPane(searchResultList);

    searchPanel.add(searchFieldPanel, BorderLayout.NORTH);
    searchPanel.add(searchResultScrollPane, BorderLayout.CENTER);

    rightPanel.add(searchPanel, BorderLayout.CENTER);

    mainPanel.add(rightPanel, BorderLayout.CENTER);
    // Add action listeners
    searchButton.addActionListener(e -> searchFriend());

    friendsList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int index = friendsList.locationToIndex(evt.getPoint()); // Get the index of the item clicked
        if (index >= 0) {
          FriendPanel friendPanel = friendsListModel.get(index);
          JButton inviteButton = friendPanel.getInviteButton();
          inviteButton.doClick();
        }
      }
    });
  }

  // Method to add a friend to the list
  public void addFriendToList(java.util.List<User> users) {

    System.out.println("USERS: " + users);
    for (User user : users) {
      friendsListModel.addElement(new FriendPanel(user, true));
    }
  }

  public void updateFriendList(User user) {
    friendsListModel.addElement(new FriendPanel(user, true));
  }

  // Method to simulate searching for a friend
  public void searchFriend() {
    String friendName = searchField.getText();
    if (!friendName.isEmpty()) {
      searchResultListModel.addElement(new UserPanel(friendName));
    }
  }

  ///////////////////////////////////////////////////////////////////////

  // Custom panel for displaying friend info with an "Invite" button
  class FriendPanel extends JPanel {
    private User friend;
    private JButton inviteButton;

    public FriendPanel(User friend, boolean isOnline) {
      this.friend = friend;

      setLayout(new FlowLayout(FlowLayout.LEFT));
      JLabel nameLabel = new JLabel(friend.getUsername());
      JLabel statusLabel = new JLabel("Online");
      inviteButton = new JButton("Mời");

      add(nameLabel);
      add(statusLabel);
      add(inviteButton);

      inviteButton.addActionListener(e -> inviteFriend());
    }

    public JButton getInviteButton() {
      return inviteButton;
    }

    private void inviteFriend() {
      JOptionPane.showMessageDialog(null, "Invitation sent to " + friend.getUsername());
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////

  // Custom panel for displaying user info with an "Add Friend" button
  class UserPanel extends JPanel {
    private String userName;
    private JButton addFriendButton;

    public UserPanel(String userName) {
      this.userName = userName;

      setLayout(new FlowLayout(FlowLayout.LEFT));
      JLabel nameLabel = new JLabel(userName);
      addFriendButton = new JButton("Add Friend");

      add(nameLabel);
      add(addFriendButton);

      addFriendButton.addActionListener(e -> addFriend());
    }

    private void addFriend() {
      JOptionPane.showMessageDialog(null, "Friend request sent to " + userName);
    }
  }

  /////////////////////////////////////////////////////////////////////

  // Renderer for friends list, displaying a FriendPanel for each friend
  class FriendListRenderer implements ListCellRenderer<FriendPanel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends FriendPanel> list, FriendPanel friendPanel, int index,
        boolean isSelected, boolean cellHasFocus) {
      return friendPanel;
    }
  }

  ////////////////////////////////////////////////////////////////////

  // Renderer for search results list, displaying a UserPanel for each user
  class UserListRenderer implements ListCellRenderer<UserPanel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends UserPanel> list, UserPanel userPanel, int index,
        boolean isSelected, boolean cellHasFocus) {
      return userPanel;
    }
  }

}
