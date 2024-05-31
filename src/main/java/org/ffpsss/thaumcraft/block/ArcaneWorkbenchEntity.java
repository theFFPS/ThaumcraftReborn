package org.ffpsss.thaumcraft.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ffpsss.thaumcraft.ThaumicBlocks;
import org.ffpsss.thaumcraft.ThaumicItems;
import org.ffpsss.thaumcraft.api.ImplementedInventory;
import org.ffpsss.thaumcraft.item.WandItem;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ArcaneWorkbenchEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(17, ItemStack.EMPTY);

    public static final List<Integer> INPUT_SLOTS = Arrays.asList(
      0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    );
    public static final int OUTPUT_SLOT = 16;
    public static final int WAND_SLOT = 15;

    public static int VIS_AER = 0;
    public static int VIS_AQUA = 0;
    public static int VIS_IGNIS = 0;
    public static int VIS_TERRA = 0;
    public static int VIS_PERDITIO = 0;
    public static int VIS_ORDO = 0;
    public static final List<Integer> SHARD_SLOTS = Arrays.asList(
      0, 1, 2, 3, 4, 5
    );

    public ArcaneWorkbenchEntity(BlockPos pos, BlockState state) {
        super(ThaumicBlocks.ARCANEWORKBENCH_ENTITY, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.thaumcraft.arcane_table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return null;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, items, registryLookup);
        nbt.putInt("aer", VIS_AER);
        nbt.putInt("aqua", VIS_AQUA);
        nbt.putInt("ignis", VIS_IGNIS);
        nbt.putInt("ordo", VIS_ORDO);
        nbt.putInt("terra", VIS_TERRA);
        nbt.putInt("perditio", VIS_PERDITIO);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, items, registryLookup);
        VIS_AER = nbt.getInt("aer");
        VIS_AQUA = nbt.getInt("aqua");
        VIS_IGNIS = nbt.getInt("ignis");
        VIS_TERRA = nbt.getInt("terra");
        VIS_ORDO = nbt.getInt("ordo");
        VIS_PERDITIO = nbt.getInt("perditio");
    }

    public static void tick(World world, BlockPos pos, BlockState state, ArcaneWorkbenchEntity entity) {
        if (world.isClient) return;

        if (entity.isOutputSlotEmptyReceivable() && entity.hasRecipe()) entity.craftItem();
//        else if (entity.canChargeWand()) entity.chargeWand();
    }

    public boolean isOutputSlotEmptyReceivable() {
        if (items.get(OUTPUT_SLOT).isEmpty()) return true;
        if (items.get(OUTPUT_SLOT).getCount() >= items.get(OUTPUT_SLOT).getMaxCount()) return false;
        // TODO: check if recipe will produce same item
        return true;
    }
    public boolean hasRecipe() {
        return true;
    }
//    public boolean canChargeWand() {
//        if (!(items.get(WAND_SLOT).getItem() instanceof WandItem)) return false;
//        return true;
//    }
    public void craftItem() {

    }
//    public void chargeWand() {
//
//    }
}
