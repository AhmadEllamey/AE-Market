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
import static Mini.Main.ShowTheStoreItemsByRate;

public class ShowItemsByRate {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JTable StoreItemsByRateTable;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreateStoreTable(){

        StoreItemsByRateTable.getTableHeader().setReorderingAllowed(false);
        StoreItemsByRateTable.getTableHeader().setResizingAllowed(false);


        StoreItemsByRateTable.setRowHeight(30);

        StoreItemsByRateTable.setModel(new DefaultTableModel(
                null,
                new String[]{ "Bar Code", "Item Name" ,"Original Price" , "Price" , "Available" , "Sold" }
        ));

        StoreItemsByRateTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0 ; i < 6 ; i++ ){
            StoreItemsByRateTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        RefreshTheStoreTable();
    }

    public void RefreshTheStoreTable(){

        DefaultTableModel model = (DefaultTableModel) StoreItemsByRateTable.getModel();

        try{

            model.setRowCount(0);
            OpenConnection();
            Statement getItems = connection.createStatement();
            String ItemsByRate = "SELECT * FROM store ORDER BY rate DESC ";
            ResultSet resultSet = getItems.executeQuery(ItemsByRate);

            while(resultSet.next()){
                Object[] row =  {resultSet.getString("bar_code") ,
                        resultSet.getString("item_name"),
                        resultSet.getString("original_price_for_one") ,
                        resultSet.getString("price_for_one"),
                        resultSet.getString("number_of_items"),
                        resultSet.getString("rate")};

                model.addRow(row);

            }

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }
    }





    public ShowItemsByRate() {

        CreateStoreTable();

        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("The user stopped editing successfully.");
                RefreshTheStoreTable();
            }
        };
        StoreItemsByRateTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);

        OKButton.addActionListener(e -> ShowTheStoreItemsByRate.dispose());
    }
}
