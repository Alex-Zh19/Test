import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI extends JFrame{
    private JTextField name=new JTextField();
    private JTextField sirname=new JTextField();
    private JTextField email=new JTextField();
    private JTextField tel=new JTextField();
    private JTextField role=new JTextField();
    private JTextField idFindField=new JTextField();

    private JButton applyButton=new JButton("Apply");
    private JButton findButton=new JButton("Find");
    private JButton addButton=new JButton("Add");
    private JButton deleteButton=new JButton("Delete");
    private JButton resetButton=new JButton("Reset");
    private JButton plusroleButton=new JButton("Add Role");
    private JButton plustelButton=new JButton("Add telephone");

    private JCheckBox findID=new JCheckBox();

    private Box dopfields=Box.createVerticalBox();


    private int countTele=1;
    private int countRole=1;
    GUI(){
        setTitle("Users");

       Box TextBox=Box.createVerticalBox();
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Name",name));
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Sirname",sirname));
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Email",email));
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Telephone",tel));
       TextBox.add(Box.createVerticalStrut(5));
       TextBox.add(createTextField("Role",role));

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
        mainButtonBox.add(addButton);
        mainButtonBox.add(Box.createHorizontalStrut(5));
        mainButtonBox.add(deleteButton);



       name.addFocusListener(new TextListener());
       plustelButton.addActionListener(new plusTelephoneListener());
       plusroleButton.addActionListener(new plusRoleListener());
        addButton.addActionListener(new AddButtonListener());

       add(TextBox, BorderLayout.WEST);
       add(ButtonPlusBox,BorderLayout.EAST);
       add(dopfields,BorderLayout.CENTER);
       add(mainButtonBox,BorderLayout.SOUTH);
       pack();
   }


    private Box createTextField(String TextName,JTextField field){
       Box textBox=Box.createVerticalBox();
       textBox.add(new JLabel(TextName));
       textBox.add(Box.createVerticalStrut(5));
       textBox.add(field);
       field.setColumns(17);
       return textBox;
   }


private boolean CheckPhoneNumber(String dataNum){
        if(dataNum!=null) {
            String firstNum = dataNum.substring(0, 3);
            if (firstNum.equals("375") && dataNum.length() == 12) {
                return true;
            }
        }
       return false;
}
private boolean CheckEmail(String dataEmail){
     if(dataEmail!=null){
       int dogy=dataEmail.indexOf('@');
       int dot=dataEmail.indexOf('.');
       if(dot>dogy+1&&dogy!=0&&dot!=0&&dogy!=dataEmail.length()-1&&dot!=dataEmail.length()-1){
           System.out.println("true");
        return true;
       }
     }
    System.out.println("false");
       return false;
}


    class TextListener implements FocusListener{
       @Override
       public void focusGained(FocusEvent focusEvent) {
           System.out.println("Selected");
       }

       @Override
       public void focusLost(FocusEvent focusEvent) {
           System.out.println("deselected");
       }
   }


   class plusTelephoneListener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent actionEvent) {
           JTextField tel=new JTextField();
           if(countTele>=3){
               System.out.println("Cant add");
           }else{
           dopfields.add(createTextField("Tellephone "+countTele,tel));
           countTele++;
           pack();
           }
       }
   }
    class plusRoleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JTextField role=new JTextField();
            if(countRole>=3){
                System.out.println("Cant add");

            }else{
                dopfields.add(createTextField("Role "+countRole,role));
                countRole++;
                pack();

            }
        }
    }

    class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CheckEmail(email.getText());
        }
    }
}
