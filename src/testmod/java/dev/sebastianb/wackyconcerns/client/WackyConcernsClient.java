package dev.sebastianb.wackyconcerns.client;

import dev.sebastianb.wackyconcerns.WackyConcerns;
import dev.sebastianb.wackyconcerns.client.entity.renderer.ScooterEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class WackyConcernsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(WackyConcerns.SCOOTER_ENTITY, ScooterEntityRenderer::new);
    }
}
