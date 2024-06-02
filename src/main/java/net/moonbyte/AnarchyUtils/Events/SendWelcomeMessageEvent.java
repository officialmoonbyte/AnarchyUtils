package net.moonbyte.AnarchyUtils.Events;

import net.moonbyte.AnarchyUtils.Helpers.ConfigurationUtils;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SendWelcomeMessageEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()){
            PlayerUtils.sendMessage(event.getPlayer(), ConfigurationUtils.getConfig().getString("welcome-message.message").replaceAll("<player>", event.getPlayer().getName()));
        }
    }
}
