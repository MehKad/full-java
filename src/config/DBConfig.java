package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
   private static final String className = "com.mysql.jdbc.Driver";
   private static final String url = "jdbc:mysql://localhost:3306/users";
   private static final String username = "root";
   private static final String password = "";

   private static Connection con;
   private static DBConfig instance;

   private DBConfig() {
      try {
         Class.forName(className);
         con = DriverManager.getConnection(url, username, password);
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
   };

   public static DBConfig getInstance() {
      if (instance == null) {
         instance = new DBConfig();
      }
      return instance;
   }

   public static Connection getCon() {
      return con;
   }
}