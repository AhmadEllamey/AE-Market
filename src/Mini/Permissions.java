package Mini;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static Mini.DBConnectionX.*;
import static Mini.DBConnectionX.CloseConnection;
import static Mini.Main.ShowThePermissions;

public class Permissions {
    private JPanel theMainScreen;
    private JTable permissionTable;
    private JButton OKButton;




    public JPanel getTheMainScreen() {
        return theMainScreen;
    }

    public void CreatePermissionTable(){


        permissionTable.getTableHeader().setReorderingAllowed(false);
        permissionTable.getTableHeader().setResizingAllowed(false);


        permissionTable.setRowHeight(25);

        permissionTable.setModel(new DefaultTableModel(
                null,
                new String[]{ "Number", "Username" ,"Operation" , "Cash" , "Note" , "Date" }
        ));

        permissionTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 22));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0 ; i < 6 ; i++ ){
            permissionTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        permissionTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        permissionTable.getColumnModel().getColumn(1).setPreferredWidth(5);
        permissionTable.getColumnModel().getColumn(2).setPreferredWidth(5);
        permissionTable.getColumnModel().getColumn(3).setPreferredWidth(5);

        RefreshThePermissionTable();
    }

    public void RefreshThePermissionTable(){

        DefaultTableModel model = (DefaultTableModel) permissionTable.getModel();

        try{
            model.setRowCount(0);
            OpenConnection();
            Statement getTheCash = connection.createStatement();
            String Cash = "SELECT * FROM cash ";
            ResultSet resultSet = getTheCash.executeQuery(Cash);

            while(resultSet.next()){
                Object[] row =  {resultSet.getString("id") ,
                        resultSet.getString("user_name"),
                        resultSet.getString("type") ,
                        resultSet.getString("amount"),
                        resultSet.getString("note"),
                        resultSet.getString("createdDateSQL")};

                model.addRow(row);

            }

            CloseConnection();

        }
        catch (Exception ee){
            ee.printStackTrace();
            CloseConnection();
        }
    }



    public Permissions() {

        CreatePermissionTable();

        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("The user stopped editing successfully.");
                RefreshThePermissionTable();
            }
        };
        permissionTable.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);

        OKButton.addActionListener(e -> ShowThePermissions.dispose());
    }


}
