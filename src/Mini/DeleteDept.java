package Mini;

import javax.swing.*;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Dept.UserDeptName;
import static Mini.Main.FirstDeptFrame;
import static Mini.Main.SureAboutTheDeletedDept;

public class DeleteDept {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public DeleteDept() {

        OKButton.addActionListener(e -> {
            try{
                OpenConnection();
                Statement DeleteNote = connection.createStatement();
                String DeleteSelectedRow = "DELETE FROM dept WHERE user_name_dept = '"+UserDeptName+"'";
                DeleteNote.executeUpdate(DeleteSelectedRow);

                Statement DeleteAllTransaction = connection.createStatement();
                String DeleteTheLogs = "DELETE FROM dept_history WHERE user_name_dept = '"+UserDeptName+"'";
                DeleteAllTransaction.executeUpdate(DeleteTheLogs);


                CloseConnection();

                SureAboutTheDeletedDept.dispose();
                FirstDeptFrame.dispose();

                TheMainFrameHub.DebtFrame();

            }catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }
        });
    }

}
