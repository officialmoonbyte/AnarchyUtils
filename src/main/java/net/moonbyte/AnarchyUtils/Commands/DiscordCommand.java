package net.moonbyte.AnarchyUtils.Commands;

import net.moonbyte.AnarchyUtils.Helpers.ConfigUtil;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        String discordMessage = ConfigUtil.Links_DiscordMessage;
        String discordLink = ConfigUtil.Links_Discord;

        PlayerUtils.sendMessage(player, discordMessage + discordLink);
        return true;
    }
}
