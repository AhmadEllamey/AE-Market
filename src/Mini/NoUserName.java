package Mini;

import javax.swing.*;

import static Mini.Main.noUserName;

public class NoUserName {
    private JPanel theMainScreen;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }


    public NoUserName() {
        OKButton.addActionListener(e -> {
            // ToDo Dispose the frame
            noUserName.dispose();
        });
    }


}
