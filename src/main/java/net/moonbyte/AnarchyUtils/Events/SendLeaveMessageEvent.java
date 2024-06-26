package net.moonbyte.AnarchyUtils.Events;

import net.moonbyte.AnarchyUtils.Commands.ToggleJoinMessagesCommand;
import net.moonbyte.AnarchyUtils.Helpers.ConfigUtil;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class SendLeaveMessageEvent implements Listener {
    @EventHandler
    public void onLeave(@NotNull PlayerQuitEvent event) {
        event.setQuitMessage("");

        for(Player player : Bukkit.getOnlinePlayers()){
            if (!ToggleJoinMessagesCommand.muted.contains(player)){
                assert ConfigUtil.JoinMessages_Leave != null;
                PlayerUtils.sendRawMessage(player, ConfigUtil.JoinMessages_Leave.replaceAll("<player>", event.getPlayer().getName()));
            }
        }
    }
}
