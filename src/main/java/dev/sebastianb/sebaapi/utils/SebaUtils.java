package dev.sebastianb.sebaapi.utils;

import com.google.common.collect.Lists;
import com.mojang.brigadier.context.CommandContext;
import dev.sebastianb.sebaapi.SebaAPI;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.Chunk;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Clob;
import java.util.List;

public class SebaUtils {

    public static class ChatUtils {

        public static void sayEmptyMessage(CommandContext<ServerCommandSource> context) {
            context.getSource().sendFeedback(new LiteralText(""), false);
        }

        public static void saySimpleMessage(CommandContext<ServerCommandSource> context, Text message) {
            saySimpleMessage(context,message, false);
        }

        public static void saySimpleMessage(CommandContext<ServerCommandSource> context, Text message, boolean broadcastToOps) {
            context.getSource().sendFeedback(message, broadcastToOps);
        }

    }

    public static class FabricTools {

        /**
         * @param modClass any specific class in your mod that you want to find the modID for
         * @return the mod ID of the mod that contains the given class
         */
        public static String getDeclaredClassModID(Class<?> modClass) {
            var commandClassLocation = modClass.getProtectionDomain().getCodeSource().getLocation();
            // FIXME:
            //  Right now, this assumes every mod has a "main" entrypoint.
            //  This is not true for every mod because some mods may have custom or serverside/client side entrypoints.
            //  See fabric docs for more info here: https://fabricmc.net/wiki/documentation:entrypoint
            List<EntrypointContainer<ModInitializer>> modEntryPoints = FabricLoader.getInstance().getEntrypointContainers("main", ModInitializer.class);

            for (EntrypointContainer<ModInitializer> entrypointContainer : modEntryPoints) {
                try {
                    var modMainClassLocation = entrypointContainer.getEntrypoint().getClass().getProtectionDomain().getCodeSource().getLocation();
                    if (modMainClassLocation.equals(commandClassLocation)) {
                        return entrypointContainer.getProvider().getMetadata().getId();
                    }
                } catch (Exception e) {
                    // prob hacky but some mods seem to throw exceptions similar to the following:
                    // java.lang.NullPointerException: Cannot invoke "java.security.CodeSource.getLocation()" because the return value of "java.security.ProtectionDomain.getCodeSource()" is null
                    if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                        e.printStackTrace();
                        SebaAPI.LOGGER.warning(entrypointContainer.getProvider().getMetadata().getName() + " is null? Please report to " + SebaAPI.MOD_ID + " devs if this is your mod! I wanna know what you did");
                    }
                }
            }
            throw new IllegalArgumentException("No mod found for class " + modClass.getName() + "! Is this correct?");
        }
    }

    // https://www.rapidtables.com/convert/number/hex-to-decimal.html
    public static class Colors {

        public static final int LIGHT_PASTEL_PURPLE = 13353955; // # CBC3E3
        public static final int GOLD                = 16755200; // # FFAA00
        public static final int RED                 = 16711680; // # FF0000
        public static final int BABY_LIGHT_PURPLE   = 15459327; // # EBE3FF

    }

    public static class WorldUtils {

        public static ChunkPos getChunkPosFromPlayer(ServerPlayerEntity player) {
            ServerWorld world = player.getWorld();
            Chunk chunk = world.getChunk(player.getBlockPos());
            return chunk.getPos();
        }

    }

    public static BlockPos VectorToBlockPos(Vec3d vec3d) {
        return new BlockPos(vec3d.getX(), vec3d.getY(), vec3d.getZ());
    }

    public static <T>List<List<T>> splitArrayIntoParts(List<T> list, int pageSize) {
        return Lists.partition(list, pageSize);
    }

    // https://stackoverflow.com/questions/2169732/most-efficient-solution-for-reading-clob-to-string-and-string-to-clob-in-java
    public static String clobToString(Clob clobObject) {
        try {
            InputStream in = clobObject.getAsciiStream();
            StringWriter w = new StringWriter();
            IOUtils.copy(in, w);
            return w.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
