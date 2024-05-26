package org.ffpsss.thaumcraft.block;

import java.util.HashMap;
import java.util.Map;

import org.ffpsss.sml.magic.Aspect;
import org.ffpsss.sml.magic.AspectStorage;
import org.ffpsss.thaumcraft.data.AuraNodeType;

import net.minecraft.block.Block;

public class AuraNode extends Block implements AspectStorage {
    private final Map<String, Aspect> aspects = new HashMap<>();
    public AuraNodeType type = AuraNodeType.getType("normal");

    public AuraNode(Settings settings) {
        super(settings);
    }

    public AuraNode setNodeType(AuraNodeType type) {
        this.type = type;
        return this;
    }

    @Override
    public void addAspect(Aspect arg0) {
        if (aspects.containsKey(arg0.ID)) aspects.get(arg0.ID).amount += arg0.amount;
        else aspects.put(arg0.ID, arg0);
    }
    @Override
    public Aspect drainAspect(Aspect arg0) {
        if (aspects.containsKey(arg0.ID)) aspects.get(arg0.ID).amount -= arg0.amount;
    }
    @Override public Map<String, Aspect> getAspects() { return aspects; }
    
}
