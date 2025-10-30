package de.cech12.colorblindnessclient;

import de.cech12.colorblindnessclient.client.ColorBlindnessKeyMappings;
import de.cech12.colorblindnessclient.client.ColorEffect;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class FabricColorBlindnessClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ColorBlindnessKeyMappings.registerAll(KeyBindingHelper::registerKeyBinding);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            ColorEffect.buttonPressed();
        });

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, context) ->
                dispatcher.register(Constants.getCommands()));
    }

}
