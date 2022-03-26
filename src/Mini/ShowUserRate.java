package Mini;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.UsersRate;




public class ShowUserRate {

    private JPanel theMainScreen;
    private JButton OKButton;
    private JTable UsersRateTable;




    public void RefreshTheRateTable(){

// ToDo >>> Fill the table from the store table

        DefaultTableModel model = (DefaultTableModel) UsersRateTable.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getUsersAndRate = connection.createStatement();
            String UserRate = "SELECT user_name , rate FROM users";
            ResultSet resultSet = getUsersAndRate.executeQuery(UserRate);

            while(resultSet.next()){
                Object[] row =  {resultSet.getString("user_name") ,
                                 resultSet.getString("rate")};
                model.addRow(row);

            }

            CloseConnection();

        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();

        }
    }

    public void CreateTheRateTable(){

        UsersRateTable.setFocusable(false);
        UsersRateTable.setRowSelectionAllowed(false);
        UsersRateTable.setCellSelectionEnabled(false);
        UsersRateTable.setEnabled(false);
        UsersRateTable.getTableHeader().setReorderingAllowed(false);
        UsersRateTable.getTableHeader().setResizingAllowed(false);

        UsersRateTable.setRowHeight(25);

        UsersRateTable.setModel(new DefaultTableModel(
                null,
                new String[]{ "User Name" , "Rate" }
        ));

        UsersRateTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 2 ;i++ ){
            UsersRateTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        RefreshTheRateTable();

    }

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }



    public ShowUserRate() {

        // fill the table with the info


        CreateTheRateTable();


        OKButton.addActionListener(e -> UsersRate.dispose());
    }


}
