package de.cech12.colorblindnessclient.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeModClientEvents {

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        ColorBlindnessKeyMappings.registerAll(event::register);
    }

    @SubscribeEvent
    public static void addReloadListener(RegisterClientReloadListenersEvent event) {
        if (event == null) {
            return;
        }
        event.registerReloadListener(new ColorblindnessReloadListener());
    }

}
