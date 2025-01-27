package dev._2lstudios.advancedauth.bukkit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import dev._2lstudios.advancedauth.bukkit.AdvancedAuth;

public class AsyncPlayerPreLoginListener implements Listener {
    private final AdvancedAuth plugin;
    
    public AsyncPlayerPreLoginListener(final AdvancedAuth plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncPlayerPreLogin (final AsyncPlayerPreLoginEvent e) {
        if (this.plugin.getMainConfig().getBoolean("settings.prevent-logged-from-another-location")) {
            for (final Player player : this.plugin.getServer().getOnlinePlayers()) {
                if (player.getName().equalsIgnoreCase(e.getName())) {
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "Already connected to this server");
                }
            }
        }
    }
}
