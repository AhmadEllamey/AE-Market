package Mini;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.*;

public class PrivateNotes {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JList showNotes;
    private JButton openButton;
    private JButton deleteButton;

    public static String TheHeaderValueForPrivate;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void ShowPrivateData(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        showPrivateNotesInfo = new JFrame("The Note !");
        showPrivateNotesInfo.setContentPane(new TheNotePrivate().getTheMainScreen());
        showPrivateNotesInfo.setIconImage(img.getImage());
        showPrivateNotesInfo.setLocationRelativeTo(null);//Setting location to the center of screen
        showPrivateNotesInfo.getContentPane().setBackground(Color.black);//setting background color
        showPrivateNotesInfo.setSize(800,500);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        showPrivateNotesInfo.setLocation(dim.width/2-showPrivateNotesInfo.getSize().width/2, dim.height/2-showPrivateNotesInfo.getSize().height/2);



        showPrivateNotesInfo.setResizable(false);
        showPrivateNotesInfo.setVisible(true);
    }


    public PrivateNotes() {


        if(Admin==0){
            deleteButton.setEnabled(false);
        }

        DefaultListModel defaultListModel = new DefaultListModel();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) showNotes.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{

            OpenConnection();
            Statement SearchForPrivateNotes = connection.createStatement();
            String Notes = "SELECT * FROM  private_notes";
            ResultSet resultSet = SearchForPrivateNotes.executeQuery(Notes);

            while (resultSet.next()){

                defaultListModel.addElement(resultSet.getString("header_line"));

            }

            showNotes.setModel(defaultListModel);

            CloseConnection();

        }
        catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }



        OKButton.addActionListener(e -> showPrivateNotes.dispose());



        openButton.addActionListener(e -> {
            try{

                OpenConnection();
                Statement CheckData = connection.createStatement();
                String Notes = "SELECT * FROM private_notes WHERE header_line = '"+showNotes.getSelectedValue().toString().trim()+"'";
                ResultSet resultSet = CheckData.executeQuery(Notes);

                if(resultSet.next()){
                    TheHeaderValueForPrivate = showNotes.getSelectedValue().toString();
                    try {
                        showPrivateNotesInfo.dispose();
                    }catch (Exception ee){
                        ee.printStackTrace();
                    }
                    ShowPrivateData();
                }

            }catch (Exception eee){
                eee.printStackTrace();
                CloseConnection();
            }

        });



        deleteButton.addActionListener(e -> {

            try{
                OpenConnection();
                Statement DeleteNote = connection.createStatement();
                String DeleteSelectedRow = "DELETE FROM private_notes WHERE header_line = '"+showNotes.getSelectedValue().toString()+"'";
                DeleteNote.executeUpdate(DeleteSelectedRow);
                defaultListModel.remove(showNotes.getSelectedIndex());
                CloseConnection();

            }catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }

        });


    }
}
