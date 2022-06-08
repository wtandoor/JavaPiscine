package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Server server = new Server(8081);
        server.startServer();
    }
}
