package Mini;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.areYouSure;
import static Mini.TheMainFrameHub.barCodeToDelete;

public class AreYouSureAboutDelete {
    private JPanel theMainScreen;
    private JButton sureButton;
    private JLabel giveTheItemName;
    private JButton cancelButton;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public AreYouSureAboutDelete() {

        try{

            OpenConnection();
            Statement getTheItemName = connection.createStatement();
            String ItemName = "SELECT * FROM store WHERE bar_code = '"+barCodeToDelete+"'";
            ResultSet resultSet = getTheItemName.executeQuery(ItemName);

            if(resultSet.next()){
                giveTheItemName.setText("Remove ("+resultSet.getString("item_name")+") Permanently from The store ? ");
            }

        }catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }




        sureButton.addActionListener(e -> {



            Runnable doHighlight = () -> {
                try {

                    OpenConnection();
                    Statement deleteStatement = connection.createStatement();
                    String deleteRow = "DELETE FROM store WHERE bar_code = '"+barCodeToDelete+"'";
                    deleteStatement.executeUpdate(deleteRow);

                    Statement deleteFromMaintain = connection.createStatement();
                    String deleteFromMaintainText = "DELETE FROM maintain_price WHERE bar_code = '"+barCodeToDelete+"'";
                    deleteFromMaintain.executeUpdate(deleteFromMaintainText);



                    CloseConnection();

                    areYouSure.dispose();


                }catch (Exception ee){
                    ee.printStackTrace();
                    CloseConnection();
                }
            };

            SwingUtilities.invokeLater(doHighlight);


        });


        cancelButton.addActionListener(e -> areYouSure.dispose());
    }
}
