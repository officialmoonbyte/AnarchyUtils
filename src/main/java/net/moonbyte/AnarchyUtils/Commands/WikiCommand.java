package net.moonbyte.AnarchyUtils.Commands;

import net.moonbyte.AnarchyUtils.Helpers.ConfigUtil;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WikiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        String wikiUrlMessage = ConfigUtil.Links_WikiURLMessage;
        String wikiUrl = ConfigUtil.Links_WikiURL;

        PlayerUtils.sendMessage(player, wikiUrlMessage + wikiUrl);
        return true;
    }
}
