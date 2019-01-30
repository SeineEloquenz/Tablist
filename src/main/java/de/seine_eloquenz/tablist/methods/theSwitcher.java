package de.seine_eloquenz.tablist.methods;

import de.seine_eloquenz.tablist.tablist;
import de.seine_eloquenz.tablist.vars;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class theSwitcher {
    public tablist plugin;

    public theSwitcher(tablist plugin) {
        this.plugin = plugin;
    }

    public static void loadMessages(tablist plugin) {
        vars.auto_HeaderMessages.clear();
        vars.auto_FooterMessages.clear();
        List<String> headerList = theConfig.getAnnouncerHeaders(plugin);
        List<String> footerList = theConfig.getAnnouncerFooters(plugin);

        for(int i = 0; i < headerList.size(); ++i) {
            String header = headerList.get(i);
            String footer;
            if (footerList.size() > i) {
                footer = footerList.get(i);
            } else {
                footer = "";
            }

            vars.auto_HeaderMessages.add(ChatColor.translateAlternateColorCodes('&', header));
            vars.auto_FooterMessages.add(ChatColor.translateAlternateColorCodes('&', footer));
        }

    }

    public static void start(tablist plugin) {
        loadMessages(plugin);
        vars.auto = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            int num = vars.auto_messagenumber;

            Player op;
            String header;
            String footer;
            for(Iterator var3 = Bukkit.getOnlinePlayers().iterator(); var3.hasNext(); theTablist.sendTablist(op, header, footer, true)) {
                op = (Player)var3.next();
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

            if (vars.auto_messagenumber < vars.auto_HeaderMessages.size() - 1) {
                ++vars.auto_messagenumber;
            } else {
                vars.auto_messagenumber = 0;
            }

        }, 0L, (long)(theConfig.getAnnouncerTime(plugin) + 1));
    }

    public static void stop() {
        vars.auto.cancel();
        vars.auto_messagenumber = 0;
    }
}
