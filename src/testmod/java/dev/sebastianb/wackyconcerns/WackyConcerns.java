package dev.sebastianb.wackyconcerns;

import dev.sebastianb.sebaapi.commands.SCommand;
import dev.sebastianb.sebaapi.registries.LootTableRegistry;
import dev.sebastianb.wackyconcerns.command.TestCommand;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class WackyConcerns implements ModInitializer {

    public static String MOD_ID = "wackyconcerns";

    public static final Identifier EXAMPLE_LOOT_TABLE = LootTableRegistry.registerLootTable(new Identifier(WackyConcerns.MOD_ID, "example_table"));

    @Override
    public void onInitialize() {
        SCommand.addCommand(new TestCommand());
    }
}
