package net.moonbyte.AnarchyUtils.Commands;

import net.moonbyte.AnarchyUtils.Helpers.ConfigUtil;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        final List<String> list = Collections.singletonList(ConfigUtil.Global_HelpLines);

        for (final String line : list) {
            if (commandSender instanceof Player) {
                final Player player = (Player)commandSender;
                PlayerUtils.sendRawMessage(player, ChatColor.translateAlternateColorCodes('&', line));
            }
        }

        return true;
    }
}
