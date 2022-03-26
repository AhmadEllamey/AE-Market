package Mini;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.*;
import static Mini.Main.mainFrame;

public class LogIn {
    private JPanel theMainScreen;
    private JButton signInButton;
    private JTextField userNameText;
    private JPasswordField passwordText;
    private JButton signInButtonForPassword;
    private JPanel userNameScreen;
    private JPanel passwordScreen;

    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }


    public void TryAgainFrameShow () {
        URL imageUrl = ClassLoader.getSystemResource("icon2.png");
        ImageIcon img = new ImageIcon(imageUrl);
        tryAgainFrame = new JFrame("Try Again !");
        tryAgainFrame.setContentPane(new WrongPasswordOrUserName().getTheMainScreen());
        tryAgainFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        tryAgainFrame.setIconImage(img.getImage());
        tryAgainFrame.setSize(450,200);
        tryAgainFrame.getContentPane().setBackground(Color.black);
        tryAgainFrame.setResizable(false);
        tryAgainFrame.setVisible(true);
    }

    public void MainFrameShow(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        mainFrame = new JFrame("Sales System @Powered by AE");
        mainFrame.setIconImage(img.getImage());
        mainFrame.setContentPane(new TheMainFrameHub().getTheMainScreen());
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Dimension dimension = new Dimension();
        dimension.setSize(1400,1000);
        mainFrame.setMinimumSize(dimension);

        mainFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public LogIn() {

        theMainScreen = new JPanel(new CardLayout());
        theMainScreen.add(userNameScreen , "UserNameCard");
        theMainScreen.add(passwordScreen , "PasswordCard");
        CardLayout cl = (CardLayout)(theMainScreen.getLayout());
        cl.show(theMainScreen, String.valueOf(userNameScreen));

        loginFrame.getRootPane().setDefaultButton(signInButton);
        userNameText.requestFocusInWindow();



        signInButton.addActionListener(e -> {
            //ToDo when Sign in clicked >>>> check the username in the database if true >>> continue to check the password ,else try again frame
            if(checkUserNameX(userNameText.getText())){
                CardLayout cl1 = (CardLayout)(theMainScreen.getLayout());
                cl1.show(theMainScreen, "PasswordCard");
                loginFrame.getRootPane().setDefaultButton(signInButtonForPassword);
                passwordText.requestFocusInWindow();
            }
            else {
                try{
                    tryAgainFrame.dispose();
                }catch (Exception eee){
                    eee.printStackTrace();
                }
                TryAgainFrameShow();
            }

        });


        signInButtonForPassword.addActionListener(e -> {
            // ToDo >>> check the password in database , if true >> go to the main screen , else >> try again screen
            if(checkPasswordX(passwordText.getText().toString())){
                // ToDo what will happened if the user and the password is true .

                String verify = sha256("A98H06M01D010E16L19L33A02MEY");

                try {

                    Statement statement =  connection.createStatement();
                    String CheckActive = "select activation_code from activation";
                    ResultSet rs = statement.executeQuery(CheckActive);

                    if(rs.next()){
                        if(verify.equals(rs.getString("activation_code"))){
                            loginFrame.dispose();
                            GetTheShiftNumber();
                            //System.out.println(String.valueOf(ShiftNumber));
                            MainFrameShow();
                        }
                    }

                }catch (Exception ee){
                    ee.printStackTrace();
                }


                CloseConnection();
            }
            else {
                try{
                    tryAgainFrame.dispose();
                }catch (Exception eee){
                    eee.printStackTrace();
                }
                TryAgainFrameShow();
            }
        });
    }
}
