package com.demeng7215.testantiswear;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is a test plugin for Senior Team service team.
 */
public final class TestAntiSwearPlugin extends JavaPlugin implements Listener {

    @Getter
    private static TestAntiSwearPlugin plugin;

    private CustomConfig config;

    @Override
    public void onEnable() {

        plugin = this;

        try {
            config = new CustomConfig("config.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bukkit.getPluginManager().registerEvents(this, this);

        getLogger().info("TestAntiSwear has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TestAntiSwear has been disabled.");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        for (String swear : config.getConfig().getStringList("blocked-words")) {

            String msg = e.getMessage();

            if (msg.toLowerCase().contains(swear)) {

                e.setCancelled(true);

                e.getPlayer().chat(msg.replace(swear, star(msg)));
            }
        }
    }

    private String star(String stringToStar) {

        StringBuilder stars = new StringBuilder();
        for (char c : stringToStar.toCharArray()) stars.append("*");

        return stars.toString();
    }
}
