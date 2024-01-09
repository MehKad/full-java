package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import users.User;
import users.UserDAO;
import users.UserDAOImpl;

public class Home extends JFrame {

   private JLabel hello;
   private JLabel users;
   private JTextArea usersArea;

   public Home(User u) {
      setTitle("Home");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(300, 400);

      hello = new JLabel("Welcome -- " + u.getUsername());
      users = new JLabel("Users in our database are : ");
      usersArea = new JTextArea();
      usersArea.setEditable(false);

      usersArea.setRows(10);
      usersArea.setColumns(30);

      List<User> usersDB = new ArrayList<>();
      UserDAO udao = new UserDAOImpl();
      usersDB = udao.getUsers();

      StringBuilder usersText = new StringBuilder();
      for (User i : usersDB) {
         usersText.append("Username: ").append(i.getUsername()).append("\n");
      }

      usersArea.setText(usersText.toString());

      setLayout(new GridLayout(6, 2));

      add(hello);
      add(users);
      add(usersArea);

      setVisible(true);
   }
}
