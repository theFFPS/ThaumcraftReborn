package org.ffpsss.thaumcraft.api.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPlacerUtil {
    public static void placeBlock(BlockPos pos, BlockState state, World world) {
        world.setBlockState(pos, state);
    }
}
