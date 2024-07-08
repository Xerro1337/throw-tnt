package org.xerro.throwtnt;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventManager implements Listener {

    JavaPlugin plugin = Throw_TNT.plugin;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if ( player.hasPermission( "throwtnt.throw") && player.getInventory().getItemInMainHand().getType() == Material.TNT ) {

            boolean LeftClickEnabled = plugin.getConfig().getBoolean("Left-Click-Throw");
            boolean RightClickEnabled = plugin.getConfig().getBoolean("Right-Click-Throw");
            boolean LeftClicked = event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK;
            boolean RightClicked = event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK;

            if ((LeftClickEnabled && LeftClicked) || (RightClickEnabled && RightClicked)) {

                TNTPrimed tnt = event.getPlayer().getWorld().spawn(player.getEyeLocation(), TNTPrimed.class);
                tnt.setFuseTicks( plugin.getConfig().getInt("Fuse-Ticks") );
                tnt.setVelocity(player.getLocation().getDirection().multiply( plugin.getConfig().getDouble("Throw-Power") ));

                player.getInventory().getItemInMainHand().setAmount( player.getInventory().getItemInMainHand().getAmount() - 1 );
                event.setCancelled(true);

            }

        }

    }

    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {

        Player player = event.getPlayer();

        if ( plugin.getConfig().getBoolean("Action-Bar-Warning.Enable") ) {
            if ( player.getInventory().getItem(event.getNewSlot()) != null && player.getInventory().getItem(event.getNewSlot()).getType() == Material.TNT ) {
                player.spigot().sendMessage( ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Action-Bar-Warning.Text"))));
                // player.sendActionBar(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Action-Bar-Warning.Text")));
            }
        }

    }

}
