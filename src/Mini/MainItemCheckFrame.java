package Mini;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.ItemMoreInfo;
import static Mini.Main.mainItemCheck;
import static Mini.TheMainFrameHub.GetMainSearchCompoText;

public class MainItemCheckFrame {
    private JPanel theMainScreen;
    private JButton okButton;
    private JTextField ItemNameText;
    private JTextField RateText;
    private JTextField LeftText;
    private JTextField CostStoreText;
    private JTextField RankText;
    private JTextField TotalIncome;
    private JButton InfoButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void SetAllDisabled(){
        ItemNameText.setEnabled(false);
        RateText.setEnabled(false);
        LeftText.setEnabled(false);
        CostStoreText.setEnabled(false);
        RankText.setEnabled(false);
    }


    public void LoadTheForm(){

        try{

            SetAllDisabled();

            OpenConnection();


            Statement GetItemInfo = connection.createStatement();
            String items = "SELECT * FROM store WHERE bar_code = '"+GetMainSearchCompoText+"'";
            ResultSet ItemResultSet = GetItemInfo.executeQuery(items);

            if(ItemResultSet.next()){

                ItemNameText.setText(ItemResultSet.getString("item_name"));
                RateText.setText(ItemResultSet.getString("rate"));
                LeftText.setText(ItemResultSet.getString("number_of_items"));
                CostStoreText.setText(ItemResultSet.getString("total_price"));
                TotalIncome.setText(String.valueOf(
                        (Double.parseDouble(ItemResultSet.getString("total_price"))
                        - Double.parseDouble(ItemResultSet.getString("original_total_price")))));

                Statement GetTheRank = connection.createStatement();
                String Rank = "SELECT * FROM store ORDER BY rate DESC ";
                ResultSet RankResultSet = GetTheRank.executeQuery(Rank);

                int RankNumber = 0 ;

                while(RankResultSet.next()){

                    RankNumber = RankNumber + 1 ;

                    if(RankResultSet.getString("bar_code").equals(GetMainSearchCompoText)){
                        break;
                    }

                }

                RankText.setText(String.valueOf(RankNumber));

            }



            CloseConnection();



        }catch (Exception e){
            e.printStackTrace();
            CloseConnection();
        }



    }

    public void GoToDetails(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        ItemMoreInfo = new JFrame("Check Item !");
        ItemMoreInfo.setContentPane(new ItemInfo().getTheMainScreen());
        ItemMoreInfo.setIconImage(img.getImage());
        ItemMoreInfo.setLocationRelativeTo(null);//Setting location to the center of screen
        ItemMoreInfo.getContentPane().setBackground(Color.black);//setting background color
        ItemMoreInfo.setSize(1500,800);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ItemMoreInfo.setLocation(dim.width/2-ItemMoreInfo.getSize().width/2, dim.height/2-ItemMoreInfo.getSize().height/2);

        ItemMoreInfo.setVisible(true);
    }



    public MainItemCheckFrame() {


        LoadTheForm();


        okButton.addActionListener(e -> mainItemCheck.dispose());

        InfoButton.addActionListener(e -> {
            try{
                ItemMoreInfo.dispose();
            }catch (Exception ee){
                ee.printStackTrace();
            }
                GoToDetails();
        });
    }


}
