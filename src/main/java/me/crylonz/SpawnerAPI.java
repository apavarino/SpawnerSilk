package me.crylonz;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Logger;

import static me.crylonz.SpawnerSilk.getSpawnerMaterial;

public class SpawnerAPI {

    private final static Logger log = Logger.getLogger("Minecraft");

    /**
     * Generate a Spawner itemStack from EntityType
     *
     * @param entity Entity type of the spawner item
     * @return an ItemStack
     */
    public static ItemStack getSpawner(EntityType entity) {
        return stringToCustomItemStack(entity.name(), 1);
    }

    /**
     * Get the Entity type of a spawner
     *
     * @param itemStack itemStack representing the spawner
     * @return an EntityType
     */
    public static EntityType getEntityType(ItemStack itemStack) {

        EntityType entity = EntityType.UNKNOWN;
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemStack.getType() == SpawnerSilk.getSpawnerMaterial() && itemMeta != null) {

            String itemMetaName = ChatColor.stripColor(itemMeta.getDisplayName().replace(" Spawner", ""));
            itemMetaName = itemMetaName.replaceAll(" ", "_");

            try {
                entity = EntityType.valueOf(itemMetaName.toUpperCase());
            } catch (IllegalArgumentException e) {
                return EntityType.UNKNOWN;
            }
        }
        return entity;
    }

    /**
     * Generate a Spawner itemStack from a string
     *
     * @param mobName Name of the mob
     * @param amount  Amount of spawner for the itemStack
     * @return an ItemStack
     */
    public static ItemStack stringToCustomItemStack(String mobName, int amount) {
        ItemStack spawnerItem = new ItemStack(getSpawnerMaterial(), amount);
        ItemMeta spawnerItemMeta = spawnerItem.getItemMeta();

        // On transforme le nom du mob en nom de spawner
        String name = capitalizeWord(mobName.replace("_", " ").toLowerCase()) + " Spawner";

        if (spawnerItemMeta != null) {
            spawnerItemMeta.setDisplayName(ChatColor.YELLOW + name);
        }

        spawnerItem.setItemMeta(spawnerItemMeta);

        return spawnerItem;
    }

    /**
     * Captitalize the first letter of each word of the given String
     * @param str String to modify
     * @return the mdofiied String
     */
    private static String capitalizeWord(String str) {
        StringBuilder s = new StringBuilder();

        // Declare a character of space
        // To identify that the next character is the starting
        // of a new word
        char ch = ' ';
        for (int i = 0; i < str.length(); i++) {

            // If previous character is space and current
            // character is not space then it shows that
            // current letter is the starting of the word
            if (ch == ' ' && str.charAt(i) != ' ')
                s.append(Character.toUpperCase(str.charAt(i)));
            else
                s.append(str.charAt(i));
            ch = str.charAt(i);
        }

        // Return the string with trimming
        return s.toString().trim();
    }
}