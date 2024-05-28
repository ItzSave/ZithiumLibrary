package net.zithium.library.action.actions;

import net.kyori.adventure.text.Component;
import net.zithium.library.action.Action;
import net.zithium.library.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastMessageAction implements Action {
    @Override
    public String getIdentifier() {
        return "BROADCAST";
    }

    /**
     * @param player  The player who is executing the action
     * @param message The message broadcast.
     */
    @Override
    public void execute(Player player, String message) {
        Bukkit.broadcast(Component.text(ColorUtil.color(message)));
    }
}
