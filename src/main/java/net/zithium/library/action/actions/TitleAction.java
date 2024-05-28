package net.zithium.library.action.actions;

import net.zithium.library.action.Action;
import net.zithium.library.utils.ColorUtil;
import net.zithium.library.version.Titles;
import net.zithium.library.version.XMaterial;
import org.bukkit.entity.Player;

/*
  Credit to https://github.com/ItsLewizzz/DeluxeHub/
 */


public class TitleAction implements Action {

    @Override
    public String getIdentifier() {
        return "TITLE";
    }

    @Override
    public void execute(Player player, String data) {
        String[] args = data.split(";");

        String mainTitle = ColorUtil.color(args[0]);
        String subTitle = ColorUtil.color(args[1]);

        int fadeIn;
        int stay;
        int fadeOut;
        try {
            fadeIn = Integer.parseInt(args[2]);
            stay = Integer.parseInt(args[3]);
            fadeOut = Integer.parseInt(args[4]);
        } catch (NumberFormatException ex) {
            fadeIn = 1;
            stay = 3;
            fadeOut = 1;
        }

        if (XMaterial.supports(10)) {
            player.sendTitle(mainTitle, subTitle, fadeIn * 20, stay * 20, fadeOut * 20);
        } else {
            Titles.sendTitle(player, fadeIn * 20, stay * 20, fadeOut * 20, mainTitle, subTitle);
        }
    }
}
