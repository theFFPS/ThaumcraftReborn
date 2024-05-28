package org.ffpsss.thaumcraft.block;

import java.util.*;

import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.BlockTags;
import org.ffpsss.thaumcraft.ThaumicBlocks;
import org.ffpsss.thaumcraft.api.aspect.Aspect;
import org.ffpsss.thaumcraft.api.aspect.AspectStorage;
import org.ffpsss.thaumcraft.api.auranode.AuraNodeType;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.ffpsss.thaumcraft.api.util.BlockPlacerUtil;

public class AuraNodeEntity extends BlockEntity implements AspectStorage {
    private Map<String, Aspect> aspects = new HashMap<>();
    private int starterAspects = 0;
    public int maxRegenPerAspect;
    public AuraNodeType type;
    
    public AuraNodeEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public AuraNodeEntity(BlockPos pos, BlockState state) {
        this(ThaumicBlocks.AURANODE_ENTITY, pos, state);
    }
    
    public AuraNodeEntity setNodeType(AuraNodeType type) {
        this.type = type;
        return this;
    }


    @Override
    public Map<String, Aspect> getAspects() {
        return aspects;
    }

    @Override
    public Aspect drainAspect(String ID, int amount) {
        if (!aspects.containsKey(ID)) return Aspect.getAspectById(ID, amount);
        Aspect aspect = aspects.get(ID);
        aspects.remove(ID);
        aspect.count -= amount;
        int overFlow = -aspect.count;
        if (overFlow < 0) aspects.put(ID, aspect);
        return Aspect.getAspectById(ID, overFlow);
    }

    @Override
    public Aspect addAspect(String ID, int amount) {
        return Aspect.getAspectById(ID, amount); // reject every aspect (can't be recharged)
    }

    public AuraNodeEntity setAspects(Map<String, Aspect> aspects, int maxRegenPerAspect) {
        this.aspects = aspects;
        this.maxRegenPerAspect = maxRegenPerAspect;
        return this;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        if (type == null) generateNode();
        nbt.putInt("max_regen", maxRegenPerAspect);
        nbt.putString("subtype", type.auraNodeSubType);
        nbt.putString("type", type.auraNodeType);

        NbtList aspectStorage = new NbtList();
        for (Map.Entry<String, Aspect> aspect : aspects.entrySet()) {
            NbtCompound aspectStored = new NbtCompound();
            aspectStored.putString("id", aspect.getKey());
            aspectStored.putInt("amount", aspect.getValue().count);
            aspectStorage.add(aspectStored);
        }
        nbt.put("aspects", aspectStorage);
    }

    private void generateNode() {
        selectNodeType();
        type.randomSubType();
        if (Objects.equals(type.auraNodeType, "hungry") && Objects.equals(type.auraNodeSubType, "fading")) {
            type = AuraNodeType.getType("unstable");
            type.auraNodeSubType = "pale";
        }
        maxRegenPerAspect = new Random().nextInt(50);
        Map<String, Aspect> possibleAspects = getPossibleAspects();
        int totalVis = 0;
        for (Map.Entry<String, Aspect> aspect : possibleAspects.entrySet()) {
            if (Objects.equals(type.auraNodeType, "tainted")) aspect.getValue().count = (aspect.getValue().count / 4) * 3;
            totalVis += aspect.getValue().count;
        }
        for (Map.Entry<String, Aspect> aspect : possibleAspects.entrySet()) {
            if (Objects.equals(type.auraNodeType, "tainted")) aspect.getValue().count = (aspect.getValue().count / 4) * 3;
            aspect.getValue().count = (totalVis / possibleAspects.size());
            if (Objects.equals(type.auraNodeSubType, "fading") && aspect.getValue().count > 15) aspect.getValue().count = 15;
            if (Objects.equals(type.auraNodeSubType, "pale") && aspect.getValue().count > 20) aspect.getValue().count = 20;
            if (aspect.getValue().count > maxRegenPerAspect) maxRegenPerAspect = aspect.getValue().count;
            aspects.put(aspect.getKey(), aspect.getValue());
        }
        starterAspects = aspects.size();
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        if (nbt.contains("type", 8)) {
            type = AuraNodeType.getType(nbt.getString("type"));
            type.auraNodeSubType = nbt.getString("subtype");
            if (Objects.equals(type.auraNodeSubType, "fading") || Objects.equals(type.auraNodeType, "unstable")) maxRegenPerAspect = 0;
            else maxRegenPerAspect = nbt.getInt("max_regen");

            NbtList aspectStorage = nbt.getList("aspects", 10);
            for (NbtElement nbtElement : aspectStorage) {
                NbtCompound aspect = (NbtCompound) nbtElement;
                aspects.put(aspect.getString("id"), Aspect.getAspectById(aspect.getString("id"), aspect.getInt("amount")));
            }
        } else generateNode();
    }

