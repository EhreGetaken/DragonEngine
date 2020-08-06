package net.dragon.engine.player;

import net.dragon.engine.events.PlayerCollideEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CollisionDetector implements Listener {

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        World w = player.getWorld();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();

        Location xp = new Location(player.getWorld(), player.getLocation().getX() + 0.5, player.getLocation().getY(),
                player.getLocation().getZ());
        Location xn = new Location(w, x - 0.5, y, z);
        Location zp = new Location(w, x, y, z + 0.5);
        Location zn = new Location(w, x, y, z - 0.5);

        if (xp.getBlock().getType().isSolid() || xn.getBlock().getType().isSolid() || zp.getBlock().getType().isSolid()
                || zn.getBlock().getType().isSolid()) {
            if (xp.getBlock().getType().isSolid()) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, xp, xp.getBlock()));
            }
            if (xn.getBlock().getType().isSolid()) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, xn, xn.getBlock()));
            }
            if (zn.getBlock().getType().isSolid()) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, zn, zn.getBlock()));
            }
            if (zp.getBlock().getType().isSolid()) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, zp, zp.getBlock()));
            }
        }

    }

}
