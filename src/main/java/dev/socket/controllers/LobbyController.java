package dev.socket.controllers;

import dev.socket.interfaces.SocketObserver;
import dev.socket.views.DashboardView;

public class LobbyController implements SocketObserver {

  private DashboardView dashboardView;

  public LobbyController() {
  }

  @Override
  public void onMessageReceived(String message) {

  }

  public void openDashboardView() {
    DashboardView dashboardView = new DashboardView();
    dashboardView.setVisible(true);
  }
}
