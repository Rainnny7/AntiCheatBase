package me.rainnny.detection;

import lombok.val;
import me.rainnny.AntiCheatBase;
import me.rainnny.detection.detections.ExampleCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class DetectionManager implements Listener {
    private List<Detection> detections;

    public DetectionManager() {
        detections = new ArrayList<>();
        getServer().getPluginManager().registerEvents(this, AntiCheatBase.INSTANCE);
        addChecks();
    }

    private void addChecks() {
        detections.add(new ExampleCheck("Example A", CheckType.OTHER, true));
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEvent(EntityDamageByEntityEvent event) {
        val damager = event.getDamager();
        val entity = event.getEntity();
        for (Detection detection : detections) {
            if (damager instanceof Player)
                detection.onCombat(AntiCheatBase.INSTANCE.getUserManager().get((Player) damager), entity);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEvent(PlayerMoveEvent event) {
        for (Detection detection : detections) {
            detection.onMove(AntiCheatBase.INSTANCE.getUserManager().get(event.getPlayer()), event.getTo(), event.getFrom());
        }
    }
}