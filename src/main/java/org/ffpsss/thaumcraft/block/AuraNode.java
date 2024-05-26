package org.ffpsss.thaumcraft.block;

import org.ffpsss.sml.api.common.SMLRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AuraNode extends BlockWithEntity {
    public AuraNode(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos arg0, BlockState arg1) {
        return new AuraNodeEntity(arg0, arg1);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, SMLRegistry.getBlockEntityType("thaumcraft:aura_node"), AuraNodeEntity::tick);
    }
}
