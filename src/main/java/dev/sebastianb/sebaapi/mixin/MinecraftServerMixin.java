package dev.sebastianb.sebaapi.mixin;

import com.mojang.brigadier.CommandDispatcher;
import dev.sebastianb.sebaapi.commands.SCommand;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Shadow public abstract CommandManager getCommandManager();

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setFavicon(Lnet/minecraft/server/ServerMetadata;)V", ordinal = 0), method = "runServer")
    public void afterStartServer(CallbackInfo info) {
        System.out.println("sex!");
        CommandManager commandManager = getCommandManager();
        CommandDispatcher<ServerCommandSource> dispatcher = commandManager.getDispatcher();

        SCommand.registerCommand(dispatcher);
    }

}
