package dev.sebastianb.wackyconcerns.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import dev.sebastianb.sebaapi.commands.ICommand;
import dev.sebastianb.sebaapi.utils.SebaUtils;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class TestCommand implements ICommand {

    @Override
    public String commandName() {
        return "test";
    }

    @Override
    public LiteralArgumentBuilder<ServerCommandSource> registerNode() {
        return CommandManager.literal(commandName())
                .executes(TestCommand::doThing);
    }

    private static int doThing(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        try {
            System.out.println(SebaUtils.FabricTools.getDeclaredClassModID(TestCommand.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Command.SINGLE_SUCCESS;
    }

}
