package net.zithium.library.action.actions;

import com.cryptomorin.xseries.XSound;
import net.zithium.library.action.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SoundAction implements Action {
    @Override
    public String getIdentifier() {
        return "SOUND";
    }

    @Override
    public void execute(Player player, String sound) {
        try {
            player.playSound(player.getLocation(), XSound.matchXSound(sound).get().parseSound(), 1L, 1L);
        } catch (Exception ex) {
            Bukkit.getLogger().log(Level.WARNING, "Invalid sound name in action: " + sound.toLowerCase());
        }

    }
}
