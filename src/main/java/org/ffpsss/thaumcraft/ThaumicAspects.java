package org.ffpsss.thaumcraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ffpsss.sml.api.common.SMLRegistry;
import org.ffpsss.sml.api.util.AspectUtil;
import org.ffpsss.sml.magic.Aspect;

import net.minecraft.util.Identifier;

public class ThaumicAspects {
    public static final List<CompoundAspect> COMPOUND_ASPECTS = new ArrayList<>();

    public static void generateAspects() {
        for (CompoundAspect aspect : COMPOUND_ASPECTS)
            AspectUtil.register(
                new Aspect(aspect.name.toLowerCase(), new Identifier("thaumcraft", "aspect_" + aspect.name.toLowerCase()), aspect.name, 0, Arrays.asList(
                    SMLRegistry.getAspect(aspect.idComp1), SMLRegistry.getAspect(aspect.idComp2)
                ))
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
        COMPOUND_ASPECTS.add(new CompoundAspect("Victus", "aqua", "terra"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Vinculum", "motus", "perditio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Vitium", "perditio", "praecantatio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Vitreus", "ordo", "terra"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Volatus", "aer", "motus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Exubitor", "alienis", "mortuus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Desidia", "vinculum", "spiritus"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Gula", "fames", "vacuos"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Infernus", "ignis", "praecantatio"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Invidia", "sensus", "fames"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Ira", "ignis", "telum"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Luxuria", "corpus", "fames"));
        COMPOUND_ASPECTS.add(new CompoundAspect("Superbia", "volatus", "vacuos"));
    }

    public static List<Aspect> disassemleAspect(Aspect aspect) {
        Aspect aspect1t = aspect.components.get(0), aspect2t = aspect.components.get(1), aspect1r, aspect2r;
    
        if (aspect1t.isPrimal) aspect1r = new Aspect(aspect1t.ID, aspect1t.texture, aspect1t.displayName, aspect.amount);
        else aspect1r = new Aspect(aspect1t.ID, aspect1t.texture, aspect1t.displayName, aspect.amount, aspect1t.components);

        if (aspect2t.isPrimal) aspect2r = new Aspect(aspect2t.ID, aspect2t.texture, aspect2t.displayName, aspect.amount);
        else aspect2r = new Aspect(aspect2t.ID, aspect2t.texture, aspect2t.displayName, aspect.amount, aspect2t.components);

        return Arrays.asList(aspect1r, aspect2r);
    }
}
