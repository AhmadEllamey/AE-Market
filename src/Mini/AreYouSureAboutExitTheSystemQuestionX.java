package Mini;

import javax.swing.*;


import static Mini.Main.ExistOfTheSystemQuestion;
import static Mini.Main.mainFrame;

public class AreYouSureAboutExitTheSystemQuestionX {
    private JPanel theMainScreen;
    private JButton exitButton;
    private JButton cancelButton;



    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public AreYouSureAboutExitTheSystemQuestionX() {

        exitButton.addActionListener(e -> System.exit(0));

        cancelButton.addActionListener(e -> {
            mainFrame.setEnabled(true);
            ExistOfTheSystemQuestion.dispose();
        });
    }

}
