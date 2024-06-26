package net.moonbyte.AnarchyUtils.Events;

import net.moonbyte.AnarchyUtils.Helpers.ConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ColoredSignsEvent implements Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onSignChange(final SignChangeEvent e) {
        final Player player = e.getPlayer();
        final String[] lines = e.getLines();

        for (int i = 0; i < lines.length; ++i) {
            final String line = lines[i];
            final String color = this.getFormattedSignString(player, line);
            e.setLine(i, color);
        }
    }

    private String getFormattedSignString(final Player player, final String line) {
        final boolean permissions = ConfigUtil.Options_UsePermission;
        if (!permissions) {
            return this.replaceAll(line);
        }
        if (player.hasPermission("signs.all") || (player.hasPermission("signs.color.all") && player.hasPermission("signs.format.all"))) {
            return this.replaceAll(line);
        }
        String finalLine = line;
        boolean ignoreColorCheck = false;
        boolean ignoreFormatCheck = false;
        if (player.hasPermission("signs.color.all")) {
            finalLine = this.replaceColorsOnly(line);
            ignoreColorCheck = true;
        }
        if (player.hasPermission("signs.format.all")) {
            finalLine = this.replaceFormattingOnly(line);
            ignoreFormatCheck = true;
        }
        if (!ignoreColorCheck) {
            finalLine = this.runColorCheck(player, finalLine);
        }
        if (!ignoreFormatCheck) {
            finalLine = this.runFormatCheck(player, finalLine);
        }
        return finalLine;
    }

    private String runColorCheck(final Player player, String line) {
        final List<String> validColors = Arrays.asList("a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        for (final String code : validColors) {
            final String permission = "signs.color." + code;
            final String caps = code.toUpperCase();
            if (player.hasPermission(permission)) {
                line = this.replaceSpecific(line, caps + code);
            }
        }
        return line;
    }

    private String runFormatCheck(final Player player, String line) {
        final List<String> validFormats = Arrays.asList("k", "l", "m", "n", "o", "r");
        for (final String code : validFormats) {
            final String permission = "signs.format." + code;
            final String caps = code.toUpperCase();
            if (player.hasPermission(permission)) {
                line = this.replaceSpecific(line, caps + code);
            }
        }
        return line;
    }

    @Contract("_ -> new")
    private @NotNull String replaceColorsOnly(final String line) {
        return this.replaceSpecific(line, "0123456789AaBbCcDdEeFf");
    }

    @Contract("_ -> new")
    private @NotNull String replaceFormattingOnly(final String line) {
        return this.replaceSpecific(line, "KkLlMmNnOoRr");
    }

    private @NotNull String replaceAll(final String line) {
        assert ConfigUtil.Signs_ColorCharactor != null;
        final char character = ConfigUtil.Signs_ColorCharactor.charAt(0);
        return ChatColor.translateAlternateColorCodes(character, line);
    }

    @Contract("_, _ -> new")
    private @NotNull String replaceSpecific(final @NotNull String line, final String specific) {
        final char character = ConfigUtil.Signs_ColorCharactor.charAt(0);
        final char[] charArray = line.toCharArray();
        for (int i = 0; i < charArray.length - 1; ++i) {
            final boolean hasColor1 = charArray[i] == character;
            final boolean hasColor2 = specific.indexOf(charArray[i + 1]) > -1;
            if (hasColor1 && hasColor2) {
                charArray[i] = '§';
                charArray[i + 1] = Character.toLowerCase(charArray[i + 1]);
            }
        }
        return new String(charArray);
    }
}