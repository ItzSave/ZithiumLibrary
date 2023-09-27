package net.zithium.library.action.actions;

import net.zithium.library.action.Action;
import net.zithium.library.version.XSound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SoundAction implements Action {
    @Override
    public String getIdentifier() {
        return "SOUND";
    }

    @Override
    public void execute(Player player, String data) {
        try {
            player.playSound(player.getLocation(), XSound.matchXSound(data).get().parseSound(), 1L, 1L);
        } catch (Exception ex) {
            Bukkit.getLogger().log(Level.WARNING, "Invalid sound name in action: " + data.toLowerCase());
        }
    }
}
