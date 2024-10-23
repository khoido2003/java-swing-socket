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
  private JButton friendListButton;
  private JButton friendlyMatchButton;
  private JButton rankingsButton;

  private JTextField searchField;
  private JButton searchButton;
  private DefaultListModel<UserPanel> searchResultListModel;
  private JList<UserPanel> searchResultList;

  private DefaultListModel<FriendPanel> friendsListModel;
  private JList<FriendPanel> friendsList;
  private DefaultListModel<FriendRequestPanel> friendRequestListModel;
  private JList<FriendRequestPanel> friendRequestList;
  private String userId;
  private String userName;

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

    userNameLabel = new JLabel("Tên: " + this.userName);
    userIdLabel = new JLabel("ID: " + this.userId);

    joinBattleButton = new JButton("Join Battle");
    rankingsButton = new JButton("Bảng xếp hạng");
    friendListButton = new JButton("Xem danh sách bạn bè");

    leftPanel.add(userNameLabel);
    leftPanel.add(userIdLabel);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between elements
    leftPanel.add(joinBattleButton);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(friendListButton);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(rankingsButton);

    leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between elements
    // New section for Friend Requests
    JPanel friendRequestPanel = new JPanel();
    friendRequestPanel.setLayout(new BorderLayout());
    friendRequestPanel.setBorder(BorderFactory.createTitledBorder("Friend Requests"));

    friendRequestListModel = new DefaultListModel<>();
    friendRequestList = new JList<>(friendRequestListModel);
    friendRequestList.setCellRenderer(new FriendRequestRenderer());
    JScrollPane friendRequestScrollPane = new JScrollPane(friendRequestList);

    friendRequestPanel.add(friendRequestScrollPane, BorderLayout.CENTER);
    leftPanel.add(friendRequestPanel, BorderLayout.SOUTH); // Add friend requests panel at the bottom

    // Use this to see the game view withou need two people to join the game
    // JButton gBtn = new JButton("Test game view");
    // leftPanel.add(gBtn);

    // gBtn.addActionListener(e ->
    // this.lobbyController.requestCreateNewGameMatch());

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
        int index = friendsList.locationToIndex(evt.getPoint());
        if (index >= 0) {
          FriendPanel friendPanel = friendsListModel.get(index);
          JButton inviteButton = friendPanel.getInviteButton();
          inviteButton.doClick();
        }
      }
    });

    searchResultList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int index = searchResultList.locationToIndex(evt.getPoint());
        if (index >= 0) {
          UserPanel userPanel = searchResultListModel.get(index);
          JButton addFriendButton = userPanel.addFriendButton;

          addFriendButton.doClick();
        }
      }
    });

    rankingsButton.addActionListener(e -> {
      this.lobbyController.showLeaderboardView();
    });

    joinBattleButton.addActionListener(e -> {
      this.lobbyController.requestJoinNewMatch();
    });

    friendListButton.addActionListener(e -> {
      this.lobbyController.showFriendListView();
    });

    friendRequestList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int index = friendRequestList.locationToIndex(evt.getPoint());
        if (index >= 0) {
          FriendRequestPanel friendRequestPanel = friendRequestListModel.get(index);
          JButton acceptButton = friendRequestPanel.getAcceptButton();
          acceptButton.doClick();
          JButton declineButton = friendRequestPanel.getDeclineButton();
          declineButton.doClick();
        }
      }
    });
  }

  // public void showGameView() {
  // // GameView gameView = new GameView();
  // // gameView.setVisible(true);

  // NewGameView game = new NewGameView();
  // game.setVisible(true);
  // this.setVisible(false);
  // }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserId() {
    return userId;
  }

  public void updateUserInfo() {
    userNameLabel.setText("Tên: " + this.userName);
    userIdLabel.setText("ID: " + this.userId);
  }

  // Method to add a friend to the list
  public void addFriendToList(java.util.List<User> users) {
    for (User user : users) {
      friendsListModel.addElement(new FriendPanel(user, true, this.lobbyController));
    }
  }

  public void removeUserFromList(String userID) {
    for (int i = 0; i < friendsListModel.size(); i++) {
      FriendPanel friendPanel = friendsListModel.get(i);
      if (friendPanel.getFriend().getUserId().equals(userID)) {
        friendsListModel.remove(i);
        break;
      }
    }
  }

  public void updateFriendList(User user) {
    friendsListModel.addElement(new FriendPanel(user, true, this.lobbyController));
  }

  // Method to simulate searching for a friend
  public void searchFriend() {
    String username = searchField.getText().trim();
    if (username.isEmpty()) {
      searchResultListModel.clear();
      return;
    }

    this.lobbyController.sendFindUser(username);
  }

  public void addUserToList(java.util.List<User> users) {
    searchResultListModel.clear();
    for (User user : users) {
      searchResultListModel.addElement(new UserPanel(user.getUsername(), user.getUserId()));
    }
  }

  public void addPendingFriendRequestList(java.util.List<User> users) {
    friendRequestListModel.clear();
    for (User user : users) {
      friendRequestListModel.addElement(new FriendRequestPanel(user, this, this.lobbyController));
    }
  }

  public void addFriendRequestToPendingList(User user) {
    friendRequestListModel.addElement(new FriendRequestPanel(user, this, this.lobbyController));
  }

  ///////////////////////////////////////////////////////////////////////

  // Custom panel for displaying friend info with an "Invite" button
  class FriendPanel extends JPanel {
    private User friend;
    private JButton inviteButton;
    private LobbyController lobbyController;

    public FriendPanel(User friend, boolean isOnline, LobbyController lobbyController) {
      this.friend = friend;
      this.lobbyController = lobbyController;

      setLayout(new FlowLayout(FlowLayout.LEFT));
      JLabel nameLabel = new JLabel(friend.getUsername());
      JLabel statusLabel = new JLabel("Online");
      inviteButton = new JButton("Mời");

      add(nameLabel);
      add(statusLabel);
      add(inviteButton);

      inviteButton.addActionListener(e -> inviteFriend(friend.getUserId()));
    }

    public User getFriend() {
      return friend;
    }

    public JButton getInviteButton() {
      return inviteButton;
    }

    private void inviteFriend(String friendID) {

      this.lobbyController.requestCreateNewGameMatch(friendID);

      // JOptionPane.showMessageDialog(null, "Invitation sent to " +
      // friend.getUsername());

    }
  }

  ///////////////////////////////////////////////////////////////////////////////////

  // Custom panel for displaying user info with an "Add Friend" button
  class UserPanel extends JPanel {
    private String userName;
    private String userId;
    private JButton addFriendButton;

    public UserPanel(String userName, String userId) {
      this.userName = userName;
      this.userId = userId;

      setLayout(new FlowLayout(FlowLayout.LEFT));
      JLabel nameLabel = new JLabel(userName);
      addFriendButton = new JButton("Add Friend");

      add(nameLabel);
      add(addFriendButton);

      addFriendButton.addActionListener(e -> addFriend());
    }

    private void addFriend() {
      lobbyController.createFriendRequest(userId);
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

  ///////////////////////////////////////////////////////////////////

  // Custom panel for displaying friend request info with "Accept" and "Decline"
  // buttons
  class FriendRequestPanel extends JPanel {
    private User user;
    private JButton acceptButton;
    private JButton declineButton;
    private LobbyView lobbyView;
    private LobbyController lobbyController;

    public FriendRequestPanel(User user, LobbyView lobbyView, LobbyController lobbyController) {
      this.lobbyView = lobbyView;
      this.user = user;
      this.lobbyController = lobbyController;

      setLayout(new FlowLayout(FlowLayout.LEFT));
      JLabel nameLabel = new JLabel(user.getUsername());
      acceptButton = new JButton("Accept");
      declineButton = new JButton("Decline");

      add(nameLabel);
      add(acceptButton);
      add(declineButton);

      // Add independent action listeners
      acceptButton.addActionListener(e -> {
        if (e.getSource() == acceptButton) {
          acceptRequest();
        }
      });

      declineButton.addActionListener(e -> {
        if (e.getSource() == declineButton) {
          declineRequest();
        }
      });
    }

    public JButton getAcceptButton() {
      return acceptButton;
    }

    public JButton getDeclineButton() {
      return declineButton;
    }

    private void acceptRequest() {
      JOptionPane.showMessageDialog(null, "Friend request from " + user.getUsername() + " accepted.");
      friendRequestListModel.removeElement(this);
      lobbyView.updateFriendList(user);

      this.lobbyController.acceptFriendRequest(lobbyView.getUserId(), user.getUserId());
    }

    private void declineRequest() {
      JOptionPane.showMessageDialog(null, "Friend request from " + user.getUsername() + " declined.");
      friendRequestListModel.removeElement(this);
      // Implement decline logic here if needed
      // this.lobbyController.declineFriendRequest(lobbyView.getUserId(),
      // user.getUserId());
    }
  }

  // Renderer for friend requests list, displaying a FriendRequestPanel for each
  // request
  class FriendRequestRenderer implements ListCellRenderer<FriendRequestPanel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends FriendRequestPanel> list,
        FriendRequestPanel friendRequestPanel, int index, boolean isSelected, boolean cellHasFocus) {
      return friendRequestPanel;
    }
  }
}
