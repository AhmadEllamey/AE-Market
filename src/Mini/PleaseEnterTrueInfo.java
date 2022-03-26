package Mini;

import javax.swing.*;

import static Mini.CreateUsers.warning;

public class PleaseEnterTrueInfo {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public PleaseEnterTrueInfo() {
        OKButton.addActionListener(e -> warning.dispose());
    }
}
