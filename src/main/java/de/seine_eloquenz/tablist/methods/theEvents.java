package de.seine_eloquenz.tablist.methods;

import de.seine_eloquenz.tablist.tablist;
import de.seine_eloquenz.tablist.events.playerJoin;
import de.seine_eloquenz.tablist.events.playerLeave;
import org.bukkit.Bukkit;

public class theEvents {
    public tablist plugin;

    public theEvents(tablist plugin) {
        this.plugin = plugin;
    }

    public static void register(tablist plugin) {
        Bukkit.getPluginManager().registerEvents(new playerJoin(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new playerLeave(plugin), plugin);
    }
}
