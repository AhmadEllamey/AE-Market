package Mini;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.Statement;

import static Mini.CreateUsers.warning;
import static Mini.DBConnectionX.*;
import static Mini.Main.*;

public class AddPublicNotes {
    private JPanel theMainScreen;
    private JButton addButton;
    private JTextField headerText;
    private JTextArea messageText;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void ShowTheHeaderWarning(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        canNotUseThisHeader = new JFrame("Change The Header !");
        canNotUseThisHeader.setContentPane(new CanNotHaveTheSameHeader().getTheMainScreen());
        canNotUseThisHeader.setIconImage(img.getImage());
        canNotUseThisHeader.setLocationRelativeTo(null);//Setting location to the center of screen
        canNotUseThisHeader.getContentPane().setBackground(Color.black);//setting background color
        canNotUseThisHeader.setSize(800,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        canNotUseThisHeader.setLocation(dim.width/2-canNotUseThisHeader.getSize().width/2, dim.height/2-canNotUseThisHeader.getSize().height/2);


        canNotUseThisHeader.setResizable(false);
        canNotUseThisHeader.setVisible(true);
    }

    public AddPublicNotes() {


        addButton.addActionListener(e -> {

            try{

                OpenConnection();
                Statement SaveNote = connection.createStatement();
                String Note = "INSERT INTO public_notes (header_line, message_body) VALUES ('"+headerText.getText().trim()+"',"+
                        "'"+messageText.getText().trim()+"')";
                if(headerText.getText().trim().isEmpty() && messageText.getText().trim().isEmpty() ){
                    // show frame to say please enter all data .

                    try{
                        warning.dispose();
                    }catch (Exception eee){
                        eee.printStackTrace();
                    }
                    CreateUsers createUsers = new CreateUsers();
                    createUsers.ShowWarning();
                }

                else{
                    SaveNote.executeUpdate(Note);
                    TheMainFrameHub.ShowNoteIsWritten();
                    // dispose the frame .

                    addPublicNoteFrame.dispose();
                }

                CloseConnection();

            }
            catch (Exception ee){
                ee.printStackTrace();
                try{
                    canNotUseThisHeader.dispose();
                }catch (Exception eee){
                    eee.printStackTrace();
                }
                ShowTheHeaderWarning();
                CloseConnection();
            }

        });
    }
}
