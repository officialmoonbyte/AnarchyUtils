package net.moonbyte.AnarchyUtils.Events;

import net.moonbyte.AnarchyUtils.Commands.ToggleJoinMessagesCommand;
import net.moonbyte.AnarchyUtils.Helpers.ConfigurationUtils;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SendLeaveMessageEvent implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");

        for(Player player : Bukkit.getOnlinePlayers()){
            if (!ToggleJoinMessagesCommand.muted.contains(player)){
                PlayerUtils.sendRawMessage(player, ConfigurationUtils.getConfig().getString("join-messages.leave").replaceAll("<player>", event.getPlayer().getName()));
            }
        }
    }
}
