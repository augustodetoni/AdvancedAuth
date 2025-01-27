package dev._2lstudios.advancedauth.bukkit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev._2lstudios.advancedauth.bukkit.AdvancedAuth;
import dev._2lstudios.advancedauth.bukkit.player.AuthPlayer;

public class PlayerJoinListener implements Listener {
    
    private final AdvancedAuth plugin;

    public PlayerJoinListener (final AdvancedAuth plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerJoin (final PlayerJoinEvent e) {
        final AuthPlayer player = (AuthPlayer) this.plugin.getPluginPlayerManager().getPlayer(e.getPlayer());

        // Prevent movement
        player.updateEffects();

        // Hide other players
        for (final Player bukkitPlayer : Bukkit.getOnlinePlayers()) {
            AuthPlayer authPlayer = (AuthPlayer) this.plugin.getPluginPlayerManager().getPlayer(bukkitPlayer);
            if (authPlayer.isHidden()) {
                player.hideOther(bukkitPlayer);
            }
        }

        // Teleport spawn
        if (this.plugin.getMainConfig().getBoolean("settings.teleport-spawn.enabled")) {
            final Location location = this.plugin.getMainConfig().getLocation("settings.teleport-spawn.location", true);
            e.getPlayer().teleport(location);
        }

        // Disable join message
        if (this.plugin.getMainConfig().getBoolean("settings.disable-join-quit-message")) {
            e.setJoinMessage(null);
        }
    }
}
