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
import static Mini.Main.ShowTheLogs;

public class ShowLogs {
    private JPanel theMainScreen;
    private JTable logsTable;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreateLogTable(){

        logsTable.getTableHeader().setReorderingAllowed(false);
        logsTable.getTableHeader().setResizingAllowed(false);


        logsTable.setRowHeight(25);

        logsTable.setModel(new DefaultTableModel(
                null,
                new String[]{ "Bill Number", "Shift Number" ,"Username" , "Date" , "Total" , "Profit" }
        ));


        logsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));



        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0 ; i < 6 ; i++ ){
            logsTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        RefreshTheLogsTable();
    }

    public void RefreshTheLogsTable(){

        DefaultTableModel model = (DefaultTableModel) logsTable.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getStoreItems = connection.createStatement();
            String Store = "SELECT * FROM logs ";
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

    public ShowLogs() {

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
        logsTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);


        OKButton.addActionListener(e -> ShowTheLogs.dispose());
    }
}
