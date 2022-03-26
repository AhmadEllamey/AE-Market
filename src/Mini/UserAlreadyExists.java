package Mini;

import javax.swing.*;

import static Mini.CreateUsers.userExist;

public class UserAlreadyExists {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public UserAlreadyExists() {
        OKButton.addActionListener(e -> userExist.dispose());
    }


}
