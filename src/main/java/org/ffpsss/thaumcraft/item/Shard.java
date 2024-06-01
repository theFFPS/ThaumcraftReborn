package org.ffpsss.thaumcraft.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import net.minecraft.item.Item;
import org.ffpsss.thaumcraft.api.entity.EntityDataSaver;
import org.ffpsss.thaumcraft.api.util.ThaumonomiconUtil;

public class Shard extends Item {
    public Shard(Settings settings) {
        super(settings);
    }

    @Override
    public void onCraftByPlayer(ItemStack stack, World world, PlayerEntity player) {
        super.onCraftByPlayer(stack, world, player);
        if (!world.isClient) {
            if (!ThaumonomiconUtil.hasFeature((EntityDataSaver) player, "thaumcraft_obtain_shards")) {
                ThaumonomiconUtil.unlockFeature((EntityDataSaver) player, "thaumcraft_obtain_shards");
                ThaumonomiconUtil.unlockFeature((EntityDataSaver) player, "tmp_quest_shards");
                player.sendMessage(Text.translatable("msg.thaumcraft.crystal_dreem_quest_start"));
//                player.isSleeping()
            }
        }
    }
}
