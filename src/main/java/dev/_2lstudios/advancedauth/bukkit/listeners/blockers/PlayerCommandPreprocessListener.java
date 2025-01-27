package dev._2lstudios.advancedauth.bukkit.listeners.blockers;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import dev._2lstudios.advancedauth.bukkit.AdvancedAuth;
import dev._2lstudios.advancedauth.bukkit.player.AuthPlayer;

public class PlayerCommandPreprocessListener extends BlockerListener {
    public PlayerCommandPreprocessListener(AdvancedAuth plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent e) {
        final AuthPlayer player = (AuthPlayer) plugin.getPluginPlayerManager().getPlayer(e.getPlayer());

        final String command = e.getMessage().split(" ")[0];
        final List<String> allowedCommands = this.plugin.getMainConfig().getStringList("security.allowed-commands");

        if (!allowedCommands.contains(command) && !isAllowed(player, "deny-commands")) {
            player.sendI18nMessage("login.not-logged");
            e.setCancelled(true);
        }
    }
}
