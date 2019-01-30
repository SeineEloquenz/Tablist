package de.seine_eloquenz.tablist.methods;

import de.seine_eloquenz.tablist.tablist;
import de.seine_eloquenz.tablist.commands.cmd_tablist;

public class theCommands {
    public tablist plugin;

    public theCommands(tablist plugin) {
        this.plugin = plugin;
    }

    public static void register(tablist plugin) {
        plugin.getCommand("tablist").setExecutor(new cmd_tablist(plugin));
    }
}
