package io.github.gum4.prefix.handlers;

import io.github.gum4.prefix.Utils.Folder;
import it.unimi.dsi.fastutil.Hash;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class YmlHandler {
    private static JavaPlugin plugin;
    private static File file;
    private static YamlConfiguration config;
    public YmlHandler(JavaPlugin plugin) {
        YmlHandler.plugin = plugin;
        file = new File(plugin.getDataFolder(), "prefixes.yml");
    }

    public boolean initYml() {
        if (Folder.createPluginDataFolder(plugin)) {
            try {
                if (file.createNewFile()) {
                    // TODO: successfully initialized data file
                    config = YamlConfiguration.loadConfiguration(file);
                    return true;
                }
                // todo: already exists. nothing changed.
                config = YamlConfiguration.loadConfiguration(file);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public HashMap<String, Component> loadYml() {
        HashMap<String, Component> prefixMap = new HashMap<>();
        for (String prefixName: config.getKeys(false)){
            prefixMap.put(prefixName, (Component) config.get(prefixName));
        }
        return prefixMap;
    }

    public boolean saveYml(HashMap<String, Component> prefixMap) {
        Set<String> prefixNames = prefixMap.keySet();
        prefixNames.addAll(config.getKeys(false));
        for (String prefixName: prefixNames) {
            config.set(prefixName, prefixMap.get(prefixName));
        }
        try {
            config.save(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().severe("Failed to save the data of prefixes!");
        }
        return false;
    }

}
