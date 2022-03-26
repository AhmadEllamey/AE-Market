package Mini;

import javax.swing.*;
import java.sql.Statement;

import static Mini.CreateUsers.warning;
import static Mini.DBConnectionX.*;
import static Mini.Main.addPrivateNoteFrame;
import static Mini.Main.canNotUseThisHeader;

public class AddPrivateNotes {
    private JPanel theMainScreen;
    private JButton addButton;
    private JTextField headerText;
    private JTextArea messageText;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public AddPrivateNotes() {

        addButton.addActionListener(e -> {

            try{

                OpenConnection();
                Statement SaveNote = connection.createStatement();
                String Note = "INSERT INTO private_notes (header_line, message_body) VALUES ('"+headerText.getText().trim()+"',"+
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

                    addPrivateNoteFrame.dispose();
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
                AddPublicNotes addPublicNotes = new AddPublicNotes();
                addPublicNotes.ShowTheHeaderWarning();
                CloseConnection();
            }

        });
    }
}
