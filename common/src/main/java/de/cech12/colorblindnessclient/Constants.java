package de.cech12.colorblindnessclient;

import de.cech12.colorblindnessclient.client.ColorEffect;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that contains all common constants.
 */
public class Constants {

    /** mod id */
    public static final String MOD_ID = "colorblindnessclient";
    /** mod name*/
    public static final String MOD_NAME = "Color Blindness Client";
    /** Logger instance */
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    /** mod name*/
    public static final String KEYMAPPING_CATEGORY = "category." + Constants.MOD_ID + ".keymappings";


    /** active effect or null */
    private static ColorEffect ACTIVE_EFFECT;

    private Constants() {}

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static ColorEffect getActiveEffect() {
        return ACTIVE_EFFECT;
    }

    public static void buttonPressed(ColorEffect effect) {
        if (ACTIVE_EFFECT == effect) {
            ACTIVE_EFFECT = null;
        } else {
            ACTIVE_EFFECT = effect;
        }
    }

}