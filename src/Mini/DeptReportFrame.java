package Mini;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Main.DeptReport;

public class DeptReportFrame {
    private JPanel theMainScreen;
    private JTable DebtTable;
    private JButton okButton;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreatTable(){

        DebtTable.getTableHeader().setReorderingAllowed(false);
        DebtTable.getTableHeader().setResizingAllowed(false);
        DebtTable.setEnabled(false);


        DebtTable.setRowHeight(25);

        DebtTable.setModel(new DefaultTableModel(
                null,
                new String[]{ "Name", "Last Time Updated" ,"Total"}
        ));

        DebtTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0 ; i < 3 ; i++ ){
            DebtTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        RefreshTable();
    }

    public void RefreshTable(){


        DefaultTableModel model = (DefaultTableModel) DebtTable.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getDebtsInfo = connection.createStatement();
            String Info = "SELECT * FROM dept ";
            ResultSet resultSet = getDebtsInfo.executeQuery(Info);

            while(resultSet.next()){
                Object[] row =  {resultSet.getString("user_name_dept") ,
                        resultSet.getString("CreatedDate"),
                        resultSet.getString("total")};

                model.addRow(row);

            }

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }

    }

    public DeptReportFrame() {

        CreatTable();

        okButton.addActionListener(e -> DeptReport.dispose());
    }
}
