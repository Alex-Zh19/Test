import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class GUI extends JFrame {
    private JTextField name=new JTextField();
    private JTextField surname=new JTextField();
    private JTextField email=new JTextField();
    private JTextField tel1=new JTextField();
    private JTextField tel2=new JTextField();
    private JTextField tel3=new JTextField();
    private JTextField role1=new JTextField();
    private JTextField role2=new JTextField();
    private JTextField role3=new JTextField();
    private JTextField idFindField=new JTextField();

    private JButton applyButton=new JButton("Apply");
    private JButton findButton=new JButton("Find");
    private JButton addButton=new JButton("Add");
    private JButton deleteButton=new JButton("Delete");
    private JButton resetButton=new JButton("Reset");
    private JButton plusroleButton=new JButton("Add Role");
    private JButton plustelButton=new JButton("Add telephone");
    private JButton showButton=new JButton("Show all");



    private JMenuItem saveMenuItem;
    private JMenuItem openMenuItem;

    private JCheckBox findID=new JCheckBox();
    IDatabase base=new Database();

    boolean isOpen=false;

    private Box dopfields=Box.createVerticalBox();

    boolean isAllowedToEditAndDelete=false;
     int saveId;

    private int countTele=2;
    private int countRole=2;
    GUI(){
        setTitle("Users");



       Box TextBox=Box.createVerticalBox();
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Name",name));
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Surname",surname));
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Email",email));
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Telephone",tel1));
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Role",role1));

       Box ButtonPlusBox=Box.createVerticalBox();
        ButtonPlusBox.add(Box.createVerticalStrut(5));
        ButtonPlusBox.add(plustelButton);
        ButtonPlusBox.add(Box.createVerticalStrut(5));
        ButtonPlusBox.add(plusroleButton );
        ButtonPlusBox.add(Box.createVerticalStrut(5));

        Box findWithIdBox=Box.createVerticalBox();
        findWithIdBox.add(createTextField("Set ID",idFindField));
        idFindField.setEnabled(false);
        findWithIdBox.add(Box.createVerticalStrut(5));
        findWithIdBox.add(new JLabel("Find With ID"));
        findWithIdBox.add(Box.createVerticalStrut(5));
        findWithIdBox.add(findID);
        ButtonPlusBox.add(findWithIdBox);



        Box mainButtonBox=Box.createHorizontalBox();
        mainButtonBox.add(Box.createHorizontalStrut(5));
        mainButtonBox.add(resetButton);
        mainButtonBox.add(Box.createHorizontalStrut(35));
        mainButtonBox.add(applyButton);
        mainButtonBox.add(Box.createHorizontalStrut(5));
        mainButtonBox.add(findButton);
        mainButtonBox.add(Box.createHorizontalStrut(5));
        mainButtonBox.add(showButton);
        mainButtonBox.add(Box.createHorizontalStrut(5));
        mainButtonBox.add(addButton);
        mainButtonBox.add(Box.createHorizontalStrut(5));
        mainButtonBox.add(deleteButton);



       //name.addFocusListener(new TextListener());
       plustelButton.addActionListener(new plusTelephoneListener());
       plusroleButton.addActionListener(new plusRoleListener());
        addButton.addActionListener(new AddButtonListener());
        applyButton.addActionListener(new ApplyButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        findButton.addActionListener(new FindButtonListener());
        resetButton.addActionListener(new ResetButtonListener());
        showButton.addActionListener(new ShowButtonListener());
        findID.addItemListener(new CheckBoxListener());

        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JMenu fileMenu = new JMenu("Файл");
        menu.add(fileMenu);
        
        saveMenuItem = fileMenu.add(saveAction);
        openMenuItem=fileMenu.add(openAction);

       add(TextBox, BorderLayout.WEST);
       add(ButtonPlusBox,BorderLayout.EAST);
       add(dopfields,BorderLayout.CENTER);
       add(mainButtonBox,BorderLayout.SOUTH);
       pack();

   }


    CompletableFuture <IDatabase> addDefaultUsersAsync =CompletableFuture.supplyAsync(() -> {
        addDefaultUsers();
        return base;
    });

 private void addDefaultUsers(){
       User user1=new User();
       user1.SetName("Anton");
       user1.SetSurname("Petrov");
       user1.SetEmail("Anton@mail.ru");
       user1.SetPhoneNumber1("375445587896");
     user1.SetPhoneNumber2("isEmpty");
     user1.SetPhoneNumber3("isEmpty");
       user1.SetRole1("boss");
     user1.SetRole2("isEmpty");
     user1.SetRole3("isEmpty");
       base.Add(user1);
     User user2=new User();
     user2.SetName("Marat");
     user2.SetSurname("Popov");
     user2.SetEmail("Popov@mail.ru");
     user2.SetPhoneNumber1("375298574896");
     user2.SetPhoneNumber2("isEmpty");
     user2.SetPhoneNumber3("isEmpty");
     user2.SetRole1("Developer");
     user2.SetRole2("isEmpty");
     user2.SetRole3("isEmpty");
     base.Add(user2);
     User user3=new User();
     user3.SetName("Alex");
     user3.SetSurname("Lubov");
     user3.SetEmail("LubovA@mail.ru");
     user3.SetPhoneNumber1("375446542918");
     user3.SetPhoneNumber2("isEmpty");
     user3.SetPhoneNumber3("isEmpty");
     user3.SetRole1("Trainee");
     user3.SetRole2("isEmpty");
     user3.SetRole3("isEmpty");
     base.Add(user3);
 }


    Action saveAction = new AbstractAction("Сохранить") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
           if(!isOpen){
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setDialogTitle("Сохранение...");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(base.getLAST_FOLDER_USED()!=null){
                fileChooser.setCurrentDirectory(new File(base.getLAST_FOLDER_USED()));}
            if (fileChooser.showSaveDialog(GUI.this) == JFileChooser.APPROVE_OPTION) {
                File folder = fileChooser.getSelectedFile();

                if(base.SaveToFile(folder)){
                    System.out.println("Saved successfully");
                }
                else{
                    JOptionPane.showMessageDialog(GUI.this, "Saving error");
                }
            }
            else{
                JOptionPane.showMessageDialog(GUI.this, "Сохранение отменено");
            }
           }else{
               File folder=new File("");
               isOpen=false;
               if(base.SaveToFile(folder)){
                   System.out.println("Saved successfully");
               }
               else{
                   JOptionPane.showMessageDialog(GUI.this, "Saving error");
               }
           }
        }
    };


    Action openAction=new AbstractAction("Открыть") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            isOpen=true;
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setDialogTitle("Открытие...");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            if(base.getLAST_FOLDER_USED()!=null){
                fileChooser.setCurrentDirectory(new File(base.getLAST_FOLDER_USED()));}
            if (fileChooser.showOpenDialog(GUI.this) == JFileChooser.OPEN_DIALOG) {
                File file = fileChooser.getSelectedFile();
               if(base.ReadFromFile(file)){
                   System.out.println("Open successfully");
               }
               else{
                    System.out.println("Opening error");
               }
            } else{
                JOptionPane.showMessageDialog(GUI.this, "Открытие отменено");
            }
        }
    };




    private Box createTextField(String TextName,JTextField field){
       Box textBox=Box.createVerticalBox();
       textBox.add(new JLabel(TextName));
       textBox.add(Box.createVerticalStrut(5));
       textBox.add(field);
       field.setColumns(17);
       return textBox;
   }


