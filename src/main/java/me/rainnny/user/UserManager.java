package me.rainnny.user;

import lombok.Getter;
import me.rainnny.AntiCheatBase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getPluginManager;

@Getter
public class UserManager implements Listener {
    private HashMap<UUID, CheatUser> users;

    public UserManager() {
        users = new HashMap<>();
        getPluginManager().registerEvents(this, AntiCheatBase.INSTANCE);
    }

    public void add(Player player) {
        users.put(player.getUniqueId(), new CheatUser(player.getUniqueId()));
    }

    public void addAll() {
        getOnlinePlayers().forEach(this::add);
    }

    public CheatUser get(Player player) {
        return users.getOrDefault(player.getUniqueId(), new CheatUser(player.getUniqueId()));
    }

    public void remove(Player player) {
        users.remove(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onEvent(PlayerJoinEvent event) {
        add(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onEvent(PlayerQuitEvent event) {
        remove(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onEvent(PlayerKickEvent event) {
        remove(event.getPlayer());
    }
}