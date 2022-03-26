package Mini;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.deleteUser;
import static Mini.Main.noUserName;

public class DeleteUser {
    private JPanel theMainScreen;
    private JButton deleteButton;
    private JTextField userNameText;

    UpDateUsers upDateUsers = new UpDateUsers();



    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public DeleteUser() {

        deleteButton.addActionListener(e -> {

            // ToDo >>> Delete the selected user if exists then dispose the frame .

            try{

                OpenConnection();
                Statement DeleteTheUserState = connection.createStatement();
                String checkTheUser = "Select * from users where user_name = '" +userNameText.getText().trim()+ "'";
                ResultSet resultSet = DeleteTheUserState.executeQuery(checkTheUser);

                if(resultSet.next()){
                    String DeleteTheUser = "DELETE FROM users WHERE user_name = '"+userNameText.getText().trim()+"'";
                    DeleteTheUserState.executeUpdate(DeleteTheUser);

                    TheMainFrameHub.ShowUserIsDeletedFrame();
                    deleteUser.dispose();
                }
                else {

                    try{
                        noUserName.dispose();
                    }catch (Exception eee){
                        eee.printStackTrace();
                    }
                    upDateUsers.NoUserLikeThis();

                }

                CloseConnection();



            }catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }

        });
    }
}
