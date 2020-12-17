import java.io.File;
import java.util.ArrayList;

public interface IDatabase {
     boolean Add(User user);
     User GetWithId(int ID);
     ArrayList<User> Get(User user);
     boolean Edit(int id,User User);
     void Show();
     boolean Delete(int id);
     String getLAST_FOLDER_USED();
     void setLAST_FOLDER_USED(String path);
     boolean SaveToFile(File selectedDir);
     boolean ReadFromFile(File selectedDir);
}
