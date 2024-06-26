package net.moonbyte.AnarchyUtils.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;

public class CancelCommandsEvent implements Listener {
    @EventHandler
    public void onCommandUse(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        final List<String> commands = Arrays.asList("pl", "bukkit:pl", "about", "bukkit:about", "version", "bukkit:version", "ver", "bukkit:ver", "plugins", "bukkit:plugins", "minecraft:pl", "minecraft:plugins", "minecraft:about", "minecraft:version", "minecaft:ver");
        commands.forEach(all -> {
            if (event.getMessage().toLowerCase().equalsIgnoreCase("/" + all.toLowerCase())) {
                event.setCancelled(true);
            }
        });
    }
}
