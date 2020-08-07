package net.dragon.engine;

import net.dragon.engine.api.Utility;
import net.dragon.engine.gamehandler.GameState;
import net.dragon.engine.logger.LogType;
import net.dragon.engine.logger.LoggerAPI;
import net.dragon.engine.player.CollisionDetector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DragonEngine extends JavaPlugin {

    private static DragonEngine dragonEngine;
    private static boolean debug;
    private static GameState gameState;

    @Override
    public void onEnable() {
        dragonEngine = this;
        LoggerAPI.log(LogType.INFO, "DragonEngine is ready to use.");
    }

    @Override
    public void onDisable() {

    }

    public static void registerEngine() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        debug = false;
        gameState = GameState.STATE_1;
        pluginManager.registerEvents(new CollisionDetector(), dragonEngine);
        LoggerAPI.log(LogType.INFO, "Starting DragonEngine " + Utility.VERSION + " @ " + DragonEngine.class.getName());
    }

    public static DragonEngine getDragonEngine() {
        return dragonEngine;
    }

    public boolean getDebugMode() {
        return debug;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        DragonEngine.gameState = gameState;
    }

    public void setDebug(boolean mode) {
        debug = mode;
    }

}
