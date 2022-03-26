package Mini;

import javax.swing.*;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Main.*;

public class MerchantNewDebt {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JTextField userNameText;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public MerchantNewDebt() {

        OKButton.addActionListener(e -> {
            try{
                if(!userNameText.getText().trim().isEmpty()) {
                    OpenConnection();
                    Statement AddInTheDeptTable = connection.createStatement();
                    String DeptUserX = "INSERT INTO merchant_dept (user_name_dept) VALUES ('" + userNameText.getText().trim() + "')";
                    AddInTheDeptTable.executeUpdate(DeptUserX);


                    MerchantCreateNewDept.dispose();
                    MerchantDebtScreen.dispose();


                    TheMainFrameHub.MerchantDebtFrame();

                    TheMainFrameHub.ShowNewUserCreated();


                    CloseConnection();
                }
            }catch (Exception ee){
                ee.printStackTrace();
                CreateUsers createUsers = new CreateUsers();
                createUsers.UserExist();
                CloseConnection();
            }
        });

    }
}
