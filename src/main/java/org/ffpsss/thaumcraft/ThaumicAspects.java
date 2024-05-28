package org.ffpsss.thaumcraft;

import java.util.*;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import org.ffpsss.thaumcraft.api.aspect.Aspect;
import org.ffpsss.thaumcraft.api.aspect.AspectMetadata;

import net.minecraft.util.Identifier;

public class ThaumicAspects {
    public static final List<CompoundAspect> COMPOUND_ASPECTS = new ArrayList<>();

    public static void generateAspects() {
        generateCompoundAspects();
        Aspect.register(new AspectMetadata("aer", "Aer", new Identifier("thaumcraft", "aspect_aer")));
        Aspect.register(new AspectMetadata("aqua", "Aqua", new Identifier("thaumcraft", "aspect_aqua")));
        Aspect.register(new AspectMetadata("terra", "Terra", new Identifier("thaumcraft", "aspect_terra")));
        Aspect.register(new AspectMetadata("ordo", "Ordo", new Identifier("thaumcraft", "aspect_ordo")));
        Aspect.register(new AspectMetadata("perditio", "Perditio", new Identifier("thaumcraft", "aspect_perditio")));
        Aspect.register(new AspectMetadata("ignis", "Ignis", new Identifier("thaumcraft", "aspect_ignis")));
        for (CompoundAspect aspect : COMPOUND_ASPECTS)
            Aspect.register(
                new AspectMetadata(
                    aspect.name.toLowerCase(), 
                    aspect.name, 
                    new Identifier("thaumcraft", "aspect_" + aspect.name.toLowerCase()), 
                    Arrays.asList(aspect.idComp1, aspect.idComp2)
                )
            );
    }
    public static void generateCompoundAspects() {
        COMPOUND_ASPECTS.add(new CompoundAspect("Victus", "terra", "aqua"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Alienis", "tenebrae", "vacuos"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Arbor", "aer", "herba"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Auram", "aer", "praecantatio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Bestia", "motus", "victus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Cognitio", "ignis", "spiritus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Corpus", "bestia", "mortuus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Exanimis", "motus", "mortuus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Fabrico", "humanus", "instrumentum"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Fames", "vauos", "victus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Gelum", "ignis", "perditio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Herba", "terra", "victus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Humanus", "bestia", "cognitio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Instrumentum", "humanus", "ordo"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Iter", "motus", "terra"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Limus", "aqua", "victus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Lucrum", "fames", "humanus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Lux", "aer", "ignis"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Machina", "instrumentum", "motus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Messis", "herba", "humanus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Metallum", "terra", "vitreus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Meto", "instrumentum", "messis"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Mortuus", "perditio", "victus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Motus", "aer", "ordo"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Pannus", "bestia", "instrumentum"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Perfodio", "humanus", "terra"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Permutatio", "ordo", "perditio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Potentia", "ignis", "ordo"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Praecantatio", "potentia", "vacuos"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Sano", "ordo", "victus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Sensus", "aer", "spiritus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Spiritus", "mortuus", "victus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Telum", "ignis", "instrumentum"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Tempestas", "aer", "aqua"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Tenebrae", "lux", "vacuos"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Tutamen", "instrumentum", "terra"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Vacuos", "aer", "perditio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Venenum", "aqua", "perditio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Vinculum", "motus", "perditio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Vitium", "perditio", "praecantatio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Vitreus", "ordo", "terra"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Volatus", "aer", "motus"));
    }

    public static List<Aspect> disassemleAspect(Aspect aspect) {
        if (aspect.metadata.components.isEmpty()) return Collections.singletonList(aspect);
        return Arrays.asList(
            Aspect.getAspectById(aspect.metadata.components.get(0), aspect.count),
            Aspect.getAspectById(aspect.metadata.components.get(1), aspect.count)
        );
    }

    public static Map<Item, List<Aspect>> ITEM_ASPECTS = new HashMap<>();
    public static Map<TagKey<Item>, List<Aspect>> ITEM_TAGS_ASPECTS = new HashMap<>();

    public static List<Aspect> getItemAspects(ItemStack item) {
        int multiplier = item.getCount();

        Map<String, Integer> aspects = new HashMap<>();

        for (Map.Entry<Item, List<Aspect>> entry : ITEM_ASPECTS.entrySet())
            if (item.isOf(entry.getKey())) for (Aspect aspect : entry.getValue()) {
                if (aspects.containsKey(aspect.metadata.ID)) {
                    Integer currV = aspects.get(aspect.metadata.ID);
                    aspects.remove(aspect.metadata.ID);
                    aspects.put(aspect.metadata.ID, aspect.count + currV);
                } else aspects.put(aspect.metadata.ID, aspect.count);
            }
        for (Map.Entry<TagKey<Item>, List<Aspect>> entry : ITEM_TAGS_ASPECTS.entrySet())
            if (item.isIn(entry.getKey())) for (Aspect aspect : entry.getValue()) {
                if (aspects.containsKey(aspect.metadata.ID)) {
                    Integer currV = aspects.get(aspect.metadata.ID);
                    aspects.remove(aspect.metadata.ID);
                    aspects.put(aspect.metadata.ID, aspect.count + currV);
                } else aspects.put(aspect.metadata.ID, aspect.count);
            }

        List<Aspect> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : aspects.entrySet())
            result.add(Aspect.getAspectById(entry.getKey(), entry.getValue() * multiplier));
        return result;
    }
}
