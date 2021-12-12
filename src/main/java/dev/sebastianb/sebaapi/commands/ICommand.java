package dev.sebastianb.sebaapi.commands;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;

public interface ICommand {

    String commandName();

    // IMPORTANT:
    // You should make your own interface for your command that overrides this method
    // THIS IS SO YOU CAN REGISTER YOUR COMMANDS AND WE KNOW WHICH MOD THIS IS FROM
    // methods such as commandInfo() are used to get the translated strings for the command (TranslatableText.class)
    default String modId() {
        return null;
    }

    LiteralArgumentBuilder<ServerCommandSource> registerNode();

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