import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI {
    private JFrame GUIFrame=new JFrame("Simple GUI");

    private JTextField name=new JTextField();
    private JTextField sirname=new JTextField();
    private JTextField email=new JTextField();
    private JTextField tel=new JTextField();
    private JTextField role=new JTextField();

    private JButton save=new JButton("Save");
    private JButton open=new JButton("Open");
    private JButton delete=new JButton("Delete");
    private JButton plusrole=new JButton("Add Role");
    private JButton plustel=new JButton("Add telephone");

    private JCheckBox editcheck=new JCheckBox();
    private JCheckBox deletecheck=new JCheckBox();

    private Box dopfields=Box.createVerticalBox();

    short  countTele=2;
    short countRole=2;
   public void start(){
       GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

       Box ButtonBox=Box.createVerticalBox();
       ButtonBox.add(Box.createVerticalStrut(5));
       ButtonBox.add(plustel);
       ButtonBox.add(Box.createVerticalStrut(5));
       ButtonBox.add(plusrole);

       name.addFocusListener(new TextListener());
       plustel.addActionListener(new plusTelephoneListener());
       plusrole.addActionListener(new plusRoleListener());

       GUIFrame.add(TextBox, BorderLayout.WEST);
       GUIFrame.add(ButtonBox,BorderLayout.CENTER);
       GUIFrame.add(dopfields,BorderLayout.EAST);
       GUIFrame.setVisible(true);
       GUIFrame.pack();
   }

   private Box createTextField(String TextName,JTextField field){
       Box textBox=Box.createVerticalBox();
       textBox.add(new JLabel(TextName));
       textBox.add(Box.createVerticalStrut(5));
       textBox.add(field);
       return textBox;
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

           if(countTele>3){
               System.out.println("Cant add");
           }else{
           dopfields.add(createTextField("Tellephone "+countTele,tel));
           countTele++;
           GUIFrame.pack();
           }
       }
   }
    class plusRoleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JTextField role=new JTextField();
            if(countRole>3){
                System.out.println("Cant add");
                dopfields.getc
            }else{
                dopfields.add(createTextField("Role "+countRole,role));
                countRole++;
                GUIFrame.pack();
            }
        }
    }
}
