package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

public class Login extends JFrame {

   private JLabel usernameL;
   private JLabel passwordL;
   private JTextField usernameF;
   private JTextField passwordF;

   private JButton login;
   private JButton register;

   public Login() {
      setTitle("Login");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(300, 400);

      usernameL = new JLabel("Username : ");
      passwordL = new JLabel("Password : ");

      usernameF = new JTextField();
      passwordF = new JTextField();

      login = new JButton("Login");
      register = new JButton("Register");

      setLayout(new GridLayout(8, 2));

      addEmptyLabel();
      addEmptyLabel();
      add(usernameL);
      add(usernameF);
      addEmptyLabel();
      addEmptyLabel();
      add(passwordL);
      add(passwordF);
      addEmptyLabel();
      addEmptyLabel();
      add(login);
      add(register);
      addEmptyLabel();
      addEmptyLabel();

      login.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String username = usernameF.getText();
            String password = passwordF.getText();
            try {
               Socket soc = new Socket("localhost", 5000);

               PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

               out.println(username);
               out.println(password);

               out.close();
               soc.close();

            } catch (Exception exept) {
               exept.printStackTrace();
            }
         }
      });

      setVisible(true);
   }

   private void addEmptyLabel() {
      add(new JLabel());
   }

   public static void main(String[] args) {
      new Login();
   }

}
