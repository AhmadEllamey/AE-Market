package Mini;

import javax.swing.*;

import static Mini.Main.RateIsDoneNow;

public class RateIsDoneFrame {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public RateIsDoneFrame() {
        OKButton.addActionListener(e -> RateIsDoneNow.dispose());
    }
}
