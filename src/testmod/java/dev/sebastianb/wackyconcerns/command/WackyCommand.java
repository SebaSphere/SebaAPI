package dev.sebastianb.wackyconcerns.command;

import dev.sebastianb.sebaapi.commands.ICommand;
import dev.sebastianb.wackyconcerns.WackyConcerns;

public interface WackyCommand extends ICommand {
    @Override
    default String modId() {
        return WackyConcerns.MOD_ID;
    }
}
