package Mini;

import java.sql.*;

public class DBConnectionX {

    public static Connection connection;
    public static String userName ;
    public static String password ;
    public static int ShiftNumber;
    public static int Admin ;

    public static void OpenConnection(){

        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName); // here is the ClassNotFoundException

            String serverName = "localhost";
            String myDatabase = "super_market";
            String url = "jdbc:mysql://" + serverName + "/" + myDatabase;

            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean checkUserNameX(String userNameInput){

        try{
            OpenConnection();
            Statement statement =  connection.createStatement();
            String checkUser = "select user_name from users where user_name='"+userNameInput+"'";
            ResultSet rs = statement.executeQuery(checkUser);

            if(rs.next()){
                userName = rs.getString("user_name");
                //System.out.println("found");
                //System.out.println(userName);
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            //System.out.println("error happened !");

        }
        return false ;
    }

    public static void CloseConnection(){
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean checkPasswordX(String passwordX){

        try{
            Statement statement =  connection.createStatement();
            String checkUser = "select password,admin from users where user_name='"+userName+"'";
            ResultSet rs = statement.executeQuery(checkUser);

            while (rs.next()){
                password = rs.getString("password");
                Admin = rs.getInt("admin");
                if(passwordX.equals(password)){
                    //System.out.println("found");
                    //System.out.println(password);
                    //System.out.println("found");
                    //System.out.println(String.valueOf(Admin));
                    return true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("error happened !");
        }
        return false ;
    }


    public static void GetTheShiftNumber(){

        try{

            Statement ScanShifts =  connection.createStatement();
            String GetTheShiftNumber = "SELECT shift_number FROM logs WHERE user_name = '"+userName+"'";
            ResultSet resultSet = ScanShifts.executeQuery(GetTheShiftNumber);

            if(resultSet.next()){
                resultSet.last();
                ShiftNumber= Integer.parseInt(resultSet.getString("shift_number")) + 1;
            }
            else{
                ShiftNumber = 1 ;
            }

        }

        catch (Exception e){
            e.printStackTrace();
        }


    }




}

