package de.seine_eloquenz.tablist.methods;

import de.seine_eloquenz.tablist.tablist;
import de.seine_eloquenz.tablist.vars;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Iterator;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class theMessages {
    public tablist plugin;

    public theMessages(tablist plugin) {
        this.plugin = plugin;
    }

    public static String setupInt(Integer i) {
        String s = "" + i;
        if (i < 10) {
            s = "0" + i;
        }

        return s;
    }

    public static String getDirection(Float yaw, int mode) {
        String outputMode1 = "";
        String outputMode2 = "";
        String outputMode3 = "";
        double dir = (double)((yaw - 90.0F) % 360.0F);
        if (dir < 0.0D) {
            dir += 360.0D;
        }

        if (dir >= 0.0D && dir < 22.5D) {
            outputMode1 = "West";
            outputMode2 = "W";
            outputMode3 = String.valueOf(dir);
        } else if (dir >= 22.5D && dir < 67.5D) {
            outputMode1 = "North West";
            outputMode2 = "NW";
            outputMode3 = String.valueOf(dir);
        } else if (dir >= 67.5D && dir < 112.5D) {
            outputMode1 = "North";
            outputMode2 = "N";
            outputMode3 = String.valueOf(dir);
        } else if (dir >= 112.5D && dir < 157.5D) {
            outputMode1 = "North East";
            outputMode2 = "NE";
            outputMode3 = String.valueOf(dir);
        } else if (dir >= 157.5D && dir < 202.5D) {
            outputMode1 = "East";
            outputMode2 = "E";
            outputMode3 = String.valueOf(dir);
        } else if (dir >= 202.5D && dir < 247.5D) {
            outputMode1 = "South East";
            outputMode2 = "SE";
            outputMode3 = String.valueOf(dir);
        } else if (dir >= 247.5D && dir < 292.5D) {
            outputMode1 = "South";
            outputMode2 = "S";
            outputMode3 = String.valueOf(dir);
        } else if (dir >= 292.5D && dir < 337.5D) {
            outputMode1 = "South West";
            outputMode2 = "SW";
            outputMode3 = String.valueOf(dir);
        } else if (dir >= 337.5D && dir < 360.0D) {
            outputMode1 = "West";
            outputMode2 = "W";
            outputMode3 = String.valueOf(dir);
        }

        switch(mode) {
            case 1:
                return outputMode1;
            case 2:
                return outputMode2;
            case 3:
                return outputMode3;
            default:
                return outputMode1;
        }
    }

    public static byte getTPS() {
        int sum = 0;
        if (vars.tpsByteList.size() <= 0) {
            return 0;
        } else {
            Byte i;
            for(Iterator var2 = vars.tpsByteList.iterator(); var2.hasNext(); sum += i) {
                i = (Byte)var2.next();
            }

            return (byte)(sum / vars.tpsByteList.size());
        }
    }

    public static String replaceWithVariables(Player p, String s) {
        int staffs = 0;
        int onlineplayers = 0;
        int vanishedplayers = 0;
        String onlinePlayersText;
        DecimalFormat df = new DecimalFormat("#.##");
        Iterator var9 = Bukkit.getOnlinePlayers().iterator();

        Player op;
        while(var9.hasNext()) {
            op = (Player)var9.next();
            if (op.hasPermission(theConfig.getStaffPermission(tablist.getPlugin()))) {
                if (theConfig.getShowVanishedPlayerOnVariable()) {
                    ++staffs;
                } else if (p.canSee(op)) {
                    ++staffs;
                }
            }

            if (theConfig.getShowVanishedPlayerOnVariable()) {
                ++onlineplayers;
            } else if (p.canSee(op)) {
                ++onlineplayers;
            } else {
                ++vanishedplayers;
            }
        }

        if (theConfig.getShowVanishedPlayerOnVariableChangeText()) {
            if (p.hasPermission(theConfig.getStaffPermission(tablist.getPlugin()))) {
                onlinePlayersText = theConfig.getShowVanishedPlayerOnVariableChangeTextStaff();
            } else {
                onlinePlayersText = theConfig.getShowVanishedPlayerOnVariableChangeTextUser();
            }
        } else {
            onlinePlayersText = "%online%";
        }

        onlinePlayersText = ChatColor.translateAlternateColorCodes('&', onlinePlayersText).replace("%online%", String.valueOf(onlineplayers)).replace("%vanished%", String.valueOf(vanishedplayers));
        int ping = 0;

        try {
            Object handle = p.getClass().getMethod("getHandle").invoke(p);
            ping = (Integer)handle.getClass().getDeclaredField("ping").get(handle);
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException | IllegalAccessException ignored) {
        }

        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH) + 1;
        int day = calender.get(Calendar.DATE);
        String date = setupInt(day) + "-" + setupInt(month) + "-" + setupInt(year);
        int hours = calender.get(Calendar.HOUR_OF_DAY);
        int minutes = calender.get(Calendar.MINUTE);
        int seconds = calender.get(Calendar.SECOND);
        String time = setupInt(hours) + ":" + setupInt(minutes) + ":" + setupInt(seconds);
        String old = ChatColor.translateAlternateColorCodes('&', s).replace("%player%", p.getName()).replace("%level%", String.valueOf(p.getLevel())).replace("%health%", String.valueOf(Math.round(p.getHealth()))).replace("%foodlevel%", String.valueOf(Math.round(Double.parseDouble(String.valueOf(p.getFoodLevel()))))).replace("%maxhealth%", String.valueOf(Math.round(p.getMaxHealth()))).replace("%iteminhandtype%", p.getItemInHand().getType().name()).replace("%iteminhandamount%", String.valueOf(p.getItemInHand().getAmount())).replace("%iteminhandid%", String.valueOf(p.getInventory().getItemInMainHand().getType().getId())).replace("%gamemode%", p.getGameMode().name()).replace("%ping%", String.valueOf(ping)).replace("%difficulty%", "" + p.getWorld().getDifficulty()).replace("%world%", p.getWorld().getName()).replace("%blockx%", df.format(p.getLocation().getX())).replace("%blocky%", df.format(p.getLocation().getY())).replace("%blockz%", df.format(p.getLocation().getZ())).replace("%direction%", getDirection(p.getLocation().getYaw(), 1)).replace("%directionNumber%", getDirection(p.getLocation().getYaw(), 3)).replace("%directionLetter%", getDirection(p.getLocation().getYaw(), 2)).replace("%onlineplayers%", onlinePlayersText).replace("%vanishedplayers%", String.valueOf(vanishedplayers)).replace("%maxonlineplayers%", String.valueOf(Bukkit.getServer().getMaxPlayers())).replace("%servermotd%", Bukkit.getServer().getMotd()).replace("%servername%", Bukkit.getServer().getName()).replace("%serverid%", Bukkit.getServer().getIp()).replace("%serverip%", Bukkit.getServer().getIp()).replace("%serverport%", String.valueOf(Bukkit.getServer().getPort())).replace("%staffs%", String.valueOf(staffs)).replace("%tps%", df.format((long)getTPS())).replace("%time%", time).replace("%date%", date);
        old = ChatColor.translateAlternateColorCodes('&', old);
        String prefix;
        if (old.contains("%money%")) {
            if (tablist.economy != null) {
                prefix = df.format(tablist.economy.getBalance(p.getName()));
                old = old.replace("%money%", prefix);
            } else {
                System.out.println(tablist.getPlugin().getDescription().getName() + "> " + vars.message_vaulterror + "Economy");
            }
        }

        if (old.contains("%prefix%") || old.contains("%suffix%") || old.contains("%group%")) {
            if (tablist.chat != null) {
                prefix = tablist.chat.getPlayerPrefix(p);
                String suffix = tablist.chat.getPlayerSuffix(p);
                String group = tablist.chat.getPlayerGroups(p)[0];
                old = old.replace("%prefix%", prefix).replace("%suffix%", suffix).replace("%group%", group);
            } else {
                System.out.println(tablist.getPlugin().getDescription().getName() + "> " + vars.message_vaulterror + "Chat");
            }
        }

        if (vars.phapi_active) {
            old = PlaceholderAPI.setPlaceholders(p, old);
        }

        return old;
    }
}
