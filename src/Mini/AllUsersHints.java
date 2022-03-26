package Mini;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.ShowAllUsersInfo;

public class AllUsersHints {
    private JPanel theMainScreen;
    private JTable usersInfoTable;
    private JButton OKButton;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    private void RefreshAllUserHintTable(){
        try{
            OpenConnection();
            String type ;
            Statement tryToGetTheInfo = connection.createStatement();
            String getInfoFromUserTable = "Select * from users";
            ResultSet resultSet = tryToGetTheInfo.executeQuery(getInfoFromUserTable);
            DefaultTableModel model = (DefaultTableModel) usersInfoTable.getModel();

            while (resultSet.next()){

                if(Integer.parseInt(resultSet.getString("admin"))==1){
                    type = "Manager" ;
                }
                else {
                    type = "Employee" ;
                }

                Object[] row = { resultSet.getString("user_name") ,
                        resultSet.getString("password"),
                        type,
                        resultSet.getString("full_name") ,
                        resultSet.getString("age"),
                        resultSet.getString("phone_number") ,
                        resultSet.getString("address"),
                        resultSet.getString("national_id"),
                        resultSet.getString("salary"),
                        resultSet.getString("rate"),
                        resultSet.getString("last_time_updated")};

                model.addRow(row);

            }
            CloseConnection();

        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }
    }

    private void CreateHintTable() {

        usersInfoTable.getTableHeader().setReorderingAllowed(false);
        usersInfoTable.getTableHeader().setResizingAllowed(false);

        usersInfoTable.setRowHeight(25);

        usersInfoTable.setModel(new DefaultTableModel(
                null,
                new String[]{"User Name" , "Password" , "Type" , "Name" , "Age" , "Phone" , "Address" , "National ID" , "Salary" , "Rate" , "Date"}
        ));

        usersInfoTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));





        // set size for some columns .
        usersInfoTable.getColumnModel().getColumn(2).setPreferredWidth(5);
        usersInfoTable.getColumnModel().getColumn(4).setPreferredWidth(5);
        usersInfoTable.getColumnModel().getColumn(9).setPreferredWidth(5);

        // set the texts in the center under the columns .
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 11 ;i++ ){
            usersInfoTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // refresh the table for the real info .
        RefreshAllUserHintTable();

    }

    public AllUsersHints() {

        // create the table with it's information .
        CreateHintTable();


        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("The user stopped editing successfully.");
                try{

                    OpenConnection();
                    Statement getTheRealInfo = connection.createStatement();
                    String GetTheRealStoreInfo = "SELECT * FROM users WHERE user_name = '"+usersInfoTable.getValueAt(usersInfoTable.getSelectedRow() ,0)+"'";
                    ResultSet resultSet = getTheRealInfo.executeQuery(GetTheRealStoreInfo);

                    if(resultSet.next()){
                        usersInfoTable.setValueAt(resultSet.getString("user_name") , usersInfoTable.getSelectedRow(), 0);
                        usersInfoTable.setValueAt(resultSet.getString("password") , usersInfoTable.getSelectedRow(), 1);
                        if(Integer.parseInt(resultSet.getString("admin"))==1){
                            usersInfoTable.setValueAt("Manager" , usersInfoTable.getSelectedRow(), 2);
                        }
                        else {
                            usersInfoTable.setValueAt("Employee" , usersInfoTable.getSelectedRow(), 2);
                        }
                        usersInfoTable.setValueAt(resultSet.getString("full_name") , usersInfoTable.getSelectedRow(), 3);
                        usersInfoTable.setValueAt(resultSet.getString("age") , usersInfoTable.getSelectedRow(), 4);
                        usersInfoTable.setValueAt(resultSet.getString("phone_number") , usersInfoTable.getSelectedRow(), 5);
                        usersInfoTable.setValueAt(resultSet.getString("address") , usersInfoTable.getSelectedRow(), 6);
                        usersInfoTable.setValueAt(resultSet.getString("national_id") , usersInfoTable.getSelectedRow(), 7);
                        usersInfoTable.setValueAt(resultSet.getString("salary") , usersInfoTable.getSelectedRow(), 8);
                        usersInfoTable.setValueAt(resultSet.getString("rate") , usersInfoTable.getSelectedRow(), 9);
                        usersInfoTable.setValueAt(resultSet.getString("last_time_updated") , usersInfoTable.getSelectedRow(), 10);
                    }
                    CloseConnection();
                }
                catch (Exception ee){
                    ee.printStackTrace();
                    CloseConnection();
                }
            }
        };
        usersInfoTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);



        OKButton.addActionListener(e -> {
            //ToDO close the hint frame.
            ShowAllUsersInfo.dispose();
        });
    }


}
