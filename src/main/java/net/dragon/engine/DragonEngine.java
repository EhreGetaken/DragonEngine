package net.dragon.engine;

import net.dragon.engine.player.CollisionDetector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class DragonEngine {

    private static DragonEngine dragonEngine;
    private static boolean debug;

    public static void registerEngine(Plugin plugin) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        debug = false;

        pluginManager.registerEvents(new CollisionDetector(), plugin);

    }

    public static DragonEngine getDragonEngine() {
        return dragonEngine;
    }

    public boolean getDebugMode() {
        return debug;
    }

    public void setDebug(boolean mode) {
        debug = mode;
    }

}
