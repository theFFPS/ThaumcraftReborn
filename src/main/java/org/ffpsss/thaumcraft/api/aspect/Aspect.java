package org.ffpsss.thaumcraft.api.aspect;

import java.util.HashMap;
import java.util.Map;

public class Aspect {
    public static final Map<String, AspectMetadata> TYPES = new HashMap<>();

    public int count = 0;
    public final AspectMetadata metadata;

    public Aspect(AspectMetadata metadata) {
        this.metadata = metadata;
    }
    public Aspect(AspectMetadata metadata, int count) {
        this.metadata = metadata;
        this.count = count;
    }

    public static void register(AspectMetadata metadata) {
        TYPES.put(metadata.ID, metadata);
    }
    public static AspectMetadata getMetadata(String ID) {
        return TYPES.get(ID);
    }
    public static Aspect getAspectById(String ID, int count) {
        return new Aspect(getMetadata(ID), count);
    }
    public static Aspect getAspectById(String ID) {
        return new Aspect(getMetadata(ID));
    }
}
