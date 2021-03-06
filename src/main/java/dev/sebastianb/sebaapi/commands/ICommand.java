package dev.sebastianb.sebaapi.commands;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.sebastianb.sebaapi.utils.SebaUtils;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

public interface ICommand {

    String commandName();

    // gets the mod id from the super class implementing this interface
    default String modId() {
        return SebaUtils.FabricTools.getDeclaredClassModID(this.getClass());
    }

    LiteralArgumentBuilder<ServerCommandSource> registerNode();

    // methods such as commandInfo() are used to get the translated strings for the command (TranslatableText.class)

    /**
     * Short summary about what the command does
     */
    default String commandInfo() {
        return modId() + ".command.info." + commandName();
    }

    /**
     * Tooltip information on hover for command args
     */
    default String commandArgs() {
        return modId() + ".command.args." + commandName();
    }

    /**
     * Tooltip information on hover for command args
     */
    default String commandTooltip() {
        return commandArgs();
    }

}