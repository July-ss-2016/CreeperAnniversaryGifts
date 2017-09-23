package vip.creeper.mcserverplugins.creeperanniversarygifts.managers;

import info.TrenTech.EasyKits.Kit.Kit;
import info.TrenTech.EasyKits.Kit.KitUser;
import org.bukkit.entity.Player;
import vip.creeper.mcserverplugins.creeperanniversarygifts.CreeperAnniversaryGifts;
import vip.creeper.mcserverplugins.creeperanniversarygifts.Settings;
import vip.creeper.mcserverplugins.creeperanniversarygifts.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperanniversarygifts.utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by July_ on 2017/9/23.
 */
public class GiftsManager {
    private CreeperAnniversaryGifts plugin;
    private SqlManager sqlManager;
    private Settings settings;

    public GiftsManager(final CreeperAnniversaryGifts plugin) {
        this.plugin = plugin;
        this.sqlManager = plugin.getSqlManager();
        this.settings = plugin.getSettings();
    }

    public boolean isReceived(final String playerName) {
        try {
            java.sql.PreparedStatement preparedStatement = sqlManager.getCon().prepareStatement("select received_player_name from " + settings.getTableName() + " where received_player_name = '" + playerName.toLowerCase() + "'");
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean setReceived(final String playerName) {
        try {
            java.sql.PreparedStatement preparedStatement = sqlManager.getCon().prepareStatement("insert into " + settings.getTableName() + "(received_player_name) values(?)");

            preparedStatement.setString(1, playerName.toLowerCase());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void giveGifts(final Player player) {
        Kit kit = new Kit("anniversary_2");

        if (!kit.exists()) {
            MsgUtil.sendMsg(player, "&c系统错误: 礼包不存在,请联系管理员.");
            return;
        }

        KitUser kitUser = new KitUser(player, kit);

        try {
            kitUser.applyKit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int r = Util.getRandomValue(1, 25);

        plugin.getPlayerPoints().getAPI().give(player.getUniqueId(), r);
        MsgUtil.sendMsg(player,"&d你抽到了 &e" + r +"个 &d点券!");
    }
}
