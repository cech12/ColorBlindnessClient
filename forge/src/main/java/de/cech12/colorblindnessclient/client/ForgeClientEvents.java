package de.cech12.colorblindnessclient.client;

import de.cech12.colorblindnessclient.Constants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.Priority;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ForgeClientEvents {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent.Post event) {
        if (Minecraft.getInstance().player == null) return;
        ColorEffect.buttonPressed();
    }

    @SubscribeEvent(priority = Priority.LOWEST)
    public static void onRender(TickEvent.RenderTickEvent.Post event) {
        if (event == null) {
            return;
        }
        EffectRendererHelper.renderColorBlindnessEffect(event.timer().getGameTimeDeltaTicks());
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Constants.getCommands());
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        ColorBlindnessKeyMappings.registerAll(event::register);
    }

}
