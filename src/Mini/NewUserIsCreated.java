package Mini;

import javax.swing.*;

import static Mini.Main.NewUserCreated;

public class NewUserIsCreated {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public NewUserIsCreated() {
        OKButton.addActionListener(e -> NewUserCreated.dispose());
    }
}
