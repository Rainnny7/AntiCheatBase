package me.rainnny.user;

import me.rainnny.detection.Detection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CheatUser {
    private UUID identifier;

    public boolean alerts = true;

    public HashMap<Detection, Integer> vl;

    public CheatUser(UUID uuid) {
        identifier = uuid;
        vl = new HashMap<>();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(identifier);
    }
}