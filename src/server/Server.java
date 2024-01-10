package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import ui.*;
import users.*;

public class Server {

   public static void main(String[] args) {
      new Server().begin();
   }

   public void begin() {
      try {
         ServerSocket serverSocket = new ServerSocket(5000);
         System.out.println("Server is waiting for connections...");

         while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            // Create a new thread to handle the client
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandler.start();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private class ClientHandler extends Thread {
      private final Socket clientSocket;

      public ClientHandler(Socket clientSocket) {
         this.clientSocket = clientSocket;
      }

      @Override
      public void run() {
         try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String requestType = in.readLine();

            if ("LOGIN".equals(requestType)) {
               handleLoginRequest(in);
            } else if ("REGISTER".equals(requestType)) {
               handleRegisterRequest(in);
            } else {
               System.out.println("Unknown request type");
            }

         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {
               clientSocket.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
            System.out.println("Connection closed");
         }
      }

      private void handleLoginRequest(BufferedReader in) {
         try {
            String username = in.readLine();
            String password = in.readLine();

            System.out.println("Received data from Login client");

            User user = new User(username, password);

            UserDAO userDAO = new UserDAOImpl();
            if (userDAO.Login(user)) {
               System.out.println("User logged in successfully!");
               new Home(user);
            } else {
               System.out.println("Wrong username or password.");
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      private void handleRegisterRequest(BufferedReader in) {
         try {
            String username = in.readLine();
            String password = in.readLine();

            System.out.println("Received data from Register client");

            User user = new User(username, password);

            UserDAO userDAO = new UserDAOImpl();
            if (userDAO.registerUser(user)) {
               System.out.println("User added successfully!");
            } else {
               System.out.println("User not added.");
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }
}
