package me.rainnny;

import lombok.Getter;
import me.rainnny.command.AlertsCommand;
import me.rainnny.detection.DetectionManager;
import me.rainnny.user.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getOnlinePlayers;

@Getter
public class AntiCheatBase extends JavaPlugin {
    public static AntiCheatBase INSTANCE;

    private UserManager userManager;
    private DetectionManager detectionManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        System.out.println("Setting up user manager...");
        userManager = new UserManager();
        if (getOnlinePlayers().size() > 0)
            userManager.addAll();

        System.out.println("Setting up detection manager...");
        detectionManager = new DetectionManager();

        System.out.println("Adding commands...");
        getCommand("alerts").setExecutor(new AlertsCommand());
    }
}