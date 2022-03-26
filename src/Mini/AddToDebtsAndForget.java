package Mini;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.AddToDebtFromSellScreen;
import static Mini.Main.CreateAndAddFrame;
import static Mini.TheMainFrameHub.BillNumberNow;
import static Mini.TheMainFrameHub.TotalBillToAddToDebt;

public class AddToDebtsAndForget {
    private JPanel theMainScreen;
    private JTextField DebtNameText;
    private JButton CreateAndAdd;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public AddToDebtsAndForget() {

        CreateAndAdd.addActionListener(e -> {


            try{

                OpenConnection();

                double totalCash = 0 ;

                Statement AddDebtUser = connection.createStatement();
                String DebtUserNameQuery = "INSERT INTO dept (user_name_dept) VALUES ('"+ DebtNameText.getText().trim()+"')";
                AddDebtUser.executeUpdate(DebtUserNameQuery);

                Statement InsertIntoDeptHistory = connection.createStatement();
                String info = "INSERT INTO dept_history (user_name_dept,bill_number, cash, note) VALUES ('"+DebtNameText.getText().trim()+"','"+BillNumberNow+"'"+
                        ","+TotalBillToAddToDebt+",'فاتوره')";
                InsertIntoDeptHistory.executeUpdate(info);

                Statement GetInfo = connection.createStatement();
                String detailsInfo = "SELECT * FROM dept_history WHERE user_name_dept ='"+DebtNameText.getText().trim()+"'";
                ResultSet resultSet = GetInfo.executeQuery(detailsInfo);

                while (resultSet.next()){

                    totalCash = totalCash + Double.parseDouble(resultSet.getString("cash")) ;

                }

                Statement UpdateTheTotalForDept = connection.createStatement();
                String UpdateTheTotal = "UPDATE dept SET total ="+totalCash+" WHERE user_name_dept = '"+DebtNameText.getText().trim()+"'";
                UpdateTheTotalForDept.executeUpdate(UpdateTheTotal);


                CreateAndAddFrame.dispose();
                AddToDebtFromSellScreen.dispose();
                TotalBillToAddToDebt = 0 ;

                CloseConnection();

            }

            catch (Exception ee){

                ee.printStackTrace();
                CloseConnection();

            }
        });

    }

}
