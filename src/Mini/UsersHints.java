package Mini;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.Main.ShowHintUsers;

public class UsersHints {
    private JPanel theMainScreen;
    private JTable userHintTable;
    private JButton OKButton;

    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    private void CreateHintTable() {

        userHintTable.getTableHeader().setReorderingAllowed(false);
        userHintTable.getTableHeader().setResizingAllowed(false);

        userHintTable.setEnabled(false);

        userHintTable.setRowHeight(25);

        userHintTable.setModel(new DefaultTableModel(
                null,
                new String[]{"User Name" , "Name" , "Phone Number"}
        ));

        userHintTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0 ; i < 3 ;i++ ){
            userHintTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        RefreshUserHintTable();

    }

    public void RefreshUserHintTable(){

        try{
            OpenConnection();
            Statement tryToGetTheInfo = connection.createStatement();
            String getInfoFromUserTable = "Select user_name,full_name,phone_number from users";
            ResultSet resultSet = tryToGetTheInfo.executeQuery(getInfoFromUserTable);
            DefaultTableModel model = (DefaultTableModel) userHintTable.getModel();

            while (resultSet.next()){

                Object[] row = { resultSet.getString("user_name") ,
                                 resultSet.getString("full_name") ,
                                 resultSet.getString("phone_number")};

                model.addRow(row);

            }

            CloseConnection();

        }catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }

    }

    public UsersHints() {

        CreateHintTable();




        OKButton.addActionListener(e -> {
            // ToDO close the hint frame .
            ShowHintUsers.dispose();
        });
    }
}
