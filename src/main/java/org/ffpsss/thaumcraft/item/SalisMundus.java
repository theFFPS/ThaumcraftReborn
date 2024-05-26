package org.ffpsss.thaumcraft.item;

import org.ffpsss.sml.api.common.SMLRegistry;
import org.ffpsss.sml.api.entity.EntityDataSaver;
import org.ffpsss.sml.api.world.BlockPlacerUtil;
import org.ffpsss.thaumcraft.util.AnimationUtil;
import org.ffpsss.thaumcraft.util.ThaumonomiconUtil;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SalisMundus extends Item {
    public SalisMundus(Settings settings) {
        super(settings);
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getHand() == Hand.MAIN_HAND && !context.getWorld().isClient) {
            context.getPlayer().setStackInHand(context.getHand(), ItemStack.EMPTY);
            BlockPos pos = context.getBlockPos();
            World world = context.getWorld();
            if (world.getBlockState(pos).isOf(Blocks.BOOKSHELF)) {
                AnimationUtil.playSalisMundusAnimation(world, pos);
                // TODO: SPAWN THAUMONOMICON
                BlockPlacerUtil.placeBlock(pos, Blocks.AIR.getDefaultState(), world);
                ThaumonomiconUtil.unlockFeature((EntityDataSaver)context.getPlayer(), "start_thaumcraft");
            } else if (world.getBlockState(pos).isOf(Blocks.CRAFTING_TABLE)) {
                if (ThaumonomiconUtil.hasFeature((EntityDataSaver)context.getPlayer(), "start_thaumcraft")) {
                    AnimationUtil.playSalisMundusAnimation(world, pos);
                    BlockPlacerUtil.placeBlock(pos, SMLRegistry.getBlock("thaumcraft:arcane_workbench").getDefaultState(), world);
                    ThaumonomiconUtil.unlockFeature((EntityDataSaver)context.getPlayer(), "create_thaumic_crafting_table");
                } else context.getPlayer().sendMessage(Text.translatable("msg.thaumcraft.not_unlocked"));
            }
        }
        return ActionResult.CONSUME_PARTIAL;
    }
}
