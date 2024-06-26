package org.ffpsss.thaumcraft.api.aspect;

import java.util.Map;

public interface AspectStorage {
    Map<String, Aspect> getAspects();
    Aspect drainAspect(String ID, int amount);
    Aspect addAspect(String ID, int amount);
}
