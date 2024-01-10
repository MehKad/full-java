package users;

import java.util.List;

public interface UserDAO {
   
   boolean Login(User user);
   boolean registerUser(User user);
   List<User> getUsers();
}
