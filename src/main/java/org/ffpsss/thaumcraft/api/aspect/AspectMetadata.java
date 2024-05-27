package org.ffpsss.thaumcraft.api.aspect;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AspectMetadata {
    public final String ID;
    public final Text displayName;
    public final List<String> components;
    public final Identifier texture;

    public AspectMetadata(String ID, Text displayName, Identifier texture) {
        this.ID = ID;
        this.displayName = displayName;
        this.texture = texture;
        components = new ArrayList<>();
    }
    public AspectMetadata(String ID, String displayName, Identifier texture) {
        this.ID = ID;
        this.displayName = Text.of(displayName);
        this.texture = texture;
        components = new ArrayList<>();
    }
    public AspectMetadata(String ID, Text displayName, Identifier texture, List<String> components) {
        this.ID = ID;
        this.displayName = displayName;
        this.texture = texture;
        this.components = components;
    }
    public AspectMetadata(String ID, String displayName, Identifier texture, List<String> components) {
        this.ID = ID;
        this.displayName = Text.of(displayName);
        this.texture = texture;
        this.components = components;
    }
}
