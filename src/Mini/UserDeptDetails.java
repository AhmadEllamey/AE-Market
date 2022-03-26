package Mini;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Dept.UserDeptName2;
import static Mini.Main.*;


public class UserDeptDetails {


    private JPanel theMainScreen;
    private JTextField DeptCashText;
    private JTextField DeptCashNote;
    private JButton ADDButton;
    private JTable DeptTable;
    private JTextField TotalText;
    private JButton DELETEButton;
    private JButton OKButton;
    private JButton CLEARALLButton;
    private JLabel TheUserNameDept;
    private JButton discountButton;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreatDeptTable(){

        DeptTable.getTableHeader().setReorderingAllowed(false);
        DeptTable.getTableHeader().setResizingAllowed(false);

        DeptTable.setRowHeight(25);

        DeptTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Name", "Cash","Bill Number", "Note", "Date"}
        ));

        DeptTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 5 ;i++ ){
            DeptTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        LoadTheTable();
    }

    public void LoadTheTable(){

        try{

            OpenConnection();
            Statement LoadTheTable = connection.createStatement();
            String TableX = "Select * from dept_history where user_name_dept = '"+UserDeptName2+"'";
            ResultSet resultSet = LoadTheTable.executeQuery(TableX);


            DefaultTableModel model = (DefaultTableModel) DeptTable.getModel();
            model.setRowCount(0);

            while (resultSet.next()){

                Object[] row = { resultSet.getString("user_name_dept") ,
                        resultSet.getString("cash") ,
                        resultSet.getString("bill_number") ,
                        resultSet.getString("note") ,
                        resultSet.getString("CreatedDate")};

                model.addRow(row);

            }


            SetTheTotal();

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();

        }
    }

    public void SetTheTotal(){

        double totalBillCount = 0 ;
        Object[] columnData = new Object[DeptTable.getRowCount()];  // One entry for each row
        for (int i = 0; i < DeptTable.getRowCount(); i++) {  // Loop through the rows
            // Record the 5th column value (index 4)
            columnData[i] = DeptTable.getValueAt(i, 1);
            totalBillCount = totalBillCount + Double.parseDouble(String.valueOf(columnData[i])) ;
        }
        TotalText.setText(String.valueOf(totalBillCount));

    }

    public void AddIntoTheTable(int x){


        if(!DeptCashText.getText().trim().isEmpty()){

            try{
                OpenConnection();

                Statement SearchTheUser = connection.createStatement();
                String CheckTheUser = "SELECT * FROM dept WHERE user_name_dept ='"+UserDeptName2+"'";
                ResultSet resultSet = SearchTheUser.executeQuery(CheckTheUser);

                if(resultSet.next()){

                    Statement InsertIntoDeptHistory = connection.createStatement();
                    String info = "INSERT INTO dept_history (user_name_dept, cash,bill_number, note) VALUES ('"+UserDeptName2+"'"+
                            ","+(Double.parseDouble(DeptCashText.getText().trim()) * x)+",'Without','"+DeptCashNote.getText().trim()+"')";
                    InsertIntoDeptHistory.executeUpdate(info);
                    DeptCashNote.setText("");
                    DeptCashText.setText("");

                    LoadTheTable();

                    OpenConnection();

                    Statement UpdateTheTotalForDept = connection.createStatement();
                    String UpdateTheTotal = "UPDATE dept SET total ="+Double.parseDouble(TotalText.getText().trim())+" WHERE user_name_dept = '"+UserDeptName2+"'";
                    UpdateTheTotalForDept.executeUpdate(UpdateTheTotal);


                    CloseConnection();

                }


            }catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }

        }
    }

    public void OpenQuestion(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ClearAllDeptFrame = new JFrame("Clear Dept Question !");
        ClearAllDeptFrame.setContentPane(new SureAboutClearAllDept().getTheMainScreen());
        ClearAllDeptFrame.setIconImage(img.getImage());
        ClearAllDeptFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        ClearAllDeptFrame.getContentPane().setBackground(Color.black);//setting background color
        ClearAllDeptFrame.setSize(400,250);
        ClearAllDeptFrame.setResizable(false);
        ClearAllDeptFrame.setVisible(true);
    }





    public UserDeptDetails() {



        TotalText.setEnabled(false);
        TheUserNameDept.setText("Debt For "+UserDeptName2);


        CreatDeptTable();


        ADDButton.addActionListener(e -> AddIntoTheTable(1));

        discountButton.addActionListener(e -> AddIntoTheTable(-1));

        DELETEButton.addActionListener(e -> {

            try{
                OpenConnection();

                Statement DeleteFromTheHis = connection.createStatement();
                String DeleteOrder = "DELETE FROM dept_history WHERE user_name_dept = '"+UserDeptName2+"' AND " +
                        "CreatedDate = '"+DeptTable.getValueAt(DeptTable.getSelectedRow() ,4)+"'";
                DeleteFromTheHis.executeUpdate(DeleteOrder);



                LoadTheTable();

                OpenConnection();

                Statement UpdateTheTotalForDept = connection.createStatement();
                String UpdateTheTotal = "UPDATE dept SET total ="+Double.parseDouble(TotalText.getText().trim())+" WHERE user_name_dept = '"+UserDeptName2+"'";
                UpdateTheTotalForDept.executeUpdate(UpdateTheTotal);

                CloseConnection();

            }
            catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }

        });

        OKButton.addActionListener(e -> {
            OpenDept.dispose();
            TheMainFrameHub.DebtFrame();
        });

        CLEARALLButton.addActionListener(e -> {
            try{
                ClearAllDeptFrame.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }

            OpenQuestion();

        });

        DeptCashText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });



    }


}
