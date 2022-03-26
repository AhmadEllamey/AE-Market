package Mini;

import javax.swing.*;

import static Mini.Main.EnterUserNameForSearch;

public class EnterUserNameForSearching {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public EnterUserNameForSearching() {
        OKButton.addActionListener(e -> EnterUserNameForSearch.dispose());
    }
}
