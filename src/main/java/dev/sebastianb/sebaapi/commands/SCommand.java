package dev.sebastianb.sebaapi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.sebastianb.sebaapi.SebaAPI;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.mixin.command.CommandManagerMixin;
import net.fabricmc.fabric.mixin.gametest.MinecraftServerMixin;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class handles the registration of commands.
 */
public class SCommand {

    private static final ArrayList<ICommand> commands = new ArrayList<>();


    /**
     * Any mod can call this command and add any command that implements the ICommand interface.
     *
     */
    public static void addCommand(ICommand command) {
        commands.add(command);
    }

    /**
     * Called in MinecraftServerMixin::afterStartServer
     * Registers all commands added to the array.
     */
    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        for (ICommand command : commands) {
            if (command.modId() != null) {
                LiteralArgumentBuilder<ServerCommandSource> builder = command.registerNode();
                dispatcher.register(builder);
            } else {
                SebaAPI.LOGGER.warning(
                        "Command " + command.commandName() + " has no ModID set. It will not be registered.\n"
                );
            }
        }
    }


}
