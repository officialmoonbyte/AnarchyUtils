package net.moonbyte.AnarchyUtils.Events;

import net.moonbyte.AnarchyUtils.Helpers.ConfigUtil;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SendWelcomeMessageEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()){
            assert ConfigUtil.WelcomeMessage_Message != null;
            PlayerUtils.sendMessage(event.getPlayer(), ConfigUtil.WelcomeMessage_Message.replaceAll("<player>", event.getPlayer().getName()));
        }
    }
}
