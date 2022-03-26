package Mini;

import javax.swing.*;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Main.*;
import static Mini.MerchantDebtGUI.MerchantNameToOpen;

public class SureAboutClearAllMerchantsDept {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JButton CANCLEButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void Delete(){
        try{

            OpenConnection();

            Statement DeleteFromTheHis = connection.createStatement();
            String DeleteOrder = "DELETE FROM merchant_dept_history WHERE user_name_dept = '"+MerchantNameToOpen+"'";
            DeleteFromTheHis.executeUpdate(DeleteOrder);

            Statement UpdateTheTotalForDept = connection.createStatement();
            String UpdateTheTotal = "UPDATE merchant_dept SET total ="+ 0 +" WHERE user_name_dept = '"+MerchantNameToOpen+"'";
            UpdateTheTotalForDept.executeUpdate(UpdateTheTotal);


            ClearAllMerchantDeptFrame.dispose();
            InSideMerchantDebtScreen.dispose();

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }
    }

    public SureAboutClearAllMerchantsDept() {

        OKButton.addActionListener(e -> Delete());


        CANCLEButton.addActionListener(e -> ClearAllMerchantDeptFrame.dispose());

    }
}
