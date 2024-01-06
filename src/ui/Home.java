package ui;

import javax.swing.*;

import users.User;

public class Home extends JFrame {

   private JLabel hello;

   public Home(User u) {
      setTitle("Home");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(300, 400);

      hello = new JLabel("Welcome -- " + u.getUsername());

      add(hello);

      setVisible(true);
   }
}
