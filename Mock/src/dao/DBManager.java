package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static String url = "jdbc:mysql://localhost/";
    private static String data_nm="usermanagement?useUnicode=true&characterEncoding=utf8";
    private static String user = "root";
    private static String pass = "password";

    /**
     * DBへ接続するコネクションを返す
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url+data_nm,user,pass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}
