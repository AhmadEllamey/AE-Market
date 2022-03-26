package Mini;

import javax.swing.*;


import static Mini.Main.tryAgainFrame;

public class WrongPasswordOrUserName {
    private JPanel theMainScreen;
    private JButton OKButton;

    //set and get methods


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void setTheMainScreen(JPanel theMainScreen) {
        this.theMainScreen = theMainScreen;
    }

    public WrongPasswordOrUserName() {
        OKButton.addActionListener(e -> tryAgainFrame.dispose());
    }
}