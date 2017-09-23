package vip.creeper.mcserverplugins.creeperanniversarygifts.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vip.creeper.mcserverplugins.creeperanniversarygifts.CreeperAnniversaryGifts;
import vip.creeper.mcserverplugins.creeperanniversarygifts.managers.GiftsManager;
import vip.creeper.mcserverplugins.creeperanniversarygifts.utils.MsgUtil;

/**
 * Created by July_ on 2017/9/23.
 */
public class PlayerCommand implements CommandExecutor {
    private GiftsManager giftsManager;

    public PlayerCommand(final CreeperAnniversaryGifts plugin) {
        this.giftsManager = plugin.getGiftsManager();
    }

    public boolean onCommand(CommandSender cs, Command cmd, String lable, String[] args) {
        if (cs instanceof Player) {
            Player player = (Player) cs;
            String playerName = player.getName();

            if (args.length == 1 && args[0].equalsIgnoreCase("get")) {
                if (giftsManager.isReceived(playerName)) {
                    MsgUtil.sendMsg(player, "&c你已经领取过二周年庆大礼包了!");
                    return true;
                }

                giftsManager.giveGifts(player);
                giftsManager.setReceived(playerName);
            }
        }

        return false;
    }
}
