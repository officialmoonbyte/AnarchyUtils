package net.moonbyte.AnarchyUtils.Listeners;

import net.moonbyte.AnarchyUtils.AnarchyUtils;
import net.moonbyte.AnarchyUtils.Config.StaticConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class SpeedLimitListener
{
    public SpeedLimitListener(final AnarchyUtils plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                final double allowed = plugin.getConfig().getDouble("speed-limit.max-meters-per-second");
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("speedlimit.bypass")) {
                        continue;
                    }
                    if (StaticConfig.locations.get(p.getName()) != null && !StaticConfig.tplocations.contains(p.getName())) {
                        final Location prevloc = StaticConfig.locations.get(p.getName()).clone();
                        final Location newloc = p.getLocation().clone();
                        if (plugin.getConfig().getBoolean("speed-limit.only-flying") && !p.isFlying()) {
                            continue;
                        }
                        if (plugin.getConfig().getBoolean("speed-limit.only-on-ground") && p.isFlying()) {
                            continue;
                        }
                        if (!plugin.getConfig().getList("speed-limit.worlds").contains(prevloc.getWorld().getName()) && !plugin.getConfig().getList("speed-limit.worlds").contains(newloc.getWorld().getName())) {
                            continue;
                        }
                        final Vector v = newloc.subtract(prevloc).toVector();
                        if (plugin.getConfig().getBoolean("speed-limit.allow-falling-bypass") && v.clone().normalize().getY() < -0.95) {
                            StaticConfig.locations.remove(p.getName());
                            continue;
                        }
                        final double distance = v.length();
                        if (distance > allowed) {
                            if (p.isInsideVehicle()) {
                                final Entity vehicle = p.getVehicle();
                                p.leaveVehicle();
                                final Location entityLoc = prevloc.clone().add(0.0, 0.5, 0.0);
                                vehicle.teleport(entityLoc);
                                if (plugin.getConfig().getBoolean("speed-limit.put-back-on-vehicle")) {
                                    vehicle.addPassenger((Entity)p);
                                }
                                else {
                                    p.teleport(prevloc);
                                }
                            }
                            else {
                                p.teleport(prevloc);
                            }
                            if (plugin.getConfig().get("speed-limit.too-fast-message") != null && !plugin.getConfig().getString("speed-limit.too-fast-message").equals("")) {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("speed-limit.too-fast-message")));
                                continue;
                            }
                            continue;
                        }
                    }
                    StaticConfig.locations.put(p.getName(), p.getLocation().clone());
                    StaticConfig.tplocations.remove(p.getName());
                }
            }
        }, 0L, 20L);
    }
}
