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
import static Mini.Main.ShowTheLogsForOne;
import static Mini.TheMainFrameHub.UserLogs;

public class ShowLogsForOneUserFrame {
    private JPanel theMainScreen;
    private JTable LogsTableForTheUser;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }


    public void CreateLogTable(){

        LogsTableForTheUser.getTableHeader().setReorderingAllowed(false);
        LogsTableForTheUser.getTableHeader().setResizingAllowed(false);


        LogsTableForTheUser.setRowHeight(25);

        LogsTableForTheUser.setModel(new DefaultTableModel(
                null,
                new String[]{ "Bill Number", "Shift Number" ,"Username" , "Date" , "Total" , "Profit" }
        ));

        LogsTableForTheUser.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0 ; i < 6 ; i++ ){
            LogsTableForTheUser.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        RefreshTheLogsTable();
    }

    public void RefreshTheLogsTable(){

        DefaultTableModel model = (DefaultTableModel) LogsTableForTheUser.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getStoreItems = connection.createStatement();
            String Store = "SELECT * FROM logs WHERE user_name = '"+UserLogs+"'";
            ResultSet resultSet = getStoreItems.executeQuery(Store);

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


    public ShowLogsForOneUserFrame() {


        CreateLogTable();

        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("The user stopped editing successfully.");
                RefreshTheLogsTable();
            }
        };
        LogsTableForTheUser.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);

        OKButton.addActionListener(e -> ShowTheLogsForOne.dispose());
    }
}
