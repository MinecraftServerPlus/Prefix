package io.github.gum4.prefix.Utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Folder {
    public boolean createPluginDataFolder(JavaPlugin plugin) {
        File pluginDataFolder = plugin.getDataFolder();
        if (!pluginDataFolder.exists()) {
            try {
                if (pluginDataFolder.mkdirs()) return true;
            } catch (SecurityException e) {
                Bukkit.getLogger().severe("SecurityException occurs!");
            }
            return false;
        }
        return true;
    }
}
