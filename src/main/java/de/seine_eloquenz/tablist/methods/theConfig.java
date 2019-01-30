package de.seine_eloquenz.tablist.methods;

import de.seine_eloquenz.tablist.tablist;
import de.seine_eloquenz.tablist.vars;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;

public class theConfig {
    public tablist plugin;
    public static String staffPermission = "Tablist.Settings.Staff.Permission";
    public static String showvanishedplayeronvariable = "Tablist.Settings.ShowVanishedPlayerOnVariable.Toggle";
    public static String showvanishedplayeronvariableChangeText = "Tablist.Settings.ShowVanishedPlayerOnVariable.ChangeText.Toggle";
    public static String showvanishedplayeronvariableChangeTextUser = "Tablist.Settings.ShowVanishedPlayerOnVariable.ChangeText.UsersSeeOnlineText";
    public static String showvanishedplayeronvariableChangeTextStaff = "Tablist.Settings.ShowVanishedPlayerOnVariable.ChangeText.StaffsSeeOnlineText";
    public static String announcerToggle = "Tablist.Settings.Toggle";
    public static String customPrefixToggle = "Tablist.Settings.CustomPrefix.Toggle";
    public static String customPrefixFormat = "Tablist.Settings.CustomPrefix.Format";
    public static String announcerTime = "Tablist.Settings.Time";
    public static String announcerMessagesHeader = "Tablist.Settings.Messages.Header";
    public static String announcerMessagesFooter = "Tablist.Settings.Messages.Footer";

    public theConfig(tablist plugin) {
        this.plugin = plugin;
    }

    public static void register(tablist plugin) {
        FileConfiguration fc = plugin.getConfig();
        FileConfigurationOptions fco = fc.options();
        fco.header(plugin.getDescription().getName() + " v" + plugin.getDescription().getVersion() + " by sn1cko" + "\nTime is set in milliseconds which means every 20 = 1 second" + "\nfor example: 6000 = 5 minuntes OR 300 = 15 seconds" + "\n" + "\nThe announcer will never stop if the time is set to 0" + "\nElse it will stop after the amount of milliseconds you wish" + "\n" + "\nThere are currently 15 variables available which can be built in" + "\nfor example %player% - will display the playername or %health% - will give out the health" + "\nall other variables can be found on the website" + "\nhttps://dev.bukkit.org/projects/tablist/pages/variables" + "\nYou can also add PlaceholderAPI to your server to get way more variables" + "\nDownload-Link (with instructions):" + "\nhttps://www.spigotmc.org/resources/placeholderapi.6245/");
        fc.addDefault(staffPermission, "tablist.staff");
        fc.addDefault(showvanishedplayeronvariable, false);
        fc.addDefault(showvanishedplayeronvariableChangeText, false);
        fc.addDefault(showvanishedplayeronvariableChangeTextUser, "%online%");
        fc.addDefault(showvanishedplayeronvariableChangeTextStaff, "%online% | %vanished%");
        fc.addDefault(customPrefixToggle, false);
        fc.addDefault(customPrefixFormat, "%prefix% %player%");
        fc.addDefault(announcerToggle, true);
        fc.addDefault(announcerTime, 20);
        ArrayList<String> msgHeader = new ArrayList<>();
        msgHeader.add("&2SERVER&aNAME\n&7Online: &a&o%onlineplayers%&7&o/&2&o%maxonlineplayers%\n&7Ping: &a%ping%\n&7===================");
        msgHeader.add("&3SERVER&bNAME\n&7Online: &b&o%onlineplayers%&7&o/&3&o%maxonlineplayers%\n&7Ping: &b%ping%\n&7===================");
        msgHeader.add("&6SERVER&eNAME\n&7Online: &e&o%onlineplayers%&8&o/&6&o%maxonlineplayers%\n&7Ping: &e%ping%\n&7===================");
        fc.addDefault(announcerMessagesHeader, msgHeader);
        ArrayList<String> msgFooter = new ArrayList<>();
        msgFooter.add("&7===================\n&2%player%\n&7Health: &a&o%health%&7/&2&o%maxhealth%\n&7Food: &a&o%foodlevel%&7/&2&o20\n&7X:&a&o%blockx%&7 Y:&a&o%blocky%&7 Z:&a&o%blockz%");
        msgFooter.add("&7===================\n&3%player%\n&7Health: &b&o%health%&7/&3&o%maxhealth%\n&7Food: &b&o%foodlevel%&7/&3&o20\n&7X:&b&o%blockx%&7 Y:&b&o%blocky%&7 Z:&b&o%blockz%");
        msgFooter.add("&7===================\n&6%player%\n&7Health: &e&o%health%&7/&6&o%maxhealth%\n&7Food: &e&o%foodlevel%&7/&6&o20\n&7X:&e&o%blockx%&7 Y:&e&o%blocky%&7 Z:&e&o%blockz%");
        fc.addDefault(announcerMessagesFooter, msgFooter);
        fco.copyHeader(true);
        fco.copyDefaults(true);
        plugin.saveConfig();
        if (fc.get(customPrefixToggle) != null) {
            vars.customPrefix = fc.getBoolean(customPrefixToggle);
        }

        if (fc.get(customPrefixFormat) != null) {
            vars.customPrefixFormat = fc.getString(customPrefixFormat);
        }

    }

    public static String getStaffPermission(tablist plugin) {
        String s = "";
        if (plugin.getConfig().getString(staffPermission) != null) {
            s = plugin.getConfig().getString(staffPermission);
        }

        return s;
    }

    public static boolean getShowVanishedPlayerOnVariable() {
        boolean b = false;
        if (tablist.getPlugin().getConfig().getBoolean(showvanishedplayeronvariable)) {
            b = true;
        }

        return b;
    }

    public static boolean getShowVanishedPlayerOnVariableChangeText() {
        boolean b = false;
        if (tablist.getPlugin().getConfig().getBoolean(showvanishedplayeronvariableChangeText)) {
            b = true;
        }

        return b;
    }

    public static String getShowVanishedPlayerOnVariableChangeTextUser() {
        String s = "";
        if (tablist.getPlugin().getConfig().getString(showvanishedplayeronvariableChangeTextUser) != null) {
            s = tablist.getPlugin().getConfig().getString(showvanishedplayeronvariableChangeTextUser);
        }

        return s;
    }

    public static String getShowVanishedPlayerOnVariableChangeTextStaff() {
        String s = "";
        if (tablist.getPlugin().getConfig().getString(showvanishedplayeronvariableChangeTextStaff) != null) {
            s = tablist.getPlugin().getConfig().getString(showvanishedplayeronvariableChangeTextStaff);
        }

        return s;
    }

    public static boolean getAnnouncerToggle(tablist plugin) {
        boolean b = false;
        if (plugin.getConfig().getBoolean(announcerToggle)) {
            b = true;
        }

        return b;
    }

    public static int getAnnouncerTime(tablist plugin) {
        return plugin.getConfig().getInt(announcerTime);
    }

    public static List<String> getAnnouncerHeaders(tablist plugin) {
        new ArrayList();
        return plugin.getConfig().getStringList(announcerMessagesHeader);
    }

    public static List<String> getAnnouncerFooters(tablist plugin) {
        new ArrayList();
        return plugin.getConfig().getStringList(announcerMessagesFooter);
    }
}
