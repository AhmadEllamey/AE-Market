package Mini;

import javax.swing.*;

import static Mini.Main.SomethingWentWrongForUserSearch;

public class SomethingWentWrong {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public SomethingWentWrong() {
        OKButton.addActionListener(e -> SomethingWentWrongForUserSearch.dispose());
    }


}
