package org.ffpsss.thaumcraft.item;

import java.util.Objects;

import org.ffpsss.thaumcraft.ThaumicBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ffpsss.thaumcraft.api.entity.EntityDataSaver;
import org.ffpsss.thaumcraft.api.util.AnimationUtil;
import org.ffpsss.thaumcraft.api.util.WorldUtil;
import org.ffpsss.thaumcraft.api.util.ThaumonomiconUtil;

public class SalisMundus extends Item {
    public SalisMundus(Settings settings) {
        super(settings);
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getHand() == Hand.MAIN_HAND && !context.getWorld().isClient) {
            Objects.requireNonNull(context.getPlayer()).setStackInHand(context.getHand(), ItemStack.EMPTY);
            BlockPos pos = context.getBlockPos();
            World world = context.getWorld();
            if (world.getBlockState(pos).isOf(Blocks.BOOKSHELF)) {
                if (ThaumonomiconUtil.hasFeature((EntityDataSaver)context.getPlayer(), "thaumcraft_obtain_shards")) {
                    AnimationUtil.playSalisMundusAnimation(world, pos);
                    // TODO: SPAWN THAUMONOMICON
                    WorldUtil.placeBlock(pos, Blocks.AIR.getDefaultState(), world);
                    if (!ThaumonomiconUtil.hasFeature((EntityDataSaver) context.getPlayer(), "thaumcraft_bookshelf"))
                        ThaumonomiconUtil.unlockFeature((EntityDataSaver) context.getPlayer(), "thaumcraft_bookshelf");
                    context.getPlayer().setStackInHand(context.getHand(), ItemStack.EMPTY);
                } else context.getPlayer().sendMessage(Text.translatable("msg.thaumcraft.not_unlocked"));
            } else if (world.getBlockState(pos).isOf(Blocks.CRAFTING_TABLE)) {
                if (ThaumonomiconUtil.hasFeature((EntityDataSaver)context.getPlayer(), "thaumcraft_bookshelf")) {
                    AnimationUtil.playSalisMundusAnimation(world, pos);
                    WorldUtil.placeBlock(pos, ThaumicBlocks.ARCANE_TABLE.getDefaultState(), world);
                    if (!ThaumonomiconUtil.hasFeature((EntityDataSaver) context.getPlayer(), "arcane_table"))
                        ThaumonomiconUtil.unlockFeature((EntityDataSaver)context.getPlayer(), "arcane_table");
                    context.getPlayer().setStackInHand(context.getHand(), ItemStack.EMPTY);
                } else context.getPlayer().sendMessage(Text.translatable("msg.thaumcraft.not_unlocked"));
            }
        }
        return ActionResult.SUCCESS;
    }
}
