package vip.creeper.mcserverplugins.creeperanniversarygifts.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import vip.creeper.mcserverplugins.creeperanniversarygifts.CreeperAnniversaryGifts;

/**
 * Created by July_ on 2017/9/23.
 */
public class OpCommand implements CommandExecutor {
    private CreeperAnniversaryGifts plugin;

    public OpCommand(final CreeperAnniversaryGifts plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender cs, Command cmd, String lable, String[] args) {
        if (cs.hasPermission("CreeperQqBinder.admin")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                plugin.loadConfig();
                cs.sendMessage("配置已重载" );
                plugin.initSql();
                return true;
            }

        } else {
            cs.sendMessage("无权限.");
            return true;
        }

        return false;
    }
}
