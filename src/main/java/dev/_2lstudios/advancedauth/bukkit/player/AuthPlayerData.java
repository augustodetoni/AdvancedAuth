package dev._2lstudios.advancedauth.bukkit.player;

import com.dotphin.milkshakeorm.entity.Entity;
import com.dotphin.milkshakeorm.entity.ID;
import com.dotphin.milkshakeorm.entity.Prop;

public class AuthPlayerData extends Entity {
    @ID
    public String _id;

    // Information.
    @Prop
    public String email;
    @Prop
    public String displayName;

    // Authentication.
    @Prop
    public String username;
    @Prop
    public String uuid;
    @Prop
    public String password;

    // Settings.
    @Prop
    public boolean enabledSession = false;

    // Extra.
    @Prop
    public String registrationIP;

    @Prop
    public long registrationDate;

    @Prop
    public String lastLoginIP;

    @Prop
    public long lastLoginDate;
}
