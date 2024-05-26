package org.ffpsss.thaumcraft;

import org.ffpsss.sml.api.common.SMLRegistry;
import org.ffpsss.sml.api.util.ItemUtil;
import org.ffpsss.thaumcraft.item.BalancedShard;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class ThaumicItems {
    public static void registerAll() {
        ItemUtil.generateItemGroup("thaumcraft", "group", Items.ACACIA_BOAT);
        ItemUtil.register(
            "thaumcraft", 
            "shard_balanced",
            new BalancedShard(new Item.Settings().maxCount(64).group(SMLRegistry.getItemGroup("thaumcraft:group")))
        );
    }
}
