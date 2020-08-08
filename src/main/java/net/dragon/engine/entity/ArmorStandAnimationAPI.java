package net.dragon.engine.entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class ArmorStandAnimationAPI extends BukkitRunnable {

    ArmorStand s;

    public ArmorStandAnimationAPI(ArmorStand stand) {
        s = stand;
    }


    @Override
    public void run() {
        if (Bukkit.getOnlinePlayers().size() >= 1) {
            EulerAngle oldRot = s.getHeadPose();
            EulerAngle newRot = oldRot.add(0, 0.01f, 0);
            s.setHeadPose(newRot);
        }
    }

}
