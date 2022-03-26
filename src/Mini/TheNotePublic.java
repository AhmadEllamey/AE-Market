package Mini;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.showPublicNotesInfo;
import static Mini.PublicNotes.TheHeaderValue;

public class TheNotePublic {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JLabel TheHeaders;
    private JTextPane TheMessage;
    private JLabel theDate;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }


    public TheNotePublic() {



        TheMessage.setEnabled(false);
        TheMessage.setEditable(false);


        try{

            Statement CheckData = connection.createStatement();
            String Notes = "SELECT * FROM public_notes WHERE header_line = '"+TheHeaderValue+"'";
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




        OKButton.addActionListener(e -> showPublicNotesInfo.dispose());
    }


}
