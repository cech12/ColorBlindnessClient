package de.cech12.colorblindnessclient.client;

import de.cech12.colorblindnessclient.Constants;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RenderFrameEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(Dist.CLIENT)
public class NeoForgeClientEvents {

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        ColorBlindnessKeyMappings.registerAll(event::register);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (Minecraft.getInstance().player == null) return;
        ColorEffect.buttonPressed();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRender(RenderFrameEvent.Post event) {
        if (event == null) {
            return;
        }
        EffectRendererHelper.renderColorBlindnessEffect(event.getPartialTick().getGameTimeDeltaTicks());
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Constants.getCommands());
    }

}
