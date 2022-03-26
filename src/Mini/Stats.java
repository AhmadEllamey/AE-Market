package Mini;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.ShowAllStats;

public class Stats {


    private JPanel theMainScreen;
    private JTextField StoreItemsNumber;
    private JTextField TotalOriginalPrice;
    private JTextField TotalProfit;
    private JTextField NumberOfBills;
    private JTextField TotalSumOfBills;
    private JTextField TotalProfitSum;
    private JTextField NumberOfTransactions;
    private JTextField WithdrawText;
    private JTextField DepositText;
    private JTextField NumOfShifts;
    private JTextField ShiftsTotalBills;
    private JTextField ShiftsEarnedTotal;
    private JTextField NumberOfDebts;
    private JTextField TotalDebtsText;
    private JButton okButton;
    private JTextField FinalProfitText;

    double TotalDepositSum ;
    double TotalWithdrawSum ;
    double TotalDebtsSum ;
    double TotalProfitsSum ;
    double AfterSale;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void StoreInfo(){

        int numberOfItems = 0 ;
        double totalStoreOriginal = 0 ;
        double totalProfit = 0 ;

        try{

            OpenConnection();
            Statement GetStoreInfo = connection.createStatement();
            String StoreInfo = "SELECT * FROM store";
            ResultSet resultSet = GetStoreInfo.executeQuery(StoreInfo);

            while (resultSet.next()){

                numberOfItems = numberOfItems + 1 ;

                totalStoreOriginal = totalStoreOriginal + Double.parseDouble(resultSet.getString("original_total_price"));

                totalProfit = totalProfit +
                        (Double.parseDouble(resultSet.getString("total_price")) -
                        (Double.parseDouble(resultSet.getString("discount")) * Double.parseDouble(resultSet.getString("number_of_items"))));

            }

            CloseConnection();

            AfterSale = totalProfit - totalStoreOriginal ;

            StoreItemsNumber.setText(String.valueOf(numberOfItems));

            TotalOriginalPrice.setText(String.valueOf(totalStoreOriginal));

            TotalProfit.setText(String.valueOf(totalProfit));



        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }


    }

    public void BillsInfo(){

        int BillsCounter = 0 ;

        double billsSum = 0 ;

        double billsProfitSum = 0 ;

        try{

            OpenConnection();
            Statement GetBillsInfo = connection.createStatement();
            String BillsInfo = "SELECT * FROM logs";
            ResultSet resultSet = GetBillsInfo.executeQuery(BillsInfo);

            while (resultSet.next()){

                BillsCounter = BillsCounter + 1 ;

                billsSum = billsSum + Double.parseDouble(resultSet.getString("total"));

                billsProfitSum = billsProfitSum + Double.parseDouble(resultSet.getString("earned"));

            }

            CloseConnection();

            TotalProfitsSum = billsProfitSum ;

            NumberOfBills.setText(String.valueOf(BillsCounter));

            TotalSumOfBills.setText(String.valueOf(billsSum));

            TotalProfitSum.setText(String.valueOf(billsProfitSum));


        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }
    }

    public void CashInfo(){

        int Counter = 0 ;

        double WithDrawSum = 0 ;

        double DepositSum = 0 ;

        try{

            OpenConnection();
            Statement GetCashInfo = connection.createStatement();
            String CashInfo = "SELECT * FROM cash WHERE type = 'Withdraw'";
            ResultSet resultSet = GetCashInfo.executeQuery(CashInfo);

            while (resultSet.next()){

                Counter = Counter + 1 ;

                WithDrawSum = WithDrawSum + Double.parseDouble(resultSet.getString("amount"));

            }

            TotalWithdrawSum = WithDrawSum;

            Statement GetTheDepositInfo = connection.createStatement();
            String DepositInfo = "SELECT * FROM cash WHERE type = 'Deposit'";
            ResultSet resultSetForDeposit = GetTheDepositInfo.executeQuery(DepositInfo);

            while (resultSetForDeposit.next()){

                Counter = Counter + 1 ;

                DepositSum = DepositSum + Double.parseDouble(resultSetForDeposit.getString("amount"));

            }

            TotalDepositSum = DepositSum ;

            CloseConnection();




            NumberOfTransactions.setText(String.valueOf(Counter));

            WithdrawText.setText(String.valueOf(WithDrawSum));

            DepositText.setText(String.valueOf(DepositSum));



        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }

    }

    public void ShiftInfo(){

        int ShiftsCounter = 0 ;

        double ShiftBillsSum = 0 ;

        double ShiftEarnedSum = 0 ;

        try{

            OpenConnection();
            Statement GetShiftsInfo = connection.createStatement();
            String ShiftsInfo = "SELECT * FROM shifts";
            ResultSet resultSet = GetShiftsInfo.executeQuery(ShiftsInfo);

            while (resultSet.next()){

                ShiftsCounter = ShiftsCounter + 1 ;

                ShiftBillsSum = ShiftBillsSum + Double.parseDouble(resultSet.getString("total_money_of_the_shift"));

                ShiftEarnedSum = ShiftEarnedSum + Double.parseDouble(resultSet.getString("total_earned_of_the_shift"));

            }

            CloseConnection();

            NumOfShifts.setText(String.valueOf(ShiftsCounter));

            ShiftsTotalBills.setText(String.valueOf(ShiftBillsSum));

            ShiftsEarnedTotal.setText(String.valueOf(ShiftEarnedSum));


        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }

    }

    public void DebtInfo(){

        int DebtsCounter = 0 ;

        double DebtSums = 0 ;


        try{

            OpenConnection();
            Statement GetDebtInfo = connection.createStatement();
            String DebtInfo = "SELECT * FROM dept WHERE total > 0";
            ResultSet resultSet = GetDebtInfo.executeQuery(DebtInfo);

            while (resultSet.next()){

                DebtsCounter = DebtsCounter + 1 ;

                DebtSums = DebtSums + Double.parseDouble(resultSet.getString("total"));

            }

            TotalDebtsSum = DebtSums;

            CloseConnection();

            NumberOfDebts.setText(String.valueOf(DebtsCounter));


            TotalDebtsText.setText(String.valueOf(DebtSums));


        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }

    }

    public void GeneralInfo(){

        double FinalProfit = TotalDepositSum + TotalWithdrawSum + TotalProfitsSum + AfterSale;

        FinalProfitText.setText(String.valueOf(FinalProfit));

    }

    public void CreateStats(){

        StoreInfo();

        BillsInfo();

        CashInfo();

        ShiftInfo();

        DebtInfo();

        GeneralInfo();


    }


    public Stats() {


        CreateStats();

        okButton.addActionListener(e -> ShowAllStats.dispose());
    }
}
