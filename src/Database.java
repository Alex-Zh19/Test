import java.util.ArrayList;

public class Database implements IDatabase {
    ArrayList<User>database=new ArrayList<>();

    final String IS_EMPTY="isEmpty";
@Override
    public boolean Add(User user){
        if(CheckUniqueEmail(user)&&CheckUniquePhoneNum1(user)&&
        CheckUniquePhoneNum2(user)&&CheckUniquePhoneNum3(user)&&
                CheckEmail(user.GetEmail())&&CheckPhoneNumber(user.GetPhoneNumber1())&&
                CheckPhoneNumber(user.GetPhoneNumber2())&&CheckPhoneNumber(user.GetPhoneNumber3())){
            user.SetID(database.size());
            database.add(user);
        return true;
        }
        return false;
    }
@Override
    public User GetWithId(int ID){
        return database.get(ID);
    }
@Override
    public ArrayList<User> Get(User user){
        ArrayList<User> Get= FindMostSimilar(database,user);
     return Get;
    }

    @Override
    public boolean Edit(int id,User user){
        if(CheckUniqueEmail(user,id)&&CheckUniquePhoneNum1(user,id)&&
                CheckUniquePhoneNum2(user,id)&&CheckUniquePhoneNum3(user,id)&&
                CheckEmail(user.GetEmail())&&CheckPhoneNumber(user.GetPhoneNumber1())&&
                CheckPhoneNumber(user.GetPhoneNumber2())&&CheckPhoneNumber(user.GetPhoneNumber3())){
            User oldOne=database.get(id);
            oldOne.SetName(user.GetName());
            oldOne.SetSurname(user.GetSurname());
            oldOne.SetEmail(user.GetEmail());
            oldOne.SetPhoneNumber1(user.GetPhoneNumber1());
            oldOne.SetPhoneNumber2(user.GetPhoneNumber2());
            oldOne.SetPhoneNumber3(user.GetPhoneNumber3());
            oldOne.SetRole1(user.GetRole1());
            oldOne.SetRole2(user.GetRole2());
            oldOne.SetRole3(user.GetRole3());
            return true;
        }
        return false;

    }

    @Override
    public void Show() {
        for(User user:database){
            user.println();
        }
    }

    @Override
    public boolean Delete(int id) {
    if(database!=null&&id>=0&&id<=database.size()-1){
     database.remove(id);
     for(User user:database){
         user.SetID(database.indexOf(user));
     }
        return true;
    }
    return false;
    }


