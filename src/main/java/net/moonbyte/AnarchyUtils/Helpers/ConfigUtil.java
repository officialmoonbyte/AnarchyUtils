package net.moonbyte.AnarchyUtils.Helpers;

import net.moonbyte.AnarchyUtils.AnarchyUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class ConfigUtil {
    private static @NotNull FileConfiguration getConfig() {
        return AnarchyUtils.getPlugin().getConfig();
    }

    public static final String NoPermissionMessageDonation = getConfig().getString("global.no-permission-message-donate");
    public static final String NcCommand_UsageFinal = getConfig().getString("nc-command.usage-message");
    public static final String NcCommand_ResetMessage = getConfig().getString("nc-command.reset-message");
    public static final String Links_DiscordMessage = getConfig().getString("links.discord-message");
    public static final String Links_Discord = getConfig().getString("links.discord");
    public static final String Global_HelpLines = getConfig().getString("global.help-lines");
    public static final String JoinMessages_Unmuted = getConfig().getString("join-messages.unmuted");
    public static final String JoinMessages_Muted = getConfig().getString("join-messages.muted");
    public static final String Links_WikiURLMessage = getConfig().getString("links.wiki-url-message");
    public static final String Links_WikiURL = getConfig().getString("links.wiki-url");
    public static final String Global_WorldCreatedOnDate = getConfig().getString("global.world-created-on-date");
    public static final int Dupes_PiglinDropRate = getConfig().getInt("dupes.piglin-drop-rate");
    public static final boolean Options_UsePermission = getConfig().getBoolean("options.use permissions");
    public static final String Signs_ColorCharactor = getConfig().getString("signs.color-character");
    public static final String JoinMessages_Join = getConfig().getString("join-messages.join");
    public static final String JoinMessages_Leave = getConfig().getString("join-messages.leave");
    public static final String WelcomeMessage_Message = getConfig().getString("welcome-message.message");
    public static final boolean Dupes_DonkeyDupeEnabled = getConfig().getBoolean("dupes.donkey-dupe-enabled");
    public static final String Global_Prefix = getConfig().getString("global.prefix");
}
