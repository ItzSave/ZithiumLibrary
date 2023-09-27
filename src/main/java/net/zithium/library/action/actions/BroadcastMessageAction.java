package net.zithium.library.action.actions;

import net.zithium.library.action.Action;
import net.zithium.library.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastMessageAction implements Action {
    @Override
    public String getIdentifier() {
        return "BROADCAST";
    }

    @Override
    public void execute(Player player, String data) {
        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(Color.stringColor(data)));
    }
}
