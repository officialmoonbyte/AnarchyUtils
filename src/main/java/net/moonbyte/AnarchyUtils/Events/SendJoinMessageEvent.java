package net.moonbyte.AnarchyUtils.Events;

import net.moonbyte.AnarchyUtils.Commands.ToggleJoinMessagesCommand;
import net.moonbyte.AnarchyUtils.Helpers.ConfigUtil;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class SendJoinMessageEvent implements Listener {
    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {
        event.setJoinMessage("");

        for(Player player : Bukkit.getOnlinePlayers()){
            if (!ToggleJoinMessagesCommand.muted.contains(player)){
                assert ConfigUtil.JoinMessages_Join != null;
                PlayerUtils.sendRawMessage(player, ConfigUtil.JoinMessages_Join.replaceAll("<player>", event.getPlayer().getName()));
            }
        }
    }
}
