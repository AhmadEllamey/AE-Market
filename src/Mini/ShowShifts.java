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
import static Mini.Main.ShowShiftsFrame;

public class ShowShifts {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JTable ShiftsTable;



    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void RefreshTheRateTable(){


        // ToDo >>> Fill the table from the store table

        DefaultTableModel model = (DefaultTableModel) ShiftsTable.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getShifts = connection.createStatement();
            String UsersShifts = "SELECT * FROM shifts";
            ResultSet resultSet = getShifts.executeQuery(UsersShifts);

            while(resultSet.next()){
                Object[] row =  {resultSet.getString("number_of_shift") ,
                        resultSet.getString("date_of_day"),
                        resultSet.getString("user_name") ,
                        resultSet.getString("user_shift_number") ,
                        resultSet.getString("total_money_of_the_shift"),
                        resultSet.getString("total_earned_of_the_shift")};

                model.addRow(row);

            }

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }
    }

    public void CreateTheRateTable(){


        ShiftsTable.getTableHeader().setReorderingAllowed(false);
        ShiftsTable.getTableHeader().setResizingAllowed(false);



        ShiftsTable.setRowHeight(25);

        ShiftsTable.setModel(new DefaultTableModel(
                null,
                new String[]{ "Number", "Date" ,"Username" , "Shift Number" ,"Total Bills", "Total Profit" }
        ));

        ShiftsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 6 ; i++ ){
            ShiftsTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        ShiftsTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        ShiftsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        ShiftsTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        ShiftsTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        ShiftsTable.getColumnModel().getColumn(4).setPreferredWidth(15);
        ShiftsTable.getColumnModel().getColumn(5).setPreferredWidth(15);

        RefreshTheRateTable();

    }

    public ShowShifts() {


        CreateTheRateTable();


        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("The user stopped editing successfully.");
                RefreshTheRateTable();
            }
        };
        ShiftsTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);

        OKButton.addActionListener(e -> ShowShiftsFrame.dispose());
    }
}
