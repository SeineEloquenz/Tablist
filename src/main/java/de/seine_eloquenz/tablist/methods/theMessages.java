package de.seine_eloquenz.tablist.methods;

import de.seine_eloquenz.tablist.tablist;
import de.seine_eloquenz.tablist.vars;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class theMessages {
    public tablist plugin;

    private static final DecimalFormat df = new DecimalFormat("#.##");
    
    private static final Map<String, Function<MessageData, String>> replacements = new HashMap<>();
    static {
        replacements.put("%player%", d -> d.player.getName());
        replacements.put("%level%", d -> String.valueOf(d.player.getLevel()));
        replacements.put("%health%", d -> String.valueOf(Math.round(d.player.getHealth())));
        replacements.put("%foodlevel%", d -> String.valueOf(Math.round(Double.parseDouble(String.valueOf(d.player.getFoodLevel())))));
        replacements.put("%maxhealth%", d -> String.valueOf(d.player.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null ? Math.round(Objects.requireNonNull(d.player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) : 0));
        replacements.put("%iteminhandtype%", d -> d.player.getInventory().getItemInMainHand().getType().name());
        replacements.put("%iteminhandamount%", d -> String.valueOf(d.player.getInventory().getItemInMainHand().getAmount()));
        replacements.put("%iteminhandid%", d -> String.valueOf(d.player.getInventory().getItemInMainHand().getType()));
        replacements.put("%gamemode%", d -> d.player.getGameMode().name());
        replacements.put("%ping%", d -> String.valueOf(d.ping));
        replacements.put("%difficulty%", d -> "" + d.player.getWorld().getDifficulty());
        replacements.put("%world%", d -> d.player.getWorld().getName());
        replacements.put("%blockx%", d -> df.format(d.player.getLocation().getX()));
        replacements.put("%blocky%", d -> df.format(d.player.getLocation().getY()));
        replacements.put("%blockz%", d -> df.format(d.player.getLocation().getZ()));
        replacements.put("%direction%", d -> getDirection(d.player.getLocation().getYaw(), 1));
        replacements.put("%directionNumber%", d -> getDirection(d.player.getLocation().getYaw(), 3));
        replacements.put("%directionLetter%", d -> getDirection(d.player.getLocation().getYaw(), 2));
        replacements.put("%onlineplayers%", d -> {
            if (theConfig.getShowVanishedPlayerOnVariableChangeText()) {
                String text;
                if (d.player.hasPermission(theConfig.getStaffPermission(tablist.getPlugin()))) {
                    text = theConfig.getShowVanishedPlayerOnVariableChangeTextStaff();
                } else {
                    text = theConfig.getShowVanishedPlayerOnVariableChangeTextUser();
                }
                return ChatColor.translateAlternateColorCodes('&', text);
            } else {
                return replacements.get("%online%").apply(d);
            }
        });
        replacements.put("%vanishedplayers%", d -> String.valueOf(d.vanishedplayers));
        replacements.put("%maxonlineplayers%", d -> String.valueOf(Bukkit.getServer().getMaxPlayers()));
        replacements.put("%servermotd%", d -> Bukkit.getServer().getMotd());
        replacements.put("%servername%", d -> Bukkit.getServer().getName());
        replacements.put("%serverid%", d -> Bukkit.getServer().getIp());
        replacements.put("%serverip%", d -> Bukkit.getServer().getIp());
        replacements.put("%serverport%", d -> String.valueOf(Bukkit.getServer().getPort()));
        replacements.put("%staffs%", d -> String.valueOf(d.staffs));
        replacements.put("%tps%", d -> df.format(getTPS()));
        replacements.put("%time%", d -> d.time);
        replacements.put("%date%", d -> d.date);
        replacements.put("%online%", d -> String.valueOf(d.onlineplayers));
        replacements.put("%vanished%", d -> String.valueOf(d.vanishedplayers));
    }

    /**
     * Messy way to encode the data used to remove a whopping lot of replace iterations
     */
    private static final class MessageData {
        private final Player player;
        private final int ping;
        private final int vanishedplayers;
        private final int onlineplayers;
        private final int staffs;
        private final String time;
        private final String date;

        private MessageData(final Player player, final int ping, final int vanishedPlayers, final int onlinePlayers,
                            final int staffs, final String time, final String date) {
            this.player = player;
            this.ping = ping;
            this.vanishedplayers = vanishedPlayers;
            this.onlineplayers = onlinePlayers;
            this.staffs = staffs;
            this.time = time;
            this.date = date;
        }
    }

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
        double dir = (yaw - 90.0F) % 360.0F;
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
            for(Iterator<Byte> var2 = vars.tpsByteList.iterator(); var2.hasNext(); sum += i) {
                i = var2.next();
            }

            return (byte)(sum / vars.tpsByteList.size());
        }
    }

    public static String replaceWithVariables(Player p, String s) {
        int staffs = 0;
        int onlineplayers = 0;
        int vanishedplayers = 0;
        Iterator<? extends Player> var9 = Bukkit.getOnlinePlayers().iterator();

        Player op;
        while(var9.hasNext()) {
            op = var9.next();
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
        String old = ChatColor.translateAlternateColorCodes('&', s);
        final MessageData data = new MessageData(p, ping, vanishedplayers, onlineplayers, staffs, time, date);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < old.length(); i++) {
            if (old.charAt(i) == '%') {
                for (int j = 1; i+j < old.length(); j++) {
                    if (old.charAt(i+j) == '%') {
                        String selected = old.substring(i, i+j+1);
                        if (replacements.containsKey(selected)) {
                            builder.append(replacements.get(selected).apply(data));
                            i += j;
                        } else {
                            builder.append(old.charAt(i));
                        }
                        break;
                    }
                }
            } else {
                builder.append(old.charAt(i));
            }
        }
        old = ChatColor.translateAlternateColorCodes('&', builder.toString());
        String prefix;
        if (old.contains("%money%")) {
            if (tablist.economy != null) {
                prefix = df.format(tablist.economy.getBalance(p));
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
