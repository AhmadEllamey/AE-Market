package Mini;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.CloseConnection;
import static Mini.DBConnectionX.connection;
import static Mini.Main.showPrivateNotesInfo;
import static Mini.PrivateNotes.TheHeaderValueForPrivate;


public class TheNotePrivate {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JLabel TheHeaders;
    private JTextPane TheMessage;
    private JLabel theDate;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public TheNotePrivate() {





        TheMessage.setEnabled(false);
        TheMessage.setEditable(false);


        try{

            Statement CheckData = connection.createStatement();
            String Notes = "SELECT * FROM private_notes WHERE header_line = '"+TheHeaderValueForPrivate+"'";
            ResultSet resultSet = CheckData.executeQuery(Notes);

            if(resultSet.next()){
                TheHeaders.setText(resultSet.getString("header_line"));
                TheMessage.setText(resultSet.getString("message_body"));
                theDate.setText(resultSet.getString("createdDateSQL"));
            }

            CloseConnection();

        }catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }




        OKButton.addActionListener(e -> showPrivateNotesInfo.dispose());


    }
}
