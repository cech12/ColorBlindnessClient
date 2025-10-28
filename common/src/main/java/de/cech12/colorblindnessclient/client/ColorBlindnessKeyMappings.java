package de.cech12.colorblindnessclient.client;

import net.minecraft.client.KeyMapping;

import java.util.function.Consumer;

public class ColorBlindnessKeyMappings {

    public static final KeyMapping ACHROMATOMALY = ColorEffect.ACHROMATOMALY.generateKeyMapping();
    public static final KeyMapping ACHROMATOPSIA = ColorEffect.ACHROMATOPSIA.generateKeyMapping();
    public static final KeyMapping DEUTERANOMALY = ColorEffect.DEUTERANOMALY.generateKeyMapping();
    public static final KeyMapping DEUTERANOPIA = ColorEffect.DEUTERANOPIA.generateKeyMapping();
    public static final KeyMapping PROTANOMALY = ColorEffect.PROTANOMALY.generateKeyMapping();
    public static final KeyMapping PROTANOPIA = ColorEffect.PROTANOPIA.generateKeyMapping();
    public static final KeyMapping TRITANOMALY = ColorEffect.TRITANOMALY.generateKeyMapping();
    public static final KeyMapping TRITANOPIA = ColorEffect.TRITANOPIA.generateKeyMapping();

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
