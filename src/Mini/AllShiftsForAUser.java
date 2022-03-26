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
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Main.ShowAllShiftsForOneUser;
import static Mini.TheMainFrameHub.WantedUserForAllShifts;

public class AllShiftsForAUser {
    private JPanel theMainScreen;
    private JButton okButton;
    private JTextField userNameText;
    private JTable ShiftsTable;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreateShiftsTable(){


        ShiftsTable.getTableHeader().setReorderingAllowed(false);
        ShiftsTable.getTableHeader().setResizingAllowed(false);

        ShiftsTable.setRowHeight(25);

        ShiftsTable.setModel(new DefaultTableModel(
                null,
                new String[]{ "Shift Number" , "Closed At" , "Cashier" , "Total Bills" ,"Total Profit" }
        ));

        ShiftsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 5 ;i++ ){
            ShiftsTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        ShiftsTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        ShiftsTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        ShiftsTable.getColumnModel().getColumn(4).setPreferredWidth(10);

        RefreshShiftsTable();
    }

    public void RefreshShiftsTable(){

        DefaultTableModel model = (DefaultTableModel) ShiftsTable.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getShifts = connection.createStatement();
            String Shifts = "SELECT * FROM shifts WHERE user_name = '"+WantedUserForAllShifts+"'";
            ResultSet resultSet = getShifts.executeQuery(Shifts);

            while(resultSet.next()){
                Object[] row =  {resultSet.getString("user_shift_number") ,
                        resultSet.getString("date_of_day"),
                        resultSet.getString("user_name"),
                        resultSet.getString("total_money_of_the_shift"),
                        resultSet.getString("total_earned_of_the_shift")};
                model.addRow(row);
            }

            CloseConnection();

        }catch (Exception ee){

            ee.printStackTrace();
            CloseConnection();

        }

    }



    public AllShiftsForAUser() {

        userNameText.setText(WantedUserForAllShifts);
        userNameText.setEnabled(false);

        userNameText.setHorizontalAlignment(JTextField.CENTER);

        CreateShiftsTable();


        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("The user stopped editing successfully.");
                RefreshShiftsTable();
            }
        };
        ShiftsTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);




        okButton.addActionListener(e -> ShowAllShiftsForOneUser.dispose());
    }
}
