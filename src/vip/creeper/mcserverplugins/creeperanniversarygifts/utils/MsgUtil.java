package vip.creeper.mcserverplugins.creeperanniversarygifts.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import vip.creeper.mcserverplugins.creeperanniversarygifts.CreeperAnniversaryGifts;

import java.util.logging.Logger;

/**
 * Created by July_ on 2017/9/2.
 */
public class MsgUtil {
    public static final String HEAD_MSG = "§a[CreeperAnniversaryGifts] §b";
    private static Logger logger = CreeperAnniversaryGifts.getInstance().getLogger();

    public static void info(final String msg) {
        logger.info(msg);
    }

    public static void warring(final String msg) {
        logger.warning(msg);
    }

    public static void sendMsg(final CommandSender cs, final String msg) {
        cs.sendMessage(HEAD_MSG + ChatColor.translateAlternateColorCodes('&', msg));
    }
}
