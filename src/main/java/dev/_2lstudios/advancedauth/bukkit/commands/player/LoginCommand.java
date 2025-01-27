package dev._2lstudios.advancedauth.bukkit.commands.player;

import dev._2lstudios.advancedauth.bukkit.AdvancedAuth;
import dev._2lstudios.advancedauth.bukkit.Logging;
import dev._2lstudios.advancedauth.bukkit.player.AuthPlayer;
import dev._2lstudios.advancedauth.bukkit.player.LoginReason;
import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandListener;

@Command(name = "login", aliases = {"l"}, silent = true)
public class LoginCommand extends CommandListener {
    private final AdvancedAuth plugin;

    public LoginCommand(final AdvancedAuth plugin) {
        this.plugin = plugin;
    }

    @Override
    public void handle(final CommandContext ctx) throws Exception {
        final AuthPlayer player = (AuthPlayer) ctx.getPluginPlayer();
        final String password = ctx.getArguments().getString(0);

        if (password == null) {
            player.sendI18nMessage("login.usage");
            return;
        }

        if (!player.isFetched()) {
            player.sendI18nMessage("common.still-downloading");
            return;
        }

        if (!player.isRegistered()) {
            player.sendI18nMessage("register.not-registered");
            return;
        }

        if (player.isLogged() || player.isGuest()) {
            player.sendI18nMessage("login.already-logged");
            return;
        }

        if (player.comparePassword(password)) {
            player.login(LoginReason.PASSWORD);
            Logging.info(player.getName() + " has been logged in using a password.");
        } else {
            this.plugin.getFailLock().handleFail(player.getAddress());
            int tries = this.plugin.getFailLock().getTries(player.getAddress());
            int maxTries = this.plugin.getMainConfig().getInt("security.fail-lock.tries");

            String msg = player.getI18nString("login.wrong-password")
                .replace("{tries}", tries + "")
                .replace("{max-tries}", maxTries + "");

            if (this.plugin.getMainConfig().getBoolean("authentication.kick", false)) {
                player.sendMessage(msg);
            } else {
                player.kick(msg);
            }

            Logging.info(player.getName() + " password failed (" + tries + "/" + maxTries + ")");
        }
    }
}
