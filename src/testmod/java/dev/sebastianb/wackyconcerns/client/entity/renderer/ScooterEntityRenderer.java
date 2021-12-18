package dev.sebastianb.wackyconcerns.client.entity.renderer;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

public class ScooterEntityRenderer extends EntityRenderer<Entity> {

    public ScooterEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(Entity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        VertexConsumerProvider vertexConsumerProvider = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

        float scale = 0.5f;
        // back wheel
        matrices.push(); {
            matrices.translate(-0.5 * scale,0,-1.7 * scale);
            matrices.scale(scale,scale, scale);
            Quaternion rotation = Vec3f.POSITIVE_X.getDegreesQuaternion(entity.age * 2);
            float degrees = MathHelper.wrapDegrees(entity.age * 2);
            // circle around (I'm still trying to make this circle like a wheel)
            // System.out.println(degrees + " " + rotation.getX());


            matrices.multiply(rotation);

            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                    Blocks.ACACIA_STAIRS.getDefaultState(), matrices, vertexConsumerProvider, light, OverlayTexture.DEFAULT_UV);
        }
        matrices.pop();
        // front wheel
        matrices.push(); {
            matrices.translate(-0.5 * scale,0,0.7 * scale);
            matrices.scale(scale,scale, scale);
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                    Blocks.BLACK_CONCRETE.getDefaultState(), matrices, vertexConsumerProvider, light, OverlayTexture.DEFAULT_UV);
        }
        matrices.pop();
        // under body frame
        matrices.push(); {
            matrices.translate(-0.5 * scale * 0.4f,0.17,-0.5 * 1);
            matrices.scale(scale * 0.4f,scale* 0.4f, 1);
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                    Blocks.IRON_BLOCK.getDefaultState(), matrices, vertexConsumerProvider, light, OverlayTexture.DEFAULT_UV);
        }
        matrices.pop();
        // standing platform
        matrices.push(); {
            matrices.translate(-0.5 * scale * 1.3f,0.35,-0.5 * 1.9f);
            matrices.scale(scale * 1.3f,scale * 0.1f, 1.9f);
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                    Blocks.SMOOTH_STONE.getDefaultState(), matrices, vertexConsumerProvider, light, OverlayTexture.DEFAULT_UV);
        }
        matrices.pop();
        // front wheel cover
        matrices.push(); {
            matrices.translate(-0.5 * 0.51f,0.3,0.67 * 0.51f);
            matrices.scale(0.51f,0.25f, 0.51f);
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                    Blocks.RED_CONCRETE.getDefaultState(), matrices, vertexConsumerProvider, light, OverlayTexture.DEFAULT_UV);
        }
        matrices.pop();
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return PlayerScreenHandler.BLOCK_ATLAS_TEXTURE;
    }

}
