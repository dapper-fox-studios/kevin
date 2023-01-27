package gg.kineticmc.kevin.platform;

import co.schemati.trevor.api.data.User;

import java.util.UUID;

public class SpigotUser extends User {

    public SpigotUser(UUID uuid, String name, String address) {
        super(uuid, name, address);
    }

}
