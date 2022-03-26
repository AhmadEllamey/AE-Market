package Mini;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.DepositFrameWindow;

public class Deposit {
    private JPanel theMainScreen;
    private JButton OKButton;
    private JTextField cashText;
    private JTextField noteWhatHappened;


    public JPanel getTheMainScreen() {
        return theMainScreen;
    }



    public Deposit() {

        cashText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char x = e.getKeyChar();

                if(Character.isAlphabetic(x)){
                    e.consume();
                }
            }
        });

        OKButton.addActionListener(e -> {
            try{

                OpenConnection();
                Statement SendMoney = connection.createStatement();
                String CashUpdate = "Insert Into cash (user_name,type, amount , note) VALUES ('"+userName+"','Deposit' ,"+Double.parseDouble(cashText.getText().trim()) +",'"+noteWhatHappened.getText().trim()+"')";
                SendMoney.executeUpdate(CashUpdate);

                TheMainFrameHub.ShowPermissionIsSuccess();
                DepositFrameWindow.dispose();
                CloseConnection();

            }catch (Exception ee){
                ee.printStackTrace();
                CloseConnection();
            }
        });
    }
}
