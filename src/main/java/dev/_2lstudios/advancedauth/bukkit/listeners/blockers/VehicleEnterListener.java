package dev._2lstudios.advancedauth.bukkit.listeners.blockers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.vehicle.VehicleEnterEvent;

import dev._2lstudios.advancedauth.bukkit.AdvancedAuth;

public class VehicleEnterListener extends BlockerListener {
    public VehicleEnterListener(AdvancedAuth plugin) {
        super(plugin);
    }

    @EventHandler
    public void onVehicleEnter(final VehicleEnterEvent e) {
        if (e.getEntered() instanceof Player) {
            if (!this.isAllowed((Player) e.getEntered(), "deny-vehicles")) {
                e.setCancelled(true);
            }
        }
    }
}
