package net.zithium.library.action.actions;

import net.zithium.library.action.Action;
import org.bukkit.entity.Player;

public class CommandAction implements Action {
    @Override
    public String getIdentifier() {
        return "COMMAND";
    }

    @Override
    public void execute(Player player, String playerCommand) {
        player.chat(playerCommand.contains("/") ? playerCommand : "/" + playerCommand);
    }
}
