package Mini;

import javax.swing.*;

 import static Mini.Main.UserUpdated;

public class UserIsUpdated {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public UserIsUpdated() {
        OKButton.addActionListener(e -> UserUpdated.dispose());
    }
}
