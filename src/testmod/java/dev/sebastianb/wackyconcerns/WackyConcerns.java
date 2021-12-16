package dev.sebastianb.wackyconcerns;

import dev.sebastianb.sebaapi.commands.SCommand;
import dev.sebastianb.sebaapi.registries.LootTableRegistry;
import dev.sebastianb.wackyconcerns.command.TestCommand;
import dev.sebastianb.wackyconcerns.entity.ScooterEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WackyConcerns implements ModInitializer {

    public static String MOD_ID = "wackyconcerns";

    public static final Identifier EXAMPLE_LOOT_TABLE = LootTableRegistry.registerLootTable(new Identifier(WackyConcerns.MOD_ID, "example_table"));

    public static final EntityType<ScooterEntity> SCOOTER_ENTITY
            = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "scooter"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, ScooterEntity::new)
                    .dimensions(EntityDimensions.changing(1, 1)).build()

    );

    @Override
    public void onInitialize() {
        SCommand.addCommand(new TestCommand());
    }
}
