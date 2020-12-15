import java.util.ArrayList;

public interface IDatabase {
     boolean Add(User user);
     User GetWithId(int ID);
     ArrayList<User> Get(User user);
     boolean Edit(int id,User User);
     void Show();
     boolean Delete(int id);

}
