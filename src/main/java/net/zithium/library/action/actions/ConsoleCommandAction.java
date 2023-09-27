package net.zithium.library.action.actions;

import net.zithium.library.action.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ConsoleCommandAction implements Action {
    @Override
    public String getIdentifier() {
        return "CONSOLE";
    }

    @Override
    public void execute(Player player, String data) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), data);
    }
}
