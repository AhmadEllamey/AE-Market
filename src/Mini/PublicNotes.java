package Mini;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.*;


public class PublicNotes {


    private JPanel theMainScreen;
    private JButton OKButton;
    private JList showNotes;
    private JButton openButton;
    private JButton deleteButton;

    public static String TheHeaderValue;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }


    public void ShowData(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        showPublicNotesInfo = new JFrame("The Note !");
        showPublicNotesInfo.setContentPane(new TheNotePublic().getTheMainScreen());
        showPublicNotesInfo.setIconImage(img.getImage());
        showPublicNotesInfo.setLocationRelativeTo(null);//Setting location to the center of screen
        showPublicNotesInfo.getContentPane().setBackground(Color.black);//setting background color
        showPublicNotesInfo.setSize(800,500);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        showPublicNotesInfo.setLocation(dim.width/2-showPublicNotesInfo.getSize().width/2, dim.height/2-showPublicNotesInfo.getSize().height/2);


        showPublicNotesInfo.setResizable(false);
        showPublicNotesInfo.setVisible(true);
    }




    public PublicNotes() {

        if(Admin==0){
            deleteButton.setEnabled(false);
        }

        DefaultListModel defaultListModel = new DefaultListModel();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) showNotes.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{

            OpenConnection();
            Statement SearchForPublicNotes = connection.createStatement();
            String Notes = "SELECT * FROM public_notes";
            ResultSet resultSet = SearchForPublicNotes.executeQuery(Notes);

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



        OKButton.addActionListener(e -> showPublicNotes.dispose());



        openButton.addActionListener(e -> {
            try{

                OpenConnection();
                Statement CheckData = connection.createStatement();
                String Notes = "SELECT * FROM public_notes WHERE header_line = '"+showNotes.getSelectedValue().toString().trim()+"'";
                ResultSet resultSet = CheckData.executeQuery(Notes);

                if(resultSet.next()){
                    TheHeaderValue = showNotes.getSelectedValue().toString();
                    try {
                        showPublicNotesInfo.dispose();
                    }catch (Exception ee){
                        ee.printStackTrace();
                    }
                    ShowData();
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
                String DeleteSelectedRow = "DELETE FROM public_notes WHERE header_line = '"+showNotes.getSelectedValue().toString()+"'";
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
