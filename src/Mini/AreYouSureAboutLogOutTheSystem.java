package Mini;

import javax.swing.*;


import static Mini.Main.LogOutOfTheSystemQuestion;
import static Mini.Main.mainFrame;

public class AreYouSureAboutLogOutTheSystem {
    private JPanel theMainScreen;
    private JButton logOutButton;
    private JButton cancelButton;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public AreYouSureAboutLogOutTheSystem() {

        logOutButton.addActionListener(e -> {

            LogOutOfTheSystemQuestion.dispose();

            mainFrame.dispose();

            new SplashScreenDemo();

        });



        cancelButton.addActionListener(e -> {
            mainFrame.setEnabled(true);
            LogOutOfTheSystemQuestion.dispose();

        });
    }
}
