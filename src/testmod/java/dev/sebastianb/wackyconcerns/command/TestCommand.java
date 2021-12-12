package dev.sebastianb.wackyconcerns.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import dev.sebastianb.sebaapi.commands.ICommand;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class TestCommand implements WackyCommand {

    @Override
    public String commandName() {
        return "cum";
    }

    @Override
    public LiteralArgumentBuilder<ServerCommandSource> registerNode() {
        return CommandManager.literal(commandName())
                .executes(TestCommand::doThing);
    }

    private static int doThing(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        System.out.println("cum sexo!");
        return Command.SINGLE_SUCCESS;
    }

}
