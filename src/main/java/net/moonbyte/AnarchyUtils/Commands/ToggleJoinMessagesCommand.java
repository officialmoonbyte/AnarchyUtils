package net.moonbyte.AnarchyUtils.Commands;

import net.moonbyte.AnarchyUtils.Helpers.ConfigurationUtils;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ToggleJoinMessagesCommand implements CommandExecutor {
    public static List<Player> muted = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;

        if(muted.contains(player)){
            muted.remove(player);
            String joinMessagesUnmuteNotification = ConfigurationUtils.getConfig().getString("join-messages.unmuted");

            PlayerUtils.sendMessage(player, joinMessagesUnmuteNotification);
        }else{
            muted.add(player);
            String joinMessagesAddNotification = ConfigurationUtils.getConfig().getString("join-messages.muted");
            PlayerUtils.sendMessage(player, joinMessagesAddNotification);
        }

        return true;
    }
}
