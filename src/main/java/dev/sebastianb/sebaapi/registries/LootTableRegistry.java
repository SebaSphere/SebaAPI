package dev.sebastianb.sebaapi.registries;

import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;

public class LootTableRegistry {

    // Use this site to make loot tables: https://misode.github.io/loot-table/
    public static <T extends Identifier> T registerLootTable(T identifier) {
        LootTables.registerLootTable(identifier);
        return identifier;
    }

}
