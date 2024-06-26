package net.moonbyte.AnarchyUtils.Commands;

import net.moonbyte.AnarchyUtils.Helpers.ConfigurationUtils;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;

        String discordMessage = ConfigurationUtils.getConfig().getString("links.discord-message");
        String discordLink = ConfigurationUtils.getConfig().getString("links.discord");

        PlayerUtils.sendMessage(player, discordMessage + discordLink);
        return true;
    }
}
