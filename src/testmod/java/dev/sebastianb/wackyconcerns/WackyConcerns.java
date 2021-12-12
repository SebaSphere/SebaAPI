package dev.sebastianb.wackyconcerns;

import dev.sebastianb.sebaapi.commands.SCommand;
import dev.sebastianb.wackyconcerns.command.TestCommand;
import net.fabricmc.api.ModInitializer;

public class WackyConcerns implements ModInitializer {

    public static String MOD_ID = "wackyconcerns";

    @Override
    public void onInitialize() {
        SCommand.addCommand(new TestCommand());
    }
}
