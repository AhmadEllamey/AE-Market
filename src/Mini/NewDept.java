package Mini;

import javax.swing.*;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.CreateNewDept;
import static Mini.Main.FirstDeptFrame;

public class NewDept {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JTextField userNameText;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public NewDept() {


        OKButton.addActionListener(e -> {
            try{
                if(!userNameText.getText().trim().isEmpty()) {
                    OpenConnection();
                    Statement AddInTheDeptTable = connection.createStatement();
                    String DeptUserX = "INSERT INTO dept (user_name_dept) VALUES ('" + userNameText.getText().trim() + "')";
                    AddInTheDeptTable.executeUpdate(DeptUserX);


                    CreateNewDept.dispose();
                    FirstDeptFrame.dispose();



                    TheMainFrameHub.DebtFrame();

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
