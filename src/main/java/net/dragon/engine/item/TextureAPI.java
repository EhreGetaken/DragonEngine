package net.dragon.engine.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class TextureAPI {

    /**
     * @param texture
     * @return
     * @throws Exception
     *
     * @func sets an empty texture to the named uniqueid
     */

    public static ItemStack withTexture(String texture) throws Exception {

        if (texture == null) {
            texture = new String("");
        }

        if (texture.isEmpty()) {
            texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFkYz" +
                    "A0OGE3Y2U3OGY3ZGFkNzJhMDdkYTI3ZDg1YzA5MTY4ODFlNTUyMmVlZWQxZTNkYWYyMTdhMzhjMWEifX19=";
        }

        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta meta = item.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));

        Field field = meta.getClass().getDeclaredField("profile");
        field.setAccessible(true);
        field.set(meta, profile);

        item.setItemMeta(meta);
        return item;
    }

}
