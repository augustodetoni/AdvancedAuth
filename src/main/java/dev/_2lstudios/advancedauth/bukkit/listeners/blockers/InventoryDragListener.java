package dev._2lstudios.advancedauth.bukkit.listeners.blockers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryDragEvent;

import dev._2lstudios.advancedauth.bukkit.AdvancedAuth;

public class InventoryDragListener extends BlockerListener {
    public InventoryDragListener(AdvancedAuth plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            if (!this.isAllowed((Player) e.getWhoClicked(), "deny-inventory")) {
                e.setCancelled(true);
            }
        }
    }
}