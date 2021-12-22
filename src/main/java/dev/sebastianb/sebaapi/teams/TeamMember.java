package dev.sebastianb.sebaapi.teams;

import net.minecraft.entity.player.PlayerEntity;

public class TeamMember extends PlayerEntity {

    public TeamMember(PlayerEntity player) {
        super(player.world, player.getBlockPos(), player.headYaw, player.getGameProfile());
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }

}
