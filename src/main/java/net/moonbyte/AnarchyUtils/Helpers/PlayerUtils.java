package net.moonbyte.AnarchyUtils.Helpers;

import me.clip.placeholderapi.PlaceholderAPI;
import net.moonbyte.AnarchyUtils.Config.StaticConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerUtils {
    public static void sendMessage(Player player, String message) {
        String prefix = ConfigUtil.Global_Prefix;

        if (StaticConfig.usingPAPI) {
            String mout = ChatColor.translateAlternateColorCodes('&', prefix + message);
            String finalMessage = PlaceholderAPI.setPlaceholders(player, mout);

            player.sendMessage(finalMessage);
        }
        else  {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
        }
    }

    public static void sendRawMessage(Player player, String message) {
        if (StaticConfig.usingPAPI) {
            String mout = ChatColor.translateAlternateColorCodes('&', message);
            String finalMessage = PlaceholderAPI.setPlaceholders(player, mout);

            player.sendMessage(finalMessage);
        }
        else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
