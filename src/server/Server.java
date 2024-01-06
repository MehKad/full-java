package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import ui.Home;
import users.*;

public class Server {

   public Server() {
      login();
   }

   private void login() {
      try {
         ServerSocket soc = new ServerSocket(5000);

         System.out.println("Server is waiting for connections...");

         while (true) {

            Socket clientSoc = soc.accept();

            System.out.println("Client connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));

            String username = in.readLine();
            String password = in.readLine();

            System.out.println("Received data from client");

            User user = new User(username, password);

            UserDAO udao = new UserDAOImpl();
            if (udao.Login(user)) {
               System.out.println("User logged in successfully!");
               new Home(user);
            } else {
               System.out.println("Wrong username or password.");
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
      new Server();
   }
}