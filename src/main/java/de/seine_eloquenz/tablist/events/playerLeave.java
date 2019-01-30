package de.seine_eloquenz.tablist.events;

import de.seine_eloquenz.tablist.tablist;
import de.seine_eloquenz.tablist.vars;
import de.seine_eloquenz.tablist.methods.theTablist;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class playerLeave implements Listener {
    public tablist plugin;

    public playerLeave(tablist plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeave() {
        if (vars.customPrefix) {
            theTablist.setCustomPrefixes();
        }

    }
}
