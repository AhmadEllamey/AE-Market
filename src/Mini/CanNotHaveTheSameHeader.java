package Mini;

import javax.swing.*;


import static Mini.Main.canNotUseThisHeader;

public class CanNotHaveTheSameHeader {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public CanNotHaveTheSameHeader() {
        OKButton.addActionListener(e -> canNotUseThisHeader.dispose());
    }
}