    private boolean CheckUniqueEmail(User user){
    if(database.size()==0){
        return true;
    }
    if(user.GetEmail()!=IS_EMPTY)  {
    for(int i=0;i<database.size();++i){
        if(user.GetEmail().equals(database.get(i).GetEmail())){
          return false;
        }
      }
    }
      return true;
}
    private boolean CheckUniquePhoneNum1(User user){
        if(database.size()==0){
            return true;
        }
       if(user.GetPhoneNumber1()!=IS_EMPTY) {
           for (int i = 0; i < database.size(); ++i) {
               if (user.GetPhoneNumber1().equals(database.get(i).GetPhoneNumber1())) {
                   return false;
               }
           }
       }
        return true;
    }
    private boolean CheckUniquePhoneNum2(User user){
        if(database.size()==0){
            return true;
        }
        if(user.GetPhoneNumber2()!=IS_EMPTY){
            for(int i=0;i<database.size();++i){
                if(user.GetPhoneNumber2().equals(database.get(i).GetPhoneNumber2())){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean CheckUniquePhoneNum3(User user){
        if(database.size()==0){
            return true;
        }
        if(user.GetPhoneNumber3()!=IS_EMPTY){
            for(int i=0;i<database.size();++i){
                if(user.GetPhoneNumber3().equals(database.get(i).GetPhoneNumber3())){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean CheckUniqueEmail(User user,int id){
        if(database.size()==0){
            return true;
        }
        if(user.GetEmail()!=IS_EMPTY)  {
            for(int i=0;i<database.size();++i){
                if(i==id){
                    continue;
                }
                if(user.GetEmail().equals(database.get(i).GetEmail())){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean CheckUniquePhoneNum1(User user,int id){
        if(database.size()==0){
            return true;
        }
        if(user.GetPhoneNumber1()!=IS_EMPTY) {
            for (int i = 0; i < database.size(); ++i) {
                if(i==id){
                    continue;
                }
                if (user.GetPhoneNumber1().equals(database.get(i).GetPhoneNumber1())) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean CheckUniquePhoneNum2(User user,int id){
        if(database.size()==0){
            return true;
        }
        if(user.GetPhoneNumber2()!=IS_EMPTY){
            for(int i=0;i<database.size();++i){
                if(i==id){
                    continue;
                }
                if(user.GetPhoneNumber2().equals(database.get(i).GetPhoneNumber2())){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean CheckUniquePhoneNum3(User user,int id){
        if(database.size()==0){
            return true;
        }
        if(user.GetPhoneNumber3()!=IS_EMPTY){
            for(int i=0;i<database.size();++i){
                if(i==id){
                    continue;
                }
                if(user.GetPhoneNumber3().equals(database.get(i).GetPhoneNumber3())){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean UserIsEmpty(User user){
        if(user.GetName()==IS_EMPTY&&user.GetSurname()==IS_EMPTY&&user.GetEmail()==IS_EMPTY&&user.GetRole1()==IS_EMPTY
                &&user.GetRole2()==IS_EMPTY&&user.GetRole3()==IS_EMPTY&&
                user.GetPhoneNumber1()==IS_EMPTY&&user.GetPhoneNumber2()==IS_EMPTY&&
                user.GetPhoneNumber3()==IS_EMPTY){
            return true;
        }
        return false;
    }

    private ArrayList<User> FindMostSimilar(ArrayList<User> database,User user){
        if(database.size()==0){
            return null;
        }
        if(UserIsEmpty(user)){
            return null;
        }
        ArrayList<User>mostSimilar=new ArrayList<>();
        boolean isTeleNum=true;
        boolean isEmail=true;
        if(user.GetEmail()==IS_EMPTY){
            isEmail=false;
        }
        if(user.GetPhoneNumber1()==IS_EMPTY&&user.GetPhoneNumber2()==IS_EMPTY&&user.GetPhoneNumber3()==IS_EMPTY){
            isTeleNum=false;
        }
        if(isTeleNum){
            User us=CheckTelephones(user);
            if(us!=null){
            mostSimilar.add(us);
            }

        }
        if(isEmail){
            for(int i=0;i<database.size();++i){
                if(database.get(i).GetEmail().equals(user.GetEmail())){
                   boolean mark=false;
                    for(int j=0;j<mostSimilar.size();++j){
                       if(CompareAllUsersParams(database.get(i),mostSimilar.get(j))){
                           mark=true;
                       }
                   }
                    if(!mark){
                    mostSimilar.add(database.get(i));}
                }
            }
        }

        //if users fields phone number and email are empty
        if(user.GetName()!=IS_EMPTY){
            if(isEmail||isTeleNum){
                System.out.println(mostSimilar.size());
                for(int i=0;i<mostSimilar.size();++i){
                    if(mostSimilar.get(i).GetName().equals(user.GetName())){
                        continue;
                    }else{
                        mostSimilar.remove(i);
                    }
                }
            }else{
            for(int i=0;i<database.size();++i){
                if(database.get(i).GetName().equals(user.GetName())){
                    mostSimilar.add(database.get(i));
                }
            }
            }
            if(mostSimilar.size()==0){
            return mostSimilar;
            }
            if(user.GetSurname()!=IS_EMPTY){
                for(int i=0;i<mostSimilar.size();++i){
                    if(mostSimilar.get(i).GetSurname().equals(user.GetSurname())){
                        continue;
                    }
                    else{
                        mostSimilar.remove(i);
                    }
                }
            }
            if(mostSimilar.size()==0){
                return mostSimilar;
            }
           mostSimilar=CheckRoles(mostSimilar,user);
            return  mostSimilar;
        }

        if(user.GetSurname()!=IS_EMPTY){
            for(int i=0;i<database.size();++i){
                if(database.get(i).GetSurname().equals(user.GetSurname())){
                    mostSimilar.add(database.get(i));
                }
            }
            if(mostSimilar.size()==0){
                return mostSimilar;
            }
            mostSimilar=CheckRoles(mostSimilar,user);
            return mostSimilar;
        }
        mostSimilar= CheckRoles(mostSimilar,user);
        return mostSimilar;
    }

private User CheckTelephones(User user){
    String tel1=user.GetPhoneNumber1();
    String tel2=user.GetPhoneNumber2();
    String tel3=user.GetPhoneNumber3();
    if(tel1!=IS_EMPTY){
        for(int i=0;i<database.size();++i){
            if(database.get(i).GetPhoneNumber1().equals(tel1)||database.get(i).GetPhoneNumber2().equals(tel1)
                    ||database.get(i).GetPhoneNumber3().equals(tel1)){
                return database.get(i);
            }
        }
    }else if(tel2!=IS_EMPTY){
        for(int i=0;i<database.size();++i){
            if(database.get(i).GetPhoneNumber1().equals(tel2)||database.get(i).GetPhoneNumber2().equals(tel2)
                    ||database.get(i).GetPhoneNumber3().equals(tel2)){
                return database.get(i);
            }
        }
    }else if(tel3!=IS_EMPTY){
        for(int i=0;i<database.size();++i){
            if(database.get(i).GetPhoneNumber1().equals(tel3)||database.get(i).GetPhoneNumber2().equals(tel3)
                    ||database.get(i).GetPhoneNumber3().equals(tel3)){
                return database.get(i);
            }
        }
    }
    return null;
}



private ArrayList<User>CheckRoles(ArrayList<User>Similar,User user){
     if(Similar.size()!=0){
         String role1= user.GetRole1();
         String role2= user.GetRole2();
         String role3= user.GetRole3();
             if(role1!=IS_EMPTY) {
                for (int i = 0; i < Similar.size(); ++i) {
                   if (Similar.get(i).GetRole1().equals(role1) || Similar.get(i).GetRole2().equals(role1) ||
                         Similar.get(i).GetRole3().equals(role1)) {
                     continue;
                   } else {
                     Similar.remove(i);
                   }
                }
             }
             if(role2!=IS_EMPTY){
                 for (int i=0;i<Similar.size();++i){
                     if(Similar.get(i).GetRole1().equals(role2)||Similar.get(i).GetRole2().equals(role2)||
                             Similar.get(i).GetRole3().equals(role2)){
                         continue;
                     }
                     else{
                         Similar.remove(i);
                     }
                 }
             }
             if(role3!=IS_EMPTY){
                 for (int i=0;i<Similar.size();++i){
                     if(Similar.get(i).GetRole1().equals(role3)||Similar.get(i).GetRole2().equals(role3)||
                             Similar.get(i).GetRole3().equals(role3)){
                         continue;
                     }
                     else{
                         Similar.remove(i);
                     }
                 }
             }

        return Similar;
     }
     ArrayList<User>mostSimilar=new ArrayList<>();
     String role1= user.GetRole1();
     String role2= user.GetRole2();
     String role3= user.GetRole3();
     for(int i=0;i<database.size();++i){
         if(database.get(i).GetRole1().equals(role1)||database.get(i).GetRole1().equals(role2)||
                 database.get(i).GetRole1().equals(role3)||
                 database.get(i).GetRole2().equals(role1)||database.get(i).GetRole2().equals(role2)||
                 database.get(i).GetRole2().equals(role3)||
                 database.get(i).GetRole3().equals(role1)||database.get(i).GetRole3().equals(role2)||
                 database.get(i).GetRole3().equals(role3)){
             mostSimilar.add(database.get(i));
         }
     }

     if(mostSimilar.size()!=0){
       return CheckRoles(mostSimilar,user);
     }
     return null;
    }


    private boolean CheckPhoneNumber(String dataNum){
        if(dataNum!=IS_EMPTY) {
            String firstNum = dataNum.substring(0, 3);
            if (firstNum.equals("375") && dataNum.length() == 12) {
                return true;
            }
            else{
                return false;
            }
        }
       return true;
    }
    private boolean CheckEmail(String dataEmail){
        if(dataEmail!=IS_EMPTY){
            int dogy=dataEmail.indexOf('@');
            int dot=dataEmail.indexOf('.');
            if(dot>dogy+1&&dogy!=0&&dot!=0&&dogy!=dataEmail.length()-1&&dot!=dataEmail.length()-1){
                return true;
            }
        }
        return false;
    }




    private boolean CompareAllUsersParams(User user,User user2){
       if(user.GetName()!=IS_EMPTY&&user2.GetName()!=IS_EMPTY&&user.GetSurname()!=IS_EMPTY&&user2.GetSurname()!=IS_EMPTY
               &&user.GetEmail()!=IS_EMPTY&&user2.GetEmail()!=IS_EMPTY&&
               user.GetPhoneNumber1()!=IS_EMPTY&&user2.GetPhoneNumber1()!=IS_EMPTY&&
               user.GetRole1()!=IS_EMPTY&&user2.GetRole1()!=IS_EMPTY){
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
       }
        return false;
    }




}
