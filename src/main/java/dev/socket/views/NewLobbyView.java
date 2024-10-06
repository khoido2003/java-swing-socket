package dev.socket.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewLobbyView extends javax.swing.JFrame {

    public NewLobbyView() {
        initComponents();
    }

    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanelRight = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListOnlineFriends = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListSearchResults = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jButtonInvite = new javax.swing.JButton(); // Thêm nút mời
        jButtonAdd = new javax.swing.JButton(); // Thêm nút thêm bạn

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(242, 246, 246));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(242, 246, 246));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel1.setText("Tên: Trần Minh Đức");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel2.setText("Id: HandSome_no1_Server");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jButton1.setText("Join Battle");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jButton2.setText("Friendly Match");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jButton3.setText("Rankings");

        // Add action listeners to buttons
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                joinBattleActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                friendlyMatchActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                rankingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addGap(40, 40, 40)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(125, Short.MAX_VALUE))
        );

        // Panel right side (Friends list and search)
        jPanelRight.setBackground(new java.awt.Color(242, 246, 246));

        // Online friends section
        jLabel3.setText("Friends Online");
        jListOnlineFriends.setModel(new javax.swing.AbstractListModel<String>() {
            String[] friends = {"Friend 1", "Friend 2", "Friend 3"};
            public int getSize() { return friends.length; }
            public String getElementAt(int i) { return friends[i]; }
        });
        jScrollPane1.setViewportView(jListOnlineFriends);

        // Nút mời
        jButtonInvite.setText("Mời");
        jButtonInvite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                inviteFriendActionPerformed(evt);
            }
        });

        // Search friends section
        jLabel4.setText("Search Friends");
        jButtonSearch.setText("Tìm");

        jButtonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                searchFriendsActionPerformed(evt);
            }
        });

        jListSearchResults.setModel(new javax.swing.AbstractListModel<String>() {
            String[] searchResults = {"Search Result 5", "Search Result 2"};
            public int getSize() { return searchResults.length; }
            public String getElementAt(int i) { return searchResults[i]; }
        });
        jScrollPane2.setViewportView(jListSearchResults);

        // Nút thêm bạn
        jButtonAdd.setText("Thêm bạn");
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addFriendActionPerformed(evt);
            }
        });

        // Layout for right side
        javax.swing.GroupLayout jPanelRightLayout = new javax.swing.GroupLayout(jPanelRight);
        jPanelRight.setLayout(jPanelRightLayout);
        jPanelRightLayout.setHorizontalGroup(
                jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelRightLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jScrollPane2)
                                        .addGroup(jPanelRightLayout.createSequentialGroup()
                                                .addGroup(jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addGroup(jPanelRightLayout.createSequentialGroup()
                                                                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jButtonInvite, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButtonAdd, javax.swing.GroupLayout.Alignment.TRAILING)) // Thêm nút thêm bạn vào layout
                                .addContainerGap())
        );
        jPanelRightLayout.setVerticalGroup(
                jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelRightLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonInvite)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonSearch))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAdd) // Đặt nút thêm bạn dưới danh sách tìm kiếm
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        // Main layout of the form
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanelRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // Xử lý sự kiện khi nhấn nút thêm bạn
    private void addFriendActionPerformed(ActionEvent evt) {
        String selectedFriend = jListSearchResults.getSelectedValue();
        if (selectedFriend != null) {
            JOptionPane.showMessageDialog(this, "Bạn đã thêm " + selectedFriend + " vào danh sách bạn bè.");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một người để thêm.");
        }
    }

    private void inviteFriendActionPerformed(ActionEvent evt) {
        String selectedFriend = jListOnlineFriends.getSelectedValue();
        if (selectedFriend != null) {
            JOptionPane.showMessageDialog(this, "Bạn đã mời " + selectedFriend + " tham gia trận đấu.");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một người bạn để mời.");
        }
    }

    private void searchFriendsActionPerformed(ActionEvent evt) {
        // Thực hiện tìm kiếm bạn bè
    }

    private void joinBattleActionPerformed(ActionEvent evt) {
        // tham gia trận đấu

    }

    private void friendlyMatchActionPerformed(ActionEvent evt) {
        //đáu bạn bè
    }

    private void rankingsActionPerformed(ActionEvent evt) {
        //bảng xếp hạng
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonInvite;
    private javax.swing.JButton jButtonAdd; // Nút thêm bạn
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jListOnlineFriends;
    private javax.swing.JList<String> jListSearchResults;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldSearch;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewLobbyView newlobbyView = new NewLobbyView();
                newlobbyView.setVisible(true);
                newlobbyView.setLocationRelativeTo(null);
            }
        });
    }
}
