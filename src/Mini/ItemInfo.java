package Mini;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Main.ItemMoreInfo;
import static Mini.TheMainFrameHub.GetMainSearchCompoText;

public class ItemInfo {
    private JPanel theMainScreen;
    private JLabel itemNameLabel;
    private JTable itemTableInfo;
    private JButton okToGo;
    private JLabel barCodeLabel;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreateTheTable(){

        //itemTableInfo.getTableHeader().setReorderingAllowed(false);
        //itemTableInfo.getTableHeader().setResizingAllowed(false);


        itemTableInfo.setRowHeight(30);

        itemTableInfo.setModel(new DefaultTableModel(
                null,
                new String[]{ "Merchant Name", "Number of boxes" ,"Number per box" , "Price for a box" , "Total Units" ,"Price For One","Total Price","Discount For One","Date" }
        ));


        itemTableInfo.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));



        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0 ; i < 9 ; i++ ){
            itemTableInfo.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        RefreshTheTable();

    }

    public void RefreshTheTable(){

        int i = 0 ;

        DefaultTableModel model = (DefaultTableModel) itemTableInfo.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getItemInfo = connection.createStatement();
            String itemInfo = "SELECT * FROM maintain_price WHERE bar_code = '"+GetMainSearchCompoText+"'";
            ResultSet resultSet = getItemInfo.executeQuery(itemInfo);


            while(resultSet.next()){

                if(i==0){
                    itemNameLabel.setText(resultSet.getString("item_name"));
                    barCodeLabel.setText(resultSet.getString("bar_code"));
                    i = 1 ;
                }

                Object[] row =  {resultSet.getString("merchant_name") ,
                        resultSet.getString("number_of_boxes"),
                        resultSet.getString("number_per_each_box") ,
                        resultSet.getString("cost_for_each_box"),
                        resultSet.getString("total_number_of_items"),
                        resultSet.getString("cost_for_one"),
                        resultSet.getString("total_cost"),
                        resultSet.getString("discount"),
                        resultSet.getString("CreatedDate")};

                model.addRow(row);

            }

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }

    }

    public ItemInfo() {


        CreateTheTable();


        okToGo.addActionListener(e -> ItemMoreInfo.dispose());
    }
}
