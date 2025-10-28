package de.cech12.colorblindnessclient.client;

import com.google.gson.JsonSyntaxException;
import de.cech12.colorblindnessclient.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EffectRendererHelper {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ResourceLocation ACHROMATOMALY = Constants.id("shaders/post/achromatomaly.json");
    private static final ResourceLocation ACHROMATOPSIA = Constants.id("shaders/post/achromatopsia.json");
    private static final ResourceLocation DEUTERANOMALY = Constants.id("shaders/post/deuteranomaly.json");
    private static final ResourceLocation DEUTERANOPIA = Constants.id("shaders/post/deuteranopia.json");
    private static final ResourceLocation PROTANOMALY = Constants.id("shaders/post/protanomaly.json");
    private static final ResourceLocation PROTANOPIA = Constants.id("shaders/post/protanopia.json");
    private static final ResourceLocation TRITANOMALY = Constants.id("shaders/post/tritanomaly.json");
    private static final ResourceLocation TRITANOPIA = Constants.id("shaders/post/tritanopia.json");

    private static PostChain achromatomalyShader;
    private static PostChain achromatopsiaShader;
    private static PostChain deuteranomalyShader;
    private static PostChain deuteranopiaShader;
    private static PostChain protanomalyShader;
    private static PostChain protanopiaShader;
    private static PostChain tritanomalyShader;
    private static PostChain tritanopiaShader;

    private static int lastWidth = 0;
    private static int lastHeight = 0;
    private static PostChain lastShader = null;

    /**
     * Should be called by a render event and renders the effect if it is active.
     * @param renderTickTime render tick time
     */
    public static void renderColorBlindnessEffect(float renderTickTime) {
        if (Constants.getActiveEffect() == null) {
            return;
        }
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            makeColorShaders();

            PostChain activeShader = switch (Constants.getActiveEffect()) {
                case ACHROMATOMALY -> achromatomalyShader;
                case ACHROMATOPSIA -> achromatopsiaShader;
                case DEUTERANOMALY -> deuteranomalyShader;
                case DEUTERANOPIA -> deuteranopiaShader;
                case PROTANOMALY -> protanomalyShader;
                case PROTANOPIA -> protanopiaShader;
                case TRITANOMALY -> tritanomalyShader;
                case TRITANOPIA -> tritanopiaShader;
            };

            if (lastShader != activeShader) {
                lastShader = activeShader;
                lastWidth = 0;
                lastHeight = 0;
            }
            updateShaderGroupSize(activeShader);
            activeShader.process(renderTickTime);
            Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
        }
    }

    private static PostChain createShaderGroup(ResourceLocation location) {
        try {
            Minecraft mc = Minecraft.getInstance();
            return new PostChain(mc.getTextureManager(), mc.getResourceManager(), mc.getMainRenderTarget(), location);
        } catch (IOException ioexception) {
            LOGGER.warn("Failed to load shader: {}", location, ioexception);
        } catch (JsonSyntaxException jsonsyntaxexception) {
            LOGGER.warn("Failed to parse shader: {}", location, jsonsyntaxexception);
        }
        return null;
    }

    private static void makeColorShaders() {
        if (achromatomalyShader == null) {
            achromatomalyShader = createShaderGroup(ACHROMATOMALY);
        }
        if (achromatopsiaShader == null) {
            achromatopsiaShader = createShaderGroup(ACHROMATOPSIA);
        }
        if (deuteranomalyShader == null) {
            deuteranomalyShader = createShaderGroup(DEUTERANOMALY);
        }
        if (deuteranopiaShader == null) {
            deuteranopiaShader = createShaderGroup(DEUTERANOPIA);
        }
        if (protanomalyShader == null) {
            protanomalyShader = createShaderGroup(PROTANOMALY);
        }
        if (protanopiaShader == null) {
            protanopiaShader = createShaderGroup(PROTANOPIA);
        }
        if (tritanomalyShader == null) {
            tritanomalyShader = createShaderGroup(TRITANOMALY);
        }
        if (tritanopiaShader == null) {
            tritanopiaShader = createShaderGroup(TRITANOPIA);
        }
    }

    private static void updateShaderGroupSize(PostChain shaderGroup) {
        if (shaderGroup != null) {
            Minecraft mc = Minecraft.getInstance();
            int width = mc.getWindow().getWidth();
            int height = mc.getWindow().getHeight();
            if (width != lastWidth || height != lastHeight) {
                lastWidth = width;
                lastHeight = height;
                shaderGroup.resize(width, height);
            }
        }
    }

}
