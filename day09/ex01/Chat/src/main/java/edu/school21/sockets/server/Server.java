package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketApplicationConfig;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Server {
    private Socket clientSocket = null;
    private ServerSocket serverSocket = null;
    private Integer port;
    private UsersService usersService;
    private List<ClientHandler> clients = new LinkedList<>();

    public Server(Integer port) {
        usersService = new AnnotationConfigApplicationContext(SocketApplicationConfig.class).getBean(UsersServiceImpl.class);
        this.port = port;
    }

    public void startServer() {
       try {
           serverSocket = new ServerSocket(port);
           System.out.println("Server started!");
           while (true){
               clientSocket = serverSocket.accept();
               ClientHandler client = new ClientHandler(clientSocket, this);
               clients.add(client);
               new Thread(client).start();
           }
       } catch (IOException e) {
           e.printStackTrace();
           System.exit(-1);
       } finally {
           try {
               clientSocket.close();
               System.out.println("Server stopped");
               serverSocket.close();

           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    public void sendAll(String message){
        for(ClientHandler user : clients){
            user.sendMessage(message);
        }
    }

    public void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);
        try {
            clientHandler.getClient().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

class ClientHandler implements  Runnable {
    private Server server;
    private PrintWriter out;
    private Scanner in;
    private User user;
    private Socket client = null;
    private static int countOfClients = 0;

    public PrintWriter getOut() {
        return out;
    }

    public Socket getClient() {
        return client;
    }

    public ClientHandler(Socket client, Server server) {
        try {
            this.server = server;
            this.client = client;
            countOfClients++;
            this.out = new PrintWriter(client.getOutputStream());
            this.in = new Scanner(client.getInputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            out.println("Hello from server");
            out.flush();
            do {
                out.println("pls signIn or signUp");
                out.flush();
                if (in.hasNext()) {
                    String command = in.nextLine();
                    if (command.equals("signIn")) {
                        user = signIn();
                        if (user == null) {
                            out.println("wrong data");
                            out.flush();
                        } else
                            break;
                    } else if (command.equals("signUp")) {
                        if (signUp()) {
                            out.println("you successfully signed up");
                            out.flush();
                            continue;
                        }
                        out.println("user with this username exists");
                        out.flush();
                    }
                }
            } while (true);
            out.println("start messaging");
            server.sendAll("New user in chat! ez CLap!");
            server.sendAll("Currently using char - " + countOfClients + " users");
            while (true){
                if (in.hasNext()){
                    String clientMessage = in.nextLine();
                    if (clientMessage.equals("exit"))
                        break;
                    System.out.println(clientMessage);
                    server.sendAll(user.getEmail() + ": " + clientMessage);
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            this.closeSelfFunction();
        }
    }

    public void closeSelfFunction(){
        server.removeClient(this);
        countOfClients--;
        server.sendAll("User - кто-то is left.");
    }

    public boolean signUp(){
        out.println("Enter username:");
        out.flush();
        String username = in.nextLine();
        out.println("Enter password:");
        out.flush();
        String password = in.nextLine();
        UsersRepository usersRepository = new AnnotationConfigApplicationContext(SocketApplicationConfig.class)
                .getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        if (usersRepository.findByEmail(username).isPresent())
            return false;
        usersRepository.save(new User(123, username, password));
        return true;
    }

    public User signIn(){
        out.println("Enter username:");
        out.flush();
        String username = in.nextLine();
        out.println("Enter password:");
        out.flush();
        String password = in.nextLine();
        UsersService userService = new AnnotationConfigApplicationContext(SocketApplicationConfig.class)
                .getBean("Service", UsersService.class);
        if (userService.signIn(username, password))
            return new User(12, username, password);
        return null;
    }

    public void sendMessage(String message){
        out.println(message);
        out.flush();
    }

}