package dev._2lstudios.advancedauth.bukkit.listeners.blockers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryInteractEvent;

import dev._2lstudios.advancedauth.bukkit.AdvancedAuth;

public class InventoryInteractListener extends BlockerListener {
    public InventoryInteractListener(AdvancedAuth plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInventoryInteract(final InventoryInteractEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            if (!this.isAllowed((Player) e.getWhoClicked(), "deny-inventory")) {
                e.setCancelled(true);
            }
        }
    }
}