public class User {
    private String name=null;
    private String surname=null;
    private String email=null;
    private String role1=null;
    private String role2=null;
    private String role3=null;
    private String phoneNumber1=null;
    private String phoneNumber2=null;
    private String phoneNumber3=null;
    private int ID;


    public void SetID(int id){this.ID=id;}
    public int GetId(){
        return ID;
    }

    public void SetName(String name){ this.name=name; }
    public String GetName(){
        return name;
    }
    public void SetSurname(String surname){
        this.surname=surname;
    }
    public String GetSurname(){
        return surname;
    }
    public void SetEmail(String email){
    this.email=email;
    }
    public String GetEmail(){
        return email;
    }
    public void SetRole1(String role1){
        this.role1=role1;
    }
    public String GetRole1(){
        return role1;
    }
    public void SetRole2(String role2){
        this.role2=role2;
    }
    public String GetRole2(){
        return role2;
    }
    public void SetRole3(String role3){ this.role3=role3; }
    public String GetRole3(){
        return role3;
    }
    public void SetPhoneNumber1(String phoneNumber1){this.phoneNumber1=phoneNumber1;}
    public String GetPhoneNumber1(){
        return phoneNumber1;
    }
    public void SetPhoneNumber2(String phoneNumber2){
        this.phoneNumber2=phoneNumber2;
    }
    public String GetPhoneNumber2(){
        return phoneNumber2;
    }
    public void SetPhoneNumber3(String phoneNumber3){
        this.phoneNumber3=phoneNumber3;
    }
    public String GetPhoneNumber3(){
        return phoneNumber3;
    }

    public void println(){
       System.out.print(ID+" ");
       if(name!="isEmpty"){
           System.out.print(name+" ");
       }
        if(surname!="isEmpty"){
            System.out.print(surname+" ");
        }
        if(email!="isEmpty"){
            System.out.print(email+" ");
        }
        if(phoneNumber1!="isEmpty"){
            System.out.print(phoneNumber1+" ");
        }
        if(phoneNumber2!="isEmpty"){
            System.out.print(phoneNumber2+" ");
        }
        if(phoneNumber3!="isEmpty"){
            System.out.print(phoneNumber3+" ");
        }
        if(role1!="isEmpty"){
            System.out.print(role1+" ");
        }
        if(role2!="isEmpty"){
            System.out.print(role2+" ");
        }
        if(role3!="isEmpty"){
            System.out.print(role3+" ");
        }
        System.out.println();

    }
}
