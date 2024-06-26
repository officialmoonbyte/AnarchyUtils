package net.moonbyte.AnarchyUtils.Listeners;

import net.moonbyte.AnarchyUtils.AnarchyUtils;
import net.moonbyte.AnarchyUtils.Config.StaticConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;

public class SpeedLimitListener
{
    public SpeedLimitListener(final AnarchyUtils plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            final double SpeedLimit_MaxMetersPerSecond = plugin.getConfig().getDouble("speed-limit.max-meters-per-second");
            final boolean SpeedLimit_OnlyFlying = plugin.getConfig().getBoolean("speed-limit.only-flying");
            final boolean SpeedLimit_OnlyOnGround = plugin.getConfig().getBoolean("speed-limit.only-on-ground");
            final List<?> List_SpeedLimit_AllowFallingBypass = plugin.getConfig().getList("speed-limit.worlds");
            final boolean Bool_SpeedLimit_AllowFallingBypass = plugin.getConfig().getBoolean("speed-limit.allow-falling-bypass");
            final boolean SpeedLimit_PutBackOnVehicle = plugin.getConfig().getBoolean("speed-limit.put-back-on-vehicle");
            final Object Obj_SpeedLimit_TooFastMessage = plugin.getConfig().get("speed-limit.too-fast-message");
            final String Str_SpeedLimit_TooFastMessage = plugin.getConfig().getString("speed-limit.too-fast-message");

            for (final Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("speedlimit.bypass")) {
                    continue;
                }
                if (StaticConfig.locations.get(p.getName()) != null && !StaticConfig.tplocations.contains(p.getName())) {
                    final Location prevloc = StaticConfig.locations.get(p.getName()).clone();
                    final Location newloc = p.getLocation().clone();
                    if (SpeedLimit_OnlyFlying && !p.isFlying()) {
                        continue;
                    }
                    if (SpeedLimit_OnlyOnGround && p.isFlying()) {
                        continue;
                    }
                    if (!Objects.requireNonNull(List_SpeedLimit_AllowFallingBypass).contains(prevloc.getWorld().getName()) && !Objects.requireNonNull(plugin.getConfig().getList("speed-limit.worlds")).contains(newloc.getWorld().getName())) {
                        continue;
                    }
                    final Vector v = newloc.subtract(prevloc).toVector();
                    if (Bool_SpeedLimit_AllowFallingBypass && v.clone().normalize().getY() < -0.95) {
                        StaticConfig.locations.remove(p.getName());
                        continue;
                    }
                    final double distance = v.length();
                    if (distance > SpeedLimit_MaxMetersPerSecond) {
                        if (p.isInsideVehicle()) {
                            final Entity vehicle = p.getVehicle();
                            p.leaveVehicle();
                            final Location entityLoc = prevloc.clone().add(0.0, 0.5, 0.0);
                            assert vehicle != null;
                            vehicle.teleport(entityLoc);
                            if (SpeedLimit_PutBackOnVehicle) {
                                vehicle.addPassenger(p);
                            }
                            else {
                                p.teleport(prevloc);
                            }
                        }
                        else {
                            p.teleport(prevloc);
                        }
                        if (Obj_SpeedLimit_TooFastMessage != null) {
                            assert Str_SpeedLimit_TooFastMessage != null;
                            if (!Str_SpeedLimit_TooFastMessage.isEmpty()) {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Str_SpeedLimit_TooFastMessage)));
                                continue;
                            }
                        }
                        continue;
                    }
                }
                StaticConfig.locations.put(p.getName(), p.getLocation().clone());
                StaticConfig.tplocations.remove(p.getName());
            }
        }, 0L, 20L);
    }
}
