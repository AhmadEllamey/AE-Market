package Mini;

import javax.swing.*;


import static Mini.Main.TransactionComplete;

public class CashActionIsDone {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public CashActionIsDone() {
        OKButton.addActionListener(e -> TransactionComplete.dispose());
    }
}
