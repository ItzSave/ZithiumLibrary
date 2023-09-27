package net.zithium.library.action;

import net.zithium.library.action.actions.*;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ActionManager class manages and executes various actions in the Minecraft game.
 * Actions can include sending messages, broadcasting messages, running commands, and more.
 */
@SuppressWarnings("unused")
public class ActionManager {

    // A mapping of action identifiers to Action objects
    private Map<String, Action> actions;

    /**
     * Initializes the ActionManager and registers predefined actions.
     * This method is typically called when the plugin is enabled.
     * <p>
     * Example: actionManager.onEnable();
     */
    public void onEnable() {
        actions = new HashMap<>();

        // Register predefined actions
        registerAction(
                new MessageAction(),
                new BroadcastMessageAction(),
                new CommandAction(),
                new ConsoleCommandAction(),
                new SoundAction()
        );
    }

    /**
     * Registers one or more Action objects.
     *
     * @param actions One or more Action objects to be registered.
     */
    public void registerAction(Action... actions) {
        Arrays.asList(actions).forEach(action -> this.actions.put(action.getIdentifier(), action));
    }

    /**
     * Executes a list of actions for a player.
     *
     * @param player The player for whom the actions should be executed.
     * @param items  A list of strings representing actions to be executed.
     */
    public void executeActions(Player player, List<String> items) {
        for (String item : items) {
            executeAction(player, item);
        }
    }

    /**
     * Executes a single action for a player.
     *
     * @param player The player for whom the action should be executed.
     * @param item   The string representing the action to be executed.
     */
    public void executeAction(Player player, String item) {
        String actionName = StringUtils.substringBetween(item, "[", "]");
        if (actionName != null) {
            actionName = actionName.toUpperCase();
            Action action = actionName.isEmpty() ? null : actions.get(actionName);

            if (action != null) {
                item = item.contains(" ") ? item.split(" ", 2)[1] : "";
                if (player != null) {
                    item = item.replace("{PLAYER}", player.getName());
                }

                // Execute the action
                action.execute(player, item);
            }
        }
    }
}
