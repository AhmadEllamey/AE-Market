package Mini;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Main.*;



public class Dept {
    private JPanel theMainScreen;
    private JList DeptList;
    private JButton ADDButton;
    private JButton deleteButton;
    private JButton okButton;
    private JButton openButton;
    private JTextField searchForDebtName;

    public static String UserDeptName;
    public static String UserDeptName2;

    DefaultListModel defaultListModel = new DefaultListModel();

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }


    public void LoadTheList(){


        defaultListModel.clear();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) DeptList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{

            OpenConnection();
            Statement SearchForDept = connection.createStatement();
            String Dept = "SELECT user_name_dept FROM  dept";
            ResultSet resultSet = SearchForDept.executeQuery(Dept);

            while (resultSet.next()){

                defaultListModel.addElement(resultSet.getString("user_name_dept"));

            }

            DeptList.setModel(defaultListModel);

            CloseConnection();

        }
        catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }
    }

    public void LoadTheListWithSearch(){


        defaultListModel.clear();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) DeptList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{

            OpenConnection();
            Statement SearchForDept = connection.createStatement();
            String Dept = "SELECT user_name_dept FROM  dept WHERE user_name_dept LIKE '%"+searchForDebtName.getText().trim()+"%'";
            ResultSet resultSet = SearchForDept.executeQuery(Dept);

            while (resultSet.next()){

                defaultListModel.addElement(resultSet.getString("user_name_dept"));

            }

            DeptList.setModel(defaultListModel);

            CloseConnection();

        }
        catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }


    }

    public void AddNewDept(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        CreateNewDept = new JFrame("Add New Debt !");
        CreateNewDept.setContentPane(new NewDept().getTheMainScreen());
        CreateNewDept.setIconImage(img.getImage());
        CreateNewDept.setLocationRelativeTo(null);//Setting location to the center of screen
        CreateNewDept.getContentPane().setBackground(Color.black);//setting background color
        CreateNewDept.setSize(600,400);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        CreateNewDept.setLocation(dim.width/2-CreateNewDept.getSize().width/2, dim.height/2-CreateNewDept.getSize().height/2);



        CreateNewDept.setResizable(false);
        CreateNewDept.setVisible(true);
    }

    public void OpenTheUserDept(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        OpenDept = new JFrame("User Debt Details !");
        OpenDept.setContentPane(new UserDeptDetails().getTheMainScreen());
        OpenDept.setIconImage(img.getImage());
        OpenDept.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        OpenDept.setLocation(dim.width/2-OpenDept.getSize().width/2, dim.height/2-OpenDept.getSize().height/2);

        OpenDept.setVisible(true);
    }

    public void AreYouSureAboutDeleteTheUserDept(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        SureAboutTheDeletedDept = new JFrame("Are You Sure !");
        SureAboutTheDeletedDept.setContentPane(new DeleteDept().getTheMainScreen());
        SureAboutTheDeletedDept.setIconImage(img.getImage());
        SureAboutTheDeletedDept.setLocationRelativeTo(null);//Setting location to the center of screen
        SureAboutTheDeletedDept.getContentPane().setBackground(Color.black);//setting background color
        SureAboutTheDeletedDept.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        SureAboutTheDeletedDept.setLocation(dim.width/2-SureAboutTheDeletedDept.getSize().width/2, dim.height/2-SureAboutTheDeletedDept.getSize().height/2);

        SureAboutTheDeletedDept.setResizable(false);
        SureAboutTheDeletedDept.setVisible(true);
    }



    public Dept() {


        LoadTheList();


        DeptList.addMouseListener(new MouseAdapter() {


            public void mouseClicked(MouseEvent evt) {

                if (evt.getClickCount() == 2) {

                    // Double-click detected


                    if(!DeptList.getSelectedValue().toString().isEmpty()) {

                        UserDeptName2 = DeptList.getSelectedValue().toString();

                        try {
                            OpenDept.dispose();
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                        FirstDeptFrame.dispose();
                        OpenTheUserDept();
                    }

                }

            }
        });


        searchForDebtName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                LoadTheListWithSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(searchForDebtName.getText().trim().isEmpty()){
                    LoadTheList();
                }else {
                    LoadTheListWithSearch();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(searchForDebtName.getText().trim().isEmpty()){
                    LoadTheList();
                }else {
                    LoadTheListWithSearch();
                }
            }
        });


        openButton.addActionListener(e -> {

            try{

                if(!DeptList.getSelectedValue().toString().isEmpty()) {

                    UserDeptName2 = DeptList.getSelectedValue().toString();

                    try {
                        OpenDept.dispose();
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                    FirstDeptFrame.dispose();
                    OpenTheUserDept();
                }

            }
            catch (Exception eee){
                eee.printStackTrace();
            }

        });



        ADDButton.addActionListener(e -> {
            try{
                CreateNewDept.dispose();
            }
            catch (Exception ee){
                ee.printStackTrace();
            }
            AddNewDept();
        });



        deleteButton.addActionListener(e -> {

            try{
                if(!DeptList.getSelectedValue().toString().isEmpty()){
                    UserDeptName = DeptList.getSelectedValue().toString();
                    AreYouSureAboutDeleteTheUserDept();
                }
            }
            catch (Exception eee){
                eee.printStackTrace();
            }


        });



        okButton.addActionListener(e -> FirstDeptFrame.dispose());


    }
}
