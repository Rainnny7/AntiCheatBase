package me.rainnny.command;

import lombok.val;
import me.rainnny.AntiCheatBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command");
            return false;
        }
        val player = (Player) sender;
        val user = AntiCheatBase.INSTANCE.getUserManager().get(player);
        user.alerts = !user.alerts;
        player.sendMessage("§7Your alerts are now §r" + (user.alerts ? "§aon" : "§coff"));
        return true;
    }
}