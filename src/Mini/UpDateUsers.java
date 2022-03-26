package Mini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.CreateUsers.warning;
import static Mini.DBConnectionX.*;
import static Mini.Main.*;

public class UpDateUsers {
    private JPanel theMainScreen;
    private JTextField userNameText;
    private JTextField passwordText;
    private JTextField nameText;
    private JTextField ageText;
    private JTextField phoneText;
    private JTextField addressText;
    private JTextField nationalIdText;
    private JTextField salaryText;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private JButton upDateButton;
    private JPanel theUpdateScreen;
    private JPanel getTheUser;
    private JTextField userNameForUpdateText;
    private JButton OKButton;
    URL imageUrl = ClassLoader.getSystemResource("icon1.png");
    ImageIcon img = new ImageIcon(imageUrl);
    CreateUsers createUsers = new CreateUsers();

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void NoUserLikeThis(){
        noUserName = new JFrame("No User Name Match !");
        noUserName.setContentPane(new NoUserName().getTheMainScreen());
        noUserName.setIconImage(img.getImage());
        noUserName.setLocationRelativeTo(null);//Setting location to the center of screen
        noUserName.getContentPane().setBackground(Color.black);//setting background color
        noUserName.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        noUserName.setLocation(dim.width/2-noUserName.getSize().width/2, dim.height/2-noUserName.getSize().height/2);

        noUserName.setResizable(false);
        noUserName.setVisible(true);
    }

    public void EnterUserName(){
        EnterUserNameForSearch = new JFrame("No User Name Match !");
        EnterUserNameForSearch.setContentPane(new EnterUserNameForSearching().getTheMainScreen());
        EnterUserNameForSearch.setIconImage(img.getImage());
        EnterUserNameForSearch.setLocationRelativeTo(null);//Setting location to the center of screen
        EnterUserNameForSearch.getContentPane().setBackground(Color.black);//setting background color
        EnterUserNameForSearch.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        EnterUserNameForSearch.setLocation(dim.width/2-EnterUserNameForSearch.getSize().width/2, dim.height/2-EnterUserNameForSearch.getSize().height/2);


        EnterUserNameForSearch.setResizable(false);
        EnterUserNameForSearch.setVisible(true);
    }

    public UpDateUsers() {

        theMainScreen.add(getTheUser,"GetTheUserCard");
        theMainScreen.add(theUpdateScreen,"UpDateCard");

        userNameText.setEnabled(false);
        userNameText.setEditable(false);

        // start the welcome screen
        CardLayout cl = (CardLayout) (theMainScreen.getLayout());
        cl.show(theMainScreen, String.valueOf(getTheUser));

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

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(adminRadioButton);
        buttonGroup.add(userRadioButton);

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

            try{

                OpenConnection();
                Statement UpDateTheUser = connection.createStatement();
                String checkTheUser = "Select * from users where user_name = '" +userNameForUpdateText.getText().trim()+ "'";
                ResultSet resultSet = UpDateTheUser.executeQuery(checkTheUser);

                if(resultSet.next()){

                    // load the data for that user
                    userNameText.setText(resultSet.getString("user_name"));
                    passwordText.setText(resultSet.getString("password"));
                    nameText.setText(resultSet.getString("full_name"));
                    ageText.setText(resultSet.getString("age"));
                    phoneText.setText(resultSet.getString("phone_number"));
                    addressText.setText(resultSet.getString("address"));
                    nationalIdText.setText(resultSet.getString("national_id"));
                    salaryText.setText(resultSet.getString("salary"));

                    if(Integer.parseInt(resultSet.getString("admin")) == 1 ){
                        adminRadioButton.setSelected(true);
                    }
                    else {
                        userRadioButton.setSelected(true);
                    }

                    // go to the second screen to change and update

                    CardLayout cl1 = (CardLayout) (theMainScreen.getLayout());

                    cl1.show(theMainScreen, "UpDateCard");

                    updateUsers.setSize(600,500);


                }
                else {

                    if(userNameForUpdateText.getText().isEmpty()){
                        // ToDo >> tell the user to enter a name to search for
                        try{
                            EnterUserNameForSearch.dispose();
                        }catch (Exception eee){
                            eee.printStackTrace();
                        }
                        EnterUserName();
                    }
                    else {
                        // ToDo >> tell the user that there's no user with this name
                        try{
                            noUserName.dispose();
                        }catch (Exception eee){
                            eee.printStackTrace();
                        }
                        NoUserLikeThis();
                    }
                }

                CloseConnection();
            }
            catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }

        });

        upDateButton.addActionListener(e -> {
             int adminCheck ;

            try{
                if(adminRadioButton.isSelected()){
                    adminCheck = 1 ;
                }
                else {
                    adminCheck = 0 ;
                }
                OpenConnection();
                Statement UpDateTheUser = connection.createStatement();
                String userUpdate = "UPDATE users SET password = '"+passwordText.getText().trim()+"', admin = "
                                     +adminCheck+", full_name = '"+nameText.getText().trim()+"', age = "+Integer.parseInt(ageText.getText().trim())+
                                     ", phone_number = '"+phoneText.getText().trim()+"', address = '"+addressText.getText().trim()+"',national_id = '"
                                     +nationalIdText.getText().trim()+"',salary = "+Double.parseDouble(salaryText.getText().trim())+" WHERE user_name = '"
                                     +userNameText.getText().trim()+"'";
                UpDateTheUser.executeUpdate(userUpdate);

                TheMainFrameHub.ShowUserUpdatedFrame();
                updateUsers.dispose();
                CloseConnection();
            }

            catch (Exception ee){
                ee.printStackTrace();
                // ToDo please enter true Values
                try{
                    warning.dispose();
                }catch (Exception eee){
                    eee.printStackTrace();
                }
                createUsers.ShowWarning();
                CloseConnection();
            }
        });

    }
}
