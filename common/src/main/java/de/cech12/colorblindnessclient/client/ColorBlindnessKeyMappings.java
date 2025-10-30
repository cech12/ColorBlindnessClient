package de.cech12.colorblindnessclient.client;

import de.cech12.colorblindnessclient.Constants;
import net.minecraft.client.KeyMapping;

import java.util.function.Consumer;

public class ColorBlindnessKeyMappings {

    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(Constants.id("keymappings"));

    public static final KeyMapping ACHROMATOMALY = ColorEffect.ACHROMATOMALY.generateKeyMapping(CATEGORY);
    public static final KeyMapping ACHROMATOPSIA = ColorEffect.ACHROMATOPSIA.generateKeyMapping(CATEGORY);
    public static final KeyMapping DEUTERANOMALY = ColorEffect.DEUTERANOMALY.generateKeyMapping(CATEGORY);
    public static final KeyMapping DEUTERANOPIA = ColorEffect.DEUTERANOPIA.generateKeyMapping(CATEGORY);
    public static final KeyMapping PROTANOMALY = ColorEffect.PROTANOMALY.generateKeyMapping(CATEGORY);
    public static final KeyMapping PROTANOPIA = ColorEffect.PROTANOPIA.generateKeyMapping(CATEGORY);
    public static final KeyMapping TRITANOMALY = ColorEffect.TRITANOMALY.generateKeyMapping(CATEGORY);
    public static final KeyMapping TRITANOPIA = ColorEffect.TRITANOPIA.generateKeyMapping(CATEGORY);

    public static void registerAll(Consumer<KeyMapping> consumer) {
        consumer.accept(ACHROMATOMALY);
        consumer.accept(ACHROMATOPSIA);
        consumer.accept(DEUTERANOMALY);
        consumer.accept(DEUTERANOPIA);
        consumer.accept(PROTANOMALY);
        consumer.accept(PROTANOPIA);
        consumer.accept(TRITANOMALY);
        consumer.accept(TRITANOPIA);
    }
    
}
