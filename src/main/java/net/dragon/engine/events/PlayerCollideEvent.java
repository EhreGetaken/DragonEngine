package net.dragon.engine.events;

import net.dragon.engine.DragonEngine;
import net.dragon.engine.logger.LogType;
import net.dragon.engine.logger.LoggerAPI;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PlayerCollideEvent extends Event implements Cancellable {

    public static HandlerList handlers = new HandlerList();
    public boolean cancelled = false;

    Player player;
    Location location;
    Block block;

    public PlayerCollideEvent(Player player, Location location, Block block) {
        this.player = player;
        this.location = location;
        this.block = block;
        if (DragonEngine.getDragonEngine().getDebugMode()) {
            LoggerAPI.log(LogType.INFO, "Called PlayerCollideEvent on " + player + " @l " + location + " @b " + block);
        }
    }

    public Location getLocation() {
        return location;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
