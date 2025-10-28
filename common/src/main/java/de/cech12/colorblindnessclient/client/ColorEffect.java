package de.cech12.colorblindnessclient.client;

import com.mojang.blaze3d.platform.InputConstants;
import de.cech12.colorblindnessclient.Constants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

import java.util.function.Supplier;

public enum ColorEffect {

    /** ACHROMATOMALY effect */
    ACHROMATOMALY(() -> ColorBlindnessKeyMappings.ACHROMATOMALY),
    /** ACHROMATOPSIA effect */
    ACHROMATOPSIA(() -> ColorBlindnessKeyMappings.ACHROMATOPSIA),
    /** DEUTERANOMALY effect */
    DEUTERANOMALY(() -> ColorBlindnessKeyMappings.DEUTERANOMALY),
    /** DEUTERANOPIA effect */
    DEUTERANOPIA(() -> ColorBlindnessKeyMappings.DEUTERANOPIA),
    /** PROTANOMALY effect */
    PROTANOMALY(() -> ColorBlindnessKeyMappings.PROTANOMALY),
    /** PROTANOPIA effect */
    PROTANOPIA(() -> ColorBlindnessKeyMappings.PROTANOPIA),
    /** TRITANOMALY effect */
    TRITANOMALY(() -> ColorBlindnessKeyMappings.TRITANOMALY),
    /** TRITANOPIA effect */
    TRITANOPIA(() -> ColorBlindnessKeyMappings.TRITANOPIA);

    final Supplier<KeyMapping> keyMapping;

    ColorEffect(Supplier<KeyMapping> keyMapping) {
        this.keyMapping = keyMapping;
    }

    private String getKeyTranslatable() {
        return "key." + Constants.MOD_ID + "." + name().toLowerCase();
    }

    public KeyMapping generateKeyMapping() {
        return new KeyMapping(
                this.getKeyTranslatable(),
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                Constants.KEYMAPPING_CATEGORY
        );
    }

    public static void buttonPressed() {
        for (ColorEffect effect : ColorEffect.values()) {
            if (effect.keyMapping.get() == null) {
                continue;
            }
            if (effect.keyMapping.get().consumeClick()) {
                Constants.buttonPressed(effect);
            }
        }
    }

}
