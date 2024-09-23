package dev.socket.controllers;

import dev.socket.views.DashboardView;

public class DashboardController {

  private DashboardView dashboardView;

  public DashboardController() {
  }

  public void openDashboardView() {
    DashboardView dashboardView = new DashboardView();
    dashboardView.setVisible(true);
  }
}
