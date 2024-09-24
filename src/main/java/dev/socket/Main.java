package dev.socket;

import dev.socket.network.SocketClient;

// import dev.socket.network.SocketClient;

public class Main {
    public static void main(String[] args) {

        // Instead of open connect to socket server right away, only connect when user
        // is logged in.

        // int port = 6987;
        // SocketClient client = new SocketClient("localhost", port,
        // "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5YjVjODYyMi03Mzc5LTExZWYtYmEwOC0xMDNkMWNlZTJhMzYiLCJlbWFpbCI6Imtob2l6cHJvMTNAZ21haWwuY29tIiwibmFtZSI6Iktob2kgRG8iLCJpYXQiOjE3MjcxNDIyODksImV4cCI6MTcyOTczNDI4OX0.osdls2fkPFtSyUry9Xtz-0rVnbxFubYurdh6SnguYezblvp4--_McbAic8vDEAeqYYdx6O8oKx8QwVybLMTzBQ");
        // new Thread(() -> client.start()).start();

        /////////////////////////////////////////////////////////

        new AppIndex();
    }
}