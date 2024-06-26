package net.moonbyte.AnarchyUtils.Commands;

import net.moonbyte.AnarchyUtils.AnarchyUtils;
import net.moonbyte.AnarchyUtils.Helpers.ConfigUtil;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

public class WorldStatusCommand implements CommandExecutor {
    private float worldSize;
    private float netherSize;
    private float endSize;

    public WorldStatusCommand(){
        String serverPath = AnarchyUtils.getPlugin().getServer().getWorldContainer().getAbsolutePath().replace(".", "");

        Path worldPath = Paths.get(serverPath + "world");
        Path netherPath = Paths.get(serverPath + "world_nether");
        Path endPath = Paths.get(serverPath + "world_the_end");

        worldSize = getSizeInGB(worldPath);
        netherSize = getSizeInGB(netherPath);
        endSize = getSizeInGB(endPath);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){

            Player player = ((Player)commandSender).getPlayer();
            String createdOnDate = ConfigUtil.Global_WorldCreatedOnDate;

            PlayerUtils.sendMessage(player,"Server World Status");
            PlayerUtils.sendRawMessage(player, "");
            PlayerUtils.sendRawMessage(player, "This server was created on " + createdOnDate);
            PlayerUtils.sendRawMessage(player, "");
            PlayerUtils.sendRawMessage(player, "In that time period, here is how big the world file's are.");
            PlayerUtils.sendRawMessage(player, "Overworld : " + worldSize + "Gb's");
            PlayerUtils.sendRawMessage(player, "Nether : " + netherSize + "Gb's");
            PlayerUtils.sendRawMessage(player, "End : " + endSize + "Gb's");
            PlayerUtils.sendRawMessage(player, "");
            PlayerUtils.sendRawMessage(player, "Total amount of player's that joined this server : " + this.GetPlayerCount());
        }

        return true;
    }

    public static float getSizeInKB(Path path){
        return (float)size(path) / (float)1024;
    }

    public static float getSizeInMB(Path path){
        return getSizeInKB(path) / (float)1024;
    }

    public static float getSizeInGB(Path path){
        return getSizeInMB(path) / (float)1024;
    }

    public static long size(Path path) {
        final AtomicLong size = new AtomicLong(0);

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

                    size.addAndGet(attrs.size());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {

                    System.out.println("skipped: " + file + " (" + exc + ")");
                    // Skip folders that can't be traversed
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {

                    if (exc != null)
                        System.out.println("had trouble traversing: " + dir + " (" + exc + ")");
                    // Ignore errors traversing a folder
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new AssertionError("walkFileTree will not throw IOException if the FileVisitor does not");
        }

        return size.get();
    }

    private int GetPlayerCount() {
        return Bukkit.getOfflinePlayers().length;
    }
}
