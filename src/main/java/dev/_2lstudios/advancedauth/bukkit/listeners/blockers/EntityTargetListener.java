package dev._2lstudios.advancedauth.bukkit.listeners.blockers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetEvent;

import dev._2lstudios.advancedauth.bukkit.AdvancedAuth;

public class EntityTargetListener extends BlockerListener {
    public EntityTargetListener(AdvancedAuth plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityTarget(final EntityTargetEvent e) {
        if (e.getTarget() instanceof Player) {
            if (!this.isAllowed((Player) e.getTarget(), "deny-be-a-mob-target")) {
                e.setTarget(null);
                e.setCancelled(true);
            }
        }
    }
}
