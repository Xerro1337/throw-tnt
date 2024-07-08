package org.xerro.throwtnt;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    JavaPlugin plugin = Throw_TNT.plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if ( args.length == 0 || !args[0].equalsIgnoreCase( "reload" ) ) {

            sender.sendMessage( ChatColor.translateAlternateColorCodes( '&', "&f[&cThrow-TNT&f] &7Type &f/throwtnt reload &7to reload the config." ) );
            return true;

        } else {

            if ( sender.hasPermission( "throwtnt.reload") ) {

                plugin.reloadConfig();
                sender.sendMessage( ChatColor.translateAlternateColorCodes( '&', "&f[&cThrow-TNT&f] &7Configuration reloaded.") );
                return true;

            } else {

                sender.sendMessage(ChatColor.translateAlternateColorCodes( '&', "&f[&cThrow-TNT&f] &7You do not have permission for this.") );
                return true;

            }
        }

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        List<String> tab = new ArrayList<>();
        if ( args.length == 1 ) {
            tab.add( "reload" );
        }
        return tab;

    }
}
