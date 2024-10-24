package dev.socket.views;

import javax.swing.Timer;

import dev.socket.controllers.GameController;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameView extends javax.swing.JFrame {

        GameController gameController;

        public NewGameView() {
                initComponents();

                this.btnExit.addActionListener(e -> listenLeaveRoom());

        }

        public void setGameController(GameController gameController) {
                this.gameController = gameController;
        }

        private void initComponents() {

                jLabel5 = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                lblQuestion = new javax.swing.JLabel();
                lblClock = new javax.swing.JLabel();
                txtAnswer = new javax.swing.JTextField();
                btnSent = new javax.swing.JButton();
                jPanel4 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                lblNumberQuestion = new javax.swing.JLabel();
                jPanel6 = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();
                lblPoint = new javax.swing.JLabel();
                btnExit = new javax.swing.JButton();

                jLabel5.setText("jLabel5");

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                jPanel5Layout.setVerticalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 45, Short.MAX_VALUE));

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(249, 250, 255));
                jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));

                jPanel2.setBackground(new java.awt.Color(249, 250, 255));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                jLabel1.setText("Người chơi:");

                jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14));

                JPanel playerNamePanel = new JPanel();
                playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.Y_AXIS));
                playerNamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                jPanel2.add(jLabel1);
                jPanel2.add(jLabel2);

                jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                jLabel3.setText("Dãy chữ cần nhớ:");

                lblQuestion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

                lblClock.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lblClock.setText("5");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                155,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblQuestion)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                107, Short.MAX_VALUE)
                                                                .addComponent(lblClock,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                36,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28)));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap(7, Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                25,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(lblQuestion)
                                                                                .addComponent(lblClock))));

                txtAnswer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

                btnSent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                btnSent.setText("Gửi");
                btnSent.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSentActionPerformed(evt);
                        }
                });

                jPanel4.setBackground(new java.awt.Color(249, 250, 255));

                jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                jLabel4.setText("Câu:");

                lblNumberQuestion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(27, 27, 27)
                                                                .addComponent(jLabel4)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblNumberQuestion,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                37,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(21, Short.MAX_VALUE)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(lblNumberQuestion))
                                                                .addContainerGap()));

                jPanel6.setBackground(new java.awt.Color(249, 250, 255));

                jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                jLabel6.setText("Điểm số:");

                lblPoint.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                lblPoint.setText("0");

                btnExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                btnExit.setText("Thoát");

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblPoint)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(btnExit)
                                                                .addGap(24, 24, 24)));
                jPanel6Layout.setVerticalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel6Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel6)
                                                                                .addComponent(lblPoint)
                                                                                .addComponent(btnExit))
                                                                .addGap(3, 3, 3)));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addComponent(txtAnswer)
                                                                .addGap(34, 34, 34)
                                                                .addComponent(btnSent)
                                                                .addGap(42, 42, 42))
                                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jPanel6,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                25,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(46, 46, 46)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(txtAnswer,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                38,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnSent))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }

        public void btnSentActionPerformed(java.awt.event.ActionEvent evt) {
                // Get the input from the text field
                String input = txtAnswer.getText();

                // Optional: Validate or process the input
                if (input.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter an answer!");
                        return;
                }

                // Send the input (example: sending it to a server or handling the logic)
                sendInput(input);

                // Clear the input field after sending
                txtAnswer.setText("");
        }

        public void sendInput(String input) {

                this.gameController.answerQuestion(input);
                // Example: Sending the input (this could be a network call, etc.)
                System.out.println("Sending answer: " + input);

                // For network, you might have something like this:
                // networkClient.sendMessage("ANSWER: " + input);
        }

        // setup Đồng hồ
        public void StartTime(int TimeinSecond) {
                // Stop any previous timer
                if (timer != null && timer.isRunning()) {
                        timer.stop();
                }

                timeRemaining = TimeinSecond;

                // Use Swing Timer for proper UI thread management
                timer = new javax.swing.Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                                // Update the clock on the Event Dispatch Thread
                                SwingUtilities.invokeLater(() -> {
                                        lblClock.setText(String.valueOf(timeRemaining));

                                        // Stop the timer when timeRemaining is 0
                                        if (timeRemaining <= 0) {
                                                lblClock.setText("0"); // Ensure the final display shows "0"
                                                timer.stop();
                                        }
                                });

                                // Decrement the time first
                                timeRemaining--;
                        }
                });
                timer.start();
        }

        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
                // (optional) ">
                /*
                 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
                 * look and feel.
                 * For details see
                 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(NewGameView.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(NewGameView.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(NewGameView.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(NewGameView.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                }

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                NewGameView newGameView = new NewGameView();
                                newGameView.setVisible(true);
                                newGameView.setLocationRelativeTo(null);
                        }
                });
        }

        public void listenLeaveRoom() {
                this.gameController.leaveRoom();
        }

        public javax.swing.JLabel getjLabel2() {
                return jLabel2;
        }

        public javax.swing.JLabel getLblQuestion() {
                return lblQuestion;
        }

        public javax.swing.JLabel getLblNumberQuestion() {
                return lblNumberQuestion;
        }

        public javax.swing.JLabel getLblPoint() {
                return lblPoint;
        }

        public javax.swing.JTextField getTxtAnswer() {
                return txtAnswer;
        }

        public javax.swing.JButton getBtnSent() {
                return btnSent;
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnExit;
        private javax.swing.JButton btnSent;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JLabel lblClock;
        private javax.swing.JLabel lblNumberQuestion;
        private javax.swing.JLabel lblPoint;
        private javax.swing.JLabel lblQuestion;
        private javax.swing.JTextField txtAnswer;
        private int timeRemaining;
        private Timer timer;
        // End of variables declaration//GEN-END:variables
}