private void Reset (){
    name.setText("");
    surname.setText("");
    email.setText("");
    tel1.setText("");
    role1.setText("");
    tel2.setText("");
    tel3.setText("");
    role2.setText("");
    role3.setText("");
    idFindField.setText("");
    countTele=2;
    countRole=2;
    dopfields.removeAll();
}

private void SetUserToFields(User user){
        if(user.GetName()!="isEmpty"){
            name.setText(user.GetName());
        }
    if(user.GetSurname()!="isEmpty"){
        surname.setText(user.GetSurname());
    }
    if(user.GetEmail()!="isEmpty"){
        email.setText(user.GetEmail());
    }
    if(user.GetPhoneNumber1()!="isEmpty"){
        tel1.setText(user.GetPhoneNumber1());
    }
    if(user.GetPhoneNumber2()!="isEmpty"){
        plustelButton.doClick();
        tel2.setText(user.GetPhoneNumber2());
    }
    if(user.GetPhoneNumber3()!="isEmpty"){
        plustelButton.doClick();
        tel3.setText(user.GetPhoneNumber3());
    }
    if(user.GetRole1()!="isEmpty"){
        role1.setText(user.GetRole1());
    }
    if(user.GetRole2()!="isEmpty"){
        plusroleButton.doClick();
        role2.setText(user.GetRole2());
    }
    if(user.GetRole3()!="isEmpty"){
        plusroleButton.doClick();
        role3.setText(user.GetRole3());
    }

}


    class plusTelephoneListener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent actionEvent) {
            switch (countTele){
                case 2:{
                    dopfields.add(createTextField("Telephone "+countTele,tel2));
                    break;
                }
                case 3:{
                    dopfields.add(createTextField("Telephone "+countTele,tel3));
                    break;
                }
                default:{
                    System.out.println("Can't add");;
                }
            }
           countTele++;
           pack();
           }
       }

    class plusRoleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
           switch (countRole){
               case 2:{
                   dopfields.add(createTextField("Role "+countRole,role2));
                   break;
               }
               case 3:{
                   dopfields.add(createTextField("Role "+countRole,role3));
                   break;
               }
               default:{
                   System.out.println("Can't add");;
               }

            }
            countRole++;
            pack();
        }
    }

    private String CheckSymbolsInString(String str){
        if(str.trim().length()==0){
            str="isEmpty";
        }
        return str;
    }

    private User AcceptUser(){
        User us=new User();
        us.SetName(CheckSymbolsInString(name.getText()));
        us.SetSurname(CheckSymbolsInString(surname.getText()));
        us.SetEmail(CheckSymbolsInString(email.getText()));
        us.SetRole1(CheckSymbolsInString(role1.getText()));
        us.SetRole2(CheckSymbolsInString(role2.getText()));
        us.SetRole3(CheckSymbolsInString(role3.getText()));
        us.SetPhoneNumber1(CheckSymbolsInString(tel1.getText()));
        us.SetPhoneNumber2(CheckSymbolsInString(tel2.getText()));
        us.SetPhoneNumber3(CheckSymbolsInString(tel3.getText()));
        return us;
    }


    class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            isAllowedToEditAndDelete=false;
            if(!base.Add(AcceptUser())){
                JOptionPane.showMessageDialog(GUI.this,
                        "Adding error", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    class FindButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(findID.isSelected()){
               Integer id=Integer.parseInt(idFindField.getText());
               if(base.GetWithId(id)!=null){
                saveId=base.GetWithId(id).GetId();
                SetUserToFields(base.GetWithId(id));
                base.GetWithId(id).println();
                isAllowedToEditAndDelete=true;
               }
            }
            else{
                ArrayList<User>users=base.Get(AcceptUser());
                if(users!=null&&users.size()!=0){
                  if(users.size()==1){
                      isAllowedToEditAndDelete=true;
                     SetUserToFields(users.get(0));
                     saveId=users.get(0).GetId();
                      for(User us:users){
                          us.println();
                      }
                  }else{
                    for(User us:users){
                    us.println();
                    }
                  }
                }else{
                    System.out.println("User isn't found");
                }
            }
        }
    }

    class ApplyButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(isAllowedToEditAndDelete) {
                isAllowedToEditAndDelete = false;
                if (base.Edit(saveId, AcceptUser())) {
                    System.out.println("Changes accepted");
                } else {
                    System.out.println("Changes not accepted ");
                }
            }else{
                System.out.println("Select user");
            }
        }
    }

    class DeleteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(isAllowedToEditAndDelete){
            isAllowedToEditAndDelete=false;
            if(base.Delete(saveId)){
                System.out.println("delete successfully");
                Reset();
            }
            else{
                System.out.println("deleting failed");
            }
            }else{
                System.out.println("Select user");
            }
        }
    }

    class ResetButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            isAllowedToEditAndDelete=false;
            Reset();
            pack();
        }
    }


    class ShowButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            base.Show();
        }
    }

    class CheckBoxListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if(itemEvent.getStateChange() == ItemEvent.SELECTED){
            idFindField.setEnabled(true);}
            else{
                idFindField.setEnabled(false);
            }
        }
    }
}
