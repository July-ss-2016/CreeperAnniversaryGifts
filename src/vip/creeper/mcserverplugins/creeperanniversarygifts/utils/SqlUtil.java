package vip.creeper.mcserverplugins.creeperanniversarygifts.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by July_ on 2017/9/9.
 */
public class SqlUtil {
    public static Connection getSqlConnection(final String host, final int port, final String database, final String username, final String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
