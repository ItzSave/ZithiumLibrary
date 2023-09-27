package net.zithium.library.action;

import org.bukkit.entity.Player;

public interface Action {

    String getIdentifier();

    void execute(Player player, String data);

}
