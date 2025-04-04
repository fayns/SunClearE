package me.fayns.suncleare;

import me.fayns.suncleare.commands.ClearCommand;
import me.fayns.suncleare.commands.Reload;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SunClearE extends JavaPlugin {

        private static SunClearE instance;

        @Override
        public void onEnable() {
            getLogger().info(ChatColor.translateAlternateColorCodes('&',"&aПлагин запустился"));
            getLogger().info(ChatColor.translateAlternateColorCodes('&',"&fРазработчик: &cfayns"));
            getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aПлагин успешно соединился с &6EssentialsX"));
            instance = this;
            saveDefaultConfig();
            getCommand("clear").setExecutor(new ClearCommand());
            getCommand("ggclear").setExecutor(new Reload());
            //getServer().getPluginManager().registerEvents(new ClearCommand(this), this);
        }

        @Override
        public void onDisable() {
            getLogger().info(ChatColor.translateAlternateColorCodes('&',"&aПлагин отключился"));
            getLogger().info(ChatColor.translateAlternateColorCodes('&',"&fРазработчик: &cfayns"));
            getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aПлагин отсоединился от &6EssentialsX"));
        }
        public static SunClearE getInstance() {
            return instance;
        }

        public void reload() {
            reloadConfig();
        }
    }

