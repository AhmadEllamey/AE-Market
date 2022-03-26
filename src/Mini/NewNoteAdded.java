package Mini;

import javax.swing.*;

import static Mini.Main.NewNoteNotification;

public class NewNoteAdded {
    private JPanel theMainScreen;
    private JButton okButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public NewNoteAdded() {
        okButton.addActionListener(e -> NewNoteNotification.dispose());
    }
}
