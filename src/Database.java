import java.util.ArrayList;

public class Database implements IDatabase {
    ArrayList<User>database;

    public void Add(User user){
        database.add(user);
    }

    public User GetWithId(int ID){
        return database.get(ID);
    }

    public void Get(User user){
        for(int i=0;i<database.size();++i){



        }

    }

    private boolean CompareUsers(User user,User user2){
        if(user.GetName().equals(user2.GetName())&&user.GetSurname().equals(user2.GetSurname())&&
                user.GetEmail().equals(user2.GetEmail())&&
                user.GetPhoneNumber1().equals(user2.GetPhoneNumber1())&&
                user.GetPhoneNumber2().equals(user2.GetPhoneNumber2())&&
                user.GetPhoneNumber3().equals(user2.GetPhoneNumber3())&&
                user.GetRole1().equals(user2.GetRole1())&&
                user.GetRole2().equals(user2.GetRole2())&&
                user.GetRole3().equals(user2.GetRole3())){
        return true;
        }
        return false;
    }
}
