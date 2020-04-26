package springall.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author caichengcheng
 * date:2020-03-13
 */
public class SeeDriver {
    public static void main(String[] args) {
        String url = "";
        String username = "";
        String password = "";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = conn.prepareStatement("sql");
            ResultSet resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
