package de.seine_eloquenz.tablist.methods;

import de.seine_eloquenz.tablist.vars;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class theTablist {
    public theTablist() {
    }

    public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    public static void sendTablist(Player p, String msg, String msg2, boolean colors) {
        if (colors) {
            msg = theMessages.replaceWithVariables(p, msg);
            msg2 = theMessages.replaceWithVariables(p, msg2);
        }

        try {
            Object header;
            Object footer;
            Object ppoplhf;
            Field f;
            Object pcon;
            //if (getServerVersion().equalsIgnoreCase("v1_13_R2"));
            header = getNmsClass("ChatComponentText").getConstructor(String.class).newInstance(ChatColor.translateAlternateColorCodes('&', msg));
            footer = getNmsClass("ChatComponentText").getConstructor(String.class).newInstance(ChatColor.translateAlternateColorCodes('&', msg2));
            ppoplhf = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor().newInstance();
            f = ppoplhf.getClass().getDeclaredField("header");
            f.setAccessible(true);
            f.set(ppoplhf, header);
            Field fb = ppoplhf.getClass().getDeclaredField("footer");
            fb.setAccessible(true);
            fb.set(ppoplhf, footer);
            pcon = p.getClass().getMethod("getHandle").invoke(p);
            pcon = pcon.getClass().getField("playerConnection").get(pcon);
            pcon.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(pcon, ppoplhf);
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | NoSuchFieldException | IllegalAccessException var12) {
            var12.printStackTrace();
        }

    }

    public static void setCustomPrefix(Player p) {
        p.setPlayerListName(ChatColor.translateAlternateColorCodes('6', theMessages.replaceWithVariables(p, vars.customPrefixFormat)));
    }

    public static void setCustomPrefixes() {

        for (Object op : Bukkit.getOnlinePlayers()) {
            setCustomPrefix((Player)op);
        }

    }
}
