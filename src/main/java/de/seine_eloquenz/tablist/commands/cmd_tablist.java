//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.seine_eloquenz.tablist.commands;

import de.seine_eloquenz.tablist.tablist;
import de.seine_eloquenz.tablist.vars;
import de.seine_eloquenz.tablist.methods.theSwitcher;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_tablist implements CommandExecutor {
    public tablist plugin;

    public cmd_tablist(tablist plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = null;
        if (sender instanceof Player) {
            p = (Player)sender;
        }

        String cmdName = "tablist";
        String prefix = vars.message_prefix;
        String errormsg = prefix + vars.message_error;
        String permmsg = prefix + vars.message_nopermissions;
        String helpmsg = vars.message_help;
        if (cmd.getName().equalsIgnoreCase(cmdName)) {
            if (p != null) {
                if (args.length == 0) {
                    p.sendMessage(helpmsg);
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (p.hasPermission(vars.permission_reload)) {
                            this.plugin.reloadConfig();
                            theSwitcher.stop();
                            theSwitcher.start(this.plugin);
                            p.sendMessage(prefix + vars.message_reload);
                        } else {
                            p.sendMessage(permmsg);
                        }
                    } else if (args[0].equalsIgnoreCase("help")) {
                        p.sendMessage(helpmsg);
                    } else {
                        p.sendMessage(errormsg);
                    }
                } else {
                    p.sendMessage(errormsg);
                }
            } else if (args.length == 0) {
                sender.sendMessage(helpmsg);
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    this.plugin.reloadConfig();
                    theSwitcher.stop();
                    theSwitcher.start(this.plugin);
                    sender.sendMessage(prefix + vars.message_reload);
                } else if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(helpmsg);
                } else {
                    sender.sendMessage(errormsg);
                }
            } else {
                sender.sendMessage(errormsg);
            }
        }

        return false;
    }
}