    private Aspect takeAspect(String ID, int amountMax) {
        int amountMin = (randomize(10, 40) ? amountMax / 2 : amountMax / 4);
        List<Integer> res = new ArrayList<>();
        for (int i = amountMin; i < amountMax; i++) res.add(i);
        return Aspect.getAspectById(ID, res.get(new Random().nextInt(res.size())));
    }
    public static boolean randomize(int falsePossibilities, int truePossibilities) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < falsePossibilities; i++) res.add(false);
        for (int i = 0; i < truePossibilities; i++) res.add(true);
        return res.get(new Random().nextInt(res.size()));
    }

    private boolean isSwampBiome(RegistryEntry<Biome> biome) {
        return (biome.matchesKey(BiomeKeys.SWAMP) || biome.matchesKey(BiomeKeys.MANGROVE_SWAMP));
    }
    private boolean isFrozenOceanBiome(RegistryEntry<Biome> biome) {
        return (biome.matchesKey(BiomeKeys.COLD_OCEAN) || biome.matchesKey(BiomeKeys.FROZEN_OCEAN) ||
                biome.matchesKey(BiomeKeys.DEEP_COLD_OCEAN) || biome.matchesKey(BiomeKeys.DEEP_FROZEN_OCEAN));
    }
    private boolean isFrozenBiome(RegistryEntry<Biome> biome) {
        return (isFrozenOceanBiome(biome) || biome.isIn(BiomeTags.IS_TAIGA) || biome.matchesKey(BiomeKeys.SNOWY_BEACH) || 
                biome.matchesKey(BiomeKeys.SNOWY_PLAINS) || biome.matchesKey(BiomeKeys.ICE_SPIKES) || biome.matchesKey(BiomeKeys.FROZEN_RIVER));
    }
    private boolean isPlainsBiome(RegistryEntry<Biome> biome) {
        return (biome.matchesKey(BiomeKeys.PLAINS) || biome.matchesKey(BiomeKeys.SNOWY_PLAINS) || biome.matchesKey(BiomeKeys.SUNFLOWER_PLAINS));
    }
    private Map<String, Aspect> getPossibleAspects() {
        Map<String, Aspect> result = new HashMap<>();
        assert world != null;
        RegistryEntry<Biome> biome = world.getBiome(pos);

        // TODO: when magical forest will be created tag it with BiomeTags.IS_FOREST
        if ((biome.isIn(BiomeTags.IS_JUNGLE) || biome.isIn(BiomeTags.IS_BADLANDS) || biome.isIn(BiomeTags.IS_SAVANNA)) && 
            randomize(1, 2))
            result.put("ignis", takeAspect("ignis", 100));
        if (biome.isIn(BiomeTags.IS_NETHER) && randomize(1, 2)) 
            result.put("ignis", takeAspect("ignis", 120));
        if (biome.matchesKey(BiomeKeys.MUSHROOM_FIELDS) && randomize(1, 2)) 
            result.put("ordo", takeAspect("ordo", 140));
        if ((biome.isIn(BiomeTags.IS_MOUNTAIN) || isFrozenBiome(biome) || biome.isIn(BiomeTags.IS_JUNGLE)) && 
            randomize(1, 2))
            result.put("ordo", takeAspect("ordo", 100));
        if ((biome.isIn(BiomeTags.IS_BEACH) || biome.matchesKey(BiomeKeys.DESERT) || biome.isIn(BiomeTags.IS_BADLANDS)) && 
            randomize(1, 2)) 
            result.put("terra", takeAspect("terra", 80));
        if (biome.isIn(BiomeTags.IS_FOREST) && randomize(1, 2)) 
            result.put("terra", takeAspect("terra", 120));
        if (isSwampBiome(biome) && randomize(1, 2)) 
            result.put("perditio", takeAspect("perditio", 120));
        if ((biome.matchesKey(BiomeKeys.SNOWY_PLAINS) || biome.isIn(BiomeTags.IS_SAVANNA) || biome.isIn(BiomeTags.IS_HILL) || 
            biome.isIn(BiomeTags.IS_BADLANDS) || biome.isIn(BiomeTags.IS_NETHER) || biome.matchesKey(BiomeKeys.DESERT)) &&
            randomize(1, 2)) 
            result.put("perditio", takeAspect("perditio", 80)); // tainted land
        if (biome.isIn(BiomeTags.IS_HILL) && randomize(1, 2)) 
            result.put("aer", takeAspect("aer", 120));
        if (biome.isIn(BiomeTags.IS_MOUNTAIN) && randomize(1, 2)) 
            result.put("aer", takeAspect("aer", 100));
        if ((biome.isIn(BiomeTags.IS_SAVANNA) || isPlainsBiome(biome)) && randomize(1, 2)) 
            result.put("aer", takeAspect("aer", 80));
        if ((biome.isIn(BiomeTags.IS_JUNGLE) || biome.matchesKey(BiomeKeys.LUSH_CAVES)) && randomize(1, 2))
            result.put("herba", takeAspect("herba", 100));
        if (biome.isIn(BiomeTags.IS_RIVER) && randomize(1, 2)) 
            result.put("aqua", takeAspect("aqua", 100));
        if ((isSwampBiome(biome) || biome.isIn(BiomeTags.IS_JUNGLE) || biome.matchesKey(BiomeKeys.DRIPSTONE_CAVES)) && 
            randomize(1, 2)) result.put("aqua", takeAspect("aqua", 80));
        if (biome.isIn(BiomeTags.IS_END) && randomize(1, 2)) 
            result.put("vacuos", takeAspect("vacuos", 80)); // outer lands
        if ((biome.matchesKey(BiomeKeys.DARK_FOREST) || biome.matchesKey(BiomeKeys.DEEP_DARK)) && randomize(1, 2)) 
            result.put("spiritus", takeAspect("spiritus", 80));
             // eerie, outer lands
        if (biome.matchesKey(BiomeKeys.DEEP_DARK)) {
            if (randomize(1, 2)) result.put("tenebrae", takeAspect("tenebrae", 100));
            if (randomize(1, 2)) result.put("mortuus", takeAspect("mortuus", 50));
        }
        if (Objects.equals(type.auraNodeType, "hungry")) result.put("fames", takeAspect("fames", 20));
        if (Objects.equals(type.auraNodeType, "tainted")) result.put("vitium", takeAspect("vitium", 20));
        if (Objects.equals(type.auraNodeType, "pure")) {
            if (randomize(1, 1)) result.put("ordo", takeAspect("ordo", 20));
            else result.put("victus", takeAspect("victus", 20));
        }
        if (Objects.equals(type.auraNodeType, "sinister")) {
            if (randomize(1, 1)) result.put("mortuus", takeAspect("mortuus", 50));
            if (randomize(1, 1)) result.put("exanimis", takeAspect("exanimis", 20));
            if (randomize(1, 1)) result.put("perditio", takeAspect("perditio", 20));
            if (randomize(1, 1)) result.put("tenebrae", takeAspect("tenebrae", 20));
        }
        /*
Magical 	--- 	--- 	Tainted land, eerie, outer lands, magical forest
        */

        int waterCounter = 0;
        int lavaCounter = 0;
        int stoneCounter = 0;
        int foliageCounter = 0;

        for (int x = pos.getX() - 5; x <= pos.getX() + 5; x++)
            for (int y = pos.getY() - 5; y <= pos.getY() + 5; y++)
                for (int z = pos.getZ() - 5; z <= pos.getZ() + 5; z++) {
                    if (world.getBlockState(new BlockPos(x, y, z)).isOf(Blocks.WATER)) waterCounter++;
                    if (world.getBlockState(new BlockPos(x, y, z)).isOf(Blocks.LAVA)) lavaCounter++;
                    if (world.getBlockState(new BlockPos(x, y, z)).isIn(BlockTags.BASE_STONE_NETHER) ||
                        world.getBlockState(new BlockPos(x, y, z)).isIn(BlockTags.STONE_ORE_REPLACEABLES) ||
                        world.getBlockState(new BlockPos(x, y, z)).isIn(BlockTags.DEEPSLATE_ORE_REPLACEABLES) ||
                        world.getBlockState(new BlockPos(x, y, z)).isIn(BlockTags.DRIPSTONE_REPLACEABLE_BLOCKS) ||
                        world.getBlockState(new BlockPos(x, y, z)).isIn(BlockTags.BASE_STONE_OVERWORLD)) stoneCounter++;
                    if (world.getBlockState(new BlockPos(x, y, z)).isIn(BlockTags.FLOWERS) ||
                        world.getBlockState(new BlockPos(x, y, z)).isIn(BlockTags.LEAVES) ||
                        world.getBlockState(new BlockPos(x, y, z)).isIn(BlockTags.LOGS)) foliageCounter++;
                }
        if (waterCounter >= 100) result.put("aqua", takeAspect("aqua", 10));
        if (lavaCounter >= 100) {
            result.put("ignis", takeAspect("ignis", 15));
            result.put("terra", takeAspect("terra", 15));
        }
        if (stoneCounter >= 500) result.put("terra", takeAspect("terra", 15));
        if (foliageCounter >= 100) result.put("herba", takeAspect("herba", 15));

        if (result.isEmpty()) {
            result.put("aer", takeAspect("aer", 5));
            result.put("terra", takeAspect("terra", 5));
            result.put("aqua", takeAspect("aqua", 5));
            result.put("ignis", takeAspect("ignis", 5));
            result.put("ordo", takeAspect("ordo", 5));
            result.put("perditio", takeAspect("perditio", 5));
        }
        return result;
    }
    private void selectNodeType() {
        assert world != null;
        RegistryEntry<Biome> biome = world.getBiome(pos);
        if (biome.matchesKey(BiomeKeys.DEEP_DARK) && randomize(4, 1)) {
            type = AuraNodeType.getType("sinister");
            return;
        }
        // if eerie / outer lands => SINISTER | NO SELECT
        // if tainted land => TAINTED | NO SELECT
        // if magical forest => PURE | NO SELECT
        List<String> selectableTypes = Arrays.asList(
            "normal", "normal", "normal", "normal", "normal", "normal", "normal", "normal", "normal", "normal",
            "unstable", "unstable", "unstable", "sinister", "sinister", "sinister", "hungry", "hungry", "hungry", "pure"
        );
        type = AuraNodeType.getType(selectableTypes.get(new Random().nextInt(selectableTypes.size())));
    }

    public static int aspectRegenTick = 0, unstableTick = 0;

    public static void tick(World world, BlockPos pos, BlockState state, AuraNodeEntity entity) {
        if (world.isClient) return;
        if (entity.type == null) entity.generateNode();

        if (!Objects.equals(entity.type.auraNodeSubType, "fading") && entity.type.regeneration) {
            for (Map.Entry<String, Aspect> aspect : entity.aspects.entrySet()) 
                if (aspect.getValue().count < entity.maxRegenPerAspect)
                    if (aspectRegenTick >= 600) {
                        aspectRegenTick = -1;
                        if (aspect.getValue().count < entity.maxRegenPerAspect) aspect.getValue().count++;
                    }
            aspectRegenTick++;
        }
        if (Objects.equals(entity.type.auraNodeType, "unstable")) {
            if (unstableTick == 100) {
                if (randomize(1, 1)) {
                    entity.drainAspect(
                            (String) (entity.aspects.keySet().toArray()[new Random().nextInt(entity.aspects.size())]),
                            1
                    );
                    // drop aspect as orb
                }
                unstableTick = -1;
            }
            unstableTick++;
        }
        if (entity.aspects.isEmpty()) BlockPlacerUtil.placeBlock(pos, Blocks.AIR.getDefaultState(), world);
        if (entity.starterAspects > entity.aspects.size()) entity.type.auraNodeSubType = "fading";

        markDirty(world, pos, state);
    }
}