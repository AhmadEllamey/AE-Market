package Mini;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Main.*;
import static Mini.TheMainFrameHub.BillNumberNow;
import static Mini.TheMainFrameHub.TotalBillToAddToDebt;

public class AddToDebtsFromTheSells {
    private JPanel theMainScreen;
    private JList CheckTheDebtsAndThenAdd;
    private JButton addButton;
    private JButton createAndAdd;
    private JButton CancelButton;
    private JTextField searchText;

    DefaultListModel defaultListModel = new DefaultListModel();


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void LoadTheList(){

        defaultListModel.clear();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) CheckTheDebtsAndThenAdd.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{

            OpenConnection();
            Statement SearchForDept = connection.createStatement();
            String Dept = "SELECT user_name_dept FROM  dept";
            ResultSet resultSet = SearchForDept.executeQuery(Dept);

            while (resultSet.next()){

                defaultListModel.addElement(resultSet.getString("user_name_dept"));

            }

            CheckTheDebtsAndThenAdd.setModel(defaultListModel);

            CloseConnection();

        }
        catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }


    }

    public void LoadTheListWithSearch(){


        defaultListModel.clear();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) CheckTheDebtsAndThenAdd.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{

            OpenConnection();
            Statement SearchForDept = connection.createStatement();
            String Dept = "SELECT user_name_dept FROM  dept WHERE user_name_dept LIKE '%"+searchText.getText().trim()+"%'";
            ResultSet resultSet = SearchForDept.executeQuery(Dept);

            while (resultSet.next()){

                defaultListModel.addElement(resultSet.getString("user_name_dept"));

            }

            CheckTheDebtsAndThenAdd.setModel(defaultListModel);

            CloseConnection();

        }
        catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }


    }



    public AddToDebtsFromTheSells() {


        AddToDebtFromSellScreen.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // check the action of the mouse clicked
                //System.out.println("Clicked!");
                TotalBillToAddToDebt = 0 ;
            }
        });

        LoadTheList();


        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                LoadTheListWithSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(searchText.getText().trim().isEmpty()){
                    LoadTheList();
                }else {
                    LoadTheListWithSearch();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(searchText.getText().trim().isEmpty()){
                    LoadTheList();
                }else {
                    LoadTheListWithSearch();
                }
            }
        });


        addButton.addActionListener(e -> {

            try{

                OpenConnection();

                String DebtName = CheckTheDebtsAndThenAdd.getSelectedValue().toString();

                double totalCash = 0 ;

                Statement InsertIntoDeptHistory = connection.createStatement();
                String info = "INSERT INTO dept_history (user_name_dept,bill_number,cash,note) VALUES ('"+DebtName+"','"
                        +String.valueOf(BillNumberNow)+"'"+
                        ","+TotalBillToAddToDebt+",'فاتوره')";
                InsertIntoDeptHistory.executeUpdate(info);

                Statement GetInfo = connection.createStatement();
                String detailsInfo = "SELECT * FROM dept_history WHERE user_name_dept ='"+DebtName+"'";
                ResultSet resultSet = GetInfo.executeQuery(detailsInfo);

                while (resultSet.next()){

                    totalCash = totalCash + Double.parseDouble(resultSet.getString("cash")) ;

                }

                Statement UpdateTheTotalForDept = connection.createStatement();
                String UpdateTheTotal = "UPDATE dept SET total ="+totalCash+" WHERE user_name_dept = '"+DebtName+"'";
                UpdateTheTotalForDept.executeUpdate(UpdateTheTotal);

                AddToDebtFromSellScreen.dispose();

                TotalBillToAddToDebt = 0 ;

                CloseConnection();

            }catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }


        });


        createAndAdd.addActionListener(e -> {
            URL imageUrl = ClassLoader.getSystemResource("icon1.png");
            ImageIcon img = new ImageIcon(imageUrl);
            CreateAndAddFrame = new JFrame("Add To Debts And Go !");
            CreateAndAddFrame.setContentPane(new AddToDebtsAndForget().getTheMainScreen());
            CreateAndAddFrame.setIconImage(img.getImage());
            CreateAndAddFrame.setLocationRelativeTo(null);//Setting location to the center of screen
            CreateAndAddFrame.getContentPane().setBackground(Color.black);//setting background color
            CreateAndAddFrame.setSize(500,250);

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            CreateAndAddFrame.setLocation(dim.width/2-CreateAndAddFrame.getSize().width/2, dim.height/2-CreateAndAddFrame.getSize().height/2);

            CreateAndAddFrame.setResizable(false);
            CreateAndAddFrame.setVisible(true);
        });


        CancelButton.addActionListener(e -> {
            AddToDebtFromSellScreen.dispose();
            TotalBillToAddToDebt = 0 ;
        });


    }

}
