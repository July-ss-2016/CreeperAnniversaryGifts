package vip.creeper.mcserverplugins.creeperanniversarygifts;

/**
 * Created by July_ on 2017/9/9.
 */
public class Settings {
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private String tableName;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tablePrefix) {
        this.tableName = tablePrefix;
    }
}
