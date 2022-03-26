package Mini;

import javax.swing.*;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Dept.UserDeptName2;
import static Mini.Main.ClearAllDeptFrame;
import static Mini.Main.OpenDept;


public class SureAboutClearAllDept {
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
            String DeleteOrder = "DELETE FROM dept_history WHERE user_name_dept = '"+UserDeptName2+"'";
            DeleteFromTheHis.executeUpdate(DeleteOrder);

            Statement UpdateTheTotalForDept = connection.createStatement();
            String UpdateTheTotal = "UPDATE dept SET total ="+ 0 +" WHERE user_name_dept = '"+UserDeptName2+"'";
            UpdateTheTotalForDept.executeUpdate(UpdateTheTotal);


            ClearAllDeptFrame.dispose();
            OpenDept.dispose();

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }
    }

    public SureAboutClearAllDept() {


        OKButton.addActionListener(e -> Delete());


        CANCLEButton.addActionListener(e -> ClearAllDeptFrame.dispose());


    }
}
