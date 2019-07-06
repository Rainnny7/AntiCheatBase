package me.rainnny.detection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import lombok.var;
import me.rainnny.AntiCheatBase;
import me.rainnny.user.CheatUser;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getOnlinePlayers;

@AllArgsConstructor @Getter
public abstract class Detection {
    private String name;
    private CheckType checkType;
    private boolean experimental;

    public abstract void onCombat(CheatUser user, Entity entity);
    public abstract void onMove(CheatUser user, Location to, Location from);

    protected void flag(CheatUser user) {
        getOnlinePlayers().stream().filter(staff -> staff.hasPermission("anticheat.alerts")).forEach(staff -> {
            val n = name.split(" ");
            val vl = user.vl.getOrDefault(this, 1);

            if (AntiCheatBase.INSTANCE.getUserManager().get(staff).alerts)
                staff.sendMessage("§8[§c!§8] §f" + user.getPlayer().getName() + " §7is suspected of " + (experimental ? "§7§o*" : "§e") + n[0] + " §7(Type " + n[1] + ") §cx" + vl);

            user.vl.put(this, vl+1);
        });
    }

    protected void ban(CheatUser user) {
        ban(user, null);
    }

    protected void ban(CheatUser user, String reason) {
        if (experimental)
            return;
        user.getPlayer().kickPlayer("[ANTICHEAT] " + (reason == null ? "Unfair Advantage" : reason));
    }
}