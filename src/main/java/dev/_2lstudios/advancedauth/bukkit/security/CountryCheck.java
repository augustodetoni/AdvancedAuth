package dev._2lstudios.advancedauth.bukkit.security;

import java.net.InetSocketAddress;
import java.util.List;

import dev._2lstudios.advancedauth.bukkit.AdvancedAuth;
import dev._2lstudios.advancedauth.common.services.GeoIPService;
import dev._2lstudios.jelly.config.Configuration;

public class CountryCheck {
    private boolean enabled;
    private String mode;
    private List<String> list;

    public CountryCheck (final AdvancedAuth plugin) {
        Configuration config = plugin.getMainConfig();

        this.enabled = config.getBoolean("security.country-check.enabled", false);
        this.mode = config.getString("security.country-check.mode");
        this.list = config.getStringList("security.country-check.list");
    }

    public boolean canJoinAddress (final String address) {
        if (!this.enabled) {
            return true;
        }

        final String country = GeoIPService.getCountryCode(address);
        final boolean isInList = this.list.contains(country);

        if (this.mode.equalsIgnoreCase("blacklist") && isInList) {
            return false;
        } else if (this.mode.equalsIgnoreCase("whitelist") && !isInList) {
            return false;
        } else {
            return true;
        }
    }

    public boolean canJoinAddress(final InetSocketAddress address) {
        return this.canJoinAddress(address.toString());
    }
}
