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


public class MerchantDebtGUI {
    private JPanel theMainScreen;
    private JList DeptList;
    private JButton ADDButton;
    private JButton deleteButton;
    private JButton okButton;
    private JButton openButton;
    private JTextField searchForDebtName;

    public static String MerchantNameToOpen ;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }


    DefaultListModel defaultListModel = new DefaultListModel();

    public void LoadTheList(){


        defaultListModel.clear();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) DeptList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{

            OpenConnection();
            Statement SearchForDept = connection.createStatement();
            String Dept = "SELECT user_name_dept FROM  merchant_dept";
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
            String Dept = "SELECT user_name_dept FROM  merchant_dept WHERE user_name_dept LIKE '%"+searchForDebtName.getText().trim()+"%'";
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
        MerchantCreateNewDept = new JFrame("Add New Debt !");
        MerchantCreateNewDept.setContentPane(new MerchantNewDebt().getTheMainScreen());
        MerchantCreateNewDept.setIconImage(img.getImage());
        MerchantCreateNewDept.setLocationRelativeTo(null);//Setting location to the center of screen
        MerchantCreateNewDept.getContentPane().setBackground(Color.black);//setting background color
        MerchantCreateNewDept.setSize(600,400);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        MerchantCreateNewDept.setLocation(dim.width/2-MerchantCreateNewDept.getSize().width/2, dim.height/2-MerchantCreateNewDept.getSize().height/2);



        MerchantCreateNewDept.setResizable(false);
        MerchantCreateNewDept.setVisible(true);
    }

    public void OpenTheUserDept(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        InSideMerchantDebtScreen = new JFrame("Merchant Debt Details !");
        InSideMerchantDebtScreen.setContentPane(new MerchantDeptDetails().getTheMainScreen());
        InSideMerchantDebtScreen.setIconImage(img.getImage());
        InSideMerchantDebtScreen.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        InSideMerchantDebtScreen.setLocation(dim.width/2-InSideMerchantDebtScreen.getSize().width/2, dim.height/2-InSideMerchantDebtScreen.getSize().height/2);

        InSideMerchantDebtScreen.setVisible(true);
    }

    public void AreYouSureAboutDeleteTheUserDept(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        MerchantSureAboutTheDeletedDept = new JFrame("Are You Sure !");
        MerchantSureAboutTheDeletedDept.setContentPane(new AreYouSureAboutDeleteTheMerchantDept().getTheMainScreen());
        MerchantSureAboutTheDeletedDept.setIconImage(img.getImage());
        MerchantSureAboutTheDeletedDept.setLocationRelativeTo(null);//Setting location to the center of screen
        MerchantSureAboutTheDeletedDept.getContentPane().setBackground(Color.black);//setting background color
        MerchantSureAboutTheDeletedDept.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        MerchantSureAboutTheDeletedDept.setLocation(dim.width/2-MerchantSureAboutTheDeletedDept.getSize().width/2, dim.height/2-MerchantSureAboutTheDeletedDept.getSize().height/2);

        MerchantSureAboutTheDeletedDept.setResizable(false);
        MerchantSureAboutTheDeletedDept.setVisible(true);
    }



    public MerchantDebtGUI() {

        LoadTheList();

        DeptList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {



                if (evt.getClickCount() == 2) {

                    // Double-click detected


                    if(!DeptList.getSelectedValue().toString().isEmpty()) {

                        MerchantNameToOpen = DeptList.getSelectedValue().toString();

                        try {
                            InSideMerchantDebtScreen.dispose();
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                        MerchantDebtScreen.dispose();
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

                    MerchantNameToOpen = DeptList.getSelectedValue().toString();

                    try {
                        InSideMerchantDebtScreen.dispose();
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                    MerchantDebtScreen.dispose();
                    OpenTheUserDept();
                }

            }
            catch (Exception eee){
                eee.printStackTrace();
            }

        });



        ADDButton.addActionListener(e -> {
            try{
                MerchantCreateNewDept.dispose();
            }
            catch (Exception ee){
                ee.printStackTrace();
            }
            AddNewDept();
        });



        deleteButton.addActionListener(e -> {

            try{
                if(!DeptList.getSelectedValue().toString().isEmpty()){
                    MerchantNameToOpen = DeptList.getSelectedValue().toString();
                    AreYouSureAboutDeleteTheUserDept();
                }
            }
            catch (Exception eee){
                eee.printStackTrace();
            }


        });



        okButton.addActionListener(e -> MerchantDebtScreen.dispose());



    }
}
