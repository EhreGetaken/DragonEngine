package net.dragon.engine.player;

import net.dragon.engine.events.PlayerCollideEvent;
import net.dragon.engine.player.utility.CollisionType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
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
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, xp, xp.getBlock(), CollisionType.BLOCK));
            }
            if (xn.getBlock().getType().isSolid()) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, xn, xn.getBlock(), CollisionType.BLOCK));
            }
            if (zn.getBlock().getType().isSolid()) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, zn, zn.getBlock(), CollisionType.BLOCK));
            }
            if (zp.getBlock().getType().isSolid()) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, zp, zp.getBlock(), CollisionType.BLOCK));
            }
        }

        if (xp.getWorld().getNearbyEntities(xp, 0, 2, 0) instanceof Player ||
                xn.getWorld().getNearbyEntities(xn, 0, 2, 0) instanceof Player ||
                zn.getWorld().getNearbyEntities(zn, 0, 2, 0) instanceof Player ||
                zp.getWorld().getNearbyEntities(zp, 0, 2, 0) instanceof Player) {

            if (xp.getWorld().getNearbyEntities(xp, 0, 2, 0) instanceof Player) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, xp,
                        (Player) xp.getWorld().getNearbyEntities(xp, 0, 2, 0), CollisionType.PLAYER));
            }
            if (xn.getWorld().getNearbyEntities(xn, 0, 2, 0) instanceof Player) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, xn,
                        (Player) xn.getWorld().getNearbyEntities(xn, 0, 2, 0), CollisionType.PLAYER));
            }
            if (zn.getWorld().getNearbyEntities(zn, 0, 2, 0) instanceof Player) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, zn,
                        (Player) zn.getWorld().getNearbyEntities(zn, 0, 2, 0), CollisionType.PLAYER));
            }
            if (zp.getWorld().getNearbyEntities(zp, 0, 2, 0) instanceof Player) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, zp,
                        (Player) zn.getWorld().getNearbyEntities(zp, 0, 2, 0), CollisionType.PLAYER));
            }
        }

        if (xp.getWorld().getNearbyEntities(xp, 0, 2, 0) instanceof Entity ||
                xn.getWorld().getNearbyEntities(xn, 0, 2, 0) instanceof Entity ||
                zn.getWorld().getNearbyEntities(zn, 0, 2, 0) instanceof Entity ||
                zp.getWorld().getNearbyEntities(zp, 0, 2, 0) instanceof Entity &&
                        !(xp.getWorld().getNearbyEntities(xp, 0, 2, 0) instanceof Player) ||
                !(xn.getWorld().getNearbyEntities(xn, 0, 2, 0) instanceof Player) ||
                !(zn.getWorld().getNearbyEntities(zn, 0, 2, 0) instanceof Player) ||
                !(zp.getWorld().getNearbyEntities(zp, 0, 2, 0) instanceof Player)) {
            if (xp.getWorld().getNearbyEntities(xp, 0, 2, 0) instanceof Player) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, xp,
                        (Player) xp.getWorld().getNearbyEntities(xp, 0, 2, 0), CollisionType.ENTITY));
            }
            if (xn.getWorld().getNearbyEntities(xn, 0, 2, 0) instanceof Player) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, xn,
                        (Player) xn.getWorld().getNearbyEntities(xn, 0, 2, 0), CollisionType.ENTITY));
            }
            if (zn.getWorld().getNearbyEntities(zn, 0, 2, 0) instanceof Player) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, zn,
                        (Player) zn.getWorld().getNearbyEntities(zn, 0, 2, 0), CollisionType.ENTITY));
            }
            if (zp.getWorld().getNearbyEntities(zp, 0, 2, 0) instanceof Player) {
                Bukkit.getPluginManager().callEvent(new PlayerCollideEvent(player, zp,
                        (Player) zn.getWorld().getNearbyEntities(zp, 0, 2, 0), CollisionType.ENTITY));
            }
        }

    }

}
