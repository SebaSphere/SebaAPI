package dev.sebastianb.sebaapi.teams;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.PersistentState;

import java.util.HashMap;

public class TeamsPersistentState extends PersistentState {

    private HashMap<Team, TeamMember> team = new HashMap<>();



    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        // writes all the teams to NBT
        NbtList nbtList = new NbtList();
        for (Team team : team.keySet()) {
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putUuid("teamID", team.getTeamId());
            nbtList.add(nbtCompound);
        }
        nbt.put("teams", nbtList);
        return nbt;
    }


}
