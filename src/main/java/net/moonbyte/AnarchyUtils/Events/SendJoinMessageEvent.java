package net.moonbyte.AnarchyUtils.Events;

import net.moonbyte.AnarchyUtils.Commands.ToggleJoinMessagesCommand;
import net.moonbyte.AnarchyUtils.Helpers.ConfigurationUtils;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SendJoinMessageEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");

        for(Player player : Bukkit.getOnlinePlayers()){
            if (!ToggleJoinMessagesCommand.muted.contains(player)){
                PlayerUtils.sendRawMessage(player, ConfigurationUtils.getConfig().getString("join-messages.join").replaceAll("<player>", event.getPlayer().getName()));
            }
        }
    }
}
