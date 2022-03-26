package Mini;



import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.util.List;
import javax.print.attribute.*;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.*;



import static Mini.DBConnectionX.*;
import static Mini.Main.*;


public class TheMainFrameHub {
    private JPanel theMainScreen;
    private JLabel mainToolBar;
    private JLabel storeToolBar;
    private JLabel sellToolBar;
    private JLabel searchToolBar;
    private JLabel statisticsTollBar;
    private JLabel developerToolBar;
    private JLabel logOutToolBar;
    private JLabel exitToolBar;
    private JPanel theSwitchableScreen;
    private JPanel mainSwitchScreen;
    private JPanel storeSwitchScreen;
    private JPanel sellSwitchScreen;
    private JPanel statisticsSwitchScreen;
    private JPanel developerSwitchScreen;
    private JPanel welcomeScreen;
    private JLabel welcomeImage;
    private JPanel searchScreen;



    //Store card
    private JTextField barCodeText;
    private JTextField itemNameText;
    private JTextField originalPriceText;
    private JTextField publicPriceText;
    private JTextField numberOfItemsText;
    private JTextField nameOfItemInTheStoreSection;
    private JTextField quantityInTheStoreSection;
    private JTextField numbersPerBox;
    private JTextField priceOfBox;
    private JTextField discountText;
    private JTextField merchantNameText;

    private JTable storeTable;

    public static String barCodeToDelete ;

    private JButton addOrUpDateButton;
    private JButton clearButton;
    private JButton deleteButton;
    private JButton checkButton;


    //sell card

    private JButton clearTheSellForm;
    private JButton OKButtonToAddIntoTable;
    private JButton returnTheBill;
    private JButton clearTheSellsTable;
    private JButton finishTheOperationInSellsTable;
    private JButton deleteTheSellsTable;

    private JTextField barCodeTextForTable;
    private JTextField itemNameTextForTable;
    private JTextField priceTextForTable;
    private JTextField quantityTextForTable;
    private JTextField totalBillText;
    private JTextField paidText;
    private JTextField backText;

    private JTable sellsTable;










    //main card
    private JButton hintInfoButton;
    private JButton allInfoButton;
    private JButton createButton;
    private JButton addPublicNote;
    private JButton addPrivateNote;
    private JButton showPublicNote;
    private JButton showPrivateNote;
    private JButton deleteButton1;
    private JButton updateButton;
    private JButton depositButton;
    private JButton withdrawButton;

    private JButton itemCheckButton;
    private JButton userCheckButton;
    private JTextField MainSearchText;

    public static String GetMainSearchCompoText;




    // search card

    private JButton searchByNameButton;
    private JButton searchByBarCodeButton;
    private JLabel barCodeForUserSearch;
    private JLabel itemsLeftForUserSearch;
    private JLabel priceForUserSearch;
    private JLabel itemNameForUserSearch;
    private JTextField textToSearchInTheStoreTableByName;
    private JTable searchTableToFindByWord;
    private JLabel discountForTheSearchTab;

    // developer card
    private JLabel faceBookShortCut;



    // stat Card

    private JButton OKButtonForRating;
    private JButton OKButtonForShowRating;
    private JButton OKButtonForAllShifts;
    private JTextField WantedUserText;
    private JButton OKButtonForLastShift;
    private JTextField allShiftsForUserText;
    private JButton okButtonForAllUsersShifts;
    private JButton OKButtonForPermission;
    private JButton OKButtonForShowingTheStoreItems;
    private JButton okButtonItemsByRate;
    private JButton okButtonForAllLogs;
    private JTextField LogsForText;
    private JButton OKButtonForShowingLogsForUser;
    private JButton okButtonLogsForTheLastDay;
    private JButton OKButtonForCalculations;
    private JTextField userNameToGetTheLastLogs;
    private JTextField shiftToGetTheLastLogs;
    private JButton deptButton;
    private JButton showReportButton;
    private JTextField SearchBillsText;
    private JButton searchBillsButton;
    private JButton finishWithDebt;
    private JLabel SittingsToolBar;
    private JPanel SittingsScreen;
    private JTextField SavedTotal;
    private JTextField BillPathText;
    private JButton SaveBillPathButton;
    private JLabel timeNow;
    private JLabel cashierName;
    private JButton merchantsDebtsButton;
    private JButton merchantDebtInfoButton;
    private JButton SavePrinterName;
    private JTextField PrinterNameText;
    private JLabel AESystemsLabel;


    public static String WantedUser;
    public static String WantedUserForAllShifts ;
    public static String UserLogs;
    public static String UserNameForLogs;
    public static int ShiftNumberForLogs;
    public static int BillNumber ;
    public static int BillNumberNow ;
    public static double TotalBillToAddToDebt = 0 ;

    double totalSavedFromTheBill = 0 ;

    public static String BillUrlPath ;
    public static String PrinterNameFromTheComputer ;


