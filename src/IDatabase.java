public interface IDatabase {
     void Add(User user);
     User GetWithId(int ID);
     void Get(User user);
}
