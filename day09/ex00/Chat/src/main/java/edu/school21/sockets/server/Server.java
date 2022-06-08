package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketApplicationConfig;
import edu.school21.sockets.repositories.UserRepositoryJdbcTemplateImpl;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    private Socket clientSocket = null;
    private ServerSocket serverSocket = null;
    private BufferedReader reader = null;
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Integer port;

    public Server(Integer port) {
        this.port = port;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(System.in));
            out.write("Hello from my server!\n");
            out.flush();
            String startmsg = in.readLine();
            System.out.printf(startmsg);
            out.write("Enter username:\n");
            out.flush();
            String username = in.readLine();
            out.write("Enter password:\n");
            out.flush();
            String password = in.readLine();
            System.out.println(password);
            ApplicationContext context = new AnnotationConfigApplicationContext(SocketApplicationConfig.class);
            UsersService usersService = context.getBean("Service", UsersService.class);
            if (usersService.signUp(username, password))
                out.write("successes");
            else
                out.write("failure");
            out.flush();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                clientSocket.close();
                serverSocket.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
