package net.moonbyte.AnarchyUtils.Dupes;

import net.moonbyte.AnarchyUtils.Helpers.ConfigurationUtils;
import org.bukkit.Material;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PiglinDupe implements Listener {
    @EventHandler
    public static void entityDeathEvent(EntityDeathEvent e) {
        if (e.getEntity() instanceof Ageable) {
            Ageable entity = (Ageable)e.getEntity();
            if (!entity.isAdult() && entity.getType() == EntityType.PIGLIN) {
                List<ItemStack> itemStacks = e.getDrops();
                if (!itemStacks.isEmpty() && itemStacks.size() >= 2) {
                    ItemStack itemToDupe = itemStacks.get(0);
                    ItemStack offhandItem = itemStacks.get(1);

                    if (offhandItem.getType() == Material.GOLD_NUGGET
                            || offhandItem.getType() == Material.GOLD_INGOT
                            || offhandItem.getType() == Material.GOLD_BLOCK
                            || offhandItem.getType() == Material.GOLDEN_PICKAXE
                            || offhandItem.getType() == Material.GOLDEN_AXE
                            || offhandItem.getType() == Material.GOLDEN_SWORD
                            || offhandItem.getType() == Material.GOLDEN_SHOVEL
                            || offhandItem.getType() == Material.GOLDEN_HOE
                            || offhandItem.getType() == Material.GOLDEN_HELMET
                            || offhandItem.getType() == Material.GOLDEN_CHESTPLATE
                            || offhandItem.getType() == Material.GOLDEN_LEGGINGS
                            || offhandItem.getType() == Material.GOLDEN_BOOTS
                            || offhandItem.getType() == Material.GOLDEN_CARROT
                            || offhandItem.getType() == Material.GOLDEN_APPLE) {

                        int timesToDupe = ConfigurationUtils.getConfig().getInt("dupes.piglin-drop-rate");
                        for (int i = 0; i < timesToDupe; i++) {
                            entity.getWorld().dropItemNaturally(entity.getLocation(), itemToDupe);
                        }
                    }
                }
            }
        }
    }
}