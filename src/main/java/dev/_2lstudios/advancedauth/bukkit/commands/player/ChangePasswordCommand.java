package dev._2lstudios.advancedauth.bukkit.commands.player;

import dev._2lstudios.advancedauth.bukkit.Logging;
import dev._2lstudios.advancedauth.bukkit.player.AuthPlayer;
import dev._2lstudios.advancedauth.bukkit.security.PasswordValidation;
import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandListener;

@Command(name = "changepassword", aliases = {"cpw"}, silent = true)
public class ChangePasswordCommand extends CommandListener {
    @Override
    public void handle(final CommandContext ctx) throws Exception {
        final AuthPlayer player = (AuthPlayer) ctx.getPluginPlayer();
        final String oldPassword = ctx.getArguments().getString(0);
        final String newPassword = ctx.getArguments().getString(1);

        if (!player.isLogged() || player.isGuest()) {
            player.sendI18nMessage("login.not-logged");
            return;
        }

        if (oldPassword == null || newPassword == null) {
            player.sendI18nMessage("changepassword.usage");
            return;
        }

        if (!player.comparePassword(oldPassword)) {
            player.sendI18nMessage("login.wrong-password");
            Logging.info(player.getName() + " has tried to change his password but failed to verify the old one.");
            return;
        }

        final String passwordValidation = PasswordValidation.validatePassword(newPassword);
        if (passwordValidation == null) {
            player.setPassword(newPassword);
            player.sendI18nMessage("changepassword.successfully");
            Logging.info(player.getName() + " has changed his password.");
        } else {
            player.sendI18nMessage("register." + passwordValidation);
        }
    }
}
