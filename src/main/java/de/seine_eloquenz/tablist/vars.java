package de.seine_eloquenz.tablist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import org.bukkit.scheduler.BukkitTask;

public class vars {
    public tablist plugin;
    public static HashMap<String, Integer> joinTasks = new HashMap<>();
    public static HashMap<String, Integer> taskNumber = new HashMap<>();
    public static BukkitTask tpsTask = null;
    public static Timer tpsTimer = null;
    public static ArrayList<Byte> tpsByteList = new ArrayList<>();
    public static byte tpsTicks = 0;
    public static boolean phapi_active = false;
    public static BukkitTask auto = null;
    public static int auto_messagenumber = 0;
    public static ArrayList<String> auto_HeaderMessages = new ArrayList<>();
    public static ArrayList<String> auto_FooterMessages = new ArrayList<>();
    public static String message_prefix = "§7[§btL§7] ";
    public static String message_nopermissions = "§7No permissions";
    public static String message_reload = "§7Config reloaded";
    public static String message_error = "§7Type §3/tl help §7to get help";
    public static String message_playernotonline = "§7Player not online";
    public static String message_vaulterror = "You have to install Vault to use the part of ";
    public static String message_placeholderapierror = "You have to install PlaceholderAPI to use some placeholders !";
    public static String message_placeholderapisuccess = "You have installed PlaceholderAPI feel free to use it !";
    public static String message_help = "§7§m-----------------§bTablist§7§m-----------------\n§3/tl reload §7- reloads the config\n§7§m---------------------------------------";
    public static String permission_reload = "tablist.reload";
    public static String permission_colorcodes = "tablist.colorcodes";
    public static boolean customPrefix = false;
    public static String customPrefixFormat = "";

    public vars(tablist plugin) {
        this.plugin = plugin;
    }
}
