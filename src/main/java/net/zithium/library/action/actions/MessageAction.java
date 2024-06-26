package net.zithium.library.action.actions;

import net.zithium.library.action.Action;
import net.zithium.library.utils.ColorUtil;
import org.bukkit.entity.Player;

public class MessageAction implements Action {
    @Override
    public String getIdentifier() {
        return "MESSAGE";
    }

    @Override
    public void execute(Player player, String message) {
        player.sendMessage(ColorUtil.color(message));
    }
}
