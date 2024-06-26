package net.moonbyte.AnarchyUtils.Events;

import net.moonbyte.AnarchyUtils.Config.StaticConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class SetLocationAndTpLocationsEvent implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        StaticConfig.locations.remove(event.getPlayer().getName());
        StaticConfig.locations.remove(event.getPlayer().getName());
    }

    @EventHandler
    public void onDeath(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        StaticConfig.tplocations.add(player.getName());
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        StaticConfig.tplocations.add(player.getName());
    }
}
