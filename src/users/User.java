package users;

public class User {
   private String username, password;

   public User(String A) {
      this.username = A;
   }

   public User(String A, String B) {
      this.username = A;
      this.password = B;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

}
