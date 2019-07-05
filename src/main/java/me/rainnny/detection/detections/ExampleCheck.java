package me.rainnny.detection.detections;

import me.rainnny.detection.CheckType;
import me.rainnny.detection.Detection;
import me.rainnny.user.CheatUser;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class ExampleCheck extends Detection {
    public ExampleCheck(String name, CheckType checkType, boolean experimental) {
        super(name, checkType, experimental);
    }

    @Override
    public void onCombat(CheatUser user, Entity entity) {
        user.getPlayer().sendMessage("You got in a fight");
        if (user.vl.getOrDefault(this, 1) >= 3) {
            ban(user);
            return;
        }
        flag(user);
    }

    @Override
    public void onMove(CheatUser user, Location to, Location from) {
        user.getPlayer().sendMessage("you moved!");
        flag(user);
    }
}