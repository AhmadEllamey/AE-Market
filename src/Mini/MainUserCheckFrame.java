package Mini;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.mainUserCheck;
import static Mini.TheMainFrameHub.GetMainSearchCompoText;
import static Mini.TheMainFrameHub.WantedUser;

public class MainUserCheckFrame {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JButton GOButton;
    private JTextField FullNameText;
    private JTextField RateOfUserText;
    private JTextField NumberOfShiftsText;
    private JTextField TotalBillsSumText;
    private JTextField TotalProfitText;
    private JTextField TotalWithdrawText;
    private JTextField TotalDepositText;
    private JLabel UserNameText;
    private JTextField NumberOfBillsText;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void LoadTheData(){

        try{

            UserNameText.setText(GetMainSearchCompoText);


            OpenConnection();


            Statement GetUsersInfo = connection.createStatement();
            String users = "SELECT * FROM users WHERE user_name = '"+GetMainSearchCompoText+"'";
            ResultSet UsersResultSet = GetUsersInfo.executeQuery(users);

            if(UsersResultSet.next()){

                FullNameText.setText(UsersResultSet.getString("full_name"));
                RateOfUserText.setText(UsersResultSet.getString("rate"));

            }


            Statement GetShiftsInfo = connection.createStatement();
            String shifts = "SELECT * FROM shifts WHERE user_name = '"+GetMainSearchCompoText+"'";
            ResultSet ShiftsResultSet = GetShiftsInfo.executeQuery(shifts);

            double TotalBills = 0 ;
            double TotalProfit = 0 ;
            int ShiftCounter = 0 ;

            while (ShiftsResultSet.next()){

                ShiftCounter = ShiftCounter + 1 ;

                TotalBills = TotalBills + Double.parseDouble(ShiftsResultSet.getString("total_money_of_the_shift"));

                TotalProfit = TotalProfit + Double.parseDouble(ShiftsResultSet.getString("total_earned_of_the_shift"));

            }

            NumberOfShiftsText.setText(String.valueOf(ShiftCounter));
            TotalBillsSumText.setText(String.valueOf(TotalBills));
            TotalProfitText.setText(String.valueOf(TotalProfit));



            Statement GetCashInfo = connection.createStatement();
            String cash = "SELECT * FROM cash WHERE user_name = '"+GetMainSearchCompoText+"' AND type = 'WithDraw'";
            ResultSet CashResultSet = GetCashInfo.executeQuery(cash);

            double TotalWithdraw = 0 ;

            while (CashResultSet.next()){

                TotalWithdraw = TotalWithdraw + Double.parseDouble(CashResultSet.getString("amount"));

            }

            TotalWithdrawText.setText(String.valueOf(TotalWithdraw));


            Statement GetCashInfoDeposit = connection.createStatement();
            String cashDeposit = "SELECT * FROM cash WHERE user_name = '"+GetMainSearchCompoText+"' AND type = 'Deposit'";
            ResultSet CashDepositResultSet = GetCashInfoDeposit.executeQuery(cashDeposit);

            double TotalDeposit = 0 ;

            while (CashDepositResultSet.next()){

                TotalDeposit = TotalDeposit + Double.parseDouble(CashDepositResultSet.getString("amount"));

            }

            TotalDepositText.setText(String.valueOf(TotalDeposit));


            Statement GetNumberOfBills = connection.createStatement();
            String Bills = "SELECT * FROM logs WHERE user_name = '"+GetMainSearchCompoText+"'";
            ResultSet BillsDepositResultSet = GetNumberOfBills.executeQuery(Bills);

            double TotalNumberOfBills = 0 ;

            while (BillsDepositResultSet.next()){

                TotalNumberOfBills = TotalNumberOfBills + 1 ;

            }

            NumberOfBillsText.setText(String.valueOf(TotalNumberOfBills));


          CloseConnection();


        }catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }


    }

    public void SetAllDisEnabled(){

        FullNameText.setEnabled(false);
        RateOfUserText.setEnabled(false);
        NumberOfShiftsText.setEnabled(false);
        TotalBillsSumText.setEnabled(false);
        TotalProfitText.setEnabled(false);
        TotalWithdrawText.setEnabled(false);
        TotalDepositText.setEnabled(false);
        NumberOfBillsText.setEnabled(false);

    }



    public MainUserCheckFrame() {


        LoadTheData();

        SetAllDisEnabled();


        GOButton.addActionListener(e -> {

            WantedUser = GetMainSearchCompoText ;

            TheMainFrameHub theMainFrameHub = new TheMainFrameHub();
            theMainFrameHub.ShowLastShift();

        });



        OKButton.addActionListener(e -> mainUserCheck.dispose());


    }
}
