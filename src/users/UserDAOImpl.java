package users;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import config.DBConfig;

public class UserDAOImpl implements UserDAO {

   public Connection getconn;

   public UserDAOImpl() {
      getconn = DBConfig.getInstance().getCon();
   }

   @Override
   public boolean Login(User user) {
      String sql = "select count(*) from users where username = ? and password = ?";
      try {
         PreparedStatement stm = getconn.prepareStatement(sql);
         stm.setString(1, user.getUsername());
         stm.setString(2, user.getPassword());
         ResultSet rs = stm.executeQuery();
         if (rs.next()) {
            int c = rs.getInt(1);
            if (c > 0) {
               return true;
            } else {
               return false;
            }
         }
         rs.close();
         stm.close();
         getconn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   @Override
   public boolean registerUser(User user) {
      String sql = "insert into users ( username, password ) values (?,?) ";
      try {
         PreparedStatement stm = getconn.prepareStatement(sql);
         stm.setString(1, user.getUsername());
         stm.setString(2, user.getPassword());
         if (stm.executeUpdate() == 1) {
            return true;
         } else {
            return false;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   @Override
   public List<User> getUsers() {
      String sql = "select username from users";
      List<User> users = new ArrayList<>();
      try {
         Statement stm = getconn.createStatement();
         ResultSet rs = stm.executeQuery(sql);
         while (rs.next()) {
            String username = rs.getString(1);
            User newUser = new User(username);
            users.add(newUser);
         }
         rs.close();
         stm.close();
         getconn.close();
         return users;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }

}
