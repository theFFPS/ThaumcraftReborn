package org.ffpsss.thaumcraft.block;

import java.util.*;

import net.minecraft.entity.*;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;
import org.ffpsss.thaumcraft.ThaumicAspects;
import org.ffpsss.thaumcraft.ThaumicBlocks;
import org.ffpsss.thaumcraft.api.aspect.Aspect;
import org.ffpsss.thaumcraft.api.aspect.AspectMetadata;
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
import org.ffpsss.thaumcraft.api.util.WorldUtil;

public class AuraNodeEntity extends BlockEntity implements AspectStorage {
    private final Map<String, Aspect> aspects = new HashMap<>();
    private final Map<String, Integer> maxForAspect = new HashMap<>();
    public AuraNodeType type;
    public boolean stable = false;
    public boolean advancedStable = false;
    
    public AuraNodeEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public AuraNodeEntity(BlockPos pos, BlockState state) {
        this(ThaumicBlocks.AURANODE_ENTITY, pos, state);
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
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        if (type == null) generateNode();
        nbt.putString("subtype", type.auraNodeSubType);
        nbt.putString("type", type.auraNodeType);

        NbtList aspectStorage = new NbtList();
        for (Map.Entry<String, Aspect> aspect : aspects.entrySet()) {
            NbtCompound aspectStored = new NbtCompound();
            aspectStored.putString("id", aspect.getKey());
            aspectStored.putInt("amount", aspect.getValue().count);
            aspectStored.putInt("max", maxForAspect.get(aspect.getKey()));
            aspectStorage.add(aspectStored);
        }
        nbt.put("aspects", aspectStorage);
    }
    private void generateNode() {
        List<Integer> probableMaxVis = new ArrayList<>();
        List<String>  probableAspects = new ArrayList<>();

        assert world != null;
        RegistryEntry<Biome> biome = world.getBiome(pos);
        if (biome.matchesKey(BiomeKeys.MUSHROOM_FIELDS) || biome.isIn(BiomeTags.IS_BEACH)) { probableAspects.add("terra"); probableMaxVis.add(80); }
        if (biome.isIn(BiomeTags.IS_TAIGA)) { probableAspects.add("terra"); probableMaxVis.add(100); }
        if (biome.isIn(BiomeTags.IS_TAIGA) || biome.isIn(BiomeTags.IS_MOUNTAIN) || biome.matchesKey(BiomeKeys.FROZEN_RIVER) ||
            biome.matchesKey(BiomeKeys.ICE_SPIKES) || biome.matchesKey(BiomeKeys.COLD_OCEAN) || biome.matchesKey(BiomeKeys.FROZEN_OCEAN) ||
            biome.matchesKey(BiomeKeys.DEEP_COLD_OCEAN) || biome.matchesKey(BiomeKeys.DEEP_FROZEN_OCEAN) || biome.matchesKey(BiomeKeys.SNOWY_BEACH) ||
            biome.matchesKey(BiomeKeys.SNOWY_PLAINS) || biome.isIn(BiomeTags.IS_END)) { probableAspects.add("ordo"); probableMaxVis.add(80); }
        if (biome.isIn(BiomeTags.IS_JUNGLE) || biome.matchesKey(BiomeKeys.DARK_FOREST)) { probableAspects.add("ordo"); probableMaxVis.add(100); }
        if (biome.matchesKey(BiomeKeys.DESERT) || biome.isIn(BiomeTags.IS_BADLANDS) || biome.isIn(BiomeTags.IS_JUNGLE) ||
            biome.isIn(BiomeTags.IS_SAVANNA) || biome.isIn(BiomeTags.IS_NETHER)) { probableAspects.add("ignis"); probableMaxVis.add(100); }
        if (biome.isIn(BiomeTags.IS_NETHER) || biome.matchesKey(BiomeKeys.DESERT) || biome.isIn(BiomeTags.IS_END)) {
            probableAspects.add("perditio");
            probableMaxVis.add(80);
        }
        if (biome.isIn(BiomeTags.IS_END)) { probableAspects.add("vacuos"); probableMaxVis.add(80); }
        if (biome.isIn(BiomeTags.IS_BADLANDS)) { probableAspects.add("ignis"); probableMaxVis.add(80); }
        if (biome.isIn(BiomeTags.IS_FOREST) || biome.isIn(BiomeTags.IS_TAIGA) || biome.isIn(BiomeTags.IS_HILL) ||
            biome.isIn(BiomeTags.IS_JUNGLE)) { probableAspects.add("terra"); probableMaxVis.add(120); }
        if (biome.matchesKey(BiomeKeys.SNOWY_TAIGA) || biome.isIn(BiomeTags.IS_MOUNTAIN) || biome.matchesKey(BiomeKeys.FROZEN_RIVER) ||
            biome.matchesKey(BiomeKeys.ICE_SPIKES) || biome.matchesKey(BiomeKeys.FROZEN_OCEAN) || biome.matchesKey(BiomeKeys.DEEP_FROZEN_OCEAN) ||
            biome.matchesKey(BiomeKeys.SNOWY_BEACH) || biome.matchesKey(BiomeKeys.SNOWY_PLAINS)) { probableAspects.add("ordo"); probableMaxVis.add(100); }
        if (biome.isIn(BiomeTags.IS_HILL)) { probableAspects.add("aer"); probableMaxVis.add(120); }
        if (biome.isIn(BiomeTags.IS_NETHER)) { probableAspects.add("ignis"); probableMaxVis.add(120); }
        if (biome.isIn(BiomeTags.IS_OCEAN)) { probableAspects.add("aqua"); probableMaxVis.add(120); }
        if (biome.isIn(BiomeTags.IS_RIVER) || biome.isIn(BiomeTags.IS_OCEAN)) { probableAspects.add("aqua"); probableMaxVis.add(100); }
        if (biome.isIn(BiomeTags.IS_HILL) || biome.isIn(BiomeTags.IS_MOUNTAIN)) { probableAspects.add("aer"); probableMaxVis.add(100); }
        if (biome.isIn(BiomeTags.IS_JUNGLE) || biome.matchesKey(BiomeKeys.LUSH_CAVES)) { probableAspects.add("herba"); probableMaxVis.add(100); }
        if (biome.matchesKey(BiomeKeys.MUSHROOM_FIELDS)) { probableAspects.add("ordo"); probableMaxVis.add(140); }
        if (biome.isIn(BiomeTags.IS_SAVANNA) || biome.matchesKey(BiomeKeys.PLAINS) || biome.matchesKey(BiomeKeys.SNOWY_PLAINS) ||
            biome.matchesKey(BiomeKeys.SUNFLOWER_PLAINS) || biome.matchesKey(BiomeKeys.MEADOW)) { probableAspects.add("aer"); probableMaxVis.add(80); }
        if (biome.isIn(BiomeTags.IS_BADLANDS) || biome.matchesKey(BiomeKeys.DESERT)) { probableAspects.add("terra"); probableMaxVis.add(80); }
        if (biome.isIn(BiomeTags.IS_BADLANDS) || biome.isIn(BiomeTags.IS_SAVANNA) || biome.isIn(BiomeTags.IS_HILL) ||
            biome.matchesKey(BiomeKeys.SNOWY_PLAINS)) { probableAspects.add("perditio"); probableMaxVis.add(80); }
        if (biome.matchesKey(BiomeKeys.DARK_FOREST) || biome.matchesKey(BiomeKeys.DEEP_DARK)) { probableAspects.add("spiritus"); probableMaxVis.add(80); }
        if (biome.matchesKey(BiomeKeys.SWAMP) || biome.matchesKey(BiomeKeys.MANGROVE_SWAMP)) { probableAspects.add("perditio"); probableMaxVis.add(120); }
        if (biome.matchesKey(BiomeKeys.SWAMP) || biome.matchesKey(BiomeKeys.MANGROVE_SWAMP) || biome.isIn(BiomeTags.IS_JUNGLE)) {
            probableAspects.add("aqua");
            probableMaxVis.add(80);
        }
        if (biome.matchesKey(BiomeKeys.DEEP_DARK)) { probableAspects.add("tenebrae"); probableAspects.add("mortuus"); probableMaxVis.add(80); }

        int selectedMaxVis = probableMaxVis.get(new Random().nextInt(probableMaxVis.size()));
        if (randomize(17, 1)) type = AuraNodeType.getType("normal");
        else {
            if (randomize(7, 3)) type = AuraNodeType.getType("unstable");
            else {
                if (randomize(4, 3)) type = AuraNodeType.getType("sinister");
                else {
                    if (randomize(1, 3)) type = AuraNodeType.getType("pure");
                    else type = AuraNodeType.getType("hungry");
                }
            }
        }
        if (biome.matchesKey(BiomeKeys.DEEP_DARK) && randomize(4, 1)) type = AuraNodeType.getType("sinister");
        /*
        if (world.getBlockState(pos).isOf(<SILVERWOOD>)) {
            selectedMaxVis /= 4;
            type = AuraNodeType.getType("pure");
        }
        if (<tainted lands check>) {
            selectedMaxVis += (selectedMaxVis / 2);
            if (randomize(1, 1) && !Objects.equals(type.auraNodeType, "pure")) {
                selectedMaxVis += (selectedMaxVis / 2);
                type = AuraNodeType.getType("tainted");
            }
        }
        */
        probableMaxVis = new ArrayList<>();
        for (int i = (selectedMaxVis / 2); i < selectedMaxVis; i++) probableMaxVis.add(i);
        selectedMaxVis = probableMaxVis.get(new Random().nextInt(probableMaxVis.size()));
        // max vis is generated now
        Map<String, Integer> aspectId2weight = new HashMap<>();
        List<String> compoundAspects = new ArrayList<>(), primalAspects = Arrays.asList("aer", "aqua", "ignis", "terra", "ordo", "perditio");
        for (AspectMetadata metadata : Aspect.TYPES.values()) if (!metadata.components.isEmpty()) compoundAspects.add(metadata.ID);
        if (probableAspects.isEmpty()) {
            aspectId2weight.put(primalAspects.get(new Random().nextInt(6)), 1);
            aspectId2weight.put(compoundAspects.get(new Random().nextInt(compoundAspects.size())), 1);
        } else aspectId2weight.put(probableAspects.get(new Random().nextInt(probableAspects.size())), 2);
        for (int i = 0; i < 3; i++) if (randomize(1, 1)) {
            if (randomize(17, 1)) {
                String selectedAspect = compoundAspects.get(new Random().nextInt(compoundAspects.size()));
                if(!aspectId2weight.containsKey(selectedAspect)) aspectId2weight.put(selectedAspect, 1);
            }
            else {
                String selectedAspect = primalAspects.get(new Random().nextInt(6));
                if(!aspectId2weight.containsKey(selectedAspect)) aspectId2weight.put(selectedAspect, 1);
            }
        }
        if (Objects.equals(type.auraNodeType, "hungry")) {
            aspectId2weight.put("fames", 2);
            if (randomize(1, 1)) aspectId2weight.put("lucrum", 1);
        }
        if (Objects.equals(type.auraNodeType, "pure")) {
            if (randomize(1, 1)) aspectId2weight.put("victus", 2);
            else { if (!aspectId2weight.containsKey("ordo")) aspectId2weight.put("ordo", 2); }
        }
        if (Objects.equals(type.auraNodeType, "sinister")) {
            if (randomize(1, 1)) aspectId2weight.put("exanimis", 1);
            if (randomize(1, 1) && !aspectId2weight.containsKey("perditio")) aspectId2weight.put("perditio", 1);
            if (randomize(1, 1) && !aspectId2weight.containsKey("tenebrae")) aspectId2weight.put("tenebrae", 1);
            if (randomize(1, 1)) aspectId2weight.put("mortuus", 1);
        }
        int waterCounter = 0, lavaCounter = 0, stoneCounter = 0, foliageCounter = 0;
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
        if (waterCounter >= 100 && !aspectId2weight.containsKey("aqua")) aspectId2weight.put("aqua", 1);
        if (lavaCounter >= 100) {
            if (!aspectId2weight.containsKey("ignis")) aspectId2weight.put("ignis", 1);
            if (!aspectId2weight.containsKey("terra")) aspectId2weight.put("terra", 1);
        }
        if (stoneCounter >= 500 && !aspectId2weight.containsKey("terra")) aspectId2weight.put("terra", 1);
        if (foliageCounter >= 100 && !aspectId2weight.containsKey("herba")) aspectId2weight.put("herba", 1);
        if (Objects.equals(type.auraNodeType, "tainted") && randomize(1, 1))
            aspectId2weight.put("vitium", (randomize(17, 1) ? 2 : 1));
        Map<String, Integer> temporaryVis = new HashMap<>();
        int totalVis = 0;
        for (Map.Entry<String, Integer> entry : aspectId2weight.entrySet()) {
            int forAspect;
            if (entry.getValue() == 2) forAspect = 50 + new Random().nextInt(25);
            else forAspect = 25 + new Random().nextInt(50);
            totalVis += forAspect;
            temporaryVis.put(entry.getKey(), forAspect);
        }
        for (Map.Entry<String, Integer> entry : temporaryVis.entrySet()) {
            int forAspect = (int) Math.ceil((double)selectedMaxVis * ((double)entry.getValue() / (double)totalVis));
            maxForAspect.put(entry.getKey(), forAspect);
            aspects.put(entry.getKey(), Aspect.getAspectById(entry.getKey(), forAspect));
        }
        if (randomize(35, 1)) {
            if (randomize(6, 3)) type.auraNodeSubType = "bright";
            else {
                if (randomize(3, 3)) type.auraNodeSubType = "pale";
                else type.auraNodeSubType = "fading";
            }
        } else type.auraNodeSubType = "none";
    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        if (nbt.contains("type", 8)) {
            type = AuraNodeType.getType(nbt.getString("type"));
            type.auraNodeSubType = nbt.getString("subtype");

            NbtList aspectStorage = nbt.getList("aspects", 10);
            for (NbtElement nbtElement : aspectStorage) {
                NbtCompound aspect = (NbtCompound) nbtElement;
                aspects.put(aspect.getString("id"), Aspect.getAspectById(aspect.getString("id"), aspect.getInt("amount")));
                maxForAspect.put(aspect.getString("id"), aspect.getInt("max"));
            }
        } else generateNode();
    }
    public static boolean randomize(int falsePossibilities, int truePossibilities) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < falsePossibilities; i++) res.add(false);
        for (int i = 0; i < truePossibilities; i++) res.add(true);
        return res.get(new Random().nextInt(res.size()));
    }
    public static int aspectRegenTick = 0, unstableTaintTick = 0, sinisterHungryPureTick = 0, taintTick = 0, stableTick = 0, hungryDamageTick = 0;
    public static void tick(World world, BlockPos pos, BlockState state, AuraNodeEntity entity) {
        if (world.isClient) return;
        if (entity.type == null) entity.generateNode();

        int regenSpeed = 600;
        if (Objects.equals(entity.type.auraNodeSubType, "bright")) regenSpeed = 400;
        if (Objects.equals(entity.type.auraNodeSubType, "pale")) regenSpeed = 900;

        if (entity.stable) {
            if (Objects.equals(entity.type.auraNodeSubType, "fading")) if (stableTick >= 100) {
                stableTick = -1;
                if (randomize(12500 - (entity.advancedStable ? 2 : 1), (entity.advancedStable ? 2 : 1)))
                    entity.type.auraNodeSubType = "pale";
            }
            if (Objects.equals(entity.type.auraNodeType, "unstable")) if (stableTick >= 100) {
                stableTick = -1;
                if (randomize(9998, 2)) entity.type.auraNodeType = "normal";
            }
            stableTick++;
        }

        if (!Objects.equals(entity.type.auraNodeSubType, "fading")) {
            if (aspectRegenTick >= regenSpeed) {
                for (Map.Entry<String, Aspect> aspect : entity.aspects.entrySet())
                    if (aspect.getValue().count < entity.maxForAspect.get(aspect.getKey())) aspect.getValue().count++;
                aspectRegenTick = -1;
            }
            aspectRegenTick++;
        }
        if (Objects.equals(entity.type.auraNodeType, "unstable")) {
            if (unstableTaintTick >= 100) {
                if (randomize(1, 1)) {
                    String ASPECT = (String) (entity.aspects.keySet().toArray()[new Random().nextInt(entity.aspects.size())]);
                    entity.drainAspect(ASPECT, 1);
                    if (Aspect.getAspectById(ASPECT).metadata.components.isEmpty()) {
                        // drop aspect as orb
                    } else {
                        if (entity.aspects.containsKey(Aspect.getAspectById(ASPECT).metadata.components.getFirst())) {
                            entity.aspects.get(Aspect.getAspectById(ASPECT).metadata.components.getFirst()).count++;

                            int tmpMax = entity.maxForAspect.get(Aspect.getAspectById(ASPECT).metadata.components.getFirst());
                            entity.maxForAspect.remove(Aspect.getAspectById(ASPECT).metadata.components.getFirst());
                            tmpMax++;
                            entity.maxForAspect.put(Aspect.getAspectById(ASPECT).metadata.components.getFirst(), tmpMax);
                        } else {
                            entity.aspects.put(
                                    Aspect.getAspectById(ASPECT).metadata.components.getFirst(),
                                    Aspect.getAspectById(Aspect.getAspectById(ASPECT).metadata.components.getFirst(), 1)
                            );
                            entity.maxForAspect.put(Aspect.getAspectById(ASPECT).metadata.components.getFirst(), 1);
                        }
                        if (entity.aspects.containsKey(Aspect.getAspectById(ASPECT).metadata.components.getLast())) {
                            entity.aspects.get(Aspect.getAspectById(ASPECT).metadata.components.getLast()).count++;

                            int tmpMax = entity.maxForAspect.get(Aspect.getAspectById(ASPECT).metadata.components.getLast());
                            entity.maxForAspect.remove(Aspect.getAspectById(ASPECT).metadata.components.getLast());
                            tmpMax++;
                            entity.maxForAspect.put(Aspect.getAspectById(ASPECT).metadata.components.getLast(), tmpMax);
                        } else {
                            entity.aspects.put(
                                    Aspect.getAspectById(ASPECT).metadata.components.getLast(),
                                    Aspect.getAspectById(Aspect.getAspectById(ASPECT).metadata.components.getLast(), 1)
                            );
                            entity.maxForAspect.put(Aspect.getAspectById(ASPECT).metadata.components.getLast(), 1);
                        }
                    }
                }
                unstableTaintTick = -1;
            }
            unstableTaintTick++;
        }
        if (Objects.equals(entity.type.auraNodeType, "tainted")) {
            if (unstableTaintTick >= 100) {
                unstableTaintTick = -1;
                // change biome 15x15
                // spawn fibrous taint in 11x11x11
            }
            unstableTaintTick++;
        }
        if (Objects.equals(entity.type.auraNodeType, "pure")) {
            if (sinisterHungryPureTick >= 50) {
                sinisterHungryPureTick = -1;
                // change biome 15x15
            }
            sinisterHungryPureTick++;
        }
        /*
        check tainted lands
        if taintTick >= 100
          taintTick = -1
          if randomize 499 1
            type.auraNodeType = "tainted";
        */
        if (Objects.equals(entity.type.auraNodeType, "sinister")) {
            if (sinisterHungryPureTick >= 50) {
                sinisterHungryPureTick = -1;
                List<BlockPos> goodPos = new ArrayList<>();
                for (int x = -12; x <= 12; x++)
                    for (int y = -12; y <= 12; y++)
                        for (int z = -12; z <= 12; z++) {
                            BlockPos selPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                            if (world.getBlockState(selPos).isOf(Blocks.AIR) &&
                                world.getBlockState(new BlockPos(pos.getX() + x, pos.getY() + y + 1, pos.getZ() + z)).isOf(Blocks.AIR) &&
                                !world.getBlockState(new BlockPos(pos.getX() + x, pos.getY() + y - 1, pos.getZ() + z)).isOf(Blocks.AIR)) {
                                goodPos.add(selPos);
                            }
                        }
                BlockPos selPos = goodPos.get(new Random().nextInt(goodPos.size()));
                if (!world.getBiome(selPos).matchesKey(BiomeKeys.DEEP_DARK) /* check not eerie | outer lands */) {
                    // changeBiome thaumcraft:eerie
                }
                if (!world.getEntitiesByType(
                        TypeFilter.instanceOf(PlayerEntity.class),
                        new Box(
                                pos.getX() + 12, pos.getY() + 12, pos.getZ() + 12,
                                pos.getX() - 12, pos.getY() - 12, pos.getZ() - 12
                        ),
                        EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR).isEmpty() && world.isNight()) {
                    ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, world);
                    zombie.setPosition(selPos.getX(), selPos.getY(), selPos.getZ());
                    world.spawnEntity(zombie);
                }
            }
            sinisterHungryPureTick++;
        }
        if (Objects.equals(entity.type.auraNodeType, "hungry")) {
            int hungryTime = 50;
            if (entity.aspects.get("fames").count >= 30) hungryTime = 45;
            else if (entity.aspects.get("fames").count >= 20) hungryTime = 40;
            if (sinisterHungryPureTick >= hungryTime) {
                List<BlockPos> blocks = getAwailableBlocks(pos, (int) Blocks.OBSIDIAN.getHardness(), world);
                BlockPos position = blocks.get(new Random().nextInt(blocks.size()));
                List<Aspect> aspects = ThaumicAspects.getItemAspects(new ItemStack(world.getBlockState(position).getBlock().asItem(), 1));
                for (Aspect aspect : aspects)
                    if (entity.aspects.containsKey(aspect.metadata.ID)) {
                        Aspect tmpAsp = entity.aspects.get(aspect.metadata.ID);
                        entity.aspects.remove(aspect.metadata.ID);
                        tmpAsp.count += aspect.count;
                        if (tmpAsp.count > entity.maxForAspect.get(aspect.metadata.ID)) {
                            if (randomize(2 * entity.maxForAspect.get(aspect.metadata.ID), 1)) {
                                int tmpMax = entity.maxForAspect.get(aspect.metadata.ID);
                                entity.maxForAspect.remove(aspect.metadata.ID);
                                tmpMax++;
                                entity.maxForAspect.put(aspect.metadata.ID, tmpMax);
                            }
                            tmpAsp.count = entity.maxForAspect.get(aspect.metadata.ID);
                        }
                        entity.aspects.put(aspect.metadata.ID, tmpAsp);
                    }
                if (!blocks.isEmpty()) world.breakBlock(position, false);
                sinisterHungryPureTick = -1;
            }

            List<Entity> entities = world.getEntitiesByType(
                    TypeFilter.instanceOf(Entity.class),
                    new Box(
                            pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2,
                            pos.getX() - 2, pos.getY() - 2, pos.getZ() - 2
                    ),
                    EntityPredicates.VALID_ENTITY
            );
            for (Entity e : entities) {
                if (e instanceof PlayerEntity) if (((PlayerEntity) e).isCreative() || e.isSpectator()) continue;
                if (e instanceof LivingEntity) if (hungryDamageTick >= 5) {
                    e.setPosition(pos.getX(), pos.getY(), pos.getZ());
                    e.damage(e.getDamageSources().outOfWorld(), 5);
                    hungryDamageTick = -1;
                }
                if (e instanceof ItemEntity) {
                    List<Aspect> aspects = ThaumicAspects.getItemAspects(((ItemEntity) e).getStack());
                    for (Aspect aspect : aspects)
                        if (entity.aspects.containsKey(aspect.metadata.ID)) {
                            Aspect tmpAsp = entity.aspects.get(aspect.metadata.ID);
                            entity.aspects.remove(aspect.metadata.ID);
                            tmpAsp.count += aspect.count;
                            if (tmpAsp.count > entity.maxForAspect.get(aspect.metadata.ID)) {
                                if (randomize(2 * entity.maxForAspect.get(aspect.metadata.ID), 1)) {
                                    int tmpMax = entity.maxForAspect.get(aspect.metadata.ID);
                                    entity.maxForAspect.remove(aspect.metadata.ID);
                                    tmpMax++;
                                    entity.maxForAspect.put(aspect.metadata.ID, tmpMax);
                                }
                                tmpAsp.count = entity.maxForAspect.get(aspect.metadata.ID);
                            }
                            entity.aspects.put(aspect.metadata.ID, tmpAsp);
                        }
                    e.discard();
                }
            }
            hungryDamageTick++;
            sinisterHungryPureTick++;
        }
        if (entity.aspects.isEmpty()) WorldUtil.placeBlock(pos, Blocks.AIR.getDefaultState(), world);

        taintTick++;

        markDirty(world, pos, state);
    }
    private static List<BlockPos> getBlocksInArea(BlockPos pos1, BlockPos pos2, BlockPos posMain, World world, int maxHardness) {
        List<BlockPos> result = new ArrayList<>();
        for (int x = pos1.getX(); x <= pos2.getX(); x++)
            for (int y = pos1.getY(); y <= pos2.getY(); y++)
                for (int z = pos1.getZ(); z <= pos2.getZ(); z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (pos.equals(posMain)) continue;
                    if (world.getBlockState(pos).isOf(Blocks.AIR) || world.getBlockState(pos).isOf(Blocks.VOID_AIR) ||
                            world.getBlockState(pos).isOf(Blocks.CAVE_AIR) || world.getBlockState(pos).getBlock().getHardness() >= maxHardness ||
                            world.getBlockState(pos).getBlock().getHardness() < 0) continue;
                    result.add(pos);
                }
        return result;
    }
    private static List<BlockPos> getAwailableBlocks(BlockPos pos, int maxHardness, World world) {
        List<BlockPos> result = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            result.addAll(getBlocksInArea(
                    new BlockPos(pos.getX() - i, pos.getY() - i, pos.getZ() - i),
                    new BlockPos(pos.getX() + i, pos.getY() + i, pos.getZ() + i),
                    pos,
                    world,
                    maxHardness
            ));
            if (!result.isEmpty()) break;
        }
        return result;
    }
}