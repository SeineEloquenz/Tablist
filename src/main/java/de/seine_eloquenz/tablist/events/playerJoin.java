package de.seine_eloquenz.tablist.events;

import de.seine_eloquenz.tablist.tablist;
import de.seine_eloquenz.tablist.vars;
import de.seine_eloquenz.tablist.methods.theTablist;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class playerJoin implements Listener {
    public tablist plugin;

    public playerJoin(tablist plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin() {
        int num = vars.auto_messagenumber;

        Player op;
        String header;
        String footer;
        for(Iterator var4 = Bukkit.getOnlinePlayers().iterator(); var4.hasNext(); theTablist.sendTablist(op, header, footer, true)) {
            op = (Player)var4.next();
            header = "";
            footer = "";
            if (vars.auto_HeaderMessages.size() >= num + 1) {
                header = vars.auto_HeaderMessages.get(num);
            } else if (vars.auto_HeaderMessages.get(0) != null) {
                header = vars.auto_HeaderMessages.get(0);
            }

            if (vars.auto_FooterMessages.size() >= num + 1) {
                footer = vars.auto_FooterMessages.get(num);
            } else if (vars.auto_FooterMessages.get(0) != null) {
                footer = vars.auto_FooterMessages.get(0);
            }
        }

        if (vars.auto_messagenumber < vars.auto_HeaderMessages.size()) {
            ++vars.auto_messagenumber;
        } else {
            vars.auto_messagenumber = 0;
        }

        if (vars.customPrefix) {
            theTablist.setCustomPrefixes();
        }

    }
}
