package net.zithium.library.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.lang.reflect.Field;
import java.util.List;

public abstract class ZithiumCommand extends Command implements CommandExecutor, TabCompleter {
    private final String name;
    private List<String> aliases;
    private final List<ZithiumCommand> subCommands;

    public ZithiumCommand(String name) {
        super(name);
        this.name = name;
        this.aliases = new ArrayList<>();
        this.subCommands = new ArrayList<>();
    }

    @Override
    public @NotNull Command setAliases(@NotNull List<String> aliases) {
        super.setAliases(aliases); // Set aliases for the Command superclass
        this.aliases = aliases;
        return this;
    }

    public void registerSubCommand(ZithiumCommand subCommand) {
        subCommands.add(subCommand);
    }

    public List<ZithiumCommand> getSubCommands() {
        return subCommands;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return execute(sender, label, args);
    }

    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            String subCommandName = args[0];
            for (ZithiumCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(subCommandName) ||
                        subCommand.getAliases().contains(subCommandName)) {
                    return subCommand.execute(sender, label, sliceArgs(args));
                }
            }
        }
        return defaultCommand(sender, label, args);
    }

    private String[] sliceArgs(String[] args) {
        String[] slicedArgs = new String[args.length - 1];
        System.arraycopy(args, 1, slicedArgs, 0, args.length - 1);
        return slicedArgs;
    }

    public abstract boolean defaultCommand(CommandSender sender, String label, String[] args);

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            for (ZithiumCommand subCommand : subCommands) {
                if (subCommand.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    suggestions.add(subCommand.getName());
                }
                for (String subAlias : subCommand.getAliases()) {
                    if (subAlias.toLowerCase().startsWith(args[0].toLowerCase())) {
                        suggestions.add(subAlias);
                    }
                }
            }
        } else if (args.length > 1) {
            String subCommandName = args[0];
            for (ZithiumCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(subCommandName) ||
                        subCommand.getAliases().contains(subCommandName)) {
                    return subCommand.onTabComplete(sender, command, alias, sliceArgs(args));
                }
            }
        }
        return suggestions;
    }

    /**
     * Registers the command directly through the command map.
     */
    public void registerCommand() {
        try {
            // Reflectively get the CommandMap from the Bukkit server
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(getLabel(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
