package vip.creeper.mcserverplugins.creeperanniversarygifts;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import vip.creeper.mcserverplugins.creeperanniversarygifts.commands.OpCommand;
import vip.creeper.mcserverplugins.creeperanniversarygifts.commands.PlayerCommand;
import vip.creeper.mcserverplugins.creeperanniversarygifts.managers.GiftsManager;
import vip.creeper.mcserverplugins.creeperanniversarygifts.managers.SqlManager;
import vip.creeper.mcserverplugins.creeperanniversarygifts.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperanniversarygifts.utils.SqlUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by July_ on 2017/9/23.
 */
public class CreeperAnniversaryGifts extends JavaPlugin {
    private static CreeperAnniversaryGifts instance;
    private SqlManager sqlManager;
    private GiftsManager giftsManager;
    private Settings settings;
    private PlayerPoints playerPoints;

    public void onEnable() {
        instance = this;
        this.settings = new Settings();

        loadConfig();

        if (!hookPlayerPoints() || !initSql()) {
            setEnabled(false);
            return;
        }

        this.giftsManager = new GiftsManager(this);

        getCommand("cag").setExecutor(new PlayerCommand(this));
        getCommand("ocag").setExecutor(new OpCommand(this));
        runTaskReConnectTask();
        MsgUtil.warring("插件初始化完毕!");
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        sqlManager.closeSql();
    }

    public static CreeperAnniversaryGifts getInstance() {
        return instance;
    }

    public PlayerPoints getPlayerPoints() {
        return playerPoints;
    }

    public SqlManager getSqlManager() {
        return sqlManager;
    }

    public GiftsManager getGiftsManager() {
        return giftsManager;
    }

    public Settings getSettings() {
        return settings;
    }

    private boolean hookPlayerPoints() {
        PlayerPoints playerPoints = PlayerPoints.class.cast(getServer().getPluginManager().getPlugin("PlayerPoints"));
        this.playerPoints = playerPoints;

        return  playerPoints != null;
    }

    public void loadConfig() {
        FileConfiguration config = getConfig();

        saveDefaultConfig();
        reloadConfig();
        settings.setHost(config.getString("mysql.host"));
        settings.setPort(config.getInt("mysql.port"));
        settings.setDatabase(config.getString("mysql.database"));
        settings.setUsername(config.getString("mysql.username"));
        settings.setPassword(config.getString("mysql.password"));
        settings.setTableName(config.getString("mysql.table_name"));
    }

    private void runTaskReConnectTask() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> Bukkit.getScheduler().runTask(instance, () -> {
            Connection con = SqlUtil.getSqlConnection(settings.getHost(), settings.getPort(), settings.getDatabase(), settings.getUsername(), settings.getPassword());

            if (con == null) {
                MsgUtil.warring("MySQL重连失败.");
            } else {
                sqlManager.setCon(con);
                MsgUtil.info("MySQL重连成功.");
            }
        }), 432000L,432000L);
    }

    public boolean initSql() {
        try {
            if (!sqlManager.isExistsTable(settings.getTableName())) {
                if (!sqlManager.executeStatement("create table " + settings.getTableName() + "(received_player_name varchar(32))")) {
                    MsgUtil.warring(settings.getTableName() + " 表创建失败!");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        this.sqlManager = new SqlManager(SqlUtil.getSqlConnection(settings.getHost(), settings.getPort(), settings.getDatabase(), settings.getUsername(), settings.getPassword()));

        if (sqlManager.getCon() == null) {
            MsgUtil.warring("MySQL连接失败.");
            return false;
        }

        return true;
    }
}
