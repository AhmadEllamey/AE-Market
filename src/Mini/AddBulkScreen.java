package Mini;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;


public class AddBulkScreen {

    private JPanel theMainScreen;
    private JButton clearTheFormButton;
    private JButton addOrUpdateButton;
    private JButton removeButton;
    private JTextField barCodeText;
    private JTextField relatedBarCodeText;
    private JTextField itemNameText;
    private JTextField numberPerEachBoxText;
    private JTextField priceText;
    private JTextField discountText;
    private JTextField originalPriceForOneText;





    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void ClearTheForm(){

        barCodeText.setText("");
        relatedBarCodeText.setText("");
        itemNameText.setText("");
        numberPerEachBoxText.setText("");
        priceText.setText("");
        discountText.setText("");
        originalPriceForOneText.setText("");

    }

    public void AddFunction(){

        String bar_code ;
        String related_bar_code ;
        String item_name ;
        double number_per_each_box ;
        double public_price ;
        double discountForOne ;
        double original_price ;

        if(!barCodeText.getText().trim().isEmpty()){

            try{

                bar_code = barCodeText.getText().trim();
                related_bar_code = relatedBarCodeText.getText().trim();
                item_name = itemNameText.getText().trim();
                number_per_each_box = Double.parseDouble(numberPerEachBoxText.getText().trim());
                public_price = Double.parseDouble(priceText.getText().trim());
                discountForOne = Double.parseDouble(discountText.getText().trim());
                original_price = Double.parseDouble(originalPriceForOneText.getText().trim());


            }catch (Exception e){
                e.printStackTrace();
                return;
            }


            try{


                OpenConnection();

                Statement getStatement = connection.createStatement();
                String GetBulkInfo = "select * from add_bulk where bar_code='"+bar_code+"'";
                ResultSet resultSet = getStatement.executeQuery(GetBulkInfo);

                if(resultSet.next()){

                    Statement upDateStatement = connection.createStatement();
                    String upDateData = "UPDATE add_bulk SET related_bar_code ='" + related_bar_code + "', item_name = '" + item_name + "',number_of_items_per_one_box = "
                            + number_per_each_box + ", price_for_one = " + public_price + ", discount = "+discountForOne+",original_price_for_one = "+original_price+
                            " WHERE bar_code = " + bar_code;
                    upDateStatement.executeUpdate(upDateData);
                    ClearTheForm();
                }

                else {

                    Statement getStatementFindRelated = connection.createStatement();
                    String findRelated = "select * from store where bar_code='"+related_bar_code+"'";
                    ResultSet resultSetForRelated = getStatementFindRelated.executeQuery(findRelated);

                    if(resultSetForRelated.next()){

                        // if the barcode doesn't exist in the database >>> insert the barcode info >>> clear the frame to add again
                        Statement insertStatement = connection.createStatement();
                        String insertData = "INSERT INTO add_bulk (bar_code, related_bar_code, item_name, number_of_items_per_one_box, price_for_one, discount, original_price_for_one,item_counter_per_unit)" +
                                " VALUES ('"+bar_code+"','"+related_bar_code+"','"+item_name+"',"+number_per_each_box+","+public_price+","+discountForOne+","+original_price+",1)";
                        insertStatement.executeUpdate(insertData);
                        ClearTheForm();
                    }

                }



                // clear the form for another try


                CloseConnection();



            }catch (Exception e){
                e.printStackTrace();
                CloseConnection();
            }


        }

    }

    public void Remove() {

        if (!barCodeText.getText().trim().isEmpty()) {
            try {

                OpenConnection();

                Statement RemoveStatement = connection.createStatement();
                String RemoveOrder = "DELETE FROM add_bulk WHERE bar_code = '" + barCodeText.getText().trim() + "'";
                RemoveStatement.executeUpdate(RemoveOrder);

                ClearTheForm();

                CloseConnection();

            } catch (Exception e) {
                e.printStackTrace();
                CloseConnection();
            }

        }

    }

    public void Search(){

        try{

            OpenConnection();

            Statement SearchForBulk = connection.createStatement();
            String BulkString = "select * from add_bulk where bar_code='"+barCodeText.getText().trim()+"'";
            ResultSet resultSet = SearchForBulk.executeQuery(BulkString);

            if(resultSet.next()){
                relatedBarCodeText.setText(resultSet.getString("related_bar_code"));
                itemNameText.setText(resultSet.getString("item_name"));
                numberPerEachBoxText.setText(resultSet.getString("number_of_items_per_one_box"));
                priceText.setText(resultSet.getString("price_for_one"));
                discountText.setText(resultSet.getString("discount"));
                originalPriceForOneText.setText(resultSet.getString("original_price_for_one"));
            }

            CloseConnection();

        }catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }

    }




    public AddBulkScreen() {

        clearTheFormButton.addActionListener(actionEvent -> {
            ClearTheForm();
        });

        addOrUpdateButton.addActionListener(actionEvent -> {
            AddFunction();
        });

        removeButton.addActionListener(actionEvent -> {
            Remove();
        });

        barCodeText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                Search();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                Search();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                Search();
            }
        });


        numberPerEachBoxText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        priceText.addKeyListener(new KeyAdapter() {
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

        originalPriceForOneText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });


    }




}
