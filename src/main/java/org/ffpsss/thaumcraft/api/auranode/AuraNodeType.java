package org.ffpsss.thaumcraft.api.auranode;

import java.util.HashMap;
import java.util.Map;

public class AuraNodeType {
    public static final Map<String, AuraNodeType> TYPES = new HashMap<>();

    public String auraNodeSubType;
    public String auraNodeType;

    public AuraNodeType(String type) {
        auraNodeType = type;
    }

    public static void generateTypes() {
        TYPES.put("normal", new AuraNodeType("normal"));
        TYPES.put("unstable", new AuraNodeType("unstable"));
        TYPES.put("sinister", new AuraNodeType("sinister"));
        TYPES.put("tainted", new AuraNodeType("tainted"));
        TYPES.put("hungry", new AuraNodeType("hungry"));
        TYPES.put("pure", new AuraNodeType("pure"));
    }

    public static AuraNodeType getType(String ID) {
        return TYPES.get(ID);
    }
}
