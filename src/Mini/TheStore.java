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
import static Mini.Main.ShowTheStore;

public class TheStore {
    private JPanel theMainScreen;
    private JTable StoreTable;
    private JButton OKButton;



    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreateTheStoreTable(){

        StoreTable.getTableHeader().setReorderingAllowed(false);
        StoreTable.getTableHeader().setResizingAllowed(false);


        StoreTable.setRowHeight(30);

        StoreTable.setModel(new DefaultTableModel(
                null,
                new String[]{ "Bar Code", "Item" ,"Original Price" , "Public Price" , "Discount For One" ,"Profit For One","Available","Sold","Last time updated" }
        ));

        StoreTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0 ; i < 9 ; i++ ){
            StoreTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }


        RefreshTheStoreTable();
    }

    public void RefreshTheStoreTable(){

        DefaultTableModel model = (DefaultTableModel) StoreTable.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getStoreItems = connection.createStatement();
            String Store = "SELECT * FROM store ";
            ResultSet resultSet = getStoreItems.executeQuery(Store);

            while(resultSet.next()){
                Object[] row =  {resultSet.getString("bar_code") ,
                        resultSet.getString("item_name"),
                        resultSet.getString("original_price_for_one") ,
                        resultSet.getString("price_for_one"),
                        resultSet.getString("discount"),
                        (Double.parseDouble(resultSet.getString("price_for_one"))-(Double.parseDouble(resultSet.getString("discount"))+Double.parseDouble(resultSet.getString("original_price_for_one")))),
                        resultSet.getString("number_of_items"),
                        resultSet.getString("rate"),
                        resultSet.getString("last_time_store_updated")};

                model.addRow(row);

            }

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }
    }


    public TheStore() {


        CreateTheStoreTable();


        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("The user stopped editing successfully.");
                RefreshTheStoreTable();
            }
        };
        StoreTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);

        OKButton.addActionListener(e -> ShowTheStore.dispose());
    }


}
