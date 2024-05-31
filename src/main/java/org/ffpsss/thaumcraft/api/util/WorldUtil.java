package org.ffpsss.thaumcraft.api.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class WorldUtil {
    public static void changeBiome(BlockPos pos1, BlockPos pos2, String biome, World world) {
        Objects.requireNonNull(world.getServer()).getCommandManager()
                .executeWithPrefix(world.getServer().getCommandSource(),
                        "fillbiome " +
                                pos1.getX() + " " + pos1.getY() + " " + pos1.getZ() + " " +
                                pos2.getX() + " " + pos2.getY() + " " + pos2.getZ() + " " + biome);
    }
    public static void placeBlock(BlockPos pos, BlockState state, World world) {
        world.setBlockState(pos, state);
    }
}
