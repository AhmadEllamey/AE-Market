package Mini;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.addNewUser;


public class CreateUsers {
    private JPanel theMainScreen;
    private JTextField userNameText;
    private JTextField passwordText;
    private JTextField nameText;
    private JTextField ageText;
    private JTextField phoneText;
    private JTextField addressText;
    private JTextField nationalIdText;
    private JTextField salaryText;
    private JButton OKButton;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private int type ;
    public static JFrame warning ;
    public static JFrame userExist ;
    URL imageUrl = ClassLoader.getSystemResource("icon1.png");
    ImageIcon img = new ImageIcon(imageUrl);


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }



    public void ShowWarning(){
        warning = new JFrame("Please Enter All The Required Data");
        warning.setContentPane(new PleaseEnterTrueInfo().getTheMainScreen());
        warning.setIconImage(img.getImage());
        warning.setLocationRelativeTo(null);//Setting location to the center of screen
        warning.getContentPane().setBackground(Color.black);//setting background color
        warning.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        warning.setLocation(dim.width/2-warning.getSize().width/2, dim.height/2-warning.getSize().height/2);

        warning.setResizable(false);
        warning.setVisible(true);
    }

    public void UserExist(){
        userExist = new JFrame("Try Another User Name !");
        userExist.setContentPane(new UserAlreadyExists().getTheMainScreen());
        userExist.setIconImage(img.getImage());
        userExist.setLocationRelativeTo(null);//Setting location to the center of screen
        userExist.getContentPane().setBackground(Color.black);//setting background color
        userExist.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        userExist.setLocation(dim.width/2-userExist.getSize().width/2, dim.height/2-userExist.getSize().height/2);

        userExist.setResizable(false);
        userExist.setVisible(true);
    }

    public CreateUsers() {

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(adminRadioButton);
        buttonGroup.add(userRadioButton);



        // some fields accepts only numbers

        ageText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        phoneText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        nationalIdText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        salaryText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        OKButton.addActionListener(e -> {
            // ToDo create the user
            try{
                OpenConnection();
                Statement CheckTheUser = connection.createStatement();
                String user = "Select * from users where user_name = '" +userNameText.getText().trim()+ "'";
                ResultSet resultSet = CheckTheUser.executeQuery(user);
                if (resultSet.next()){

                    //ToDo >> tell that the user is already exist , try another user name

                    try{
                        userExist.dispose();
                    }catch (Exception eee){
                        eee.printStackTrace();
                    }

                    UserExist();

                }

                else {
                    if(adminRadioButton.isSelected()){
                        type = 1 ;
                    }
                    else if (userRadioButton.isSelected()){
                        type = 0 ;
                    }

                    String inputNewUser = "INSERT INTO users (user_name, password, admin, full_name, age, phone_number, address, national_id, salary) " +
                            "VALUES ('"+userNameText.getText().trim()+
                            "','" +passwordText.getText()+
                            "'," + type+
                            ",'" + nameText.getText().trim()+
                            "'," + Integer.parseInt(ageText.getText().trim())+
                            ",'" +  phoneText.getText().trim()+
                            "','" + addressText.getText().trim()+
                            "','" +  nationalIdText.getText().trim()+
                            "'," + Double.parseDouble(salaryText.getText().trim())+
                            ")";
                    CheckTheUser.executeUpdate(inputNewUser);

                    TheMainFrameHub.ShowNewUserCreated();
                    addNewUser.dispose();
                }


                CloseConnection();

            }
            catch (Exception ee){
                ee.printStackTrace();
                //ToDo >> tell that all fields should be added correctly

                try{
                    warning.dispose();
                }catch (Exception eee){
                    eee.printStackTrace();
                }
                ShowWarning();
                CloseConnection();
            }

        });

    }
}
