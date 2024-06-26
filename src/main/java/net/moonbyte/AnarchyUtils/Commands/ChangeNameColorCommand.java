package net.moonbyte.AnarchyUtils.Commands;

import net.moonbyte.AnarchyUtils.Helpers.ConfigurationUtils;
import net.moonbyte.AnarchyUtils.Helpers.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ChangeNameColorCommand implements CommandExecutor {
    public static HashMap<Player, String> customname = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player)commandSender;

        if (!p.hasPermission("Moonbyte.donator")){
            PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("global.no-permission-message-donate"));
            return true;
        }

        char nameColor = 0;
        char nameStyle = 0;

        if(strings.length == 0){
            PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.usage-message"));
        }else{
            String color = strings[0];
            String style = null;

            if (strings.length == 2){
                style = strings[1];
            }

            // here goes the monkey code
            if(color.equalsIgnoreCase("black")){
                nameColor = ChatColor.BLACK.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "black"));
            }else if(color.equalsIgnoreCase("darkblue")){
                nameColor = ChatColor.DARK_BLUE.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "darkblue"));
            }else if(color.equalsIgnoreCase("darkgreen")){
                nameColor = ChatColor.DARK_GREEN.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "darkgreen"));
            }else if(color.equalsIgnoreCase("darkaqua")){
                nameColor = ChatColor.DARK_AQUA.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "darkaqua"));
            }else if(color.equalsIgnoreCase("red")){
                nameColor = ChatColor.RED.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "red"));
            }else if(color.equalsIgnoreCase("darkpurple")){
                nameColor = ChatColor.DARK_PURPLE.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "darkpurple"));
            }else if(color.equalsIgnoreCase("gold")){
                nameColor = ChatColor.GOLD.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "gold"));
            }else if(color.equalsIgnoreCase("gray")){
                nameColor = ChatColor.GRAY.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "gray"));
            }else if(color.equalsIgnoreCase("darkgray")){
                nameColor = ChatColor.DARK_GRAY.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "darkgray"));
            }else if(color.equalsIgnoreCase("blue")){
                nameColor = ChatColor.BLUE.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "blue"));
            }else if(color.equalsIgnoreCase("green")){
                nameColor = ChatColor.GREEN.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "green"));
            }else if(color.equalsIgnoreCase("aqua")){
                nameColor = ChatColor.AQUA.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "aqua"));
            }else if(color.equalsIgnoreCase("lightpurple")){
                nameColor = ChatColor.LIGHT_PURPLE.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "lightpurple"));
            }else if(color.equalsIgnoreCase("yellow")){
                nameColor = ChatColor.YELLOW.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "yellow"));
            }else if(color.equalsIgnoreCase("white")){
                nameColor = ChatColor.WHITE.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "white"));
            }else if(color.equalsIgnoreCase("darkred")){
                nameColor = ChatColor.DARK_RED.getChar();
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "white"));
            }else if(color.equalsIgnoreCase("reset")) {
                nameColor = 0;
                PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.reset-message"));
            }
            else{
                nameColor = 0;
            }

            if (style != null){
                //Check for the style
                if(style.equalsIgnoreCase("bold")){
                    nameStyle = ChatColor.BOLD.getChar();
                    PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "bold"));
                }else if(style.equalsIgnoreCase("magic")){
                    nameStyle = ChatColor.MAGIC.getChar();
                    PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "magic"));
                }else if(style.equalsIgnoreCase("underline")){
                    nameStyle = ChatColor.UNDERLINE.getChar();
                    PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "underline"));
                }else if(style.equalsIgnoreCase("strike")){
                    nameStyle = ChatColor.STRIKETHROUGH.getChar();
                    PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "strike"));
                }else if(style.equalsIgnoreCase("italic")){
                    nameStyle = ChatColor.ITALIC.getChar();
                    PlayerUtils.sendMessage(p, ConfigurationUtils.getConfig().getString("nc-command.success-message").replaceAll("<color>", "italic"));
                }else{
                    nameStyle = 0;
                }
            }
        }

        setPlayerColor(p, nameColor, nameStyle);

        return true;
    }

    private void setPlayerColor(Player p, char nameColor, char nameStyle){
        if (nameColor != 0 && nameStyle != 0){
            String playerName = ChatColor.getByChar(nameStyle) + p.getName() + ChatColor.RESET;
            p.setDisplayName(ChatColor.getByChar(nameStyle) + p.getName() + ChatColor.RESET);
            p.setPlayerListName(ChatColor.getByChar(nameStyle) + p.getName() + ChatColor.RESET);

            p.setDisplayName(ChatColor.getByChar(nameColor) + p.getDisplayName() + ChatColor.RESET);
            p.setPlayerListName(ChatColor.getByChar(nameColor) + p.getDisplayName() + ChatColor.RESET);
        }
        else if (nameColor != 0) {
            p.setDisplayName(ChatColor.getByChar(nameColor) + p.getName() + ChatColor.RESET);
            p.setPlayerListName(ChatColor.getByChar(nameColor) + p.getName() + ChatColor.RESET);
        }
        else if (nameColor != 0){
            p.setDisplayName(ChatColor.getByChar(nameStyle) + p.getName() + ChatColor.RESET);
            p.setPlayerListName(ChatColor.getByChar(nameStyle) + p.getName() + ChatColor.RESET);
        }
        else{
            p.setDisplayName(p.getName());
            p.setDisplayName(p.getName());
        }
    }
}
