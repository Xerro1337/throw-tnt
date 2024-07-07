package org.xerro.throwtnt;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Throw_TNT extends JavaPlugin {

    public static Throw_TNT plugin;

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EventManager(), this);

        getCommand("throwtnt").setExecutor( new CommandManager() );

        Bukkit.getConsoleSender().sendMessage( "Throw-TNT " + plugin.getDescription().getVersion() + " is enabled." );

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage( "Throw-TNT " + plugin.getDescription().getVersion() + " is disabled." );
    }
}
