package Mini;

import javax.swing.*;

import static Mini.Main.NotInTheStoreFrame;

public class NotInTheStore {
    private JPanel theMainScreen;
    private JButton OKButton;



    public JPanel getTheMainScreen() {
        return theMainScreen;
    }


    public NotInTheStore() {
        OKButton.addActionListener(e -> NotInTheStoreFrame.dispose());
    }
}
