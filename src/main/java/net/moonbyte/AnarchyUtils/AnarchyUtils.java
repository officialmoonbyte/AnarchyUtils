package net.moonbyte.AnarchyUtils;

import net.moonbyte.AnarchyUtils.Commands.*;
import net.moonbyte.AnarchyUtils.Config.StaticConfig;
import net.moonbyte.AnarchyUtils.Dupes.PiglinDupe;
import net.moonbyte.AnarchyUtils.Events.*;
import net.moonbyte.AnarchyUtils.Helpers.ConfigurationUtils;
import net.moonbyte.AnarchyUtils.Listeners.SpeedLimitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AnarchyUtils extends JavaPlugin {
    private Logger log;

    @Override
    public void onEnable() {
        log = Bukkit.getLogger();
        log.info("Anarchy Utils is initializing!");

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceHolderAPI")) {
            StaticConfig.usingPAPI = true;
        }

        log.info("Anarchy Utils is loading...");

        this.saveDefaultConfig();

        registerCommands();
        registerEvents();
        registerListeners();

        // This was used during the 1.16 update to allow access to the patched paper dupe.
        // This is now disabled on moonbyte and will remain disabled. Any future dupes
        // Should be added here.
        if (ConfigurationUtils.getConfig().getBoolean("dupes.donkey-dupe-enabled")) {
            Bukkit.getServer().getPluginManager().registerEvents(new PiglinDupe(), this);
            log.info("Anarchy Utils has the Minecart dupe enabled!");
        }

        String anarchyUtilsLogo = "                                                                  \n" +
                "    ___                          __             __  ____  _ __    \n" +
                "   /   |  ____  ____ ___________/ /_  __  __   / / / / /_(_) /____\n" +
                "  / /| | / __ \\/ __ `/ ___/ ___/ __ \\/ / / /  / / / / __/ / / ___/\n" +
                " / ___ |/ / / / /_/ / /  / /__/ / / / /_/ /  / /_/ / /_/ / (__  ) \n" +
                "/_/  |_/_/ /_/\\__,_/_/   \\___/_/ /_/\\__, /   \\____/\\__/_/_/____/  \n" +
                "                                   /____/                         \n" +
                "                       Made by moonbyte.us                        \n" +
                "                                                                  ";
        log.info(anarchyUtilsLogo);
    }

    @Override
    public void onDisable() {
        log.info("Disabled Anarchy Utils!");
    }

    public static AnarchyUtils getPlugin() {
        return getPlugin(AnarchyUtils.class);
    }

    private void registerCommands() {
        this.getCommand("nc").setExecutor(new ChangeNameColorCommand());
        this.getCommand("discord").setExecutor(new DiscordCommand());
        this.getCommand("help").setExecutor(new HelpCommand());
        this.getCommand("kill").setExecutor(new KillCommand());
        this.getCommand("togglejoinmsg").setExecutor(new ToggleJoinMessagesCommand());
        this.getCommand("wiki").setExecutor(new WikiCommand());
        this.getCommand("worldstatus").setExecutor(new WorldStatusCommand());
    }

    private void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new CancelCommandsEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ColoredSignsEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SendJoinMessageEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SendLeaveMessageEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SendWelcomeMessageEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SetLocationAndTpLocationsEvent(), this);
    }

    private void registerListeners() {
        new SpeedLimitListener(this);
    }
}
