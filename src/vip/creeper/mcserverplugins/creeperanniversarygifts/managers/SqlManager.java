package vip.creeper.mcserverplugins.creeperanniversarygifts.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by July_ on 2017/9/23.
 */
public class SqlManager {
    private Connection con;

    public SqlManager(final Connection con) {
        this.con = con;
    }

    public boolean executeStatement(final String sql) {
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean isExistsTable(final String tableName) throws SQLException {
        PreparedStatement sql = con.prepareStatement("show tables like '" + tableName + "'");
        ResultSet resultSet = sql.executeQuery();

        while(resultSet.next()) {
            if(resultSet.getString(1).equalsIgnoreCase(tableName)) {
                return true;
            }
        }
        return false;
    }

    public Connection getCon() {
        return this.con;
    }

    public void setCon(final Connection con) {
        this.con = con;
    }

    public void closeSql() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
