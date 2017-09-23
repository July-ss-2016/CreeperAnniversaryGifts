package vip.creeper.mcserverplugins.creeperanniversarygifts.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * Created by July_ on 2017/9/23.
 */
public class Util {
    private static Random random = new Random();
    public static int getInventoryFreeSize(final Player player) {
        int i = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item.getType() == Material.AIR) {
                i++;
            }
        }

        return i;
    }

    // 2 3
    public static int getRandomValue(final int min, final int max) {
        return (int) (Math.random() * (max)) + min;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(getRandomValue(1, 10));
        }
    }
}
