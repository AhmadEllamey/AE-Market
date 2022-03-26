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
import static Mini.Main.BillsSearchFrame;
import static Mini.TheMainFrameHub.BillNumber;

public class BillsActivities {
    private JPanel theMainScreen;
    private JTable BillsInfoTable;
    private JTextField userNameText;
    private JTextField dateText;
    private JButton OKButton;
    private JLabel billNumber;
    private JTextField ShiftNumberText;
    private JTextField paidText;
    private JTextField savedText;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreateBillsInfoTable(){

        billNumber.setText(String.valueOf(BillNumber));

        BillsInfoTable.getTableHeader().setReorderingAllowed(false);
        BillsInfoTable.getTableHeader().setResizingAllowed(false);


        BillsInfoTable.setRowHeight(30);

        BillsInfoTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Bar Code" , "Item Name" , "Price" , "Quantity" ,"Discount For One","Total Discount","Total"}
        ));

        BillsInfoTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));


        // set the texts in the center under the columns .
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 7 ;i++ ){
            BillsInfoTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        RefreshBillsTable();
    }

    public void RefreshBillsTable(){

        try{

            DefaultTableModel modelX = (DefaultTableModel) BillsInfoTable.getModel();
            modelX.setRowCount(0);

            int flag = 1 ;
            OpenConnection();
            Statement GetBillsInfo = connection.createStatement();
            String BillsInfo = "Select * from sells_manager WHERE id_number = "+BillNumber+"";
            ResultSet resultSet = GetBillsInfo.executeQuery(BillsInfo);
            DefaultTableModel model = (DefaultTableModel) BillsInfoTable.getModel();

            while (resultSet.next()){

                if(flag == 1){

                    userNameText.setEnabled(false);
                    ShiftNumberText.setEnabled(false);
                    dateText.setEnabled(false);
                    savedText.setEnabled(false);
                    paidText.setEnabled(false);

                    userNameText.setHorizontalAlignment(JTextField.CENTER);
                    ShiftNumberText.setHorizontalAlignment(JTextField.CENTER);
                    dateText.setHorizontalAlignment(JTextField.CENTER);
                    savedText.setHorizontalAlignment(JTextField.CENTER);
                    paidText.setHorizontalAlignment(JTextField.CENTER);

                    userNameText.setText(resultSet.getString("user_name"));
                    ShiftNumberText.setText(resultSet.getString("shift_id"));
                    dateText.setText(resultSet.getString("CreatedDate"));

                    flag = 0 ;

                }

                Object[] row = { resultSet.getString("bar_code") ,
                        resultSet.getString("item_name"),
                        resultSet.getString("price") ,
                        resultSet.getString("quantity"),
                        resultSet.getString("discount") ,
                        resultSet.getString("total_discount") ,
                        resultSet.getString("total")};

                model.addRow(row);

            }

            CalculateTheTable();

            CloseConnection();

        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }

    }

    public void CalculateTheTable(){
        double totalBillCount = 0 ;
        double totalSavedCount = 0 ;
        Object[] columnData = new Object[BillsInfoTable.getRowCount()];
        Object[] columnData2 = new Object[BillsInfoTable.getRowCount()];
        for (int i = 0; i < BillsInfoTable.getRowCount(); i++) {  // Loop through the rows
            // Record the 5th column value (index 4)
            columnData[i] = BillsInfoTable.getValueAt(i, 6);
            columnData2[i] = BillsInfoTable.getValueAt(i, 5);
            totalBillCount = totalBillCount + Double.parseDouble(String.valueOf(columnData[i])) ;
            totalSavedCount = totalSavedCount + Double.parseDouble(String.valueOf(columnData2[i])) ;
        }
        paidText.setText(String.valueOf(totalBillCount));
        savedText.setText(String.valueOf(totalSavedCount));
    }

    public BillsActivities() {


        CreateBillsInfoTable();

        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("The user stopped editing successfully.");
                RefreshBillsTable();
            }
        };
        BillsInfoTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);

        OKButton.addActionListener(e -> BillsSearchFrame.dispose());
    }
}