    // set and get methods

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }


    // some important functions for store card
    public void ClearTheForm() {
        barCodeText.setText("");
        itemNameText.setText("");
        originalPriceText.setText("");
        publicPriceText.setText("");
        numberOfItemsText.setText("");
        numbersPerBox.setText("");
        priceOfBox.setText("");
        nameOfItemInTheStoreSection.setText("");
        quantityInTheStoreSection.setText("");
        discountText.setText("");
        merchantNameText.setText("");
    }


    // ToDO set the table for the sells card

    private void CreateSellsTable() {

        sellsTable.getTableHeader().setReorderingAllowed(false);
        sellsTable.getTableHeader().setResizingAllowed(false);

        sellsTable.setRowHeight(25);

        sellsTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Bar Code", "Item Name", "Price", "Quantity","Discount For One","Total Discount","Total"}
        ));

        sellsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 7 ;i++ ){
            sellsTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

    }

    public void RefreshSellsTable(){

        try{
            OpenConnection();
            Statement tryToGetTheInfo = connection.createStatement();
            String getInfoFromStoreTable = "Select * from store where bar_code = '"+barCodeTextForTable.getText().trim()+"'";
            ResultSet resultSet = tryToGetTheInfo.executeQuery(getInfoFromStoreTable);
            DefaultTableModel model = (DefaultTableModel) sellsTable.getModel();


            if(resultSet.next()){
                double priceForOne =  Double.parseDouble(resultSet.getString("price_for_one"));
                int numberOfItems = Integer.parseInt(resultSet.getString("item_counter_per_unit"));
                double totalRow = priceForOne * numberOfItems ;
                double totalDiscount = Double.parseDouble(resultSet.getString("discount")) * numberOfItems ;

                Object[] row = { resultSet.getString("bar_code") ,
                        resultSet.getString("item_name") ,
                        Double.parseDouble(resultSet.getString("price_for_one")) ,
                        Integer.parseInt(resultSet.getString("item_counter_per_unit")),
                        Double.parseDouble(resultSet.getString("discount")),
                        String.valueOf(totalDiscount),
                        String.valueOf(totalRow - totalDiscount) };

                model.addRow(row);
                MaintainTheTotalBill();

                // ToDo why this line doesn't work , it says >> Attempt to mutate in notification .

                Runnable doHighlight = () -> {
                    // your highlight code
                    barCodeTextForTable.setText("");
                    //System.out.println("repeated again in sells");
                };
                SwingUtilities.invokeLater(doHighlight);

            }
            else {

                Statement tryToGetTheInfo2 = connection.createStatement();
                String getInfoFromStoreTable2 = "Select * from add_bulk where bar_code = '"+barCodeTextForTable.getText().trim()+"'";
                ResultSet resultSet2 = tryToGetTheInfo2.executeQuery(getInfoFromStoreTable2);
                DefaultTableModel model2 = (DefaultTableModel) sellsTable.getModel();


                if(resultSet2.next()){
                    double priceForOne =  Double.parseDouble(resultSet2.getString("price_for_one"));
                    double numberOfItems = Integer.parseInt(resultSet2.getString("item_counter_per_unit"));
                    double totalRow = priceForOne * numberOfItems ;
                    double totalDiscount = Double.parseDouble(resultSet2.getString("discount")) * numberOfItems ;

                    Object[] row = {
                            resultSet2.getString("bar_code") ,
                            resultSet2.getString("item_name") ,
                            Double.parseDouble(resultSet2.getString("price_for_one")) ,
                            Integer.parseInt(resultSet2.getString("item_counter_per_unit")),
                            Double.parseDouble(resultSet2.getString("discount")),
                            String.valueOf(totalDiscount),
                            String.valueOf(totalRow - totalDiscount)
                    };

                    model2.addRow(row);
                    MaintainTheTotalBill();

                    // ToDo why this line doesn't work , it says >> Attempt to mutate in notification .

                    Runnable doHighlight = () -> {
                        // your highlight code
                        barCodeTextForTable.setText("");
                        //System.out.println("repeated again in sells");
                    };
                    SwingUtilities.invokeLater(doHighlight);

                }


            }

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();

        }


    }

    public void UpdateRowInSellsTable(){

        try{

            OpenConnection();
            Statement getTheOriginalPrice = connection.createStatement();
            String getOriginal = "SELECT * FROM store WHERE bar_code = '"+ sellsTable.getValueAt(sellsTable.getSelectedRow() ,0)+ "'";
            ResultSet resultSet = getTheOriginalPrice.executeQuery(getOriginal);

            if(resultSet.next()){

                if(Double.parseDouble(resultSet.getString("original_price_for_one")) >
                        Double.parseDouble(String.valueOf(sellsTable.getValueAt(sellsTable.getSelectedRow() ,2)))){

                    sellsTable.setValueAt(resultSet.getString("original_price_for_one") , sellsTable.getSelectedRow(), 2);
                    //System.out.println("try to help");
                }
            }else {

                Statement getTheOriginalPrice2 = connection.createStatement();
                String getOriginal2 = "SELECT * FROM add_bulk WHERE bar_code = '"+ sellsTable.getValueAt(sellsTable.getSelectedRow() ,0)+ "'";
                ResultSet resultSet2 = getTheOriginalPrice2.executeQuery(getOriginal2);

                if(resultSet2.next()){

                    if(Double.parseDouble(resultSet2.getString("original_price_for_one")) >
                            Double.parseDouble(String.valueOf(sellsTable.getValueAt(sellsTable.getSelectedRow() ,2)))){

                        sellsTable.setValueAt(resultSet2.getString("original_price_for_one") , sellsTable.getSelectedRow(), 2);
                        //System.out.println("try to help");
                    }
                }

            }
            CloseConnection();
        }
        catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }

        double totalDiscount = 0 ;

        try {

            totalDiscount = Double.parseDouble(String.valueOf(sellsTable.getValueAt(sellsTable.getSelectedRow() ,3))) *
                    Double.parseDouble(String.valueOf(sellsTable.getValueAt(sellsTable.getSelectedRow() ,4)));
            sellsTable.setValueAt(totalDiscount, sellsTable.getSelectedRow(), 5);

        }catch (Exception e){
            e.printStackTrace();
        }



        double data = Double.parseDouble(String.valueOf(sellsTable.getValueAt(sellsTable.getSelectedRow() ,2))) *
                Double.parseDouble(String.valueOf(sellsTable.getValueAt(sellsTable.getSelectedRow() ,3)));
        sellsTable.setValueAt(data - totalDiscount, sellsTable.getSelectedRow(), 6);



        MaintainTheTotalBill();
        barCodeTextForTable.requestFocusInWindow();

    }

    public void MaintainTheTotalBill(){

        double totalBillCount = 0 ;
        totalSavedFromTheBill = 0 ;

        Object[] columnData = new Object[sellsTable.getRowCount()];  // One entry for each row
        Object[] columnData2 = new Object[sellsTable.getRowCount()];  // One entry for each row
        for (int i = 0; i < sellsTable.getRowCount(); i++) {  // Loop through the rows
            // Record the 5th column value (index 4)
            columnData[i] = sellsTable.getValueAt(i, 6);
            columnData2[i] = sellsTable.getValueAt(i, 5);
            totalBillCount = totalBillCount + Double.parseDouble(String.valueOf(columnData[i])) ;
            totalSavedFromTheBill = totalSavedFromTheBill + Double.parseDouble(String.valueOf(columnData2[i]));
        }
        totalBillText.setText(String.valueOf(totalBillCount));
        SavedTotal.setText(String.valueOf(totalSavedFromTheBill));

        //totalSavedText.setText(String.valueOf(totalSavedFromTheBill));
    }

    public void UpDateTheShift(){

        double totalBillsForShift = 0 ;
        double totalEarnedForShift = 0 ;

        // ***** in this function the shift is calculated for the user about his shift and what happened in it , in the last 24 hours of his shift

        try{


            // check if the user is already in the shifts or not yet
            Statement CheckIfTheShiftExist = connection.createStatement();
            String FindTheShift = "SELECT * FROM shifts WHERE user_name = '"+userName+"'  AND user_shift_number = "+ShiftNumber+"";
            ResultSet resultSetForTheShift = CheckIfTheShiftExist.executeQuery(FindTheShift);


            // get all bills for the user in this shift
            Statement GetUserInfo = connection.createStatement();
            String TransactionsOfTheDay = "SELECT * FROM logs WHERE user_name = '"+userName+"'  AND shift_number = "+ShiftNumber+"";
            ResultSet resultSet = GetUserInfo.executeQuery(TransactionsOfTheDay);

            if(resultSetForTheShift.next()){

                while (resultSet.next()){
                    totalBillsForShift = totalBillsForShift + Double.parseDouble(resultSet.getString("total"));
                    totalEarnedForShift = totalEarnedForShift + Double.parseDouble(resultSet.getString("earned"));
                }

                Statement UpDateShiftTable = connection.createStatement();


                String SetTheShift = "UPDATE shifts Set total_money_of_the_shift = "+totalBillsForShift+", total_earned_of_the_shift = "+totalEarnedForShift+" WHERE " +
                        "user_name = '"+userName+"' AND user_shift_number = "+ShiftNumber+"";

                UpDateShiftTable.executeUpdate(SetTheShift);


            }
            else{

                while (resultSet.next()){
                    totalBillsForShift = totalBillsForShift + Double.parseDouble(resultSet.getString("total"));
                    totalEarnedForShift = totalEarnedForShift + Double.parseDouble(resultSet.getString("earned"));
                }

                Statement UpDateShiftTable = connection.createStatement();


                String SetTheShift = "INSERT INTO shifts (user_name,user_shift_number,total_money_of_the_shift, total_earned_of_the_shift) VALUES " +
                        "('"+userName+"',"+ShiftNumber+","+totalBillsForShift+","+totalEarnedForShift+")";

                UpDateShiftTable.executeUpdate(SetTheShift);
            }




        }catch (Exception e){
            e.printStackTrace();

        }

    }



    private void PrintReportToPrinter(JasperPrint jp) {

        try {
            // TODO Auto-generated method stub
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
            printRequestAttributeSet.add(new Copies(1));

            PrinterName printerName = new PrinterName(PrinterNameFromTheComputer, null); //gets printer

            PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
            printServiceAttributeSet.add(printerName);

            JRPrintServiceExporter exporter = new JRPrintServiceExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public void FinishTheBill(){

        if(Double.parseDouble(totalBillText.getText()) > 0) {
            OpenConnection();
            String bar;
            String fullName = "null";
            double quantity;
            double leftInStore;
            double originalPrice = 0;
            double earned ;
            int barCodeForSellsManager = 0 ;
            double rate ;

            List<Map<String,?>> dataSource = new ArrayList();


            Object[] barData = new Object[sellsTable.getRowCount()];
            Object[] quantityData = new Object[sellsTable.getRowCount()];
            // loop through the table
            for (int i = 0; i < sellsTable.getRowCount(); i++) {  // Loop through the rows
                // Record the 5th column value (index 4)
                leftInStore = 0 ;
                rate = 0 ;
                barData[i] = sellsTable.getValueAt(i, 0);
                bar = String.valueOf(barData[i]);
                quantityData[i] = sellsTable.getValueAt(i, 3);
                // get the store update
                try {
                    quantity = Double.parseDouble(String.valueOf(quantityData[i]));
                    Statement getTheNumberOfItemsInTheStore = connection.createStatement();
                    String getTheItemsLeft = "SELECT * FROM store WHERE bar_code = '" + bar + "'";
                    ResultSet resultSet = getTheNumberOfItemsInTheStore.executeQuery(getTheItemsLeft);

                    if (resultSet.next()) {
                        leftInStore = Double.parseDouble(resultSet.getString("number_of_items"));
                        originalPrice = originalPrice + (Double.parseDouble(resultSet.getString("original_price_for_one")) * quantity);
                        leftInStore = leftInStore - quantity;
                        Statement upDateTheStoreNow = connection.createStatement();
                        String UpdateStore = "UPDATE store SET number_of_items = " + leftInStore +", rate = "
                                +(quantity+Double.parseDouble(resultSet.getString("rate")))
                                +" WHERE bar_code = '" + bar + "'";
                        upDateTheStoreNow.executeUpdate(UpdateStore);
                    }else {


                        Statement getTheNumberOfItemsInTheStore2 = connection.createStatement();
                        String getTheItemsLeft2 = "SELECT * FROM add_bulk WHERE bar_code = '"+ bar +"'";
                        ResultSet resultSet2 = getTheNumberOfItemsInTheStore2.executeQuery(getTheItemsLeft2);

                        if (resultSet2.next()) {
                            String relatedBar = resultSet2.getString("related_bar_code");
                            originalPrice = originalPrice + (Double.parseDouble(resultSet2.getString("original_price_for_one")) * quantity);

                            quantity = quantity * Double.parseDouble(resultSet2.getString("number_of_items_per_one_box"));

                            Statement getTheNumberOfItemsInTheStore3 = connection.createStatement();
                            String getTheItemsLeft3 = "SELECT * FROM store WHERE bar_code = '" + relatedBar + "'";
                            ResultSet resultSet3 = getTheNumberOfItemsInTheStore3.executeQuery(getTheItemsLeft3);

                            if(resultSet3.next()){
                                leftInStore = Double.parseDouble(resultSet3.getString("number_of_items"));
                                rate = Double.parseDouble(resultSet3.getString("rate"));
                            }

                            leftInStore = leftInStore - quantity;


                            Statement upDateTheStoreNow = connection.createStatement();
                            String UpdateStore = "UPDATE store SET number_of_items = " + leftInStore +", rate = "
                                    +(quantity+rate)
                                    +" WHERE bar_code = '" + relatedBar + "'";
                            upDateTheStoreNow.executeUpdate(UpdateStore);
                        }

                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }


                Map<String, Object> m = new HashMap();
                m.put("item_name",String.valueOf(sellsTable.getValueAt(i, 1)));
                m.put("price",String.valueOf(sellsTable.getValueAt(i, 2)));
                m.put("quantity",String.valueOf(sellsTable.getValueAt(i, 3)));
                m.put("discount",String.valueOf(sellsTable.getValueAt(i, 5)));
                m.put("total",String.valueOf(sellsTable.getValueAt(i, 6)));
                dataSource.add(m);

            }

            // update the logs tables with the bill !
            try {

                earned = Double.parseDouble(totalBillText.getText()) - originalPrice;
                Statement UpDateTheLogs = connection.createStatement();
                String SendDataToLogs = "INSERT INTO logs (shift_number,user_name,total,earned) VALUES (" +ShiftNumber+",'"+userName+"',"+ Double.parseDouble(totalBillText.getText()) + "," + earned + ")";
                UpDateTheLogs.executeUpdate(SendDataToLogs);


                // add into sells manager .
                Statement UpDateTheSellsManager = connection.createStatement();
                String BillsData = "SELECT id FROM logs WHERE shift_number = "+ShiftNumber+"" ;
                ResultSet resultSetForGettingTheId = UpDateTheSellsManager.executeQuery(BillsData);

                if(resultSetForGettingTheId.next()){

                    resultSetForGettingTheId.last();
                    barCodeForSellsManager = Integer.parseInt(resultSetForGettingTheId.getString("id"));
                    BillNumberNow = barCodeForSellsManager ;
                    Statement InsertIntoSellsManager = connection.createStatement();

                    for (int i = 0; i < sellsTable.getRowCount(); i++){

                        String sellsManager = "INSERT INTO sells_manager (id_number,user_name,shift_id,bar_code, item_name, price, quantity,discount,total_discount, total) VALUES ("
                                +barCodeForSellsManager+
                                ",'"+userName+"',"+ShiftNumber+
                                ",'"+sellsTable.getValueAt(i, 0)+"','"+sellsTable.getValueAt(i, 1)+
                                "',"+Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 2)))+
                                ","+Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 3)))+
                                ","+Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 4)))+
                                ","+Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 5)))+
                                ","+Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 6)))+")";
                        InsertIntoSellsManager.executeUpdate(sellsManager);

                    }

                }

                // continue the method
                TotalBillToAddToDebt = Double.parseDouble(totalBillText.getText().trim());

                DefaultTableModel model = (DefaultTableModel) sellsTable.getModel();

                UpDateTheShift();


                Statement getFullName = connection.createStatement();
                String FullNameString = "SELECT full_name FROM users WHERE user_name = '"+userName+"'";
                ResultSet resultSetForFullName = getFullName.executeQuery(FullNameString);

                if(resultSetForFullName.next()){
                    fullName = resultSetForFullName.getString("full_name");
                }




                CloseConnection();

                totalBillText.setText("0.0");
                SavedTotal.setText("0.0");
                paidText.setText("0.0");
                backText.setText("0.0");
                model.setRowCount(0);
                barCodeTextForTable.requestFocusInWindow();

                // print the bill


                HashMap parameter = new HashMap();
                parameter.put("TotalP",String.valueOf(TotalBillToAddToDebt));
                parameter.put("SavedP",String.valueOf(totalSavedFromTheBill));
                parameter.put("CashierName",fullName);
                parameter.put("BillNumberH",String.valueOf(barCodeForSellsManager));

                JRBeanCollectionDataSource dataSource1 = new JRBeanCollectionDataSource(dataSource);

                System.out.println(BillUrlPath);

                JasperDesign jd = JRXmlLoader.load(BillUrlPath);
                JasperReport jp = JasperCompileManager.compileReport(jd);



                JasperPrint print = JasperFillManager.fillReport(jp, parameter, dataSource1);

                //JasperViewer.viewReport(print, false);

                PrintReportToPrinter(print);



            }


            catch (Exception ee) {
                ee.printStackTrace();
                CloseConnection();
            }



        }

    }

    public void ReturnTheBill(){
        if(Double.parseDouble(totalBillText.getText()) > 0) {
            String bar;
            double quantity;
            double leftInStore;
            double originalPrice = 0;
            double lost  ;
            double rate ;


            Object[] barData = new Object[sellsTable.getRowCount()];// One entry for each row
            Object[] quantityData = new Object[sellsTable.getRowCount()];
            // loop through the table
            for (int i = 0; i < sellsTable.getRowCount(); i++) {  // Loop through the rows
                // Record the 5th column value (index 4)
                barData[i] = sellsTable.getValueAt(i, 0);
                bar = String.valueOf(barData[i]);
                quantityData[i] = sellsTable.getValueAt(i, 3);
                rate = 0 ;
                leftInStore = 0 ;
                quantity = Double.parseDouble(String.valueOf(quantityData[i]));
                // get the store update
                try {
                    OpenConnection();
                    Statement getTheNumberOfItemsInTheStore = connection.createStatement();
                    String getTheItemsLeft = "SELECT * FROM store WHERE bar_code = '" + bar + "'";
                    ResultSet resultSet = getTheNumberOfItemsInTheStore.executeQuery(getTheItemsLeft);

                    if (resultSet.next()) {
                        leftInStore = Double.parseDouble(resultSet.getString("number_of_items"));
                        originalPrice = originalPrice + (Double.parseDouble(resultSet.getString("original_price_for_one")) * quantity);
                        leftInStore = leftInStore + quantity;
                        Statement upDateTheStoreNow = connection.createStatement();
                        String UpdateStore = "UPDATE store SET number_of_items = " + leftInStore +", rate ="
                                +(Double.parseDouble(resultSet.getString("rate"))-quantity)+" WHERE bar_code = '" + bar + "'";
                        upDateTheStoreNow.executeUpdate(UpdateStore);
                    }else {


                        Statement getTheNumberOfItemsInTheStore2 = connection.createStatement();
                        String getTheItemsLeft2 = "SELECT * FROM add_bulk WHERE bar_code = '"+ bar +"'";
                        ResultSet resultSet2 = getTheNumberOfItemsInTheStore2.executeQuery(getTheItemsLeft2);

                        if (resultSet2.next()) {
                            String relatedBar = resultSet2.getString("related_bar_code");
                            originalPrice = originalPrice + (Double.parseDouble(resultSet2.getString("original_price_for_one")) * quantity);

                            quantity = quantity * Double.parseDouble(resultSet2.getString("number_of_items_per_one_box"));

                            Statement getTheNumberOfItemsInTheStore3 = connection.createStatement();
                            String getTheItemsLeft3 = "SELECT * FROM store WHERE bar_code = '" + relatedBar + "'";
                            ResultSet resultSet3 = getTheNumberOfItemsInTheStore3.executeQuery(getTheItemsLeft3);

                            if(resultSet3.next()){
                                leftInStore = Double.parseDouble(resultSet3.getString("number_of_items"));
                                rate = Double.parseDouble(resultSet3.getString("rate"));
                            }

                            leftInStore = leftInStore - (quantity*-1);


                            Statement upDateTheStoreNow = connection.createStatement();
                            String UpdateStore = "UPDATE store SET number_of_items = " + leftInStore +", rate = "
                                    +(rate-quantity)
                                    +" WHERE bar_code = '" + relatedBar + "'";
                            upDateTheStoreNow.executeUpdate(UpdateStore);
                        }


                    }




                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // update the logs tables with the bill !
            try {

                lost = ((Double.parseDouble(totalBillText.getText()) - originalPrice) * -1 );
                Statement UpDateTheLogs = connection.createStatement();
                String SendDataToLogs = "INSERT INTO logs (shift_number,user_name,total,earned) VALUES ("+ShiftNumber+",'"+ userName+"',"+ (Double.parseDouble(totalBillText.getText())*-1) + "," + lost + ")";
                UpDateTheLogs.executeUpdate(SendDataToLogs);


                // add into sells manager .
                Statement UpDateTheSellsManager = connection.createStatement();
                String BillsData = "SELECT id FROM logs WHERE shift_number = "+ShiftNumber+"" ;
                ResultSet resultSetForGettingTheId = UpDateTheSellsManager.executeQuery(BillsData);

                if(resultSetForGettingTheId.next()){

                    resultSetForGettingTheId.last();
                    int barCodeForSellsManager = Integer.parseInt(resultSetForGettingTheId.getString("id"));
                    Statement InsertIntoSellsManager = connection.createStatement();

                    for (int i = 0; i < sellsTable.getRowCount(); i++){

                        String sellsManager = "INSERT INTO sells_manager (id_number,user_name,shift_id,bar_code, item_name, price, quantity,discount,total_discount, total) VALUES ("
                                +barCodeForSellsManager+
                                ",'"+userName+"',"+ShiftNumber+
                                ",'"+sellsTable.getValueAt(i, 0)+"','"+sellsTable.getValueAt(i, 1)+
                                "',"+(Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 2))) * -1)+
                                ","+(Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 3))) * -1)+
                                ","+(Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 4))) * 1)+
                                ","+(Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 5))) * 1)+
                                ","+(Double.parseDouble(String.valueOf(sellsTable.getValueAt(i, 6))) * -1)+")";
                        InsertIntoSellsManager.executeUpdate(sellsManager);

                    }

                }

                // continue the methods .
                totalBillText.setText("0.0");
                SavedTotal.setText("0.0");
                paidText.setText("0.0");
                backText.setText("0.0");
                DefaultTableModel model = (DefaultTableModel) sellsTable.getModel();
                model.setRowCount(0);
                barCodeTextForTable.requestFocusInWindow();
                UpDateTheShift();
                CloseConnection();

            } catch (Exception ee) {
                ee.printStackTrace();
                CloseConnection();
            }
        }
    }

    public void UpdateTheBillPath(){
        try {
            OpenConnection();

            Statement getTheBillUrl = connection.createStatement();
            String BillUrl = "SELECT * FROM bill_path ";
            ResultSet resultSet = getTheBillUrl.executeQuery(BillUrl);

            if (resultSet.next()){
                resultSet.last();
                BillUrlPath = String.valueOf(resultSet.getString("path_url"));
                PrinterNameFromTheComputer = String.valueOf(resultSet.getString("printer_name"));
            }

            CloseConnection();

        }catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }
    }

    public void ChooseTheWantedDebtUser(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        AddToDebtFromSellScreen = new JFrame("Add To Debts And Go !");
        AddToDebtFromSellScreen.setContentPane(new AddToDebtsFromTheSells().getTheMainScreen());
        AddToDebtFromSellScreen.setIconImage(img.getImage());
        AddToDebtFromSellScreen.setLocationRelativeTo(null);//Setting location to the center of screen
        AddToDebtFromSellScreen.getContentPane().setBackground(Color.black);//setting background color
        AddToDebtFromSellScreen.setSize(600,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        AddToDebtFromSellScreen.setLocation(dim.width/2-AddToDebtFromSellScreen.getSize().width/2, dim.height/2-AddToDebtFromSellScreen.getSize().height/2);

        AddToDebtFromSellScreen.setResizable(false);
        AddToDebtFromSellScreen.setVisible(true);
    }

    public void UpdateBack(){

        double paid ;

        try {

            paid = Double.parseDouble(paidText.getText().trim());

            paid = paid - Double.parseDouble(totalBillText.getText().trim());

            backText.setText(String.valueOf(paid));

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }





    // ToDo set the table for the store card

    private void CreateStoreTable() {

        storeTable.setFocusable(false);
        storeTable.setRowSelectionAllowed(false);
        storeTable.setCellSelectionEnabled(false);
        storeTable.setEnabled(false);
        storeTable.getTableHeader().setReorderingAllowed(false);
        storeTable.getTableHeader().setResizingAllowed(false);

        storeTable.setRowHeight(25);

        storeTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Item Name", "Price","Discount","Original Price","Available"}
        ));

        storeTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 5 ;i++ ){
            storeTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        RefreshStoreTable();

    }

    public void RefreshStoreTable(){



        // ToDo >>> Fill the table from the store table

        DefaultTableModel model = (DefaultTableModel) storeTable.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement ForFreshInfo = connection.createStatement();
            String Fresh = "SELECT * FROM store";
            ResultSet FreshResultSet = ForFreshInfo.executeQuery(Fresh);

            while(FreshResultSet.next()){
                Object[] row = { FreshResultSet.getString("item_name")
                        ,FreshResultSet.getString("price_for_one")
                        ,FreshResultSet.getString("discount")
                        ,FreshResultSet.getString("original_price_for_one")
                        ,FreshResultSet.getString("number_of_items")};
                model.addRow(row);

            }

            CloseConnection();

        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();

        }

    }

    public void UpdateTheStore(){

        String bar_code ;
        String item_name ;
        String nameOfTheMerchant;
        double public_price ;
        double number_of_boxes ;
        double number_per_each_box ;
        double price_of_box ;
        double discountForOne ;
        double costForOneItem ;

        if(!barCodeText.getText().trim().isEmpty()){

            try{

                bar_code = barCodeText.getText().trim();
                item_name = itemNameText.getText().trim();
                public_price = Double.parseDouble(publicPriceText.getText().trim());
                number_of_boxes = Double.parseDouble(numberOfItemsText.getText().trim());
                number_per_each_box = Double.parseDouble(numbersPerBox.getText().trim());
                price_of_box = Double.parseDouble(priceOfBox.getText().trim());
                discountForOne = Double.parseDouble(discountText.getText().trim());
                nameOfTheMerchant = merchantNameText.getText().trim();

                costForOneItem = Double.parseDouble(originalPriceText.getText().trim());

            }catch (Exception e){
                e.printStackTrace();
                return;
            }


            try{


                OpenConnection();

                Statement checkStatement = connection.createStatement();
                String checkBarCode = "select * from store where bar_code='"+ bar_code +"'";
                ResultSet resultSet = checkStatement.executeQuery(checkBarCode);

                if(resultSet.next()){


                    double total_quantity_over_all_time = 0 ;
                    double total_cost_price_over_all_time = 0 ;

                    Statement insertIntoMaintainTable = connection.createStatement();
                    String typeIntoMaintainTable = "INSERT INTO maintain_price (bar_code, item_name, number_of_boxes, number_per_each_box, cost_for_each_box,discount,cost_for_one,merchant_name) VALUES ('"+
                            bar_code+"','"+item_name+"',"+number_of_boxes+","+number_per_each_box+","+price_of_box+","+discountForOne+","+costForOneItem+",'"+nameOfTheMerchant+"')" ;
                    insertIntoMaintainTable.executeUpdate(typeIntoMaintainTable);


                    Statement getStoreInfo = connection.createStatement();
                    String getInfo = "SELECT * FROM maintain_price WHERE bar_code = '"+bar_code+"'" ;
                    ResultSet resultSetForInfo = getStoreInfo.executeQuery(getInfo);

                    while (resultSetForInfo.next()){

                        total_quantity_over_all_time = total_quantity_over_all_time + Integer.parseInt(resultSetForInfo.getString("total_number_of_items"));

                        total_cost_price_over_all_time = total_cost_price_over_all_time + Double.parseDouble(resultSetForInfo.getString("total_cost"));

                    }

                    double meanPriceForOne = total_cost_price_over_all_time / total_quantity_over_all_time ;



                    // if the barcode already exists in the database >>> update the database >>> clear the frame to add again
                    double total = Double.parseDouble(resultSet.getString("number_of_items")) + (number_of_boxes * number_per_each_box);

                    Statement upDateStatement = connection.createStatement();
                    String upDateData = "UPDATE store SET item_name ='" + item_name + "', price_for_one = " + public_price + ",original_price_for_one = "
                            + meanPriceForOne + ", number_of_items = " + total + ", discount = "+discountForOne+" WHERE bar_code = " + bar_code;
                    upDateStatement.executeUpdate(upDateData);


                }

                else {



                    // if the barcode doesn't exist in the database >>> insert the barcode info >>> clear the frame to add again
                    Statement insertStatement = connection.createStatement();
                    String insertData = "INSERT INTO store (bar_code, item_name,item_counter_per_unit,price_for_one, original_price_for_one, number_of_items,rate,discount) VALUES ('"
                            + bar_code +
                            "','" + item_name + "',"+1+","+ public_price + "," + ((price_of_box * number_of_boxes)/(number_of_boxes * number_per_each_box)) + "," + (number_of_boxes * number_per_each_box) + ", 0 ,"+discountForOne+")";
                    insertStatement.executeUpdate(insertData);


                    Statement insertIntoMaintainTable = connection.createStatement();
                    String typeIntoMaintainTable = "INSERT INTO maintain_price (bar_code, item_name, number_of_boxes, number_per_each_box, cost_for_each_box, discount,cost_for_one,merchant_name) VALUES ('" +
                            bar_code+"','"+item_name+"',"+number_of_boxes+","+number_per_each_box+","+price_of_box+","+discountForOne+","+costForOneItem+",'"+nameOfTheMerchant+"')" ;
                    insertIntoMaintainTable.executeUpdate(typeIntoMaintainTable);


                }



                // clear the form for another try
                ClearTheForm();
                RefreshStoreTable();
                barCodeText.requestFocusInWindow();

                CloseConnection();



            }catch (Exception e){
                e.printStackTrace();
                CloseConnection();
            }


        }


    }

    public void CalculateTheOriginalPrice(){

        try{

            if(Double.parseDouble(numberOfItemsText.getText().trim()) != 0 && Double.parseDouble(priceOfBox.getText().trim()) != 0 && Double.parseDouble(numbersPerBox.getText().trim()) != 0 ){

                double originalPrice = (Double.parseDouble(numberOfItemsText.getText().trim()) * Double.parseDouble(priceOfBox.getText().trim())) / (Double.parseDouble(numberOfItemsText.getText().trim()) * Double.parseDouble(numbersPerBox.getText().trim())) ;

                originalPriceText.setText(String.valueOf(originalPrice));

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void OpenAddBulkScreen(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        AddBulkFrame = new JFrame("Add Bulks !");
        AddBulkFrame.setContentPane(new AddBulkScreen().getTheMainScreen());
        AddBulkFrame.setIconImage(img.getImage());
        AddBulkFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        AddBulkFrame.getContentPane().setBackground(Color.black);//setting background color
        AddBulkFrame.setSize(800,600);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        AddBulkFrame.setLocation(dim.width/2-AddBulkFrame.getSize().width/2, dim.height/2-AddBulkFrame.getSize().height/2);

        AddBulkFrame.setResizable(false);
        AddBulkFrame.setVisible(true);
    }

    // on typed listener for <<<<beta version>>>>> Check it Later .
    public void SearchInStoreTable(){

        Runnable doHighlight = () -> {
            // your highlight code
            try{
                OpenConnection();
                Statement SearchStore = connection.createStatement();
                String StoreItems = "select * from store where bar_code='"+barCodeText.getText().trim()+"'";
                ResultSet resultSet = SearchStore.executeQuery(StoreItems);

                if(resultSet.next()){

                    itemNameText.setText(resultSet.getString("item_name"));
                    publicPriceText.setText(resultSet.getString("price_for_one"));
                    originalPriceText.setText(resultSet.getString("original_price_for_one"));
                    discountText.setText(resultSet.getString("discount"));

                    nameOfItemInTheStoreSection.setText(resultSet.getString("item_name"));
                    quantityInTheStoreSection.setText(resultSet.getString("number_of_items"));


                }

                Statement merchant = connection.createStatement();
                String merchantName = "select * from maintain_price where bar_code='"+barCodeText.getText().trim()+"'";
                ResultSet resultSetMerchant = merchant.executeQuery(merchantName);

                if(resultSetMerchant.next()){

                    merchantNameText.setText(resultSetMerchant.getString("merchant_name"));

                }

                CloseConnection();


            }catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }
        };

        SwingUtilities.invokeLater(doHighlight);

    }




    // ToDo set the table for the search card

    private void CreateSearchTable() {

        searchTableToFindByWord.setFocusable(false);
        searchTableToFindByWord.setRowSelectionAllowed(false);
        searchTableToFindByWord.setCellSelectionEnabled(false);
        searchTableToFindByWord.setEnabled(false);
        searchTableToFindByWord.getTableHeader().setReorderingAllowed(false);
        searchTableToFindByWord.getTableHeader().setResizingAllowed(false);

        searchTableToFindByWord.setRowHeight(25);


        searchTableToFindByWord.setModel(new DefaultTableModel(
                null,
                new String[]{"Bar Code","Item Name","Price","Discount","Available"}
        ));

        searchTableToFindByWord.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));


        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 5 ;i++ ){
            searchTableToFindByWord.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        RefreshSearchTable();


    }

    public void RefreshSearchTable(){
        // ToDo >>> Fill the table from the store table

        DefaultTableModel model = (DefaultTableModel) searchTableToFindByWord.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement ForFreshInfo = connection.createStatement();
            String Fresh = "SELECT * FROM store";
            ResultSet FreshResultSet = ForFreshInfo.executeQuery(Fresh);

            while(FreshResultSet.next()){
                Object[] row = { FreshResultSet.getString("bar_code")
                        ,FreshResultSet.getString("item_name")
                        ,FreshResultSet.getString("price_for_one")
                        ,FreshResultSet.getString("discount")
                        ,FreshResultSet.getString("number_of_items")};
                model.addRow(row);

            }

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();

        }
    }

    public void ShowNotInTheStore(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        NotInTheStoreFrame = new JFrame("Not in The Store !");
        NotInTheStoreFrame.setContentPane(new NotInTheStore().getTheMainScreen());
        NotInTheStoreFrame.setIconImage(img.getImage());
        NotInTheStoreFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        NotInTheStoreFrame.getContentPane().setBackground(Color.black);//setting background color
        NotInTheStoreFrame.setSize(600,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        NotInTheStoreFrame.setLocation(dim.width/2-NotInTheStoreFrame.getSize().width/2, dim.height/2-NotInTheStoreFrame.getSize().height/2);

        NotInTheStoreFrame.setResizable(false);
        NotInTheStoreFrame.setVisible(true);
    }

    public void ShowSomeThingWentWrong(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        SomethingWentWrongForUserSearch = new JFrame("Something Went Wrong !");
        SomethingWentWrongForUserSearch.setContentPane(new SomethingWentWrong().getTheMainScreen());
        SomethingWentWrongForUserSearch.setIconImage(img.getImage());
        SomethingWentWrongForUserSearch.setLocationRelativeTo(null);//Setting location to the center of screen
        SomethingWentWrongForUserSearch.getContentPane().setBackground(Color.black);//setting background color
        SomethingWentWrongForUserSearch.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        SomethingWentWrongForUserSearch.setLocation(dim.width/2-SomethingWentWrongForUserSearch.getSize().width/2, dim.height/2-SomethingWentWrongForUserSearch.getSize().height/2);

        SomethingWentWrongForUserSearch.setResizable(false);
        SomethingWentWrongForUserSearch.setVisible(true);
    }

    public void AutoSearchByBarCode(){

        Runnable doHighlight = () -> {
            // your highlight code
            try{
                OpenConnection();
                Statement SearchStore = connection.createStatement();
                String StoreItems = "select * from store where bar_code='"+textToSearchInTheStoreTableByName.getText().trim()+"'";
                ResultSet resultSet = SearchStore.executeQuery(StoreItems);

                if(resultSet.next()){
                    barCodeForUserSearch.setText(resultSet.getString("bar_code"));
                    itemNameForUserSearch.setText(resultSet.getString("item_name"));
                    priceForUserSearch.setText(resultSet.getString("price_for_one"));
                    discountForTheSearchTab.setText(resultSet.getString("discount"));
                    itemsLeftForUserSearch.setText(resultSet.getString("number_of_items"));
                    textToSearchInTheStoreTableByName.setText("");
                }

                CloseConnection();


            }catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }
        };

        SwingUtilities.invokeLater(doHighlight);

    }


    // ToDo Some methods to use in the main Frame



    public static void ShowNewUserCreated(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        NewUserCreated = new JFrame("New User Created !");
        NewUserCreated.setContentPane(new NewUserIsCreated().getTheMainScreen());
        NewUserCreated.setIconImage(img.getImage());
        NewUserCreated.setLocationRelativeTo(null);//Setting location to the center of screen
        NewUserCreated.getContentPane().setBackground(Color.black);//setting background color
        NewUserCreated.setSize(400,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        NewUserCreated.setLocation(dim.width/2-NewUserCreated.getSize().width/2, dim.height/2-NewUserCreated.getSize().height/2);

        NewUserCreated.setResizable(false);
        NewUserCreated.setVisible(true);
    }

    public static void ShowUserUpdatedFrame(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        UserUpdated = new JFrame("User Updated !");
        UserUpdated.setContentPane(new UserIsUpdated().getTheMainScreen());
        UserUpdated.setIconImage(img.getImage());
        UserUpdated.setLocationRelativeTo(null);//Setting location to the center of screen
        UserUpdated.getContentPane().setBackground(Color.black);//setting background color
        UserUpdated.setSize(400,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        UserUpdated.setLocation(dim.width/2-UserUpdated.getSize().width/2, dim.height/2-UserUpdated.getSize().height/2);

        UserUpdated.setResizable(false);
        UserUpdated.setVisible(true);
    }

    public static void ShowUserIsDeletedFrame(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        UserDeleted = new JFrame("User Deleted !");
        UserDeleted.setContentPane(new UserIsDeleted().getTheMainScreen());
        UserDeleted.setIconImage(img.getImage());
        UserDeleted.setLocationRelativeTo(null);//Setting location to the center of screen
        UserDeleted.getContentPane().setBackground(Color.black);//setting background color
        UserDeleted.setSize(400,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        UserDeleted.setLocation(dim.width/2-UserDeleted.getSize().width/2, dim.height/2-UserDeleted.getSize().height/2);

        UserDeleted.setResizable(false);
        UserDeleted.setVisible(true);
    }

    public static void ShowPermissionIsSuccess(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        TransactionComplete = new JFrame("Transaction Completed !");
        TransactionComplete.setContentPane(new CashActionIsDone().getTheMainScreen());
        TransactionComplete.setIconImage(img.getImage());
        TransactionComplete.setLocationRelativeTo(null);//Setting location to the center of screen
        TransactionComplete.getContentPane().setBackground(Color.black);//setting background color
        TransactionComplete.setSize(800,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        TransactionComplete.setLocation(dim.width/2-TransactionComplete.getSize().width/2, dim.height/2-TransactionComplete.getSize().height/2);

        TransactionComplete.setResizable(false);
        TransactionComplete.setVisible(true);
    }

    public static void ShowNoteIsWritten(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        NewNoteNotification = new JFrame("New Note Is Written !");
        NewNoteNotification.setContentPane(new NewNoteAdded().getTheMainScreen());
        NewNoteNotification.setIconImage(img.getImage());
        NewNoteNotification.setLocationRelativeTo(null);//Setting location to the center of screen
        NewNoteNotification.getContentPane().setBackground(Color.black);//setting background color
        NewNoteNotification.setSize(800,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        NewNoteNotification.setLocation(dim.width/2-NewNoteNotification.getSize().width/2, dim.height/2-NewNoteNotification.getSize().height/2);

        NewNoteNotification.setResizable(false);
        NewNoteNotification.setVisible(true);
    }

    public void ShowUsersHint(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowHintUsers = new JFrame("Users Hints !");
        ShowHintUsers.setContentPane(new UsersHints().getTheMainScreen());
        ShowHintUsers.setIconImage(img.getImage());
        ShowHintUsers.setLocationRelativeTo(null);//Setting location to the center of screen
        ShowHintUsers.getContentPane().setBackground(Color.black);//setting background color
        ShowHintUsers.setSize(800,600);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowHintUsers.setLocation(dim.width/2-ShowHintUsers.getSize().width/2, dim.height/2-ShowHintUsers.getSize().height/2);

        ShowHintUsers.setResizable(false);
        ShowHintUsers.setVisible(true);
    }

    public void ShowAllUsersInformation(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowAllUsersInfo = new JFrame("All Users Info !");
        ShowAllUsersInfo.setContentPane(new AllUsersHints().getTheMainScreen());
        ShowAllUsersInfo.setIconImage(img.getImage());

        ShowAllUsersInfo.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowAllUsersInfo.setLocation(dim.width/2-ShowAllUsersInfo.getSize().width/2, dim.height/2-ShowAllUsersInfo.getSize().height/2);


        ShowAllUsersInfo.setVisible(true);
    }

    public void CreateNewUser(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        addNewUser = new JFrame("Add New User !");
        addNewUser.setContentPane(new CreateUsers().getTheMainScreen());
        addNewUser.setIconImage(img.getImage());
        addNewUser.setLocationRelativeTo(null);//Setting location to the center of screen
        addNewUser.getContentPane().setBackground(Color.black);//setting background color
        addNewUser.setSize(600,500);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        addNewUser.setLocation(dim.width/2-addNewUser.getSize().width/2, dim.height/2-addNewUser.getSize().height/2);

        addNewUser.setResizable(false);
        addNewUser.setVisible(true);
    }

    public void UpDateUser(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        updateUsers = new JFrame("Update Users !");
        updateUsers.setContentPane(new UpDateUsers().getTheMainScreen());
        updateUsers.setIconImage(img.getImage());
        updateUsers.setLocationRelativeTo(null);//Setting location to the center of screen
        updateUsers.getContentPane().setBackground(Color.black);//setting background color
        updateUsers.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        updateUsers.setLocation(dim.width/2-updateUsers.getSize().width/2, dim.height/2-updateUsers.getSize().height/2);

        updateUsers.setResizable(false);
        updateUsers.setVisible(true);
    }

    public void DeleteUser(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        deleteUser = new JFrame("Delete User !");
        deleteUser.setContentPane(new DeleteUser().getTheMainScreen());
        deleteUser.setIconImage(img.getImage());
        deleteUser.setLocationRelativeTo(null);//Setting location to the center of screen
        deleteUser.getContentPane().setBackground(Color.black);//setting background color
        deleteUser.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        deleteUser.setLocation(dim.width/2-deleteUser.getSize().width/2, dim.height/2-deleteUser.getSize().height/2);

        deleteUser.setResizable(false);
        deleteUser.setVisible(true);
    }

    public void AddPublicNoteFun(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        addPublicNoteFrame = new JFrame("Add Public Note !");
        addPublicNoteFrame.setContentPane(new AddPublicNotes().getTheMainScreen());
        addPublicNoteFrame.setIconImage(img.getImage());
        addPublicNoteFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        addPublicNoteFrame.getContentPane().setBackground(Color.black);//setting background color
        addPublicNoteFrame.setSize(800,500);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        addPublicNoteFrame.setLocation(dim.width/2-addPublicNoteFrame.getSize().width/2, dim.height/2-addPublicNoteFrame.getSize().height/2);


        addPublicNoteFrame.setResizable(false);
        addPublicNoteFrame.setVisible(true);
    }

    public void AddPrivateNoteFun(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        addPrivateNoteFrame = new JFrame("Add Private Note !");
        addPrivateNoteFrame.setContentPane(new AddPrivateNotes().getTheMainScreen());
        addPrivateNoteFrame.setIconImage(img.getImage());
        addPrivateNoteFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        addPrivateNoteFrame.getContentPane().setBackground(Color.black);//setting background color
        addPrivateNoteFrame.setSize(800,500);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        addPrivateNoteFrame.setLocation(dim.width/2-addPrivateNoteFrame.getSize().width/2, dim.height/2-addPrivateNoteFrame.getSize().height/2);

        addPrivateNoteFrame.setResizable(false);
        addPrivateNoteFrame.setVisible(true);
    }

    public void ShowPublicNotesFun(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        showPublicNotes = new JFrame("Public Notes !");
        showPublicNotes.setContentPane(new PublicNotes().getTheMainScreen());
        showPublicNotes.setIconImage(img.getImage());
        showPublicNotes.setLocationRelativeTo(null);//Setting location to the center of screen
        showPublicNotes.getContentPane().setBackground(Color.black);//setting background color
        showPublicNotes.setSize(800,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        showPublicNotes.setLocation(dim.width/2-showPublicNotes.getSize().width/2, dim.height/2-showPublicNotes.getSize().height/2);


        showPublicNotes.setResizable(false);
        showPublicNotes.setVisible(true);
    }

    public void ShowPrivateNotesFun(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        showPrivateNotes = new JFrame("Private Notes !");
        showPrivateNotes.setContentPane(new PrivateNotes().getTheMainScreen());
        showPrivateNotes.setIconImage(img.getImage());
        showPrivateNotes.setLocationRelativeTo(null);//Setting location to the center of screen
        showPrivateNotes.getContentPane().setBackground(Color.black);//setting background color
        showPrivateNotes.setSize(800,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        showPrivateNotes.setLocation(dim.width/2-showPrivateNotes.getSize().width/2, dim.height/2-showPrivateNotes.getSize().height/2);

        showPrivateNotes.setResizable(false);
        showPrivateNotes.setVisible(true);
    }

    public void WithDrawFrame(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        WithDrawFrameWindow = new JFrame("WithDraw Permission !");
        WithDrawFrameWindow.setContentPane(new WithDraw().getTheMainScreen());
        WithDrawFrameWindow.setIconImage(img.getImage());
        WithDrawFrameWindow.setLocationRelativeTo(null);//Setting location to the center of screen
        WithDrawFrameWindow.getContentPane().setBackground(Color.black);//setting background color
        WithDrawFrameWindow.setSize(600,400);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        WithDrawFrameWindow.setLocation(dim.width/2-WithDrawFrameWindow.getSize().width/2, dim.height/2-WithDrawFrameWindow.getSize().height/2);

        WithDrawFrameWindow.setResizable(false);
        WithDrawFrameWindow.setVisible(true);
    }

    public void DepositFrame(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        DepositFrameWindow = new JFrame("Deposit Permission !");
        DepositFrameWindow.setContentPane(new Deposit().getTheMainScreen());
        DepositFrameWindow.setIconImage(img.getImage());
        DepositFrameWindow.setLocationRelativeTo(null);//Setting location to the center of screen
        DepositFrameWindow.getContentPane().setBackground(Color.black);//setting background color
        DepositFrameWindow.setSize(600,400);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        DepositFrameWindow.setLocation(dim.width/2-DepositFrameWindow.getSize().width/2, dim.height/2-DepositFrameWindow.getSize().height/2);


        DepositFrameWindow.setResizable(false);
        DepositFrameWindow.setVisible(true);
    }

    public static void DebtFrame(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        FirstDeptFrame = new JFrame("Debt !");
        FirstDeptFrame.setContentPane(new Dept().getTheMainScreen());
        FirstDeptFrame.setIconImage(img.getImage());
        FirstDeptFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        FirstDeptFrame.getContentPane().setBackground(Color.black);//setting background color
        FirstDeptFrame.setSize(800,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        FirstDeptFrame.setLocation(dim.width/2-FirstDeptFrame.getSize().width/2, dim.height/2-FirstDeptFrame.getSize().height/2);

        FirstDeptFrame.setResizable(false);
        FirstDeptFrame.setVisible(true);
    }

    public void DebtReportFunction(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        DeptReport = new JFrame("All Debts Info !");
        DeptReport.setContentPane(new DeptReportFrame().getTheMainScreen());
        DeptReport.setIconImage(img.getImage());
        DeptReport.setLocationRelativeTo(null);//Setting location to the center of screen
        DeptReport.getContentPane().setBackground(Color.black);//setting background color
        DeptReport.setSize(1000,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        DeptReport.setLocation(dim.width/2-DeptReport.getSize().width/2, dim.height/2-DeptReport.getSize().height/2);


        DeptReport.setResizable(false);
        DeptReport.setVisible(true);
    }

    public void ShowMainUserCheck(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        mainUserCheck = new JFrame("Check User !");
        mainUserCheck.setContentPane(new MainUserCheckFrame().getTheMainScreen());
        mainUserCheck.setIconImage(img.getImage());
        mainUserCheck.setLocationRelativeTo(null);//Setting location to the center of screen
        mainUserCheck.getContentPane().setBackground(Color.black);//setting background color
        mainUserCheck.setSize(1000,600);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainUserCheck.setLocation(dim.width/2-mainUserCheck.getSize().width/2, dim.height/2-mainUserCheck.getSize().height/2);


        mainUserCheck.setResizable(false);
        mainUserCheck.setVisible(true);
    }

    public void ShowMainItemCheck(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        mainItemCheck = new JFrame("Check Item !");
        mainItemCheck.setContentPane(new MainItemCheckFrame().getTheMainScreen());
        mainItemCheck.setIconImage(img.getImage());
        mainItemCheck.setLocationRelativeTo(null);//Setting location to the center of screen
        mainItemCheck.getContentPane().setBackground(Color.black);//setting background color
        mainItemCheck.setSize(1000,600);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainItemCheck.setLocation(dim.width/2-mainItemCheck.getSize().width/2, dim.height/2-mainItemCheck.getSize().height/2);


        mainItemCheck.setResizable(false);
        mainItemCheck.setVisible(true);
    }

    public static void MerchantDebtFrame(){

        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        MerchantDebtScreen = new JFrame("Merchants Debt !");
        MerchantDebtScreen.setContentPane(new MerchantDebtGUI().getTheMainScreen());
        MerchantDebtScreen.setIconImage(img.getImage());
        MerchantDebtScreen.setLocationRelativeTo(null);//Setting location to the center of screen
        MerchantDebtScreen.getContentPane().setBackground(Color.black);//setting background color
        MerchantDebtScreen.setSize(800,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        MerchantDebtScreen.setLocation(dim.width/2-MerchantDebtScreen.getSize().width/2, dim.height/2-MerchantDebtScreen.getSize().height/2);

        MerchantDebtScreen.setResizable(false);
        MerchantDebtScreen.setVisible(true);

    }

    public void MerchantDebtReportFunction(){

        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        MerchantDebtInfoScreen = new JFrame("All Merchants Debts Info !");
        MerchantDebtInfoScreen.setContentPane(new MerchantDebtInfoGUI().getTheMainScreen());
        MerchantDebtInfoScreen.setIconImage(img.getImage());
        MerchantDebtInfoScreen.setLocationRelativeTo(null);//Setting location to the center of screen
        MerchantDebtInfoScreen.getContentPane().setBackground(Color.black);//setting background color
        MerchantDebtInfoScreen.setSize(1000,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        MerchantDebtInfoScreen.setLocation(dim.width/2-MerchantDebtInfoScreen.getSize().width/2, dim.height/2-MerchantDebtInfoScreen.getSize().height/2);


        MerchantDebtInfoScreen.setResizable(false);
        MerchantDebtInfoScreen.setVisible(true);

    }







    // ToDO some methods for LogOut and Exit in the Tool bar

    public void ShowLogOutAlert(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        LogOutOfTheSystemQuestion = new JFrame("Log Out !");
        LogOutOfTheSystemQuestion.setContentPane(new AreYouSureAboutLogOutTheSystem().getTheMainScreen());
        LogOutOfTheSystemQuestion.setIconImage(img.getImage());
        LogOutOfTheSystemQuestion.setLocationRelativeTo(null);//Setting location to the center of screen
        LogOutOfTheSystemQuestion.getContentPane().setBackground(Color.black);//setting background color
        LogOutOfTheSystemQuestion.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        LogOutOfTheSystemQuestion.setLocation(dim.width/2-LogOutOfTheSystemQuestion.getSize().width/2, dim.height/2-LogOutOfTheSystemQuestion.getSize().height/2);

        LogOutOfTheSystemQuestion.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setEnabled(true);
            }
        });

        LogOutOfTheSystemQuestion.setResizable(false);
        LogOutOfTheSystemQuestion.setVisible(true);
    }

    public void ShowExistAlert(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ExistOfTheSystemQuestion = new JFrame("Exit !");
        ExistOfTheSystemQuestion.setContentPane(new AreYouSureAboutExitTheSystemQuestionX().getTheMainScreen());
        ExistOfTheSystemQuestion.setIconImage(img.getImage());
        ExistOfTheSystemQuestion.setLocationRelativeTo(null);//Setting location to the center of screen
        ExistOfTheSystemQuestion.getContentPane().setBackground(Color.black);//setting background color
        ExistOfTheSystemQuestion.setSize(500,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ExistOfTheSystemQuestion.setLocation(dim.width/2-ExistOfTheSystemQuestion.getSize().width/2, dim.height/2-ExistOfTheSystemQuestion.getSize().height/2);

        ExistOfTheSystemQuestion.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setEnabled(true);
            }
        });

        ExistOfTheSystemQuestion.setResizable(false);
        ExistOfTheSystemQuestion.setVisible(true);
    }



    // ToDo some Methods for Statistics Card

    public void ShowTheRateReport(){

        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        RateIsDoneNow = new JFrame("Rate Is Done !");
        RateIsDoneNow.setContentPane(new RateIsDoneFrame().getTheMainScreen());
        RateIsDoneNow.setIconImage(img.getImage());
        RateIsDoneNow.setLocationRelativeTo(null);//Setting location to the center of screen
        RateIsDoneNow.getContentPane().setBackground(Color.black);//setting background color
        RateIsDoneNow.setSize(800,250);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        RateIsDoneNow.setLocation(dim.width/2-RateIsDoneNow.getSize().width/2, dim.height/2-RateIsDoneNow.getSize().height/2);

        RateIsDoneNow.setResizable(false);
        RateIsDoneNow.setVisible(true);

    }

    public void RateTheUsers(){
        double counter  ;
        double totalEarned  ;
        double calculatedRate ;

        try{
            OpenConnection();
            Statement getAllUsers = connection.createStatement();
            String AllUsers = "SELECT user_name FROM users ";
            ResultSet resultSet = getAllUsers.executeQuery(AllUsers);

            while (resultSet.next()){
                counter = 0 ;
                totalEarned= 0 ;
                calculatedRate = 0 ;

                Statement getTotalEarned = connection.createStatement();
                String getTheDataAboutTheUser = "SElECT total_earned_of_the_shift FROM shifts WHERE user_name = '"+resultSet.getString("user_name")+"'";
                ResultSet resultSetWithUserData = getTotalEarned.executeQuery(getTheDataAboutTheUser);

                while (resultSetWithUserData.next()){

                    totalEarned = totalEarned + Double.parseDouble(resultSetWithUserData.getString("total_earned_of_the_shift"));
                    counter++;

                }

                try{
                    calculatedRate = totalEarned / counter ;
                }catch (Exception eee){
                    eee.printStackTrace();
                }

                Statement setTheRate = connection.createStatement();
                String UpdateRate = "UPDATE users SET rate = "+calculatedRate+" WHERE user_name = '"+resultSet.getString("user_name")+"'";
                setTheRate.executeUpdate(UpdateRate);
                System.out.println("OMG Now I AM DONE");
            }


            try{
                RateIsDoneNow.dispose();
            }catch (Exception e){
                e.printStackTrace();
            }
            ShowTheRateReport();

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            try{
                RateIsDoneNow.dispose();
            }catch (Exception e){
                e.printStackTrace();
            }
            ShowTheRateReport();
            CloseConnection();
        }
    }

    public void ShowRatingUsers(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        UsersRate = new JFrame("Users Rate !");
        UsersRate.setContentPane(new ShowUserRate().getTheMainScreen());
        UsersRate.setIconImage(img.getImage());
        UsersRate.setLocationRelativeTo(null);//Setting location to the center of screen
        UsersRate.getContentPane().setBackground(Color.black);//setting background color
        UsersRate.setSize(800,500);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        UsersRate.setLocation(dim.width/2-UsersRate.getSize().width/2, dim.height/2-UsersRate.getSize().height/2);

        UsersRate.setResizable(false);
        UsersRate.setVisible(true);
    }

    public void ShowAllShifts(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowShiftsFrame = new JFrame("Shifts !");
        ShowShiftsFrame.setContentPane(new ShowShifts().getTheMainScreen());
        ShowShiftsFrame.setIconImage(img.getImage());
        ShowShiftsFrame.getContentPane().setBackground(Color.black);//setting background color
        ShowShiftsFrame.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowShiftsFrame.setLocation(dim.width/2-ShowShiftsFrame.getSize().width/2, dim.height/2-ShowShiftsFrame.getSize().height/2);

        ShowShiftsFrame.setVisible(true);
    }

    public void StoreItemsByRate(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowTheStoreItemsByRate = new JFrame("Store Items by Rate !");
        ShowTheStoreItemsByRate.setContentPane(new ShowItemsByRate().getTheMainScreen());
        ShowTheStoreItemsByRate.setIconImage(img.getImage());
        ShowTheStoreItemsByRate.getContentPane().setBackground(Color.black);//setting background color

        ShowTheStoreItemsByRate.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowTheStoreItemsByRate.setLocation(dim.width/2-ShowTheStoreItemsByRate.getSize().width/2, dim.height/2-ShowTheStoreItemsByRate.getSize().height/2);


        ShowTheStoreItemsByRate.setVisible(true);
    }

    public void ShowLastShift(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowLastShift = new JFrame("Last Shift !");
        ShowLastShift.setContentPane(new LastShiftForUser().getTheMainScreen());
        ShowLastShift.setIconImage(img.getImage());
        ShowLastShift.getContentPane().setBackground(Color.black);//setting background color
        ShowLastShift.setSize(800,500);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowLastShift.setLocation(dim.width/2-ShowLastShift.getSize().width/2, dim.height/2-ShowLastShift.getSize().height/2);

        ShowLastShift.setResizable(false);
        ShowLastShift.setVisible(true);
    }

    public void ShowAllShiftsMethod(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowAllShiftsForOneUser = new JFrame("Shifts For  !");
        ShowAllShiftsForOneUser.setContentPane(new AllShiftsForAUser().getTheMainScreen());
        ShowAllShiftsForOneUser.setIconImage(img.getImage());
        ShowAllShiftsForOneUser.setLocationRelativeTo(null);//Setting location to the center of screen
        ShowAllShiftsForOneUser.getContentPane().setBackground(Color.black);//setting background color
        ShowAllShiftsForOneUser.setSize(1400,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowAllShiftsForOneUser.setLocation(dim.width/2-ShowAllShiftsForOneUser.getSize().width/2, dim.height/2-ShowAllShiftsForOneUser.getSize().height/2);

        ShowAllShiftsForOneUser.setResizable(false);
        ShowAllShiftsForOneUser.setVisible(true);
    }

    public void ShowAllPermissionsMethod(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowThePermissions = new JFrame("All Permissions !");
        ShowThePermissions.setContentPane(new Permissions().getTheMainScreen());
        ShowThePermissions.setIconImage(img.getImage());
        ShowThePermissions.setLocationRelativeTo(null);//Setting location to the center of screen
        ShowThePermissions.getContentPane().setBackground(Color.black);//setting background color
        ShowThePermissions.setSize(1400,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowThePermissions.setLocation(dim.width/2-ShowThePermissions.getSize().width/2, dim.height/2-ShowThePermissions.getSize().height/2);

        ShowThePermissions.setResizable(false);
        ShowThePermissions.setVisible(true);
    }

    public void ShowTheStore(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowTheStore = new JFrame("The Store !");
        ShowTheStore.setContentPane(new TheStore().getTheMainScreen());
        ShowTheStore.setIconImage(img.getImage());
        ShowTheStore.getContentPane().setBackground(Color.black);//setting background color
        ShowTheStore.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowTheStore.setLocation(dim.width/2-ShowTheStore.getSize().width/2, dim.height/2-ShowTheStore.getSize().height/2);


        ShowTheStore.setVisible(true);
    }

    public void ShowTheLogsMethod(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowTheLogs = new JFrame("The Logs !");
        ShowTheLogs.setContentPane(new ShowLogs().getTheMainScreen());
        ShowTheLogs.setIconImage(img.getImage());
        ShowTheLogs.getContentPane().setBackground(Color.black);//setting background color

        ShowTheLogs.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowTheLogs.setLocation(dim.width/2-ShowTheLogs.getSize().width/2, dim.height/2-ShowTheLogs.getSize().height/2);


        ShowTheLogs.setVisible(true);
    }

    public void ShowTheLogsForOneUserMethod(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowTheLogsForOne = new JFrame("The Logs !");
        ShowTheLogsForOne.setContentPane(new ShowLogsForOneUserFrame().getTheMainScreen());
        ShowTheLogsForOne.setIconImage(img.getImage());
        ShowTheLogsForOne.getContentPane().setBackground(Color.black);//setting background color

        ShowTheLogsForOne.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowTheLogsForOne.setLocation(dim.width/2-ShowTheLogsForOne.getSize().width/2, dim.height/2-ShowTheLogsForOne.getSize().height/2);


        ShowTheLogsForOne.setVisible(true);
    }

    public void GoToTheLogsReport(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowTheLastLogsForAUser = new JFrame("The Logs !");
        ShowTheLastLogsForAUser.setContentPane(new ShiftForAUser().getTheMainScreen());
        ShowTheLastLogsForAUser.setIconImage(img.getImage());
        ShowTheLastLogsForAUser.getContentPane().setBackground(Color.black);//setting background color

        ShowTheLastLogsForAUser.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowTheLastLogsForAUser.setLocation(dim.width/2-ShowTheLastLogsForAUser.getSize().width/2, dim.height/2-ShowTheLastLogsForAUser.getSize().height/2);


        ShowTheLastLogsForAUser.setVisible(true);
    }

    public void ShowTheStats(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ShowAllStats = new JFrame("The Statistics  !");
        ShowAllStats.setContentPane(new Stats().getTheMainScreen());
        ShowAllStats.setIconImage(img.getImage());
        ShowAllStats.setLocationRelativeTo(null);//Setting location to the center of screen
        ShowAllStats.getContentPane().setBackground(Color.black);//setting background color
        ShowAllStats.setSize(1000,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ShowAllStats.setLocation(dim.width/2-ShowAllStats.getSize().width/2, dim.height/2-ShowAllStats.getSize().height/2);

        ShowAllStats.setResizable(false);
        ShowAllStats.setVisible(true);
    }

    public void ShowSearchBills(){

        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);

        BillsSearchFrame = new JFrame("Bill Activity !");
        BillsSearchFrame.setContentPane(new BillsActivities().getTheMainScreen());
        BillsSearchFrame.setIconImage(img.getImage());
        BillsSearchFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        BillsSearchFrame.getContentPane().setBackground(Color.black);//setting background color

        BillsSearchFrame.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        BillsSearchFrame.setLocation(dim.width/2-BillsSearchFrame.getSize().width/2, dim.height/2-BillsSearchFrame.getSize().height/2);

        BillsSearchFrame.setVisible(true);
    }






    // ToDo methods for developer toolbar

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }



    // ToDo (((((((the constructor))))))) *************$$$$$$$$$$$$$$$$$$***************



    public TheMainFrameHub() {



        //theSwitchableScreen = new JPanel(new CardLayout());
        theSwitchableScreen.add(mainSwitchScreen, "MainCard");
        theSwitchableScreen.add(storeSwitchScreen, "StoreCard");
        theSwitchableScreen.add(sellSwitchScreen, "SellCard");
        theSwitchableScreen.add(searchScreen, "SearchCard");
        theSwitchableScreen.add(statisticsSwitchScreen, "StatCard");
        theSwitchableScreen.add(developerSwitchScreen, "DeveloperCard");
        theSwitchableScreen.add(SittingsScreen, "SittingsScreenCard");


        // set the welcome screen
        URL imageUrl = ClassLoader.getSystemResource("AELogoForApp.jpg");
        ImageIcon icon = new ImageIcon(imageUrl);
        welcomeImage.setIcon(icon);

        // set the timer

        ActionListener updateClockAction = e -> {
            // Assumes clock is a JLabel
            timeNow.setText(new Date().toString());
        };

        Timer t = new Timer(999, updateClockAction);
        t.start();

        try{

            OpenConnection();
            Statement getTheCashierName = connection.createStatement();
            String CashierName = "SELECT full_name FROM users WHERE user_name = '"+userName+"'";
            ResultSet CashierR = getTheCashierName.executeQuery(CashierName);

            if(CashierR.next()){

                cashierName.setText(CashierR.getString("full_name"));

            }

            CloseConnection();

        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();

        }






        // start the welcome screen
        CardLayout cl = (CardLayout) (theSwitchableScreen.getLayout());
        cl.show(theSwitchableScreen, String.valueOf(welcomeScreen));


        // set the red close action

        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // check the action of the mouse clicked
                //System.out.println("Clicked!");
                mainFrame.setEnabled(false);
                ShowExistAlert();
            }
        });



        // ToDO >>>>>> Update The Bill Path

        UpdateTheBillPath();

        // ToDo >>>>> add the tables

        CreateSellsTable();
        CreateStoreTable();

        // some basics
        totalBillText.setEnabled(false);
        totalBillText.setEditable(false);
        nameOfItemInTheStoreSection.setEnabled(false);
        quantityInTheStoreSection.setEnabled(false);

        // ToDo make the table listen to the changes in it's data *****$$$$$$$*******

        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                //System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                //System.out.println("The user stopped editing successfully.");
                UpdateRowInSellsTable();
            }
        };
        sellsTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);









        // toolbar action for items

        mainToolBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // check the action of the mouse clicked
                //System.out.println("Clicked!");

                // manage the color of clicked item
                mainToolBar.setForeground(Color.RED);
                storeToolBar.setForeground(Color.yellow);
                sellToolBar.setForeground(Color.yellow);
                searchToolBar.setForeground(Color.yellow);
                statisticsTollBar.setForeground(Color.yellow);
                developerToolBar.setForeground(Color.yellow);
                SittingsToolBar.setForeground(Color.yellow);

                // manage the view of theSwitchableScreen .

                try {
                    CardLayout cl = (CardLayout) (theSwitchableScreen.getLayout());

                    cl.show(theSwitchableScreen, "MainCard");
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });


        storeToolBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // check the action of the mouse clicked

                if(Admin == 1 || Admin == 3){

                    //System.out.println("Clicked!");

                    RefreshStoreTable();

                    // manage the color of clicked item
                    mainToolBar.setForeground(Color.yellow);
                    storeToolBar.setForeground(Color.red);
                    sellToolBar.setForeground(Color.yellow);
                    searchToolBar.setForeground(Color.yellow);
                    statisticsTollBar.setForeground(Color.yellow);
                    developerToolBar.setForeground(Color.yellow);
                    SittingsToolBar.setForeground(Color.yellow);


                    // manage the view of theSwitchableScreen .

                    CardLayout cl = (CardLayout) (theSwitchableScreen.getLayout());

                    cl.show(theSwitchableScreen, "StoreCard");

                    barCodeText.requestFocusInWindow();
                }

            }
        });


        sellToolBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // check the action of the mouse clicked
                //System.out.println("Clicked!");

                // manage the color of clicked item
                mainToolBar.setForeground(Color.yellow);
                storeToolBar.setForeground(Color.yellow);
                sellToolBar.setForeground(Color.red);
                searchToolBar.setForeground(Color.yellow);
                statisticsTollBar.setForeground(Color.yellow);
                developerToolBar.setForeground(Color.yellow);
                SittingsToolBar.setForeground(Color.yellow);




                // manage the view of theSwitchableScreen .

                CardLayout cl = (CardLayout) (theSwitchableScreen.getLayout());

                cl.show(theSwitchableScreen, "SellCard");

                //mainFrame.getRootPane().setDefaultButton(finishTheOperationInSellsTable);

                barCodeTextForTable.requestFocusInWindow();

            }
        });


        searchToolBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                mainToolBar.setForeground(Color.yellow);
                storeToolBar.setForeground(Color.yellow);
                sellToolBar.setForeground(Color.yellow);
                searchToolBar.setForeground(Color.red);
                statisticsTollBar.setForeground(Color.yellow);
                developerToolBar.setForeground(Color.yellow);
                SittingsToolBar.setForeground(Color.yellow);


                // fill the table from the store

                CreateSearchTable();


                // manage the view of theSwitchableScreen .

                CardLayout cl = (CardLayout) (theSwitchableScreen.getLayout());

                cl.show(theSwitchableScreen, "SearchCard");

                textToSearchInTheStoreTableByName.requestFocusInWindow();




            }
        });


        statisticsTollBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // check the action of the mouse clicked
                //System.out.println("Clicked!");

                if(Admin == 1 || Admin == 3) {
                    // manage the color of clicked item
                    mainToolBar.setForeground(Color.yellow);
                    storeToolBar.setForeground(Color.yellow);
                    sellToolBar.setForeground(Color.yellow);
                    searchToolBar.setForeground(Color.yellow);
                    statisticsTollBar.setForeground(Color.red);
                    developerToolBar.setForeground(Color.yellow);
                    SittingsToolBar.setForeground(Color.yellow);



                    // manage the view of theSwitchableScreen .
                    CardLayout cl = (CardLayout) (theSwitchableScreen.getLayout());

                    cl.show(theSwitchableScreen, "StatCard");
                }

            }
        });

        SittingsToolBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(Admin == 3) {

                    mainToolBar.setForeground(Color.yellow);
                    storeToolBar.setForeground(Color.yellow);
                    sellToolBar.setForeground(Color.yellow);
                    searchToolBar.setForeground(Color.yellow);
                    statisticsTollBar.setForeground(Color.yellow);
                    developerToolBar.setForeground(Color.yellow);
                    SittingsToolBar.setForeground(Color.red);

                    CardLayout cl = (CardLayout) (theSwitchableScreen.getLayout());

                    cl.show(theSwitchableScreen, "SittingsScreenCard");

                }

            }
        });


        developerToolBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // check the action of the mouse clicked
                //System.out.println("Clicked!");

                // manage the color of clicked item
                mainToolBar.setForeground(Color.yellow);
                storeToolBar.setForeground(Color.yellow);
                sellToolBar.setForeground(Color.yellow);
                searchToolBar.setForeground(Color.yellow);
                statisticsTollBar.setForeground(Color.yellow);
                developerToolBar.setForeground(Color.red);
                SittingsToolBar.setForeground(Color.yellow);


                // manage the view of theSwitchableScreen .

                CardLayout cl = (CardLayout) (theSwitchableScreen.getLayout());

                cl.show(theSwitchableScreen, "DeveloperCard");


            }
        });

        logOutToolBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // check the action of the mouse clicked
                //System.out.println("Clicked!");
                mainFrame.setEnabled(false);
                ShowLogOutAlert();

            }
        });

        exitToolBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // check the action of the mouse clicked
                //System.out.println("Clicked!");
                mainFrame.setEnabled(false);
                ShowExistAlert();

            }
        });









        // ToDO Cards Action Buttons ***********************************************************************************



        // ToDo store card buttons <<<<<<<Start>>>>>>>>>


        addOrUpDateButton.addActionListener(e -> UpdateTheStore());

        checkButton.addActionListener(e -> {

            try {
                AddBulkFrame.dispose();
            }
            catch (Exception ee){
                ee.printStackTrace();
            }

            OpenAddBulkScreen();

        });

        clearButton.addActionListener(e -> {
            ClearTheForm();
            RefreshStoreTable();
            barCodeText.requestFocusInWindow();
        });

        deleteButton.addActionListener(e -> {

            if(!barCodeText.getText().trim().isEmpty()) {
                barCodeToDelete = barCodeText.getText().trim();
                ImageIcon img = new ImageIcon("C:\\Users\\ahmad\\IdeaProjects\\MiniMarket\\yellowSign!.png");
                areYouSure = new JFrame("Are You Sure ?");
                areYouSure.setIconImage(img.getImage());
                areYouSure.setResizable(false);
                areYouSure.setSize(800, 250);

                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                areYouSure.setLocation(dim.width/2-areYouSure.getSize().width/2, dim.height/2-areYouSure.getSize().height/2);


                areYouSure.setContentPane(new AreYouSureAboutDelete().getTheMainScreen());
                areYouSure.setLocationRelativeTo(null);//Setting location to the center of screen
                areYouSure.setVisible(true);
                ClearTheForm();
            }

        });

        // ToDo >>>> search about items on store While typing <<<< beta Version >>>> check it later .
        barCodeText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SearchInStoreTable();
            }


            @Override
            public void removeUpdate(DocumentEvent e) {
                SearchInStoreTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SearchInStoreTable();
            }
        });

        numberOfItemsText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
             CalculateTheOriginalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                CalculateTheOriginalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                CalculateTheOriginalPrice();
            }
        });

        numbersPerBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                CalculateTheOriginalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                CalculateTheOriginalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                CalculateTheOriginalPrice();
            }
        });

        priceOfBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                CalculateTheOriginalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                CalculateTheOriginalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                CalculateTheOriginalPrice();
            }
        });






        // ToDo >>> makes some text fields accepts only numbers

        originalPriceText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        publicPriceText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        numberOfItemsText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        numbersPerBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        priceOfBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        discountText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });






        // Finish store card ToDo , the end _________________________





        // ToDo search card buttons <<<<<<<Start>>>>>>>>>



        searchByBarCodeButton.addActionListener(e -> {
            try{
                if(!textToSearchInTheStoreTableByName.getText().trim().isEmpty()) {
                    OpenConnection();
                    Statement SearchForTheUser = connection.createStatement();
                    String SearchForTheUserText = "SELECT * FROM store WHERE bar_code ='" + textToSearchInTheStoreTableByName.getText().trim() + "'";
                    ResultSet resultSet = SearchForTheUser.executeQuery(SearchForTheUserText);

                    if (resultSet.next()) {
                        barCodeForUserSearch.setText(resultSet.getString("bar_code"));
                        itemNameForUserSearch.setText(resultSet.getString("item_name"));
                        priceForUserSearch.setText(resultSet.getString("price_for_one"));
                        discountForTheSearchTab.setText(resultSet.getString("discount"));
                        itemsLeftForUserSearch.setText(resultSet.getString("number_of_items"));
                        textToSearchInTheStoreTableByName.setText("");
                    } else {
                        // ToDo Tell the user that this item isn't in the store right now

                        ShowNotInTheStore();
                    }

                    CloseConnection();
                }

            }catch (Exception ee){
                ee.printStackTrace();
                // ToDo Tell the user that Something Is Wrong

                CloseConnection();
                ShowSomeThingWentWrong();
            }
        });


        searchByNameButton.addActionListener(e -> {
            try{
                if(!textToSearchInTheStoreTableByName.getText().trim().isEmpty()) {
                    OpenConnection();
                    Statement SearchForTheUser = connection.createStatement();
                    String SearchForTheUserText = "SELECT * FROM store WHERE item_name ='" + textToSearchInTheStoreTableByName.getText().trim() + "'";
                    ResultSet resultSet = SearchForTheUser.executeQuery(SearchForTheUserText);

                    if (resultSet.next()) {
                        barCodeForUserSearch.setText(resultSet.getString("bar_code"));
                        itemNameForUserSearch.setText(resultSet.getString("item_name"));
                        priceForUserSearch.setText(resultSet.getString("price_for_one"));
                        discountForTheSearchTab.setText(resultSet.getString("discount"));
                        itemsLeftForUserSearch.setText(resultSet.getString("number_of_items"));
                        textToSearchInTheStoreTableByName.setText("");
                    } else {
                        // ToDo Tell the user that this item isn't in the store right now

                        ShowNotInTheStore();

                    }

                }

                CloseConnection();

            }catch (Exception ee){
                ee.printStackTrace();
                // ToDo Tell the user that Something Is Wrong

                CloseConnection();
                ShowSomeThingWentWrong();

            }
        });


        textToSearchInTheStoreTableByName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                AutoSearchByBarCode();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                AutoSearchByBarCode();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                AutoSearchByBarCode();
            }
        });



        // Finish search card ToDo , the end _________________________





        // ToDo start the main screen



        // give the admin power or take it away

        if(Admin==0){
            allInfoButton.setEnabled(false);
            createButton.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton1.setEnabled(false);
            addPrivateNote.setEnabled(false);
            showPrivateNote.setEnabled(false);
            withdrawButton.setEnabled(false);
            depositButton.setEnabled(false);
            statisticsTollBar.setEnabled(false);
            storeToolBar.setEnabled(false);
            deptButton.setEnabled(false);
            showReportButton.setEnabled(false);
            userCheckButton.setEnabled(false);
            MainSearchText.setEnabled(false);
            itemCheckButton.setEnabled(false);
            merchantDebtInfoButton.setEnabled(false);
            merchantsDebtsButton.setEnabled(false);
        }

        SittingsToolBar.setEnabled(false);

        if(Admin == 3){

            SittingsToolBar.setEnabled(true);

        }


        hintInfoButton.addActionListener(e -> {
            try{
                ShowHintUsers.dispose();
            }catch (Exception eee){
                eee.printStackTrace();
            }
            ShowUsersHint();
        });


        allInfoButton.addActionListener(e -> {
            try{
                ShowAllUsersInfo.dispose();
            }catch (Exception eee){
                eee.printStackTrace();
            }
            ShowAllUsersInformation();
        });


        createButton.addActionListener(e -> {
            try{
                addNewUser.dispose();
            }catch (Exception eee){
                eee.printStackTrace();
            }
            CreateNewUser();
        });


        updateButton.addActionListener(e -> {
            try{
                updateUsers.dispose();
            }catch (Exception eee){
                eee.printStackTrace();
            }
            UpDateUser();
        });


        deleteButton1.addActionListener(e -> {
            try{
                deleteUser.dispose();
            }catch (Exception eee){
                eee.printStackTrace();
            }
            DeleteUser();
        });


        addPublicNote.addActionListener(e -> {
            try{
                addPublicNoteFrame.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            AddPublicNoteFun();
        });


        addPrivateNote.addActionListener(e -> {
            try{
                addPrivateNoteFrame.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            AddPrivateNoteFun();
        });


        showPublicNote.addActionListener(e -> {
            try{
                showPublicNotes.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            ShowPublicNotesFun();
        });


        showPrivateNote.addActionListener(e -> {
            try{
                showPrivateNotes.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            ShowPrivateNotesFun();
        });


        withdrawButton.addActionListener(e -> {
            try{
                WithDrawFrameWindow.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            WithDrawFrame();
         });


        depositButton.addActionListener(e -> {
            try{
                DepositFrameWindow.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            DepositFrame();
        });


        deptButton.addActionListener(e -> {
            try{
                FirstDeptFrame.dispose();
                OpenDept.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            DebtFrame();
        });


        showReportButton.addActionListener(e -> {
            try{
                DeptReport.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            DebtReportFunction();
        });


        userCheckButton.addActionListener(e -> {


            if(!MainSearchText.getText().trim().isEmpty()){

                try{
                    mainUserCheck.dispose();
                }catch (Exception ee){
                    ee.printStackTrace();
                }

                GetMainSearchCompoText = MainSearchText.getText().trim();
                ShowMainUserCheck();

            }


        });


        itemCheckButton.addActionListener(e -> {


            if(!MainSearchText.getText().trim().isEmpty()){

                try{
                    mainItemCheck.dispose();
                }catch (Exception ee){
                    ee.printStackTrace();
                }

                GetMainSearchCompoText = MainSearchText.getText().trim();
                ShowMainItemCheck();

            }


        });

        merchantsDebtsButton.addActionListener(actionEvent -> {

            try{
                MerchantDebtScreen.dispose();
                InSideMerchantDebtScreen.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            MerchantDebtFrame();

        });

        merchantDebtInfoButton.addActionListener(actionEvent -> {

            try{
                MerchantDebtInfoScreen.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            MerchantDebtReportFunction();

        });



        //Finish main card ToDo , the end _________________________



        // ToDo sell card buttons <<<<<<<Start>>>>>>>>>




        deleteTheSellsTable.addActionListener(e -> {

            DefaultTableModel model = (DefaultTableModel) sellsTable.getModel();
            int i = sellsTable.getSelectedRow();
            if(i >= 0){
                model.removeRow(i);
            }
            MaintainTheTotalBill();
            barCodeTextForTable.requestFocusInWindow();
        });


        finishTheOperationInSellsTable.addActionListener(e -> {
            FinishTheBill();
            TotalBillToAddToDebt = 0 ;
            barCodeTextForTable.requestFocusInWindow();
        });


        clearTheSellsTable.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) sellsTable.getModel();
            model.setRowCount(0);
            MaintainTheTotalBill();
            totalBillText.setText("0.0");
            SavedTotal.setText("0.0");
            paidText.setText("0.0");
            backText.setText("0.0");
            barCodeTextForTable.requestFocusInWindow();
        });


        OKButtonToAddIntoTable.addActionListener(e -> {

            String barCodeForTable;
            String itemNameForTable;
            double priceForTheTable;
            double quantityForTheTable;
            double totalForTheTable;
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            // get the text field info
            try {
                barCodeForTable = barCodeTextForTable.getText().trim();
                itemNameForTable = itemNameTextForTable.getText();
                priceForTheTable = Double.parseDouble(priceTextForTable.getText().trim());
                quantityForTheTable = Double.parseDouble(quantityTextForTable.getText().trim());
                totalForTheTable = priceForTheTable * quantityForTheTable ;

            }
            catch (Exception ee){
                ee.printStackTrace();
                return;
            }
            Object[] row = { barCodeForTable, itemNameForTable, priceForTheTable, quantityForTheTable,"0","0",numberFormat.format(totalForTheTable) };
            DefaultTableModel model = (DefaultTableModel) sellsTable.getModel();
            model.addRow(row);
            MaintainTheTotalBill();
            barCodeTextForTable.setText("");
            itemNameTextForTable.setText("");
            priceTextForTable.setText("");
            quantityTextForTable.setText("");
            barCodeTextForTable.requestFocusInWindow();
        });


        clearTheSellForm.addActionListener(e -> {
            barCodeTextForTable.setText("");
            itemNameTextForTable.setText("");
            priceTextForTable.setText("");
            quantityTextForTable.setText("");
            barCodeTextForTable.requestFocusInWindow();
        });


        returnTheBill.addActionListener(e -> {
            ReturnTheBill();
            barCodeTextForTable.requestFocusInWindow();
        });


        barCodeTextForTable.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                RefreshSellsTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                RefreshSellsTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                RefreshSellsTable();
            }
        });



        paidText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                UpdateBack();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                UpdateBack();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                UpdateBack();
            }
        });


        finishWithDebt.addActionListener(e -> {

            FinishTheBill();

            if(TotalBillToAddToDebt > 0){

                ChooseTheWantedDebtUser();
            }

        });


        priceTextForTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        quantityTextForTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        paidText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        backText.setEnabled(false);
        SavedTotal.setEnabled(false);


        // ToDo start the Developer card Code


        faceBookShortCut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                openWebpage(URI.create("https://www.facebook.com/www.ellamey"));
            }
        });

        AESystemsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                openWebpage(URI.create("https://www.facebook.com/AE-Systems-109962004750537/?ref=page_internal"));
            }
        });


        // End of the developer Card Code


        // ToDO start of the statistics Card


        OKButtonForRating.addActionListener(e -> RateTheUsers());

        OKButtonForShowRating.addActionListener(e -> {

            try {
                UsersRate.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            ShowRatingUsers();
        });

        OKButtonForAllShifts.addActionListener(e -> {

            try {
                ShowShiftsFrame.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }

            ShowAllShifts();
        });

        okButtonItemsByRate.addActionListener(e -> {

            try {
                ShowTheStoreItemsByRate.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }

            StoreItemsByRate();
        });

        OKButtonForLastShift.addActionListener(e -> {

            if(!WantedUserText.getText().trim().isEmpty()) {

                try {
                    ShowLastShift.dispose();
                }catch (Exception ee){
                    ee.printStackTrace();
                }

                WantedUser = WantedUserText.getText().trim();
                ShowLastShift();
            }

        });

        okButtonForAllUsersShifts.addActionListener(e -> {

            if(!allShiftsForUserText.getText().trim().isEmpty()) {

                try {
                    ShowAllShiftsForOneUser.dispose();
                }catch (Exception ee){
                    ee.printStackTrace();
                }

                WantedUserForAllShifts = allShiftsForUserText.getText().trim();
                System.out.println(WantedUserForAllShifts);
                ShowAllShiftsMethod();
            }
        });

        OKButtonForPermission.addActionListener(e -> {
            try {
                ShowThePermissions.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            ShowAllPermissionsMethod();
        });

        OKButtonForShowingTheStoreItems.addActionListener(e -> {
            try {
                ShowTheStore.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            ShowTheStore();
        });

        okButtonForAllLogs.addActionListener(e -> {
            try {
                ShowTheLogs.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
            ShowTheLogsMethod();
        });

        OKButtonForShowingLogsForUser.addActionListener(e -> {

            if(!LogsForText.getText().trim().isEmpty()){

                try {
                    ShowTheLogsForOne.dispose();
                }catch (Exception ee){
                    ee.printStackTrace();
                }

                UserLogs = LogsForText.getText().trim();
                ShowTheLogsForOneUserMethod();
            }

        });

        okButtonLogsForTheLastDay.addActionListener(e -> {


            if(!userNameToGetTheLastLogs.getText().trim().isEmpty() && !shiftToGetTheLastLogs.getText().trim().isEmpty()){
                try {
                    ShowTheLastLogsForAUser.dispose();
                }catch (Exception ee){
                    ee.printStackTrace();
                }

                UserNameForLogs = userNameToGetTheLastLogs.getText().trim();
                ShiftNumberForLogs = Integer.parseInt(shiftToGetTheLastLogs.getText().trim());

                GoToTheLogsReport();
            }
        });

        OKButtonForCalculations.addActionListener(e -> {
            try {
                ShowAllStats.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }

            ShowTheStats();
        });

        searchBillsButton.addActionListener(e -> {

            if(!SearchBillsText.getText().trim().isEmpty()) {

                try {
                    BillsSearchFrame.dispose();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
                BillNumber = Integer.parseInt(SearchBillsText.getText().trim());
                ShowSearchBills();
            }

        });

        SearchBillsText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(!Character.isDigit(x)){
                    e.consume();
                }
            }
        });


        shiftToGetTheLastLogs.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });


        // ToDo Manage the Sittings Screen

        SaveBillPathButton.addActionListener(actionEvent -> {
            try {
                if(!BillPathText.getText().trim().isEmpty()) {
                    OpenConnection();

                    Statement SetUrlForBillPath = connection.createStatement();
                    //String urlPath = "INSERT INTO bill_path (path_url) VALUES ('" + BillPathText.getText().trim() + "')";
                    String urlPath = "UPDATE bill_path SET path_url = '"+BillPathText.getText().trim()+"'";
                    SetUrlForBillPath.executeUpdate(urlPath);

                    BillPathText.setText("");

                    UpdateTheBillPath();

                    CloseConnection();

                }

            }catch (Exception exception){
                exception.printStackTrace();
                CloseConnection();
            }

        });

        SavePrinterName.addActionListener(actionEvent -> {

            try {
                if(!PrinterNameText.getText().trim().isEmpty()) {
                    OpenConnection();

                    Statement SetPrinterName = connection.createStatement();
                    //String PrinterName = "INSERT INTO bill_path (printer_name) VALUES ('"+PrinterNameText.getText().trim()+"')";
                    String PrinterName = "UPDATE bill_path SET printer_name = '"+PrinterNameText.getText().trim()+"'";
                    SetPrinterName.executeUpdate(PrinterName);

                    PrinterNameText.setText("");

                    UpdateTheBillPath();

                    CloseConnection();

                }

            }catch (Exception exception){
                exception.printStackTrace();
                CloseConnection();
            }

        });



    }
}
