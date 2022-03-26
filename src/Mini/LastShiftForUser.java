package Mini;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.ShowLastShift;
import static Mini.TheMainFrameHub.WantedUser;

public class LastShiftForUser {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JTextField numOfShiftText;
    private JTextField DateText;
    private JTextField userNameText;
    private JTextField billsSumText;
    private JTextField profitText;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public LastShiftForUser() {


        numOfShiftText.setEnabled(false);
        DateText.setEnabled(false);
        userNameText.setEnabled(false);
        billsSumText.setEnabled(false);
        profitText.setEnabled(false);

        numOfShiftText.setHorizontalAlignment(JTextField.CENTER);
        DateText.setHorizontalAlignment(JTextField.CENTER);
        userNameText.setHorizontalAlignment(JTextField.CENTER);
        billsSumText.setHorizontalAlignment(JTextField.CENTER);
        profitText.setHorizontalAlignment(JTextField.CENTER);

        try{
            OpenConnection();
            Statement getTheShifts = connection.createStatement();
            String UserRate = "SELECT * FROM shifts WHERE user_name ='"+WantedUser+"'";
            ResultSet resultSet = getTheShifts.executeQuery(UserRate);

            if(resultSet.last()){
                numOfShiftText.setText(resultSet.getString("user_shift_number"));
                DateText.setText(resultSet.getString("date_of_day"));
                userNameText.setText(resultSet.getString("user_name"));
                billsSumText.setText(resultSet.getString("total_money_of_the_shift"));
                profitText.setText(resultSet.getString("total_earned_of_the_shift"));
                WantedUser = "";
            }

            CloseConnection();

        }catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }


        OKButton.addActionListener(e -> ShowLastShift.dispose());
    }
}
