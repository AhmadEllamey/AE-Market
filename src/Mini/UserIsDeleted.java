package Mini;

import javax.swing.*;

import static Mini.Main.UserDeleted;

public class UserIsDeleted {
    private JPanel theMainScreen;
    private JButton okButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public UserIsDeleted() {
        okButton.addActionListener(e -> UserDeleted.dispose());
    }
}
