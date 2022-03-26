package Mini;

import javax.swing.*;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Main.*;
import static Mini.MerchantDebtGUI.MerchantNameToOpen;

public class AreYouSureAboutDeleteTheMerchantDept {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public AreYouSureAboutDeleteTheMerchantDept() {

        OKButton.addActionListener(e -> {
            try{
                OpenConnection();
                Statement DeleteNote = connection.createStatement();
                String DeleteSelectedRow = "DELETE FROM merchant_dept WHERE user_name_dept = '"+MerchantNameToOpen+"'";
                DeleteNote.executeUpdate(DeleteSelectedRow);

                Statement DeleteAllTransaction = connection.createStatement();
                String DeleteTheLogs = "DELETE FROM merchant_dept_history WHERE user_name_dept = '"+MerchantNameToOpen+"'";
                DeleteAllTransaction.executeUpdate(DeleteTheLogs);


                CloseConnection();

                MerchantSureAboutTheDeletedDept.dispose();
                MerchantDebtScreen.dispose();

                TheMainFrameHub.MerchantDebtFrame();

            }catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }
        });

    }
}
