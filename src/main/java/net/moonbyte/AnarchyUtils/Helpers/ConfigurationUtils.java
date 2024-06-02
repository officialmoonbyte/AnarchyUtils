package net.moonbyte.AnarchyUtils.Helpers;

import net.moonbyte.AnarchyUtils.AnarchyUtils;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationUtils {
    public static FileConfiguration getConfig() {
        return AnarchyUtils.getPlugin().getConfig();
    }
}
