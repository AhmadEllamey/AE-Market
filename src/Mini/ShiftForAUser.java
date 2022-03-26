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
import static Mini.Main.ShowTheLastLogsForAUser;
import static Mini.TheMainFrameHub.ShiftNumberForLogs;
import static Mini.TheMainFrameHub.UserNameForLogs;

public class ShiftForAUser {
    private JPanel theMainScreen;
    private JTable ShiftsTable;
    private JButton okButton;
    private JLabel TitleText;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreateLogTable(){

        ShiftsTable.getTableHeader().setReorderingAllowed(false);
        ShiftsTable.getTableHeader().setResizingAllowed(false);


        ShiftsTable.setRowHeight(25);

        ShiftsTable.setModel(new DefaultTableModel(
                null,
                new String[]{ "Bill Number", "Shift Number" ,"Username" , "Date" , "Total" , "Profit" }
        ));

        ShiftsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0 ; i < 6 ; i++ ){
            ShiftsTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        RefreshTheLogsTable();
    }

    public void RefreshTheLogsTable(){

        DefaultTableModel model = (DefaultTableModel) ShiftsTable.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getShifts = connection.createStatement();
            String shifts = "SELECT * FROM logs WHERE user_name = '"+UserNameForLogs+"' AND shift_number = "+ShiftNumberForLogs+"";
            ResultSet resultSet = getShifts.executeQuery(shifts);

            while(resultSet.next()){
                Object[] row =  {resultSet.getString("id") ,
                        resultSet.getString("shift_number"),
                        resultSet.getString("user_name") ,
                        resultSet.getString("createdDateSQl"),
                        resultSet.getString("total"),
                        resultSet.getString("earned")};

                model.addRow(row);

            }

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }

    }

    public ShiftForAUser() {



        CreateLogTable();

        TitleText.setText("Logs for ("+UserNameForLogs+") In Shift Number ("+ShiftNumberForLogs+")");


        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("The user stopped editing successfully.");
                try{

                    OpenConnection();
                    Statement getTheRealInfo = connection.createStatement();
                    String GetTheRealStoreInfo = "SELECT * FROM logs WHERE id = '"+ShiftsTable.getValueAt(ShiftsTable.getSelectedRow() ,0)+"'";
                    ResultSet resultSet = getTheRealInfo.executeQuery(GetTheRealStoreInfo);

                    if(resultSet.next()){
                        ShiftsTable.setValueAt(resultSet.getString("id") , ShiftsTable.getSelectedRow(), 0);
                        ShiftsTable.setValueAt(resultSet.getString("shift_number") , ShiftsTable.getSelectedRow(), 1);
                        ShiftsTable.setValueAt(resultSet.getString("user_name") , ShiftsTable.getSelectedRow(), 2);
                        ShiftsTable.setValueAt(resultSet.getString("createdDateSQL") , ShiftsTable.getSelectedRow(), 3);
                        ShiftsTable.setValueAt(resultSet.getString("total") , ShiftsTable.getSelectedRow(), 4);
                        ShiftsTable.setValueAt(resultSet.getString("earned") , ShiftsTable.getSelectedRow(), 5);
                    }
                    CloseConnection();
                }
                catch (Exception ee){
                    ee.printStackTrace();
                    CloseConnection();
                }
            }
        };
        ShiftsTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);

        okButton.addActionListener(e -> ShowTheLastLogsForAUser.dispose());

    }
}
